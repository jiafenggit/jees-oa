package com.iisquare.jees.oa.controller.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.core.util.UrlUtil;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.domain.Notice;
import com.iisquare.jees.oa.service.NoticeService;

/**
 * 通知公告管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class NoticeController extends PermitController {
	@Autowired
	public NoticeService noticeService;
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	@SuppressWarnings("unchecked")
	public String listAction () throws Exception {
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500, null);
		Map<Object, Object> map = noticeService.search(parameter, "sort desc", page, pageSize);
		List<Map<String, Object>> rows = (List<Map<String, Object>>) map.get("rows");
		for (Map<String, Object> row : rows) {
			row.put("fullUrl", UrlUtil.concat(webUrl, DPUtil.parseString(row.get("url"))));
		}
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray(rows));
		return displayJSON();
	}
	
	/**
	 * 首页列表
	 */
	@SuppressWarnings("unchecked")
	public String listIndexAction () throws Exception {
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500, null);
		parameter.put("status", new Object[]{1});
		Map<Object, Object> map = noticeService.search(parameter, "sort desc", page, pageSize);
		List<Map<String, Object>> rows = (List<Map<String, Object>>) map.get("rows");
		for (Map<String, Object> row : rows) {
			row.put("fullUrl", UrlUtil.concat(webUrl, DPUtil.parseString(row.get("url"))));
		}
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray(rows));
		return displayJSON();
	}
	
	/**
	 * 首页阅读
	 */
	public String readAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Map<String, Object> info = noticeService.getById(id, true);
		if(null == info || !DPUtil.equals(info.get("status"), 1)) {
			return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String showAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Map<String, Object> info = noticeService.getById(id, true);
		if(null == info) {
			return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		return displayTemplate();
	}
	
	public String editAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Notice info;
		if(DPUtil.empty(id)) {
			info = new Notice();
			info.setSort(System.currentTimeMillis());
		} else {
			info = noticeService.getById(id);
			if(DPUtil.empty(info)) return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		assign("statusMap", noticeService.getStatusMap(false));
		return displayTemplate();
	}
	
	public String saveAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Notice persist;
		if(DPUtil.empty(id)) {
			persist = new Notice();
		} else {
			persist = noticeService.getById(id);
			if(DPUtil.empty(persist)) return displayMessage(3001, "信息不存在，请刷新后再试", null);
		}
		String typeId = get("typeId");
		if(ValidateUtil.isNull(typeId, true)) return displayMessage(3003, "请选择所属类型", null);
		persist.setTypeId(ValidateUtil.filterInteger(typeId, true, 1, null, 0));
		String title = ValidateUtil.filterSimpleString(get("title"), true, 1, 64, null);
		if(DPUtil.empty(title)) return displayMessage(3002, "标题参数错误", null);
		persist.setTitle(title);
		String content = get("content");
		persist.setContent(content);
		persist.setSort(ValidateUtil.filterLong(get("sort"), true, null, null, null));
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
			result = noticeService.insert(persist);
		} else {
			result = noticeService.update(persist);
		}
		if(result > 0) {
			return displayMessage(0, "操作成功", null);
		} else {
			return displayMessage(500, "操作失败", null);
		}
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = getArray("ids");
		int result = noticeService.delete(idArray);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
}
