package com.iisquare.jees.oa.controller.index;

import java.util.Collection;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServletUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.domain.Setting;

/**
 * 配置管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class SettingController extends PermitController {
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	public String listAction () throws Exception {
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500);
		Map<Object, Object> map = settingService.search(ServletUtil.singleParameterMap(_REQUEST_), "operate_time desc", page, pageSize);
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
	
	public String editAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null);
		Setting info;
		if(DPUtil.empty(id)) {
			info = new Setting();
		} else {
			info = settingService.getById(id);
			if(DPUtil.empty(info)) return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String saveAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null);
		Setting persist;
		if(DPUtil.empty(id)) {
			persist = new Setting();
		} else {
			persist = settingService.getById(id);
			if(DPUtil.empty(persist)) return displayMessage(3001, "信息不存在，请刷新后再试");
		}
		String name = ValidateUtil.filterSimpleString(get("name"), true, 1, 64);
		if(DPUtil.empty(name)) return displayMessage(3002, "名称参数错误");
		persist.setName(name);
		String type = ValidateUtil.filterSimpleString(get("type"), true, 1, 64);
		if(DPUtil.empty(type)) return displayMessage(3003, "类型参数错误");
		persist.setType(type);
		persist.setContent(DPUtil.trim(get("content")));
		persist.setRemark(DPUtil.parseString(get("remark")));
		persist.setOperateId(currentMember.getId());
		persist.setOperateIp(ServletUtil.getRemoteAddr(_REQUEST_));
		long time = System.currentTimeMillis();
		persist.setOperateTime(time);
		int result;
		if(DPUtil.empty(persist.getId())) {
			result = settingService.insert(persist);
		} else {
			result = settingService.update(persist);
		}
		if(result > 0) {
			return displayMessage(0, url("layout"));
		} else {
			return displayMessage(500, "操作失败");
		}
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = DPUtil.explode(get("ids"), ",", " ");
		int result = settingService.delete(idArray);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
}
