/*
 * Dependence:code.js
*/

/* Extend prototype */
Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, // month 
		"d+" : this.getDate(), // day 
		"h+" : this.getHours(), // hour 
		"m+" : this.getMinutes(), // minute 
		"s+" : this.getSeconds(), // second 
		"q+" : Math.floor((this.getMonth()+3) / 3), // quarter 
		"S" : this.getMilliseconds() // millisecond 
	} 
	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 
	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			var temp = RegExp.$1.length == 1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length);
			format = format.replace(RegExp.$1, temp); 
		} 
	} 
	return format; 
} 

/* Global functions Start */
function Web_alertInfo(msg, callBack) {
	$.messager.alert('提示' , msg, 'info', callBack);
}
function Web_confirm(msg, callBack) {
	$.messager.confirm('提示' , msg, callBack);
}

function Web_refreshCurrentPage() {
	window.location.reload();
}

function Web_openPage(url) {
	window.open(url);
}

function Web_redirectPage(url) {
	if(isNaN(url)) {
		window.location.href = url;
	} else {
		window.history.go(url);
	}
}

function Web_empty(object) {
	if(typeof object == "undefined") return true;
	if(null == object) return true;
	if(typeof object == "boolean") return !object;
	object += "";
	if(object.length < 1) return true;
	if("0" == object) return true;
	return false;
}

function Web_trim(str, trimStr) {
	if(Web_empty(str)) return "";
	if(typeof trimStr == "undefined" || null == trimStr) {
		trimStr = "";
	}
	var regexLeft = eval("/^" + trimStr + "*/");
	str = str.replace(regexLeft, "");
	var regexRight = eval("/" + trimStr + "*$/");
	str = str.replace(regexRight, "");
	return str;
}

function Web_trimLeft(str, trimStr) {
	if(Web_empty(str)) return "";
	if(typeof trimStr == "undefined" || null == trimStr) {
		trimStr = "";
	}
	var regexLeft = eval("/^" + trimStr + "*/");
	str = str.replace(regexLeft, "");
	return str;
}

function Web_trimRight(str, trimStr) {
	if(Web_empty(str)) return "";
	if(typeof trimStr == "undefined" || null == trimStr) {
		trimStr = "";
	}
	var regexRight = eval("/" + trimStr + "*$/");
	str = str.replace(regexRight, "");
	return str;
}

function Web_inArray(value, array) {
	for (var i = 0; i < array.length; i++) {
		if(array[i] == value) return true;
	}
	return false;
}

function Web_formatComboTree(data, valueArray, tips, fieldValue, fieldId, fieldChildren) {
	if($.isEmptyObject(fieldId)) fieldId = 'id';
	if($.isEmptyObject(fieldValue)) fieldValue = fieldId;
	if($.isEmptyObject(fieldChildren)) fieldChildren = 'children';
	var rows = [];
	if(!$.isEmptyObject(tips)) {
		var object = {};
		object[fieldId] = 0;
		object[fieldValue] = tips;
		object[fieldChildren] = [];
		data = $.merge([object], data);
	}
	for (var key in data) {
		var value = data[key];
		rows.push({
			id : value[fieldId],
			text : value[fieldValue],
			checked : -1 != $.inArray(value[fieldId], valueArray),
			children : Web_formatComboTree(
				value[fieldChildren], valueArray, null, fieldValue, fieldId, fieldChildren)
		});
	}
	return rows;
}

function Web_getDataGridRowId(object) {
	var row = object.datagrid('getSelected');
	if(!row) {
		Web_alertInfo('请选择要操作的记录！');
		return -1;
	}
	return row.id;	
}
/* Global functions End */

/* Global settings Start */
$.extend($.fn.pagination.defaults, {  
	pageSize : Web_pageSize,
	pageList : [10, 15, 20, 25, 30, 35, 40, 50, 70, 100]
});
$.extend($.fn.datagrid.defaults, {  
	pageSize : $.fn.pagination.defaults.pageSize,
	pageList : $.fn.pagination.defaults.pageList
});
$.extend($.messager.defaults, {  
	ok : '确定',  
	cancel : '取消'  
});
$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	if(10 > m) m = '0' + m;
	var d = date.getDate();
	if(10 > d) d = '0' + d;
	return y + '-' + m + '-' + d;
}
/* Global settings End */

/* Comming tools Start */
var platformTab = {};
$(function () {
	// Attributes
	var platformTabsContextMenuIndex = -1;
	
	var $platformTabs = $('#platformTabs');
	var $platformTabsMenu = $('#platformTabsMenu');
	
	/* Events for Platform Start */
	platformTab = {
		add : function (title, url, className, target) { // add tab page
			if(Web_empty(url)) return ;
			if(0 == url.indexOf('@')) url = Web_projectUrl + '/' + url.substr(1);
			var bInFrame = true;
			switch (target) {
			case '_self' : // 当前页打开
				window.location.href = url;
				return ;
			break;
			case '_blank' : // 新窗口打开
				window.open(url);
				return ;
			break;
			case '_tab' : // Tab内容打开
				bInFrame = false;
			break;
			default : // Tab框架打开
				
			}
			var tabs = $platformTabs.tabs('tabs');
			for (var i = 0; i < tabs.length; i++) { // select tab when it exists
				var tab = tabs[i][0];
				if(url == tab.id) {
					$platformTabs.tabs('select', $platformTabs.tabs('getTabIndex', tab));
					return ;
				}
			}
			if(bInFrame) {
				$platformTabs.tabs('add',{
					id : url,
					title : title,
					content : '<iframe name="platformIFrame" scrolling="auto" frameborder="0" src="'
							+ url + '" class="full-w-h"></iframe>',
					closable : true
				});
			} else {
				$platformTabs.tabs('add',{
					id : url,
					title : title,
					href : url,
					closable : true
				});
			}
			if(className && className.length > 0) {
				var tabs = $platformTabs.tabs('tabs');
				var lastTab = tabs[tabs.length - 1];
				lastTab.addClass(className);
				$platformTabs.tabs('resize');
			}
		},
		selectMenuTab : function () {
			$platformTabs.tabs('select', platformTabsContextMenuIndex);
		}
	}
	
	$platformTabs.tabs({ // bind tab context menu
		onContextMenu : function (e, title, index) {
			platformTabsContextMenuIndex = index;
			if(index > 0) {
				$platformTabsMenu.menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}
			e.stopPropagation();
			e.preventDefault();
			return false;
		}
	});
	
	$platformTabsMenu.menu({ // tab context menu events
		onClick : function (item) {
			var menuClickedIndex = item.name;
			switch (menuClickedIndex) {
				case 'refreshCurrent' : // 刷新当前页
					platformTab.selectMenuTab();
					var tab = $platformTabs.tabs('getTab', platformTabsContextMenuIndex);
					tab.panel('refresh');
				break;
				case 'closeCurrent' : // 关闭当前页
					$platformTabs.tabs('close', platformTabsContextMenuIndex);
				break;
				case 'closeLeft' : // 关闭左侧页
					var length = $platformTabs.tabs('tabs').length;
					for (var i = platformTabsContextMenuIndex - 1; i > 0; i--) {
						$platformTabs.tabs('close', i);
					}
				break;
				case 'closeRight' : // 关闭右侧页
					var length = $platformTabs.tabs('tabs').length;
					for (var i = length - 1; i > platformTabsContextMenuIndex; i--) {
						$platformTabs.tabs('close', i);
					}
				break;
				case 'closeOther' : // 关闭其他页
					platformTab.selectMenuTab();
					var length = $platformTabs.tabs('tabs').length;
					for (var i = length - 1; i > 0; i--) {
						if(i != platformTabsContextMenuIndex) {
							$platformTabs.tabs('close', i);
						}
					}
				break;
				case 'closeAll' : // 关闭全部页
					var length = $platformTabs.tabs('tabs').length;
					for (var i = length - 1; i > 0; i--) {
						$platformTabs.tabs('close', i);
					}
				break;
			}
		}
	});
	/* Events for Platform End */
	$('#platform-west').load(Web_projectUrl + '/index/menu/memberMenuList', function () { // Trigger Events
		$.parser.parse($(this)); // 渲染easyUI元素
		$('.platformMenuTree').each(function(index, element) {
			$(this).tree({ // bind menu tree clicked
				'data' : platformMenuTreeData[parseInt($(this).attr('index'), 10)],
				'onClick' : function (node) {
					if(typeof node.text == "undefined") return ;
					if(typeof node.attributes == "undefined") return ;
					platformTab.add(node.text, node.attributes.url, node.attributes.className, node.attributes.target);
				}
			});
		});
	});
	//platformTab.add('1', 'http://www.iisquare.com/');
});
/* Comming tools End */