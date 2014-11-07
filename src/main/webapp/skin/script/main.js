/*
 * Dependence:code.js
*/

/* Extend prototype */
/**
 * 为Date对象拓展format格式化日期函数
 */
Date.prototype.format = function(format){
	var o = {
		"M+" : this.getMonth()+1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth()+3) / 3), // quarter
		"S" : this.getMilliseconds() // millisecond
	};
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
};

$.extend($.fn.form.methods, {
	/**
	 * 获取表单数据
	 * $('#id').form('getData');
	 */
	getData: function(jq, params){
		var formArray = jq.serializeArray();
		var oRet = {};
		for (var i in formArray) {
			if($.type(oRet[formArray[i].name]) == 'undefined') {
				oRet[formArray[i].name] = formArray[i].value;
			} else if($.type(oRet[formArray[i].name]) == 'array'){
				oRet[formArray[i].name].push(formArray[i].value);
			} else {
				oRet[formArray[i].name] = [oRet[formArray[i].name], formArray[i].value];
			}
		}
		return oRet;
	}
});

/* Global functions Start */
/**
 * 消息提示
 */
function Web_alertInfo(msg, callBack) {
	$.messager.alert('提示' , msg, 'info', callBack);
}
/**
 * 消息确认
 */
function Web_confirm(msg, callBack) {
	$.messager.confirm('提示' , msg, callBack);
}

/**
 * 刷新当前页面
 */
function Web_refreshCurrentPage() {
	window.location.reload();
}

/**
 * 打开新页面
 */
function Web_openPage(url) {
	window.open(url);
}

/**
 * 页面跳转，若url为整数则前进或后退历史记录
 */
function Web_redirectPage(url) {
	if(isNaN(url)) {
		window.location.href = url;
	} else {
		window.history.go(url);
	}
}

/**
 * 判断对象是否为空
 */
function Web_empty(object) {
	if(typeof object == "undefined") return true;
	if(null == object) return true;
	if(typeof object == "boolean") return !object;
	object += "";
	if(object.length < 1) return true;
	if("0" == object) return true;
	return false;
}

/**
 * 去除字符串两边指定的字符串
 */
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
/**
 * 去除字符串左边指定的字符串
 */
function Web_trimLeft(str, trimStr) {
	if(Web_empty(str)) return "";
	if(typeof trimStr == "undefined" || null == trimStr) {
		trimStr = "";
	}
	var regexLeft = eval("/^" + trimStr + "*/");
	str = str.replace(regexLeft, "");
	return str;
}
/**
 * 去除字符串右边指定的字符串
 */
function Web_trimRight(str, trimStr) {
	if(Web_empty(str)) return "";
	if(typeof trimStr == "undefined" || null == trimStr) {
		trimStr = "";
	}
	var regexRight = eval("/" + trimStr + "*$/");
	str = str.replace(regexRight, "");
	return str;
}

/**
 * 将数据格式化为combotree需要的格式
 * @param data 原数据
 * @param valueArray 已选择项数组
 * @param tips 提示信息（可选）
 * @param valueField 内容字段名称（可选）
 * @param idField 值名称（可选）
 * @param childrenField 子节点数据名称（可选）
 * @param formatter(value, row, index) 内容格式化函数（可选）
 * @returns 格式化后的数据
 */
function Web_formatComboTree(data, valueArray, tips, valueField, idField, childrenField, formatter) {
	if($.isEmptyObject(idField)) idField = 'id';
	if($.isEmptyObject(valueField)) valueField = idField;
	if($.isEmptyObject(childrenField)) childrenField = 'children';
	var rows = [];
	if(!$.isEmptyObject(tips)) {
		var object = {};
		object[idField] = 0;
		object[valueField] = tips;
		object[childrenField] = [];
		data = $.merge([object], data);
	}
	for (var key in data) {
		var value = data[key];
		rows.push({
			id : value[idField],
			text : (formatter && $.isFunction(formatter)) ? formatter(value[valueField], value, key) : value[valueField],
			checked : -1 != $.inArray(value[idField], valueArray) || -1 != $.inArray(value[idField] + '', valueArray),
			children : Web_formatComboTree(
				value[childrenField], valueArray, null, valueField, idField, childrenField, formatter)
		});
	}
	return rows;
}

/**
 * 获取datagrid已Checked的主键数组
 */
function Web_getDatagridCheckedIdArray($datagrid) {
	var rows = $datagrid.datagrid('getChecked');
	var idField = $datagrid.datagrid('options')['idField'];
	var idArray = [], size = rows.length;
	for(var i = 0; i < size; i++) {
		idArray.push(rows[i][idField]);
	}
	return idArray;
}
/* Global functions End */

/* Global settings Start */
/**
 * 设置pagination默认值
 */
$.extend($.fn.pagination.defaults, {
	pageSize : Web_pageSize,
	pageList : [10, 15, 20, 25, 30, 35, 40, 50, 70, 100]
});
/**
 * 设置datagrid默认值
 */
$.extend($.fn.datagrid.defaults, {  
	pageSize : $.fn.pagination.defaults.pageSize,
	pageList : $.fn.pagination.defaults.pageList
});
/**
 * 设置messager默认值
 */
$.extend($.messager.defaults, {  
	ok : '确定',  
	cancel : '取消'  
});
/**
 * 设置datebox默认日期格式
 */
$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	if(10 > m) m = '0' + m;
	var d = date.getDate();
	if(10 > d) d = '0' + d;
	return y + '-' + m + '-' + d;
};
/* Global settings End */