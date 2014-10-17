package com.iisquare.jees.oa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.Configuration;
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
	@Autowired
	public Configuration configuration;
	
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
		String primaryKey = memberDao.getPrimaryKey();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Object serial = map.get("serial");
		if(!DPUtil.empty(serial)) {
			sb.append(" and serial = :serial");
			paramMap.put("serial", serial);
		}
		Object name = map.get("name");
		if(!DPUtil.empty(name)) {
			sb.append(" and name = :name");
			paramMap.put("name", name);
		}
		Object createIp = map.get("createIp");
		if(!DPUtil.empty(createIp)) {
			sb.append(" and create_ip = :createIp");
			paramMap.put("createIp", createIp);
		}
		Object activeIp = map.get("activeIp");
		if(!DPUtil.empty(activeIp)) {
			sb.append(" and active_ip = :activeIp");
			paramMap.put("activeIp", activeIp);
		}
		String organizeIds = SqlUtil.buildSafeWhere(",", (Object[]) map.get("organizeIds"));
		if(!DPUtil.empty(organizeIds)) {
			sb.append(" and ").append(primaryKey).append(" in (select member_id from ")
				.append(memberOrganizeRelDao.tableName()).append(" where organize_id in (").append(organizeIds).append("))");
		}
		int dutyId = ValidateUtil.filterInteger(DPUtil.parseString(map.get("dutyId")), true, 0, null, 0);
		if(!DPUtil.empty(dutyId)) {
			sb.append(" and ").append(primaryKey).append(" in (select member_id from ")
				.append(memberOrganizeRelDao.tableName()).append(" where duty_id = :dutyId)");
			paramMap.put("dutyId", dutyId);
		}
		String roleIds = SqlUtil.buildSafeWhere(",", (Object[]) map.get("roleIds"));
		if(!DPUtil.empty(roleIds)) {
			sb.append(" and ").append(primaryKey).append(" in (select member_id from ")
				.append(memberRoleRelDao.tableName()).append(" where role_id in (").append(roleIds).append("))");
		}
		String status = DPUtil.parseString(map.get("status"));
		if(!ValidateUtil.isNull(status, true)) {
			sb.append(" and status = :status");
			paramMap.put("status", DPUtil.parseInt(status));
		}
		Object timeStartCreate = map.get("timeStartCreate");
		if(!DPUtil.empty(timeStartCreate)) {
			sb.append(" and create_time >= :timeStartCreate");
			paramMap.put("timeStartCreate", DPUtil.dateTimeToMillis(timeStartCreate, configuration.getDateTimeFormat()));
		}
		Object timeEndCreate = map.get("timeEndCreate");
		if(!DPUtil.empty(timeEndCreate)) {
			sb.append(" and create_time <= :timeEndCreate");
			paramMap.put("timeEndCreate", DPUtil.dateTimeToMillis(timeEndCreate, configuration.getDateTimeFormat()));
		}
		Object timeStartModify = map.get("timeStartModify");
		if(!DPUtil.empty(timeStartModify)) {
			sb.append(" and modify_time >= :timeStartModify");
			paramMap.put("timeStartModify", DPUtil.dateTimeToMillis(timeStartModify, configuration.getDateTimeFormat()));
		}
		Object timeEndModify = map.get("timeEndModify");
		if(!DPUtil.empty(timeEndModify)) {
			sb.append(" and modify_time <= :timeEndModify");
			paramMap.put("timeEndModify", DPUtil.dateTimeToMillis(timeEndModify, configuration.getDateTimeFormat()));
		}
		Object timeStartActive = map.get("timeStartActive");
		if(!DPUtil.empty(timeStartActive)) {
			sb.append(" and active_time >= :timeStartActive");
			paramMap.put("timeStartActive", DPUtil.dateTimeToMillis(timeStartActive, configuration.getDateTimeFormat()));
		}
		Object timeEndActive = map.get("timeEndActive");
		if(!DPUtil.empty(timeEndActive)) {
			sb.append(" and active_time <= :timeEndActive");
			paramMap.put("timeEndActive", DPUtil.dateTimeToMillis(timeEndActive, configuration.getDateTimeFormat()));
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
	
	public Member getBySerial(Integer exceptId, String serial) {
		if(null == exceptId) exceptId = 0;
		return memberDao.getByFields(new String[]{memberDao.getPrimaryKey(), "serial"},
				new Object[]{exceptId, serial}, new String[]{"!=", "="}, null);
	}
	
	public Member getByName(Integer exceptId, String name) {
		if(null == exceptId) exceptId = 0;
		return memberDao.getByFields(new String[]{memberDao.getPrimaryKey(), "name"},
				new Object[]{exceptId, name}, new String[]{"!=", "="}, null);
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
