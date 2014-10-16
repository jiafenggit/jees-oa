package com.iisquare.jees.oa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.controller.ControllerBase;
import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.CodeUtil;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.framework.util.SqlUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.MemberOrganizeRelDao;
import com.iisquare.jees.oa.dao.MemberRoleRelDao;
import com.iisquare.jees.oa.domain.Member;
import com.iisquare.jees.oa.domain.MemberOrganizeRel;
import com.iisquare.jees.oa.domain.MemberRoleRel;

@Service
public class MemberService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public MemberOrganizeRelDao memberOrganizeRelDao;
	@Autowired
	public MemberRoleRelDao memberRoleRelDao;
	
	public Map<String, String> getStatusMap(boolean bAll) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if(bAll) {
			map.put("-1", "已删除");
		}
		map.put("0", "禁用");
		map.put("1", "正常");
		return map;
	}
	
	public MemberService() {}
	
	public Map<Object, Object> search(Map<String, Object> map, String orderBy, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select * from ")
			.append(memberDao.tableName()).append(" where 1 = 1");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Object title = map.get("title");
		if(!DPUtil.empty(title)) {
			sb.append(" and title like :title");
			paramMap.put("title", DPUtil.stringConcat("%", title, "%"));
		}
		int typeId = ValidateUtil.filterInteger(DPUtil.parseString(map.get("typeId")), true, 0, null, 0);
		if(!DPUtil.empty(typeId)) {
			sb.append(" and type_id = :typeId");
			paramMap.put("typeId", typeId);
		}
		Object serial = map.get("serial");
		if(!DPUtil.empty(serial)) {
			sb.append(" and operate_id in (select ").append(memberDao.getPrimaryKey())
				.append(" from ").append(memberDao.tableName()).append(" where serial = :serial)");
			paramMap.put("serial", serial);
		}
		if(!DPUtil.empty(orderBy)) sb.append(" order by ").append(orderBy);
		String sql = sb.toString();
		int total = memberDao.getCount(sql, paramMap, true);
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> rows = memberDao.npJdbcTemplate().queryForList(sql, paramMap);
		rows = ServiceUtil.fillFields(rows, new String[]{"status"}, new Map<?, ?>[]{getStatusMap(true)}, null);
		rows = ServiceUtil.fillRelations(rows, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return DPUtil.buildMap(new String[]{"total", "rows"}, new Object[]{total, rows});
	}
	
	public List<Member> getList(Map<String, Object> where, Map<String, String> operators, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Member> list = memberDao.getList(where, operators, append, page, pageSize);
		return list;
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = memberDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public int insert(Member persist, Object[] organizeIds, Object[] roleIds) {
		int result =  memberDao.insert(persist);
		if(result > 0) updateRel(result, organizeIds, roleIds);
		return result;
	}
	
	public int update(Member persist, Object[] organizeIds, Object[] roleIds) {
		int result =  memberDao.update(persist);
		if(result >= 0) updateRel(persist.getId(), organizeIds, roleIds);
		return result;
	}
	
	public void updateRel(Object id, Object[] organizeIds, Object[] roleIds) {
		if(DPUtil.empty(id)) return ;
		if(null != organizeIds) {
			memberOrganizeRelDao.delete(new String[]{"member_id"}, new Object[]{id}, null);
			for (Object organizeId : organizeIds) {
				MemberOrganizeRel persis = new MemberOrganizeRel();
				persis.setMemberId(DPUtil.parseInt(id));
				persis.setOrganizeId(DPUtil.parseInt(organizeId));
				persis.setDutyId(0);
				memberOrganizeRelDao.insert(persis);
			}
		}
		if(null != roleIds) {
			memberRoleRelDao.delete(new String[]{"member_id"}, new Object[]{id}, null);
			for (Object roleId : roleIds) {
				MemberRoleRel persis = new MemberRoleRel();
				persis.setMemberId(DPUtil.parseInt(id));
				persis.setRoleId(DPUtil.parseInt(roleId));
				memberRoleRelDao.insert(persis);
			}
		}
	}
	
	public int delete(Object... ids) {
		return memberDao.deleteByIds(ids);
	}
	
	public Member getBySerial(String serial) {
		return memberDao.getByField("serial", serial, null, null);
	}
	
	public Member getByName(String name) {
		return memberDao.getByField("name", name, null, null);
	}
	
	public String encodePassword(String password, String salt) {
		return CodeUtil.md5(CodeUtil.md5(password) + salt);
	}
	
	public Member getById(Object id) {
		return memberDao.getById(id);
	}
	
	public boolean setCurrent(ControllerBase controller, Member member) {
		if(null == member) return false;
		int mid = member.getId();
		if(DPUtil.empty(mid)) return false;
		controller.getRequest().getSession().setAttribute("mid", mid);
		return true;
	}
	
	public Member getCurrent(ControllerBase controller) {
		int mid = DPUtil.parseInt(controller.getRequest().getSession().getAttribute("mid"));
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
