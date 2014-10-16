package com.iisquare.jees.oa.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.framework.util.ServletUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.domain.Resource;

/**
 * 资源管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class ResourceController extends PermitController {
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	public String listAction () throws Exception {
		boolean bLogSetting = !DPUtil.empty(get("log_setting"));
		List<Map<String, Object>> list = resourceService.getList(ServletUtil.singleParameterMap(request), "sort desc", 1, 0);
		if(bLogSetting) list = logService.fillSetting(list);
		list = ServiceUtil.formatRelation(list, 0);
		assign("total", list.size());
		assign("rows", DPUtil.collectionToArray(list));
		return displayJSON();
	}
	
	public String showAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Map<String, Object> info = resourceService.getById(id, true);
		if(null == info) {
			return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String editAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Resource info;
		if(DPUtil.empty(id)) {
			info = new Resource();
			info.setParentId(ValidateUtil.filterInteger(get("parentId"), true, 0, null, null));
		} else {
			info = resourceService.getById(id);
			if(DPUtil.empty(info)) return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		assign("statusMap", resourceService.getStatusMap());
		return displayTemplate();
	}
	
	public String saveAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Resource persist;
		if(DPUtil.empty(id)) {
			persist = new Resource();
		} else {
			persist = resourceService.getById(id);
			if(DPUtil.empty(persist)) return displayMessage(3001, "信息不存在，请刷新后再试");
		}
		persist.setParentId(ValidateUtil.filterInteger(get("parentId"), true, 0, null, null));
		persist.setMenuListEnable(ValidateUtil.filterInteger(get("menuListEnable"), true, 0, null, null));
		persist.setMenuPickEnable(ValidateUtil.filterInteger(get("menuPickEnable"), true, 0, null, null));
		String name = ValidateUtil.filterSimpleString(get("name"), true, 1, 64, null);
		if(DPUtil.empty(name)) return displayMessage(3002, "名称参数错误");
		persist.setName(name);
		String module = ValidateUtil.filterSimpleString(get("module"), true, 0, 64, null);
		if(null == module) return displayMessage(3002, "模块参数错误");
		persist.setModule(module);
		String controller = ValidateUtil.filterSimpleString(get("controller"), true, 0, 64, null);
		if(null == controller) return displayMessage(3002, "控制器参数错误");
		persist.setController(controller);
		String action = ValidateUtil.filterSimpleString(get("action"), true, 0, 64, null);
		if(null == action) return displayMessage(3002, "方法参数错误");
		persist.setAction(action);
		if(null != resourceService.getByRouter(persist.getId(), module, controller, action)) {
			return displayMessage(3006, "对应资源已存在");
		}
		persist.setReferId(ValidateUtil.filterInteger(get("referId"), true, 0, null, null));
		persist.setSort(ValidateUtil.filterInteger(get("sort"), true, null, null, null));
		String status = get("status");
		if(ValidateUtil.isNull(status, true)) return displayMessage(3003, "请选择记录状态");
		persist.setStatus(ValidateUtil.filterInteger(status, true, null, null, null));
		long time = System.currentTimeMillis();
		persist.setUpdateId(currentMember.getId());
		persist.setUpdateTime(time);
		int result;
		if(DPUtil.empty(persist.getId())) {
			persist.setCreateId(currentMember.getId());
			persist.setCreateTime(time);
			result = resourceService.insert(persist);
		} else {
			result = resourceService.update(persist);
		}
		if(result > 0) {
			return displayMessage(0, url("layout"));
		} else {
			return displayMessage(500, "操作失败");
		}
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = DPUtil.explode(get("ids"), ",", " ", true);
		int result = resourceService.delete(idArray);
		if(-1 == result) return displayInfo("该信息拥有下级节点，不允许删除", null);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
}
