package com.iisquare.jees.oa.controller.index;

import java.util.Collection;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServletUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.domain.Member;

/**
 * 用户管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class MemberController extends PermitController {
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	public String listAction () throws Exception {
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500, null);
		Map<Object, Object> map = memberService.search(ServletUtil.singleParameterMap(request), "sort desc", page, pageSize);
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
	
	public String editAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Member info;
		if(DPUtil.empty(id)) {
			info = new Member();
		} else {
			info = memberService.getById(id);
			if(DPUtil.empty(info)) return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		assign("statusMap", memberService.getStatusMap(false));
		return displayTemplate();
	}
	
	public String saveAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Member persist;
		if(DPUtil.empty(id)) {
			persist = new Member();
		} else {
			persist = memberService.getById(id);
			if(DPUtil.empty(persist)) return displayMessage(3001, "信息不存在，请刷新后再试");
		}
		long time = System.currentTimeMillis();
		persist.setUpdateId(currentMember.getId());
		persist.setUpdateTime(time);
		int result;
		if(DPUtil.empty(persist.getId())) {
			persist.setCreateId(currentMember.getId());
			persist.setCreateTime(time);
			result = memberService.insert(persist);
		} else {
			result = memberService.update(persist);
		}
		if(result > 0) {
			return displayMessage(0, url("layout"));
		} else {
			return displayMessage(500, "操作失败");
		}
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = DPUtil.explode(get("ids"), ",", " ", true);
		int result = memberService.delete(idArray);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
	
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
		String serial = ValidateUtil.filterSimpleString(get("serial"), true, 1, 64, null);
		String password = ValidateUtil.filterSimpleString(get("password"), true, 1, null, null);
		if(DPUtil.empty(serial) || DPUtil.empty(password)) {
			return displayMessage(1, "请输入正确的账号和密码！");
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
		request.getSession().invalidate();
		return redirect(_WEB_URL_);
	}
	
	@RequestMapping(value="/platform")
	public String platformAction() throws Exception {
		if(null == currentMember) return redirect("/login");
		assign("currentMember", currentMember);
		return displayTemplate();
	}
	
	private String convertForward(String forward) throws Exception {
		String platformUrl = DPUtil.stringConcat(_WEB_URL_, "/platform");
		if(DPUtil.empty(forward)) {
			return platformUrl;
		} else {
			if("back".equals(forward)) {
				String backUrl = request.getHeader("Referer");
				if(null == backUrl) {
					return platformUrl;
				} else {
					return backUrl;
				}
			} else {
				return forward;
			}
		}
	}
}
