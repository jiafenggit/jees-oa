package com.iisquare.jees.core.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.oa.domain.Member;
import com.iisquare.jees.oa.service.MemberService;
import com.iisquare.jees.oa.service.SettingService;

@Controller
@Scope("prototype")
public class CPermitController extends CController {
	@Autowired
	public MemberService memberService;
	@Autowired
	public SettingService settingService;
	
	public Member currentMember;
	
	@Override
	public void init(HttpServletRequest request, HttpServletResponse response,
			Object handler) {
		super.init(request, response, handler);
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
	}
	
}
