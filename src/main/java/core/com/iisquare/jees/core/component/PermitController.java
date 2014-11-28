package com.iisquare.jees.core.component;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.iisquare.jees.oa.domain.Log;
import com.iisquare.jees.oa.domain.LogSetting;
import com.iisquare.jees.oa.domain.Member;
import com.iisquare.jees.oa.domain.Resource;
import com.iisquare.jees.oa.service.LogService;
import com.iisquare.jees.oa.service.MemberService;
import com.iisquare.jees.oa.service.ResourceService;
import com.iisquare.jees.oa.service.RoleService;
import com.iisquare.jees.oa.service.SettingService;
import com.iisquare.jees.core.exception.PermitDediedException;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServletUtil;

@Controller
@Scope("prototype")
public abstract class PermitController extends CoreController {
	@Autowired
	public MemberService memberService;
	@Autowired
	public RoleService roleService;
	@Autowired
	public LogService logService;
	@Autowired
	public SettingService settingService;
	@Autowired
	public ResourceService resourceService;
	
	public Member currentMember;
	private boolean isCheckPermit = true; // 是否进行权限验证
	
	public boolean isCheckPermit() {
		return isCheckPermit;
	}

	public void setCheckPermit(boolean isCheckPermit) {
		this.isCheckPermit = isCheckPermit;
	}

	@Override
	public void init(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		super.init(request, response, handler);
		currentMember = memberService.getCurrent(this, true);
		preCheckPermit();
		if(isCheckPermit && !hasPermit()) throw new PermitDediedException();
	}
	
	/**
	 * 检测权限前执行
	 */
	protected void preCheckPermit() {}
	
	/**
	 * 判断是否拥有当前Action的权限
	 */
	public boolean hasPermit () {
		return hasPermit(moduleName, controllerName, actionName);
	}
	
	/**
	 * 判断是否拥有对应Action的权限
	 */
	public boolean hasPermit (String action) {
		return hasPermit(moduleName, controllerName, action);
	}
	
	/**
	 * 判断是否拥有对应Controller中Action的权限
	 */
	public boolean hasPermit (String controller, String action) {
		return hasPermit(moduleName, controller, action);
	}
	
	/**
	 * 判断是否拥有对应Module、Controller、Action的权限
	 */
	public boolean hasPermit (String module, String controller, String action) {
		if(isCheckPermit) return true; // 调试模式，拥有所有权限
		Resource resource = resourceService.getByRouter(0, module, controller, action); // 获取当前资源对象
		resource = resourceService.getReferRoot(resource); // 处理引用关系
		if(null == resource) return false; // 资源不存在
		if(-1 == resource.getStatus()) return true; // 禁用验证
		if(null == currentMember) return false; // 用户未登录
		Integer memberId = currentMember.getId();
		List<Object> list = resourceService.getIdArrayByRoleId(
				roleService.getIdListByMemberId(memberId, null)); // 获取登录用户全部可用资源
		if(list.contains(resource.getId())) return true; // 验证登录用户是否拥有当前资源
		return false;
	}
	
	@Override
	public void destroy(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		String viewName = modelAndView.getViewName();
		if(!DPUtil.empty(viewName) && !viewName.startsWith("redirect:")) {
			modelAndView.addObject("_webName_", settingService.get("system", "webName"));
			modelAndView.addObject("_pageSize_", settingService.get("system", "pageSize"));
		}
		super.destroy(request, response, handler, modelAndView);
		/* 日志处理 */
		Resource resource = resourceService.getByRouter(0, moduleName, controllerName, actionName);
		if(null == resource) return ;
		LogSetting logSetting = logService.getSettingById(resource.getId());
		if(null == logSetting || 1 != logSetting.getEnable()) return ;
		Log log = new Log();
		log.setName(resource.getName());
		log.setType("system");
		log.setModule(resource.getModule());
		log.setController(resource.getController());
		log.setAction(resource.getAction());
		if(1 == logSetting.getReferer()) log.setReferer(request.getHeader("referer"));
		if(1 == logSetting.getRequestUrl()) log.setRequestUrl(request.getRequestURL().toString());
		if(1 == logSetting.getRequestParam()) {
			if("base".equals(moduleName) && "member".equals(controllerName) && "logon".equals(actionName)) {
				log.setRequestParam("******");
			} else {
				log.setRequestParam(JSONObject.fromObject(request.getParameterMap()).toString());
			}
		}
		if(1 == logSetting.getSessionId()) log.setSessionId(request.getRequestedSessionId());
		if(1 == logSetting.getSessionValue()) log.setSessionValue(
				JSONObject.fromObject(ServletUtil.getSessionMap(request)).toString());
		if(1 == logSetting.getResponseView()) log.setResponseView(viewName);
		if(1 == logSetting.getResponseData()) log.setResponseData(JSONObject.fromObject(assign).toString());
		log.setOperateId(null == currentMember ? 0 : currentMember.getId());
		log.setOperateIp(ServletUtil.getRemoteAddr(request));
		log.setOperateTime(System.currentTimeMillis());
		logService.insert(log);
	}
	
	/**
	 * 输出提示信息页面
	 */
	public String displayInfo(String message, String forward) throws Exception {
		assign("message", message);
		assign("url", forward);
		return displayTemplate("public", "info");
	}
}
