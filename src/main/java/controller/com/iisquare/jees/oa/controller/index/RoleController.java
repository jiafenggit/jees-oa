package com.iisquare.jees.oa.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
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
		list = DPUtil.formatRelation(list, 0);
		assign("total", list.size());
		assign("rows", DPUtil.collectionToArray(list));
		return displayJSON();
	}
	
	public String editAction() throws Exception {
		Object id = get("id");
		Role role;
		if(DPUtil.empty(id)) {
			role = new Role();
		} else {
			role = roleService.getById(id);
			if(DPUtil.empty(role)) return displayInfo("您访问的信息不存在，请刷新后再试！", null);
		}
		List<Map<String, Object>> list = roleService.getList("*", null, null, "sort", 1, 0);
		list = DPUtil.formatRelation(list, 0);
		assign("info", role);
		assign("list", DPUtil.collectionToArray(list));
		return displayTemplate();
	}
}
