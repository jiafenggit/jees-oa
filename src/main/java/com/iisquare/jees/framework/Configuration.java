package com.iisquare.jees.framework;

public class Configuration {

	private String modulePrefix = "com.iisquare.smh.action."; // 模块包前缀
	private String controllerSuffix = "Controller"; // 控制器名称后缀
	private String actionSuffix = "Action"; // 方法名称后缀
	private String themeName = "default"; // 主题名称，留空为不区分主题
	private String skinFolder = "skin"; // 主题资源所在目录
	private String templateLoaderPath = "/WEB-INF/template/"; // 视图模板所在路径
	private String templateSuffix = ".htm"; // 视图模板文件后缀
	private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss"; // 日期格式
	private String sessionName = "JSESSIONID"; // 服务器环境的SESSIONID
	private String tablePrefix = "jees_"; // 数据库表前缀

	public String getModulePrefix() {
		return modulePrefix;
	}

	public void setModulePrefix(String modulePrefix) {
		this.modulePrefix = modulePrefix;
	}

	public String getControllerSuffix() {
		return controllerSuffix;
	}

	public void setControllerSuffix(String controllerSuffix) {
		this.controllerSuffix = controllerSuffix;
	}

	public String getActionSuffix() {
		return actionSuffix;
	}

	public void setActionSuffix(String actionSuffix) {
		this.actionSuffix = actionSuffix;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getSkinFolder() {
		return skinFolder;
	}

	public void setSkinFolder(String skinFolder) {
		this.skinFolder = skinFolder;
	}

	public String getTemplateLoaderPath() {
		return templateLoaderPath;
	}

	public void setTemplateLoaderPath(String templateLoaderPath) {
		this.templateLoaderPath = templateLoaderPath.replaceAll("\\\\", "/");
	}

	public String getTemplateSuffix() {
		return templateSuffix;
	}

	public void setTemplateSuffix(String templateSuffix) {
		this.templateSuffix = templateSuffix;
	}

	public String getDateTimeFormat() {
		return dateTimeFormat;
	}

	public void setDateTimeFormat(String dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public Configuration() {}
}
