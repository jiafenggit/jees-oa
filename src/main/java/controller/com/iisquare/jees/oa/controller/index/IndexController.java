package com.iisquare.jees.oa.controller.index;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;

/**
 * 管理首页
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class IndexController extends PermitController {
	
	public String indexAction() throws Exception {
		return displayTemplate();
	}
	
}
