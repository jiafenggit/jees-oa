package com.iisquare.jees.oa.controller.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.core.util.UrlUtil;
import com.iisquare.jees.core.util.ViewUtil;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.domain.Menu;
import com.iisquare.jees.oa.service.MenuService;

/**
 * 菜单管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class MenuController extends PermitController {
	
	@Autowired
	public MenuService menuService;
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	public String listAction () throws Exception {
		boolean bCollapse = !DPUtil.empty(get("collapse")); // 折叠菜单
		List<Map<String, Object>> list = menuService.getList("*", "sort desc", 1, 0);
		for (Map<String, Object> map : list) {
			map.put("iconCls", map.get("icon"));
		}
		list = ServiceUtil.formatRelation(list, 0);
		if(bCollapse) ViewUtil.collapseAll(list);
		assign("total", list.size());
		assign("rows", DPUtil.collectionToArray(list));
		return displayJSON();
	}
	
	/**
	 * 当前登录用户菜单
	 */
	public String listSelfAction () throws Exception {
		List<Map<String, Object>> list = menuService.getListByRoleId(
				roleService.getIdListByMemberId(currentMember.getId(), null));
		for (Map<String, Object> map : list) {
			map.put("text", map.get("name"));
			map.put("iconCls", map.get("icon"));
			map.put("fullUrl", UrlUtil.concat(_WEB_URL_, DPUtil.parseString(map.get("url"))));
		}
		list = ServiceUtil.formatRelation(list, null);
		return displayJSON(DPUtil.collectionToArray(list));
	}
	
	public String showAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Map<String, Object> info = menuService.getById(id, true);
		if(null == info) {
			return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String editAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Menu info;
		if(DPUtil.empty(id)) {
			info = new Menu();
			info.setParentId(ValidateUtil.filterInteger(get("parentId"), true, 0, null, null));
		} else {
			info = menuService.getById(id);
			if(DPUtil.empty(info)) return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		assign("statusMap", menuService.getStatusMap());
		assign("goalMap", menuService.getGoalMap());
		return displayTemplate();
	}
	
	public String saveAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Menu persist;
		if(DPUtil.empty(id)) {
			persist = new Menu();
		} else {
			persist = menuService.getById(id);
			if(DPUtil.empty(persist)) return displayMessage(3001, "信息不存在，请刷新后再试", null);
		}
		persist.setParentId(ValidateUtil.filterInteger(get("parentId"), true, 0, null, null));
		String name = ValidateUtil.filterSimpleString(get("name"), true, 1, 64, null);
		if(DPUtil.empty(name)) return displayMessage(3002, "名称参数错误", null);
		persist.setName(name);
		String icon = ValidateUtil.filterSimpleString(get("icon"), true, 0, 64, null);
		if(null == icon) return displayMessage(3002, "图标参数错误", null);
		persist.setIcon(icon);
		String goal = ValidateUtil.filterItem(get("goal"), false,
				DPUtil.collectionToStringArray(menuService.getGoalMap().keySet()), null);
		if(null == goal) return displayMessage(3002, "打开方式参数错误", null);
		persist.setGoal(goal);
		String url = DPUtil.trim(get("url"));
		persist.setUrl(url);
		persist.setSort(ValidateUtil.filterInteger(get("sort"), true, null, null, null));
		String status = get("status");
		if(ValidateUtil.isNull(status, true)) return displayMessage(3003, "请选择记录状态", null);
		persist.setStatus(ValidateUtil.filterInteger(status, true, null, null, null));
		long time = System.currentTimeMillis();
		persist.setUpdateId(currentMember.getId());
		persist.setUpdateTime(time);
		int result;
		if(DPUtil.empty(persist.getId())) {
			persist.setCreateId(currentMember.getId());
			persist.setCreateTime(time);
			result = menuService.insert(persist);
		} else {
			result = menuService.update(persist);
		}
		if(result > 0) {
			return displayMessage(0, "操作成功", url("layout"));
		} else {
			return displayMessage(500, "操作失败", null);
		}
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = getArray("ids");
		int result = menuService.delete(idArray);
		if(-1 == result) return displayInfo("该信息拥有下级节点，不允许删除", null);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
}
