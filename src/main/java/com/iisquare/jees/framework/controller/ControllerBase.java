package com.iisquare.jees.framework.controller;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import com.iisquare.jees.framework.Configuration;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.FileUtil;
import com.iisquare.jees.framework.util.ServletUtil;

@Controller
@Scope("prototype")
public abstract class ControllerBase {
	
	public static final class ResultType { // 当前支持的视图资源类型
		public static final String TYPE_FREEMARKER = "TYPE_FREEMARKER";
		public static final String TYPE_REDIRECT = "TYPE_REDIRECT";
		public static final String TYPE_TEXT = "TYPE_TEXT";
		public static final String TYPE_STREAM = "TYPE_STREAM";
		public static final String TYPE_PLAINTYPE_TEX = "TYPE_PLAINTYPE_TEX";
	}
	public static final String CONTENT_TYPE = "text/html;charset=utf-8";
	
	@Autowired
	protected Configuration configuration; // 框架配置对象
	protected HttpServletRequest request; // HTTP请求对象
	protected HttpServletResponse response; // HTTP响应对象
	protected Map<String, Object> parameter; // 请求参数Map对象
	protected boolean isWebUrlWithDomain = true; // 项目路径是否携带域名

	public String moduleName, controllerName, actionName;
	public Map<String, Object> assign; // 视图数据Map对象
	public String webRoot, webUrl, skinUrl, themeUrl, directorySeparator;

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map<String, Object> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public boolean isWebUrlWithDomain() {
		return isWebUrlWithDomain;
	}

	public void setWebUrlWithDomain(boolean isWebUrlWithDomain) {
		this.isWebUrlWithDomain = isWebUrlWithDomain;
	}

	public ControllerBase() {}
	
	/**
	 * 初始化函数，设置相关参数
	 * @throws Exception 
	 */
	public void init(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		this.request = request;
		this.response = response;
		parameter = ServletUtil.parseParameterMap(request.getParameterMap());
		assign = new HashMap<String, Object>(0);
		webRoot = ServletUtil.getWebRoot(request);
		webUrl = ServletUtil.getWebUrl(request, isWebUrlWithDomain);
		String skinFolder = configuration.getSkinFolder();
		if(DPUtil.empty(skinFolder)) {
			skinUrl = webUrl;
		} else {
			StringBuilder sb = new StringBuilder(webUrl);
			sb.append("/").append(skinFolder);
			skinUrl = sb.toString();
		}
		String themeName = configuration.getThemeName();
		if(DPUtil.empty(themeName)) {
			themeUrl = skinUrl;
		} else {
			StringBuilder sb = new StringBuilder(skinUrl);
			sb.append("/").append(themeName);
			themeUrl = sb.toString();
		}
		directorySeparator = ServletUtil.getDirectorySeparator(request);
		Method method = ((HandlerMethod) handler).getMethod();
		/* 提取相关URI路径参数 */
		String classFullName = this.getClass().getName();
		actionName = method.getName();
		/* 约定前提判定 */
		if(classFullName.startsWith(configuration.getModulePrefix())
				&& classFullName.endsWith(configuration.getControllerSuffix())
				&& actionName.endsWith(configuration.getActionSuffix())) {
			/* 提取Module名称 */
			moduleName = classFullName.substring(0, classFullName.lastIndexOf("."));
			moduleName = moduleName.substring(configuration.getModulePrefix().length());
			moduleName = moduleName.replaceAll("\\.", "/");
			/* 提取Controller名称 */
			controllerName = classFullName.substring(classFullName.lastIndexOf(".") + 1);
			controllerName = controllerName.substring(0, controllerName.lastIndexOf(configuration.getControllerSuffix()));
			controllerName = DPUtil.lowerCaseFirst(controllerName);
			/* 提取Action名称 */
			actionName = actionName.substring(0, actionName.lastIndexOf(configuration.getActionSuffix()));
		}
	}
	
	/**
	 * 当Action方法执行后被调用
	 */
	public void destroy(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		String viewName = modelAndView.getViewName();
		if(DPUtil.empty(viewName)) {
			modelAndView.clear();
		} else if(viewName.startsWith("redirect:")) {
			modelAndView.addAllObjects(assign);
		} else {
			modelAndView.addObject("_base_", this)
				.addObject("_config_", configuration)
				.addObject("_moduleName_", moduleName)
				.addObject("_controllerName_", controllerName)
				.addObject("_actionName_", actionName)
				.addObject("_webRoot_", webRoot)
				.addObject("_webUrl_", webUrl)
				.addObject("_skinUrl_", skinUrl)
				.addObject("_themeUrl_", themeUrl)
				.addObject("_directorySeparator_", directorySeparator)
				.addAllObjects(assign);
		}
	}
	
	public String url() {
		return url(actionName);
	}
	
	public String url(String action) {
		return url(controllerName, action);
	}
	
	public String url(String controller, String action) {
		return url(moduleName, controller, action);
	}
	
	/**
	 * 获取URL地址
	 * @param module 模块名称
	 * @param controller 控制器名称
	 * @param action 方法名称
	 * @return
	 */
	public String url(String module, String controller, String action) {
		StringBuilder sb = new StringBuilder(webUrl)
			.append("/").append(module).append("/").append(controller).append("/").append(action);
		return sb.toString();
	}
	
	protected String displayTemplate() throws Exception {
		return displayTemplate(actionName);
	}
	
	protected String displayTemplate(String action) throws Exception {
		return displayTemplate(controllerName, action);
	}
	
	protected String displayTemplate(String controller, String action) throws Exception {
		return displayTemplate(moduleName, controller, action);
	}
	
	/**
	 * 渲染视图
	 * @param module
	 * @param controller
	 * @param action
	 * @return
	 * @throws Exception
	 */
	protected String displayTemplate(String module, String controller, String action) throws Exception {
		String themeName = configuration.getThemeName(); // 主题名称
		String viewName = DPUtil.stringConcat("/", module, "/", controller, "/", action); // 模板路径
		if(DPUtil.empty(themeName)) return display(viewName, ResultType.TYPE_FREEMARKER); // 未启用主题
		String themeViewName = DPUtil.stringConcat("/", themeName, viewName); // 主题模板路径
		String themeViewPath = DPUtil.stringConcat("/".equals(directorySeparator) ? webRoot : webRoot.replaceAll("\\\\", "/"),
				"/", DPUtil.trim(configuration.getTemplateLoaderPath(), "/"), themeViewName, configuration.getTemplateSuffix());
		if(!"default".equals(themeName) && !FileUtil.isExists(themeViewPath)) { // 设定主题模板文件不存在
			themeViewName = DPUtil.stringConcat("/", "default", viewName); // 采用默认模板文件
		}
		return display(themeViewName, ResultType.TYPE_FREEMARKER);
	}
	
	/**
	 * 输出文本信息
	 * @param text
	 * @return
	 * @throws Exception
	 */
	protected String displayText(String text) throws Exception {
		return displayText(text, CONTENT_TYPE);
	}
	
	/**
	 * 输出文本信息
	 * @param text
	 * @param contentType 页面编码字符串
	 * @return
	 * @throws Exception
	 */
	protected String displayText(String text, String contentType) throws Exception {
		response.setContentType(contentType);
		return display(text, ResultType.TYPE_TEXT);
	}
	
	/**
	 * 将assign中的数据输出为JSON格式
	 * @return
	 * @throws Exception
	 */
	protected String displayJSON() throws Exception {
		return displayJSON(assign);
	}
	
	/**
	 * 输出JSON信息
	 * @param object 对输出对象
	 * @return
	 * @throws Exception
	 */
	protected String displayJSON(Object object) throws Exception {
		return displayJSON(object, CONTENT_TYPE);
	}
	
	/**
	 * 输出JSON信息
	 * @param object 待输出对象
	 * @param contentType 页面编码字符串
	 * @return
	 * @throws Exception
	 */
	protected String displayJSON(Object object, String contentType) throws Exception {
		String result;
		if(object instanceof Map) {
			result = JSONObject.fromObject(object).toString();
		} else {
			result = JSONArray.fromObject(object).toString();
		}
		return displayText(result, contentType);
	}

	/**
	 * 重定向自定义URL地址
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String redirect(String url) throws Exception {
		return display(url, ResultType.TYPE_REDIRECT);
	} 
	
	/**
	 * 根据类型输出视图
	 * @param result
	 * @param type
	 * @return
	 * @throws Exception
	 */
	protected String display(String result, String type) throws Exception {
		if(ResultType.TYPE_FREEMARKER.equals(type)) return result;
		if(ResultType.TYPE_TEXT.equals(type)){
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			return "";
		}
		if(ResultType.TYPE_REDIRECT.equals(type)) return DPUtil.stringConcat("redirect:", result);
		return null;
	}
	
	/**
	 * 设置视图中需要的参数
	 * @param key
	 * @param value
	 */
	protected void assign(String key, Object value) {
		assign.put(key, value);
	}

	/**
	 * 获取请求参数
	 * @param key 参数名称
	 * @return
	 */
	protected String get(String key) {
		return DPUtil.parseString(parameter.get(key));
	}
	
	/**
	 * 获取请求参数数组
	 * @param key 参数名称
	 * @return
	 */
	protected String[] getArray(String key) {
		Object value = parameter.get(key);
		if(null == value || !value.getClass().isArray()) return new String[]{};
		return (String[]) value;
	}
	
	/**
	 * 获取请求参数Map
	 * @param key 参数名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getMap(String key) {
		Object value = parameter.get(key);
		if(null == value || !(value instanceof Map)) return new LinkedHashMap<String, Object>();
		return (Map<String, Object>) value;
	}
}
