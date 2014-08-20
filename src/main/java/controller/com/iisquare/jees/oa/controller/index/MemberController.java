package com.iisquare.jees.oa.controller.index;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iisquare.jees.core.component.CPermitController;
import com.iisquare.jees.framework.util.DPUtil;

/**
 * 用户管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class MemberController extends CPermitController {
	
	@RequestMapping(value="/login")
	public String loginAction() throws Exception {
		String forward = convertForward(get("forward").toString());
		if(null != currentMember) return redirect(forward);
		String year = DPUtil.millisToDateTime(System.currentTimeMillis(), "yyyy");
		assign("forward", forward);
		assign("year", year);
		return displayTemplate();
	}
	
	@RequestMapping(value="/platform")
	public String platformAction() throws Exception {
		if(null == currentMember) return redirect("login", "");
		return displayTemplate();
	}
	
	private String convertForward(String forward) throws Exception {
		if(DPUtil.empty(forward)) {
			return _WEB_URL_ + "/platform";
		} else {
			if("back".equals(forward)) {
				String backUrl = _REQUEST_.getHeader("Referer");
				if(null == backUrl) {
					return _WEB_URL_ + "/platform";
				} else {
					return backUrl;
				}
			} else {
				return forward;
			}
		}
	}
}
