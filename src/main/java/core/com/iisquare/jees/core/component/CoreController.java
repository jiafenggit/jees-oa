package com.iisquare.jees.core.component;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iisquare.jees.framework.controller.ControllerBase;

@Controller
@Scope("prototype")
public abstract class CoreController extends ControllerBase {
	/**
	 * 返回JSON信息，可根据需要拓展XML等格式
	 * @param status 状态码，根据内部规范自定义
	 * 		小于0 - 正常返回的具体状态码
	 * 		等于0 - 返回正常
	 * 		大于0 - 错误码
	 * @param message 状态说明
	 * @param data 返回数据
	 * @return
	 * @throws Exception
	 */
	public String displayMessage(int status, Object message, Object data) throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>(2);
		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
		return displayJSON(map);
	}
}
