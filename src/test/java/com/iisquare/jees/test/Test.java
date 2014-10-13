package com.iisquare.jees.test;

import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.SqlUtil;

public class Test {
	
	public static void main(String[] args) {
		String str = "not in";
		System.out.println(DPUtil.isMatcher(SqlUtil.regexSqlIn, str));
	}
}
