package com.iisquare.jees.oa.controller.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
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
	
	public String showSelfAction() throws Exception {
		Integer id = currentMember.getId();
		Map<String, Object> info = memberService.getById(id, true);
		if(null == info) {
			return displayInfo("系统异常，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String editSelfAction() throws Exception {
		Integer id = currentMember.getId();
		Map<String, Object> info = memberService.getById(id, true);
		if(null == info) {
			return displayInfo("系统异常，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String saveSelfAction() throws Exception {
		Integer id = currentMember.getId();
		Member persist = memberService.getById(id);
		if(DPUtil.empty(persist)) return displayMessage(3001, "系统异常，请刷新后再试", null);
		String name = ValidateUtil.filterSimpleString(get("name"), true, 1, 64, null);
		if(DPUtil.empty(name)) return displayMessage(3004, "请输入合理的名称", null);
		if(null != memberService.getByName(persist.getId(), name)) return displayMessage(3005, "名称已存在", null);
		persist.setName(name);
		long time = System.currentTimeMillis();
		persist.setUpdateId(currentMember.getId());
		persist.setUpdateTime(time);
		int result = memberService.update(persist, null, null);
		if(result > 0) {
			return displayMessage(0, "操作成功", null);
		} else {
			return displayMessage(500, "操作失败", null);
		}
	}
	
	public String editPasswordAction() throws Exception {
		Integer id = currentMember.getId();
		Map<String, Object> info = memberService.getById(id, false);
		if(null == info) {
			return displayInfo("系统异常，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String savePasswordAction() throws Exception {
		Integer id = currentMember.getId();
		Member persist = memberService.getById(id);
		if(DPUtil.empty(persist)) return displayMessage(3001, "系统异常，请刷新后再试", null);
		String password = DPUtil.trim(get("password"));
		String passwordNew = DPUtil.trim(get("passwordNew"));
		if(6 > passwordNew.length()) return displayMessage(3002, "密码长度不小于6位", null);
		if(!memberService.encodePassword(password, persist.getSalt())
				.equals(persist.getPassword())) return displayMessage(3003, "原密码错误", null);
		String salt = DPUtil.random(6);
		persist.setSalt(salt);
		persist.setPassword(memberService.encodePassword(passwordNew, salt));
		long time = System.currentTimeMillis();
		persist.setUpdateId(currentMember.getId());
		persist.setUpdateTime(time);
		int result = memberService.update(persist, null, null);
		if(result > 0) {
			request.getSession().invalidate(); // 退出登陆
			return displayMessage(0, "操作成功", null);
		} else {
			return displayMessage(500, "操作失败", null);
		}
	}
	
	public String layoutAction() throws Exception {
		assign("statusMap", memberService.getStatusMap(false));
		return displayTemplate();
	}
	
	public String listAction () throws Exception {
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500, null);
		Map<Object, Object> map = memberService.search(parameter, "sort desc", page, pageSize);
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
	
	public String showAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Map<String, Object> info = memberService.getById(id, true);
		if(null == info) {
			return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	@SuppressWarnings("unchecked")
	public String editAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Map<String, Object> info;
		if(DPUtil.empty(id)) {
			info = new HashMap<String, Object>();
		} else {
			info = memberService.getById(id, true);
			if(DPUtil.empty(info)) return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		Object roleRelInfo = info.get("role_info");
		if(DPUtil.empty(roleRelInfo)) {
			assign("roleIds", "");
		} else {
			assign("roleIds", DPUtil.implode(",", DPUtil.collectionToArray(
					ServiceUtil.getFieldValues((List<Map<String, Object>>) roleRelInfo, new String[]{"role_id"}))));
		}
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
			if(DPUtil.empty(persist)) return displayMessage(3001, "信息不存在，请刷新后再试", null);
		}
		String serial = ValidateUtil.filterSimpleString(get("serial"), true, 1, 64, null);
		if(DPUtil.empty(serial)) return displayMessage(3002, "账号参数错误", null);
		if(null != memberService.getBySerial(persist.getId(), serial)) return displayMessage(3003, "账号已存在", null);
		persist.setSerial(serial);
		String name = ValidateUtil.filterSimpleString(get("name"), true, 1, 64, null);
		if(DPUtil.empty(name)) return displayMessage(3004, "名称参数错误", null);
		if(null != memberService.getByName(persist.getId(), name)) return displayMessage(3005, "名称已存在", null);
		persist.setName(name);
		String password = DPUtil.trim(get("password"));
		if(!DPUtil.empty(password)) {
			if(6 > password.length()) return displayMessage(3006, "密码参数错误", null);
			String salt = DPUtil.random(6);
			persist.setSalt(salt);
			persist.setPassword(memberService.encodePassword(password, salt));
		}
		String[] organizeDutyIds = getArray("organizeDutyIds");
		if(DPUtil.empty(organizeDutyIds)) return displayMessage(3007, "部门参数错误", null);
		String[] roleIds = getArray("roleIds");
		if(DPUtil.empty(roleIds)) return displayMessage(3008, "角色参数错误", null);
		persist.setSort(ValidateUtil.filterInteger(get("sort"), true, null, null, null));
		String status = get("status");
		if(ValidateUtil.isNull(status, true)) return displayMessage(3009, "请选择记录状态", null);
		persist.setStatus(ValidateUtil.filterInteger(status, true, null, null, null));
		long time = System.currentTimeMillis();
		persist.setUpdateId(currentMember.getId());
		persist.setUpdateTime(time);
		int result;
		if(DPUtil.empty(persist.getId())) {
			persist.setCreateId(currentMember.getId());
			persist.setCreateIp(ServletUtil.getRemoteAddr(request));
			persist.setCreateTime(time);
			result = memberService.insert(persist, organizeDutyIds, roleIds);
		} else {
			result = memberService.update(persist, organizeDutyIds, roleIds);
		}
		if(result > 0) {
			return displayMessage(0, "操作成功", null);
		} else {
			return displayMessage(500, "操作失败", null);
		}
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = getArray("ids");
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
		String serial = settingService.get("system", "guestSerial");
		assign("forward", forward);
		assign("serial", serial);
		return displayTemplate();
	}
	
	public String logonAction() throws Exception {
		String serial = ValidateUtil.filterSimpleString(get("serial"), true, 1, 64, null);
		String password = ValidateUtil.filterSimpleString(get("password"), true, 1, null, null);
		if(DPUtil.empty(serial) || DPUtil.empty(password)) {
			return displayMessage(1, "请输入正确的账号和密码！", null);
		}
		Member member = memberService.getBySerial(0, serial);
		if(null == member || 1 != member.getStatus()
				|| !memberService.encodePassword(password, member.getSalt()).equals(member.getPassword())) {
			return displayMessage(2, "账号或密码错误，请重新输入！", null);
		}
		memberService.setCurrent(this, member);
		logService.record(this, member, "用户登陆", "service", null);
		return displayMessage(0, "操作成功", convertForward(get("forward").toString()));
	}
	
	/**
	 * 访客模式登陆
	 */
	@RequestMapping(value="/guest")
	public String guestAction() throws Exception {
		String serial = settingService.get("system", "guestSerial");
		Member member = memberService.getBySerial(0, serial);
		if(DPUtil.empty(serial) || null == member || 1 != member.getStatus()) {
			return displayInfo("访客模式已关闭，请采用系统账号登陆", convertForward("login"));
		}
		memberService.setCurrent(this, member);
		logService.record(this, member, "用户登陆", "service", null);
		return redirect(convertForward(get("forward").toString()));
	}
	
	public String logoutAction() throws Exception {
		request.getSession().invalidate();
		return redirect(webUrl);
	}
	
	@RequestMapping(value="/platform")
	public String platformAction() throws Exception {
		if(null == currentMember) return redirect(convertForward("login"));
		assign("currentMember", currentMember);
		return displayTemplate();
	}
	
	private String convertForward(String forward) throws Exception {
		String platformUrl = DPUtil.stringConcat(webUrl, "/platform");
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
			} else if("login".equals(forward)) {
				return DPUtil.stringConcat(webUrl, "/login");
			} else {
				return forward;
			}
		}
	}
}
