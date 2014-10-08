package com.iisquare.jees.oa.controller.index;

import java.util.Collection;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
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
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500);
		Map<Object, Object> map = resourceService.search(ServletUtil.singleParameterMap(_REQUEST_), page, pageSize);
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
	
	public String showAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null);
		Map<String, Object> info = resourceService.getById(id, true);
		if(null == info) {
			return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String editAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null);
		Resource info;
		if(DPUtil.empty(id)) {
			info = new Resource();
		} else {
			info = resourceService.getById(id);
			if(DPUtil.empty(info)) return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String saveAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null);
		Resource persist;
		if(DPUtil.empty(id)) {
			persist = new Resource();
		} else {
			persist = resourceService.getById(id);
			if(DPUtil.empty(persist)) return displayMessage(3001, "信息不存在，请刷新后再试");
		}
		String name = ValidateUtil.filterSimpleString(get("name"), true, 1, 64);
		if(DPUtil.empty(name)) return displayMessage(3002, "名称参数错误");
		persist.setName(name);
		String module = ValidateUtil.filterSimpleString(get("module"), true, 1, 64);
		if(DPUtil.empty(module)) return displayMessage(3003, "模块参数错误");
		persist.setModule(module);
		String controller = ValidateUtil.filterSimpleString(get("controller"), true, 1, 64);
		if(DPUtil.empty(controller)) return displayMessage(3004, "控制器参数错误");
		persist.setController(controller);
		String action = ValidateUtil.filterSimpleString(get("action"), true, 1, 64);
		if(DPUtil.empty(action)) return displayMessage(3005, "方法参数错误");
		persist.setAction(action);
		if(null != resourceService.getByRouter(module, controller, action)) {
			return displayMessage(3006, "对应资源已存在");
		}
		persist.setReferId(ValidateUtil.filterInteger(get("referId"), true, 0, null));
		persist.setSort(ValidateUtil.filterInteger(get("sort"), true, null, null));
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
		Object[] idArray = DPUtil.explode(get("ids"), ",", " ");
		int result = resourceService.delete(idArray);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
}
