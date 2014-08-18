package com.iisquare.jees.demo.controller.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iisquare.jees.core.component.CController;
import com.iisquare.jees.demo.domain.Test;
import com.iisquare.jees.demo.service.TestService;

/**
 * 示例程序
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class IndexController extends CController {
	
	@Autowired
	public TestService testService;
	
	/* 当默认首页不存在时，该方法被执行 */
	@RequestMapping(value="/")
	public String indexAction() throws Exception {
		return displayTemplate();
	}
	
	/* FreeMarker视图模板示例 */
	public String templateAction() throws Exception {
		assign("hw", "Hello World!");
		return displayTemplate();
	}
	
	/* 纯文本输出示例 */
	public String textAction() throws Exception {
		return displayText("Hello World!");
	}
	
	/* JSON输出示例 */
	public String jsonAction() throws Exception {
		assign("hw", "Hello World!");
		return displayJSON();
	}
	
	/* 跳转示例 */
	public String redirectAction() throws Exception {
		assign("hw", "Hello World!");
		return redirect("/?a=123");
	}
	
	/* springMVC融合示例 */
	@RequestMapping(value="/mapping")
	public String springMVCAction() throws Exception {
		assign("hw", "Hello springMVC!");
		return displayTemplate("template");
	}
	
	/* 全局参数输出示例 */
	public String paramAction() throws Exception {
		return displayTemplate();
	}
	
	/* 请求参数获取示例 */
	public String injectAction() throws Exception {
		assign("op", get("op"));
		return displayJSON();
	}
	
	/* 数据库操作示例 - 添加 */
	public String modelCAction() throws Exception {
		long time = System.currentTimeMillis();
		Test test = new Test();
		test.setTitle("标题" + time);
		test.setContent("内容");
		test.setStatus(0);
		test.setTimeCreate(time);
		test.setTimeUpdate(time);
		int id = testService.insert(test);
		assign("insertId", id);
		assign("object", test);
		return displayJSON();
	}
	
	/* 数据库操作示例 - 修改 */
	public String modelUAction() throws Exception {
		long time = System.currentTimeMillis();
		int id = I(get("id"));
		Test test = testService.getById(id);
		if(null == test) {
			return displayMessage(500, "对象不存在");
		}
		test.setContent("内容" + time);
		test.setTimeUpdate(time);
		int result = testService.update(test);
		assign("result", result);
		assign("object", test);
		return displayJSON();
	}
	
	/* 数据库操作示例 - 读取 */
	public String modelRAction() throws Exception {
		int page = I(get("page"));
		int pageSize = 15;
		String append = "order by time_update desc";
		int count = testService.getCount(null, null, append);
		List<Test> result = testService.getPage(null, null, append, page, pageSize);
		assign("page", page);
		assign("pageSize", pageSize);
		assign("count", count);
		assign("result", result);
		return displayJSON();
	}
	
	/* 数据库操作示例 - 删除 */
	public String modelDAction() throws Exception {
		int id = I(get("id"));
		int result = testService.deleteByIds(id);
		assign("deleteId", id);
		assign("result", result);
		return displayJSON();
	}
}
