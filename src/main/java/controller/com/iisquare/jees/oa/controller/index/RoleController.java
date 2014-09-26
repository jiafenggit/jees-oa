package com.iisquare.jees.oa.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.oa.domain.Role;
import com.iisquare.jees.oa.service.RoleService;

/**
 * 角色管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class RoleController extends PermitController {
	
	@Autowired
	public RoleService roleService;
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	public String listAction() throws Exception {
		List<Map<String, Object>> list = roleService.getList("*", null, null, "sort", 1, 0);
		list = ServiceUtil.formatRelation(list, 0);
		assign("total", list.size());
		assign("rows", DPUtil.collectionToArray(list));
		return displayJSON();
	}
	
	public String showAction() throws Exception {
		Object id = get("id");
		Map<String, Object> info = roleService.getById(id, true);
		if(null == info) {
			return displayInfo("您访问的信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String editAction() throws Exception {
		Object id = get("id");
		Role info;
		if(DPUtil.empty(id)) {
			info = new Role();
		} else {
			info = roleService.getById(id);
			if(DPUtil.empty(info)) return displayInfo("您访问的信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String saveAction() throws Exception {
		Object id = get("id");
		Role persist;
		if(DPUtil.empty(id)) {
			persist = new Role();
		} else {
			persist = roleService.getById(id);
			if(DPUtil.empty(persist)) return displayMessage(3001, "您访问的信息不存在，请刷新后再试");
		}
		persist.setParentId(DPUtil.parseInt(get("parentId")));
		String name = DPUtil.trim(get("name"));
		if(DPUtil.empty(name)) return displayMessage(3002, "请输入名称");
		persist.setName(name);
		persist.setSort(DPUtil.parseInt(get("sort")));
		persist.setStatus(DPUtil.parseInt(get("status")));
		persist.setRemark(get("remark"));
		long time = System.currentTimeMillis();
		persist.setUpdateId(currentMember.getId());
		persist.setUpdateTime(time);
		int result;
		if(DPUtil.empty(persist.getId())) {
			persist.setCreateId(currentMember.getId());
			persist.setCreateTime(time);
			result = roleService.insert(persist);
		} else {
			result = roleService.update(persist);
		}
		if(result > 0) {
			return displayMessage(0, url("layout"));
		} else {
			return displayMessage(500, "操作失败");
		}
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = DPUtil.explode(get("ids"), ",", " ");
		int result = roleService.delete(idArray);
		if(-1 == result) return displayInfo("该信息拥有下级节点，不允许删除", null);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
}
