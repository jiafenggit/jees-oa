package com.iisquare.jees.oa.controller.index;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServletUtil;
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
		int page = DPUtil.parseInt(get("page"));
		int pageSize = DPUtil.parseInt(get("rows"));
		Map<Object, Object> map = menuService.search(ServletUtil.singleParameterMap(_REQUEST_), page, pageSize);
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
}
