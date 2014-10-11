package com.iisquare.jees.test;

import java.util.List;

import com.iisquare.jees.framework.util.DPUtil;

public class Test {
	
	public static final String regexD = "\\{tl:D\\}[^(\\{tl:F\\}.*?\\{/tl:F\\})]*?\\{/tl:D\\}";
	
	public static List<String> findD(String content) {
		return DPUtil.getMatcher(regexD, content, false);
	}
	
	public static void main(String[] args) {
		String content = "{tl:F}…aa……{tl:D}………bb………{/tl:D}……cc……{/tl:F}" +
				"{tl:D}………dd………{/tl:D}" +
				"{tl:D}……ee…{tl:F}…ff……{/tl:F}……gg……{/tl:D}";
		System.out.println(findD(content));
	}
}
