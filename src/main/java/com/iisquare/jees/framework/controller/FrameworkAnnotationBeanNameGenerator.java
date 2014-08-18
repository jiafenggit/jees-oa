package com.iisquare.jees.framework.controller;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

public class FrameworkAnnotationBeanNameGenerator extends AnnotationBeanNameGenerator {

	@Override
	protected String buildDefaultBeanName(BeanDefinition definition,
			BeanDefinitionRegistry registry) {
		return definition.getBeanClassName();
	}

}
