package com.iisquare.jees.oa.controller.index;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServletUtil;
import com.iisquare.jees.oa.domain.Resource;
import com.iisquare.jees.oa.service.ResourceService;

/**
 * 资源管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class ResourceController extends PermitController {
	
	@Autowired
	public ResourceService resourceService;
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	public String listAction () throws Exception {
		int page = DPUtil.parseInt(get("page"));
		int pageSize = DPUtil.parseInt(get("pageSize"));
		Map<Object, Object> map = resourceService.search(ServletUtil.singleParameterMap(_REQUEST_), page, pageSize);
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
	
	public String showAction() throws Exception {
		Object id = get("id");
		Map<String, Object> info = resourceService.getById(id, true);
		if(null == info) {
			return displayInfo("您访问的信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String editAction() throws Exception {
		Object id = get("id");
		Resource info;
		if(DPUtil.empty(id)) {
			info = new Resource();
		} else {
			info = resourceService.getById(id);
			if(DPUtil.empty(info)) return displayInfo("您访问的信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String saveAction() throws Exception {
		Object id = get("id");
		Resource persist;
		if(DPUtil.empty(id)) {
			persist = new Resource();
		} else {
			persist = resourceService.getById(id);
			if(DPUtil.empty(persist)) return displayMessage(3001, "您访问的信息不存在，请刷新后再试");
		}
		String name = DPUtil.trim(get("name"));
		if(DPUtil.empty(name)) return displayMessage(3002, "请输入名称");
		persist.setName(name);
		String module = DPUtil.trim(get("module"));
		if(DPUtil.empty(module)) return displayMessage(3003, "请模块名称");
		persist.setModule(module);
		String controller = DPUtil.trim(get("controller"));
		if(DPUtil.empty(controller)) return displayMessage(3004, "请输入控制器名称");
		persist.setController(controller);
		String action = DPUtil.trim(get("action"));
		if(DPUtil.empty(action)) return displayMessage(3005, "请输入方法名称");
		persist.setAction(action);
		persist.setReferId(DPUtil.parseInt(get("referId")));
		persist.setSort(DPUtil.parseInt(get("sort")));
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
