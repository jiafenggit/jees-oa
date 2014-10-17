package com.iisquare.jees.oa.controller.index;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.domain.LogSetting;
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
	
	public String editSettingAction() throws Exception {
		return displayTemplate();
	}
	
	public String saveSettingAction() throws Exception {
		LogSetting persist = new LogSetting();
		persist.setId(ValidateUtil.filterInteger(get("id"), true, 0, null, null));
		persist.setEnable(ValidateUtil.filterInteger(get("enable"), true, 0, 1, null));
		persist.setReferer(ValidateUtil.filterInteger(get("referer"), true, 0, 1, null));
		persist.setRequestUrl(ValidateUtil.filterInteger(get("request_url"), true, 0, 1, null));
		persist.setRequestParam(ValidateUtil.filterInteger(get("request_param"), true, 0, 1, null));
		persist.setSessionId(ValidateUtil.filterInteger(get("session_id"), true, 0, 1, null));
		persist.setSessionValue(ValidateUtil.filterInteger(get("session_value"), true, 0, 1, null));
		persist.setResponseView(ValidateUtil.filterInteger(get("response_view"), true, 0, 1, null));
		persist.setResponseData(ValidateUtil.filterInteger(get("response_data"), true, 0, 1, null));
		long time = System.currentTimeMillis();
		persist.setOperateId(currentMember.getId());
		persist.setOperateTime(time);
		int result = logService.saveSetting(persist);
		if(result > 0) {
			return displayMessage(0, "操作成功");
		} else {
			return displayMessage(500, "操作失败");
		}
	}
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	public String listAction () throws Exception {
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500, null);
		Map<Object, Object> map = logService.search(parameterMap, "operate_time desc", page, pageSize);
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
	
	public String showAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Map<String, Object> info = logService.getById(id, true);
		if(null == info) {
			return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = DPUtil.explode(get("ids"), ",", " ", true);
		int result = logService.delete(idArray);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
	
	public String truncateAction() throws Exception {
		logService.truncate();
		return displayInfo("操作已执行", url("layout"));
	}
}
