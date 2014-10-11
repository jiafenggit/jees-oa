package com.iisquare.jees.core.util;

import com.iisquare.jees.framework.util.DPUtil;

/**
 * 链接处理类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class UrlUtil {
	public static String concat(String webUrl, String url) {
		if(null == url || url.startsWith("http")) return url;
		return DPUtil.stringConcat(webUrl, "/", url);
	}
}
