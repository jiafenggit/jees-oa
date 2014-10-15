package com.iisquare.jees.oa.controller.index;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.core.util.UrlUtil;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.domain.Icon;
import com.iisquare.jees.oa.service.IconService;

/**
 * 图标管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class IconController extends PermitController {
	
	@Autowired
	public IconService iconService;
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}

	public String listAction () throws Exception {
		List<Map<String, Object>> list = iconService.getList("*", "sort desc", 1, 0);
		for (Map<String, Object> row : list) {
			row.put("fullUrl", UrlUtil.concat(_WEB_URL_, DPUtil.parseString(row.get("url"))));
		}
		list = ServiceUtil.formatRelation(list, 0);
		assign("total", list.size());
		assign("rows", DPUtil.collectionToArray(list));
		return displayJSON();
	}
	
	public String editAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Icon info;
		if(DPUtil.empty(id)) {
			info = new Icon();
			info.setParentId(ValidateUtil.filterInteger(get("parentId"), true, 0, null, null));
		} else {
			info = iconService.getById(id);
			if(DPUtil.empty(info)) return displayInfo("信息不存在，请刷新后再试", null);
		}
		assign("info", info);
		assign("statusMap", iconService.getStatusMap());
		assign("fullUrl", UrlUtil.concat(_WEB_URL_, DPUtil.parseString(info.getUrl())));
		assign("sessionId", request.getSession().getId());
		return displayTemplate();
	}
	
	public String saveAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null, null);
		Icon persist;
		if(DPUtil.empty(id)) {
			persist = new Icon();
		} else {
			persist = iconService.getById(id);
			if(DPUtil.empty(persist)) return displayMessage(3001, "信息不存在，请刷新后再试");
		}
		persist.setParentId(ValidateUtil.filterInteger(get("parentId"), true, 0, null, null));
		String name = ValidateUtil.filterSimpleString(get("name"), true, 1, 64, null);
		if(DPUtil.empty(name)) return displayMessage(3002, "名称参数错误");
		persist.setName(name);
		String url = DPUtil.trim(get("url"));
		if(DPUtil.empty(url)) return displayMessage(3004, "地址参数错误");
		persist.setUrl(url);
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
			result = iconService.insert(persist);
		} else {
			result = iconService.update(persist);
		}
		if(result > 0) {
			return displayMessage(0, url("layout"));
		} else {
			return displayMessage(500, "操作失败");
		}
	}
	
	public String deleteAction() throws Exception {
		Object[] idArray = DPUtil.explode(get("ids"), ",", " ", true);
		int result = iconService.delete(idArray);
		if(-1 == result) return displayInfo("该节点拥有下级节点，不允许删除", null);
		if(result > 0) {
			return displayInfo("操作成功", url("layout"));
		} else {
			return displayInfo("操作失败，请刷新后再试", null);
		}
	}
	
	public String renderCssAction() throws Exception {
		String cssPath = DPUtil.stringConcat(_WEB_ROOT_, "/", "files/work/icon.auto.css");
		File cssFile = new File(cssPath);
		//检查文件
		if(!cssFile.exists()) return displayMessage(3001, "样式文件不存在");
		//检查目录写权限
		if(!cssFile.canWrite()) return displayMessage(3002, "样式文件没有操作权限");
		try {
			OutputStream os = new FileOutputStream(cssFile);
			for (Icon icon : iconService.getList(null, null, null, 1, 0)) {
				StringBuilder sb = new StringBuilder();
				sb.append(".icon-auto");
				sb.append(icon.getId());
				sb.append(" {background:url('");
				sb.append(UrlUtil.concat(_WEB_URL_, icon.getUrl()));
				sb.append("') no-repeat center center;}\r\n");
				os.write(sb.toString().getBytes());
			}
			os.flush();
			os.close();
		} catch (Exception e) {
			return displayMessage(3003, "操作失败");
		}
		return displayMessage(0, "操作成功");
	}
}
