package com.iisquare.jees.oa.controller.base;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;

/**
 * 首页控制器
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class IndexController extends PermitController {
	
	/**
	 * 任务面板
	 */
	public String taskAction() throws Exception {
		return displayTemplate();
	}
	
}
