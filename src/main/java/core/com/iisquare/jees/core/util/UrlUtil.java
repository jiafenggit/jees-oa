package com.iisquare.jees.core.util;

import com.iisquare.jees.framework.util.DPUtil;

/**
 * 链接处理类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class UrlUtil {
	
	public static final String regexUrl = "^[a-zA-Z]+://.+";
	
	/**
	 * 将相对路径组合成绝对路径
	 */
	public static String concat(String webUrl, String url) {
		url = DPUtil.trim(url);
		if(DPUtil.empty(url) || DPUtil.isMatcher(regexUrl, url)) return url;
		return DPUtil.stringConcat(webUrl, url.startsWith("/") ? "" : "/", url);
	}
}
