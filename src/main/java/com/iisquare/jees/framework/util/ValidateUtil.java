package com.iisquare.jees.framework.util;

/**
 * 安全过滤验证类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class ValidateUtil {
	
	public static final String regexEnglish = "^[a-zA-Z]+$";
	public static final String regexChinese = "^[\u4E00-\u9FA5]+$";
	public static final String regexWord = "^\\w+$";
	public static final String regexSimpleString = "^[\u4E00-\u9FA5\\w]+$";
	public static final String regexEmail = "^\\w+@(\\w+.)+[a-z]{2,3}$";
	public static final String regexDomain = "^(\\w+.)+[a-z]{2,3}$";
	public static final String regexUrl = "^[a-zA-Z]+://(((\\w+.)+[a-z]{2,3})|((\\d{1,3}.){3}\\d{1,3})){1}(/?\\S*)$";
	public static final String regexIPv4 = "^(\\d{1,3}.){3}\\d{1,3}$";
	public static final String regexMobile = "^((\\+86)|(86))?1\\d{10}$";
	public static final String regexPhone = "^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$";
	public static final String regexIdCard = "^(([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3})|([1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|[xX]))){1}$";
	public static final String regexPostCode = "^[1-9]\\d{5}(?!\\d)$";
	
	public static boolean isNull(String object, boolean bTrim) {
		return null == object || "".endsWith(bTrim ? DPUtil.trim(object) : object);
	}
	
	public static boolean isEnglish(String object) {
		return null != DPUtil.getFirstMatcher(regexEnglish, object);
	}
	
	public static boolean isChinese(String object) {
		return null != DPUtil.getFirstMatcher(regexChinese, object);
	}
	
	public static boolean isWord(String object) {
		return null != DPUtil.getFirstMatcher(regexWord, object);
	}
	
	public static boolean isSimpleString(String object) {
		return null != DPUtil.getFirstMatcher(regexSimpleString, object);
	}
	
	public static boolean isEmail(String object) {
		return null != DPUtil.getFirstMatcher(regexEmail, object);
	}
	
	public static boolean isDomain(String object) {
		return null != DPUtil.getFirstMatcher(regexDomain, object);
	}
	
	public static boolean isUrl(String object) {
		return null != DPUtil.getFirstMatcher(regexUrl, object);
	}
	
	public static boolean isIPv4(String object) {
		return null != DPUtil.getFirstMatcher(regexIPv4, object);
	}
	
	public static boolean isMobile(String object) {
		return null != DPUtil.getFirstMatcher(regexMobile, object);
	}
	
	public static boolean isPhone(String object) {
		return null != DPUtil.getFirstMatcher(regexPhone, object);
	}
	
	public static boolean isIdCard(String object) {
		return null != DPUtil.getFirstMatcher(regexIdCard, object);
	}
	
	public static boolean isPostCode(String object) {
		return null != DPUtil.getFirstMatcher(regexPostCode, object);
	}
	
	public static Integer filterInteger(String object, boolean bBound, Integer min, Integer max) {
		int obj = DPUtil.parseInt(object);
		if(null != min && obj < min) return bBound ? min : null;
		if(null != max && obj > max) return bBound ? max : null;
		return obj;
	}
	
	public static Double filterDouble(String object, boolean bBound, Double min, Double max) {
		double obj = DPUtil.parseDouble(object);
		if(null != min && obj < min) return bBound ? min : null;
		if(null != max && obj > max) return bBound ? max : null;
		return obj;
	}
	
	public static Float filterFloat(String object, boolean bBound, Float min, Float max) {
		float obj = DPUtil.parseFloat(object);
		if(null != min && obj < min) return bBound ? min : null;
		if(null != max && obj > max) return bBound ? max : null;
		return obj;
	}
	
	public static String filterLength(String object, Integer min, Integer max) {
		int obj = object.length();
		if(null != min && obj < min) return null;
		if(null != max && obj > max) return null;
		return object;
	}
	
	/**
	 * 判断字符串是否存在于给定的数组中，若不存在则返回默认值
	 */
	public static String filterItem(String object, boolean bTrim, String[] itemArray, String defaultItem) {
		if(null == object) return defaultItem;
		if(bTrim) object = DPUtil.trim(object);
		for (String item : itemArray) {
			if(DPUtil.equals(object, item)) return item;
		}
		return defaultItem;
	}
	
	public static String filterEnglish(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isEnglish(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterChinese(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isChinese(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterWord(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isWord(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterSimpleString(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isSimpleString(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterEmail(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isEmail(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterDomain(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isDomain(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterUrl(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isUrl(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterIPv4(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isIPv4(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterMobile(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isMobile(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterPhone(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isPhone(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterIdCard(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isIdCard(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterPostCode(String object, boolean bTrim, Integer min, Integer max) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		if(!isPostCode(object)) return null;
		return filterLength(object, min, max);
	}
	
	public static String filterDateTime(String object, boolean bTrim, String format) {
		if(null == object) return null;
		if(bTrim) object = DPUtil.trim(object);
		long millis = DPUtil.dateTimeToMillis(object, format);
		if(-1 == millis) return null;
		return DPUtil.millisToDateTime(millis, format);
	}
}
