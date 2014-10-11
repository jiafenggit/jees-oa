package com.iisquare.jees.oa.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;
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
}
