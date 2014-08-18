package com.iisquare.jees.framework.view;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.iisquare.jees.framework.Configuration;

/**
 * FreeMarker自定义函数管理器
 */
public class FreemarkerTemplateManager {
	
	private Configuration configuration;
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		freemarker.template.Configuration fmConfiguration = freeMarkerConfigurer.getConfiguration();
		fmConfiguration.setSharedVariable("millisToDateTime",
        		new FreemarkerMillisToDateTimeModel(this.configuration));
		fmConfiguration.setSharedVariable("empty", new FreemarkerEmptyModel());
		fmConfiguration.setSharedVariable("escapeHtml", new FreemarkerEscapeHtmlModel());
		fmConfiguration.setSharedVariable("unescapeHtml", new FreemarkerUnescapeHtmlModel());
	}
	
	public FreemarkerTemplateManager() {}
}
