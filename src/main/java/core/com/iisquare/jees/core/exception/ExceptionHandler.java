package com.iisquare.jees.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import com.iisquare.jees.framework.Configuration;
import com.iisquare.jees.framework.controller.ControllerBase;
import com.iisquare.jees.framework.util.DPUtil;

/**
 * 全局异常处理
 * @author Ouyang <iisquare@163.com>
 *
 */
public class ExceptionHandler extends HandlerExceptionResolverComposite {

	@Autowired
	protected Configuration configuration;
		
	public Configuration getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if(ex instanceof PermitDediedException) { // 权限不足异常处理
			ModelAndView modelAndView = new ModelAndView();
			/* 设置视图路径 */
			StringBuilder viewName = new StringBuilder("/");
			String themeName = configuration.getThemeName();
			if(!DPUtil.empty(themeName)) {
				viewName.append(themeName).append("/");
			}
			viewName.append("base/exception/permit");
			modelAndView.setViewName(viewName.toString());
			/* 控制器善后处理 */
			Object object = ((HandlerMethod) handler).getBean();
			if(object instanceof ControllerBase) {
				((ControllerBase) object).destroy(request, response, handler, modelAndView);
			}
			return modelAndView;
		}
		return super.resolveException(request, response, handler, ex);
	}
	
}
