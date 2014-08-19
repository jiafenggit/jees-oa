/*
 * Dependence:code.js
*/

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

function Web_encodePassword(password) {
	var str = hex_md5(password);
	str = str.substr(0, 20) + 'iisquare.com' + str.substr(18);
	return hex_md5(str);
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

/* Comming tools Start */
var platformTab = {};
var controllerBar = {};
/* Comming tools End */

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
							+ url + '" class="fullWH"></iframe>',
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
	
	/* Events for ControllerBar Start */
	controllerBar = {
		enable : function () {
			$('.controllerBar').find('a').each(function () {
				$(this).linkbutton('enable');
			});
		},
		disable : function () {
			$('.controllerBar').find('a').each(function () {
				$(this).linkbutton('disable');
			});
		},
		actions : {
			beforeCreate : function (eventObject) {
				return {
					status : 0,
					message : {}
				};
				//return Web_url('create'); // 返回添加路径
			},
			create : function (eventObject, data) {
				if(typeof data == 'undefined' || null == data) return ;
				if(0 == data.status) {
					$('.createActionForm').setTemplateElement('Template-List');
					$('.createActionForm').processTemplate(data.message);
					return true;
				} else {
					Web_alertInfo(data.message);
					return false;
				}
			},
			afterCreate : function (eventObject, data) {
				// 添加窗口显示完成
			},
			beforeInsert : function (eventObject) {
				eventObject.linkbutton('disable');
				return Web_url('insert'); // 返回添加路径
			},
			afterInsert : function (eventObject, data) {
				if(typeof data == 'undefined' || null == data) return ;
				if(0 == data.status) {
					Web_confirm(data.message + '重新载入当前页面......', function (result) {
						if(result)Web_refreshCurrentPage();
					});
					eventObject.linkbutton('enable');
					return true; // 信息添加完成，关闭窗口
				} else {
					Web_alertInfo(data.message);
					eventObject.linkbutton('enable');
					return false;
				}
				eventObject.linkbutton('enable');
			},
			beforeModify : function (eventObject) {
				var id = Web_getDataGridRowId($('.listAction'));
				if(-1 == id) return ;
				return Web_url('modify') + '?id=' + id; // 获取修改路径
			},
			modify : function (eventObject, data) {
				if(typeof data == 'undefined' || null == data) return false;
				data = $.parseJSON(data);
				if(0 == data.status) {
					$('.modifyActionForm').setTemplateElement('Template-List');
					$('.modifyActionForm').processTemplate(data.message);
					return true;
				} else {
					Web_alertInfo(data.message);
					return false;
				}
			},
			afterModify : function (eventObject, data) {
				// 修改窗口显示完成
			},
			beforeUpdate : function (eventObject) {
				eventObject.linkbutton('disable');
				var id = Web_getDataGridRowId($('.listAction'));
				if(-1 == id) return ;
				return Web_url('update') + '?id=' + id; // 获取更新路径
			},
			afterUpdate : function (eventObject, data) {
				eventObject.linkbutton('enable');
				if(typeof data == 'undefined' || null == data) return false;
				if(0 == data.status) {
					Web_confirm(data.message + '重新载入当前页面......', function (result) {
						if(result)Web_refreshCurrentPage();
					});
					eventObject.linkbutton('enable');
					return true; // 信息更新完成。关闭窗口
				} else {
					Web_alertInfo(data.message);
					eventObject.linkbutton('enable');
					return false;
				}
			},
			beforeDelete : function (eventObject) {
				var id = Web_getDataGridRowId($('.listAction'));
				if(-1 == id) return ;
				return Web_url('delete') + '?id=' + id; // 获取删除路径
			},
			afterDelete : function (eventObject, data) {
				if(typeof data == 'undefined' || null == data) return ;
				data = $.parseJSON(data);
				if(0 == data.status) {
					Web_confirm(data.message + '重新载入当前页面......', function (result) {
						if(result)Web_refreshCurrentPage();
					});
				} else {
					Web_alertInfo(data.message);
				}
			}
		}
	}
	
	$('.createAction').live('click', function () {
		function insert(eventObject, insertData) {
			if(controllerBar.actions.afterInsert(eventObject, insertData)) {
				$('.createActionDialog').dialog('close');
			}
		}
		function create(eventObject, createData) {
			if(controllerBar.actions.create(eventObject, createData)) {
				$('.createActionDialog').dialog({
					title : '添加',
					iconCls : 'icon-add',
					onOpen : function () {
						controllerBar.actions.afterCreate(eventObject, createData);
						$.parser.parse($(this)); // 渲染easyUI元素
					}
				});
				$('.createActionOK').unbind('click.action').bind('click.action' ,function () {
					var $insertObject = $(this);
					var insertData = controllerBar.actions.beforeInsert($insertObject);
					if(typeof insertData == 'string' && insertData.length > 0) {
						$('.createActionForm').form({
							url : insertData,
							success : function (data) {
								data = $.parseJSON(data);
								insert($insertObject, data);
							}
						}).submit();
					} else {
						insert($insertObject, insertData);
					}
				})
				$('.createActionCancel').unbind('click.action').bind('click.action' ,function () {
					$('.createActionDialog').dialog('close');
				});
			}
		}
		var $createObject = $(this);
		var createData = controllerBar.actions.beforeCreate($createObject);
		if(typeof createData == 'string' && createData.length > 0) {
			$.post(createData, function (data) { // 获取新建数据
				create($createObject, data);
			});
		} else {
			create($createObject, createData);
		}
	});
	$('.modifyAction').live('click', function () {
		function update(eventObject, updateData) {
			if(controllerBar.actions.afterUpdate(eventObject, updateData)) {
				$('.modifyActionDialog').dialog('close');
			}
		}
		function modify(eventObject, modifyData) {
			if(controllerBar.actions.modify(eventObject, modifyData)) { // 调取修改函数
				$('.modifyActionDialog').dialog({
					title : '修改',
					iconCls : 'icon-edit',
					onOpen : function () {
						controllerBar.actions.afterModify(eventObject, modifyData);
						$.parser.parse($(this)); // 渲染easyUI元素
					}
				});
				$('.modifyActionOK').unbind('click.action').bind('click.action' ,function () {
					var $updateObject = $(this);
					var updateData = controllerBar.actions.beforeUpdate($updateObject);
					if(typeof updateData == 'string' && updateData.length > 0) {
						$('.modifyActionForm').form('submit', {
							url : updateData,
							success : function (data) {
								data = $.parseJSON(data);
								update($updateObject, data);
							}
						});
					} else {
						update($insertObject, updateData);
					}
				})
				$('.modifyActionCancel').unbind('click.action').bind('click.action' ,function () {
					$('.modifyActionDialog').dialog('close');
				});
			}
		}
		var $modifyObject = $(this);
		var modifyData = controllerBar.actions.beforeModify($modifyObject);
		if(typeof modifyData == 'string' && modifyData.length > 0) {
			$.post(modifyData, function (data) { // 获取修改数据
				modify($modifyObject, data);
			});
		} else {
			modify($modifyObject, modifyData);
		}
	});
	$('.deleteAction').live('click', function () {
		var $deleteObject = $(this);
		var deleteData = controllerBar.actions.beforeDelete($deleteObject);
		if(typeof deleteData == 'string' && deleteData.length > 0) {
			$.post(deleteData, function (data) {
				controllerBar.actions.afterDelete($deleteObject, data);
			});
		}
	});
	/* Events for ControllerBar End */
	
	// Trigger Events
	$('#platformWest').load(Web_url('memberMenuList', 'Menu'), function () {
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