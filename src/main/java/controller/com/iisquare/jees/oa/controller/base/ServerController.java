package com.iisquare.jees.oa.controller.base;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.core.component.PermitController;

/**
 * 服务器信息管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class ServerController extends PermitController {
	
	public String infoAction() throws Exception {
		return displayTemplate();
	}
	
}
