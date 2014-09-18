package com.iisquare.jees.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.controller.ControllerBase;
import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.CodeUtil;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.domain.Member;

@Service
public class MemberService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	
	public MemberService() {}
	
	public Member getBySerial(String serial) {
		return memberDao.getByField("serial", serial, null, null);
	}
	
	public String encodePassword(String password, String salt) {
		return CodeUtil.md5(CodeUtil.md5(password) + salt);
	}
	
	public Member getById(int id) {
		return memberDao.getById(id);
	}
	
	public boolean setCurrent(ControllerBase controller, Member member) {
		if(null == member) return false;
		int mid = member.getId();
		if(DPUtil.empty(mid)) return false;
		controller._REQUEST_.getSession().setAttribute("mid", mid);
		return true;
	}
	
	public Member getCurrent(ControllerBase controller) {
		int mid = DPUtil.parseInt(controller._REQUEST_.getSession().getAttribute("mid"));
		if(DPUtil.empty(mid)) return null;
		Member member = getById(mid);
		if(null == member || 1 != member.getId()) return null;
		return member;
	}
	
	public static void main(String[] args) {
		MemberService memberService = new MemberService();
		System.out.println(memberService.encodePassword("admin888", "888888"));
	}
}
