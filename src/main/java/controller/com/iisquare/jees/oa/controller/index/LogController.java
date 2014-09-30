package com.iisquare.jees.oa.controller.index;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServletUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.service.ResourceService;

/**
 * 日志管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class LogController extends PermitController {

	@Autowired
	public ResourceService resourceService;
	
	@SuppressWarnings("unchecked")
	public String listSettingAction () throws Exception {
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500);
		Map<Object, Object> map = resourceService.search(ServletUtil.singleParameterMap(_REQUEST_), page, pageSize);
		map.put("rows", logService.fillSetting((List<Map<String, Object>>) map.get("rows"), null));
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
	
	public String editSettingAction() throws Exception {
		return displayTemplate();
	}
	
	public String saveSettingAction() throws Exception {
		return displayTemplate();
	}
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	public String listAction () throws Exception {
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500);
		Map<Object, Object> map = logService.search(ServletUtil.singleParameterMap(_REQUEST_), page, pageSize);
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
	
	public String showAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null);
		Map<String, Object> info = logService.getById(id, true);
		if(null == info) {
			return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = DPUtil.explode(get("ids"), ",", " ");
		int result = logService.delete(idArray);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
	
	public String truncateAction() throws Exception {
		int result = logService.truncate();
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
}
