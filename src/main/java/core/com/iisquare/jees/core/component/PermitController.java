package com.iisquare.jees.core.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServletUtil;
import com.iisquare.jees.oa.domain.Log;
import com.iisquare.jees.oa.domain.LogSetting;
import com.iisquare.jees.oa.domain.Member;
import com.iisquare.jees.oa.domain.Resource;
import com.iisquare.jees.oa.service.LogService;
import com.iisquare.jees.oa.service.MemberService;
import com.iisquare.jees.oa.service.ResourceService;
import com.iisquare.jees.oa.service.SettingService;

@Controller
@Scope("prototype")
public abstract class PermitController extends CoreController {
	@Autowired
	public MemberService memberService;
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
			Object handler) {
		super.init(request, response, handler);
		currentMember = memberService.getCurrent(this);
		preCheckPermit();
		if(isCheckPermit) checkPermit();
	}
	
	/**
	 * 检测权限前执行
	 */
	protected void preCheckPermit() {}
	
	/**
	 * 执行权限检测处理
	 */
	protected void checkPermit() {
		
	}
	
	/**
	 * 判断是否拥有当前Action的权限
	 */
	public boolean hasPermit () {
		return hasPermit(_MODULE_, _CONTROLLER_, _ACTION_);
	}
	
	/**
	 * 判断是否拥有对应Action的权限
	 */
	public boolean hasPermit (String action) {
		return hasPermit(_MODULE_, _CONTROLLER_, action);
	}
	
	/**
	 * 判断是否拥有对应Controller中Action的权限
	 */
	public boolean hasPermit (String controller, String action) {
		return hasPermit(_MODULE_, controller, action);
	}
	
	/**
	 * 判断是否拥有对应Module、Controller、Action的权限
	 */
	public boolean hasPermit (String module, String controller, String action) {
		return true;
	}
	
	@Override
	public void destroy(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		String viewName = modelAndView.getViewName();
		if(!DPUtil.empty(viewName) && !viewName.startsWith("redirect:")) {
			modelAndView.addObject("_WEB_NAME_", settingService.get("system", "webName"));
			modelAndView.addObject("_PAGE_SIZE_", settingService.get("system", "pageSize"));
		}
		super.destroy(request, response, handler, modelAndView);
		/* 日志处理 */
		Resource resource = resourceService.getByRouter(0, _MODULE_, _CONTROLLER_, _ACTION_);
		if(null == resource) return ;
		LogSetting logSetting = logService.getSettingById(resource.getId());
		if(null == logSetting || 1 != logSetting.getEnable()) return ;
		Log log = new Log();
		log.setName(resource.getName());
		log.setType("system");
		log.setModule(resource.getModule());
		log.setController(resource.getController());
		log.setAction(resource.getAction());
		if(1 == logSetting.getReferer()) log.setReferer(_REQUEST_.getHeader("referer"));
		if(1 == logSetting.getRequestUrl()) log.setRequestUrl(_REQUEST_.getRequestURL().toString());
		if(1 == logSetting.getRequestParam()) {
			if("index".equals(_MODULE_) && "member".equals(_CONTROLLER_) && "logon".equals(_ACTION_)) {
				log.setRequestParam("******");
			} else {
				log.setRequestParam(JSONObject.fromObject(_REQUEST_.getParameterMap()).toString());
			}
		}
		if(1 == logSetting.getSessionId()) log.setSessionId(_REQUEST_.getRequestedSessionId());
		if(1 == logSetting.getSessionValue()) log.setSessionValue(
				JSONObject.fromObject(ServletUtil.getSessionMap(_REQUEST_)).toString());
		if(1 == logSetting.getResponseView()) log.setResponseView(viewName);
		if(1 == logSetting.getResponseData()) log.setResponseData(JSONObject.fromObject(_ASSIGN_).toString());
		log.setOperateId(null == currentMember ? 0 : currentMember.getId());
		log.setOperateIp(ServletUtil.getRemoteAddr(_REQUEST_));
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
