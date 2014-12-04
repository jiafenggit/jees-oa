package com.iisquare.jees.framework.view;

import java.util.List;

import com.iisquare.jees.framework.util.DPUtil;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 判断变量是否为空
 */
public class FreemarkerEmptyModel implements TemplateMethodModelEx {
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List list) throws TemplateModelException {
		return DPUtil.empty(list.get(0));
	}
}