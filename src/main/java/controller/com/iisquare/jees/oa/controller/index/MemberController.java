package com.iisquare.jees.oa.controller.index;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iisquare.jees.core.component.CPermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.oa.domain.Member;

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
	
	public String logonAction() throws Exception {
		String serial = get("serial");
		String password = get("password");
		if(DPUtil.empty(serial) || DPUtil.empty(password)) {
			return displayMessage(1, "请输入账号和密码！");
		}
		Member member = memberService.getBySerial(serial);
		if(null == member || 1 != member.getStatus()
				|| !memberService.encodePassword(password, member.getSalt()).equals(member.getPassword())) {
			return displayMessage(2, "账号或密码错误，请重新输入！");
		}
		memberService.setCurrent(this, member);
		return displayMessage(0, convertForward(get("forward").toString()));
	}
	
	public String logoutAction() throws Exception {
		_REQUEST_.getSession().invalidate();
		return redirect(_WEB_URL_);
	}
	
	@RequestMapping(value="/platform")
	public String platformAction() throws Exception {
		if(null == currentMember) return redirect("/login");
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
