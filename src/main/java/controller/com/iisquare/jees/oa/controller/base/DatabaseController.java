package com.iisquare.jees.oa.controller.base;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.oa.service.DatabaseService;

/**
 * 数据库管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class DatabaseController extends PermitController {
	
	@Autowired
	public DatabaseService databaseService;
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	private String getBackupPath() {
		String backupPath = settingService.get("database", "backupPath");
		if(DPUtil.empty(backupPath)) backupPath = DPUtil.stringConcat(_WEB_ROOT_, "/META-INF/sql/");
		return backupPath;
	}
	
	public String listAction () throws Exception {
		List<Map<String, Object>> list = databaseService.listBackup(getBackupPath());
		assign("total", list.size());
		assign("rows", DPUtil.collectionToArray(list));
		return displayJSON();
	}
	
	public String backupAction() throws Exception {
		if(databaseService.backup(currentMember,
				settingService.get("database", "binPath"), getBackupPath())) {
			return displayInfo("备份成功", null);
		} else {
			return displayInfo("备份失败", null);
		}
	}
	
	public String revertAction() throws Exception {
		if(databaseService.revert(
				settingService.get("database", "binPath"), getBackupPath(), get("path"))) {
			return displayInfo("还原成功", null);
		} else {
			return displayInfo("还原失败", null);
		}
	}
	
	public String deleteAction() throws Exception {
		if(databaseService.delete(getBackupPath(), get("path"))) {
			return displayInfo("删除成功", null);
		} else {
			return displayInfo("删除失败", null);
		}
	}
}