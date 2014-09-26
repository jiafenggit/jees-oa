package com.iisquare.jees.oa.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
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
		List<Map<String, Object>> list = resourceService.getList("*", null, null, "sort", page, pageSize);
		assign("total", list.size());
		assign("rows", DPUtil.collectionToArray(list));
		return displayJSON();
	}
}
