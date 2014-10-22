package com.iisquare.jees.oa.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.FileUtil;
import com.iisquare.jees.framework.util.OSUtil;
import com.iisquare.jees.oa.domain.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Service
public class DatabaseService extends ServiceBase {
	
	@Autowired
	public ComboPooledDataSource dataSource;

	public DatabaseService() {}
	
	public boolean backup(Member member, String binPath, String backupPath) {
		String filePath = DPUtil.stringConcat(backupPath, System.currentTimeMillis(), "By",
				(null == member || null == member.getId() ? 0 : member.getId()), ".sql");
		DatabaseInfo databaseInfo = new DatabaseInfo(dataSource);
		if("MYSQL".equals(databaseInfo.getType())) {
			return backupMySQL(databaseInfo, binPath, filePath);
		}
		return false;
	}
	
	public boolean backupMySQL(DatabaseInfo databaseInfo, String binPath, String filePath) {
		StringBuilder cmd = new StringBuilder(binPath);
		cmd.append("mysqldump --add-drop-table -h ").append(databaseInfo.getHost())
			.append(" -P ").append(databaseInfo.getPort()).append(" -u").append(databaseInfo.getUsername())
			.append(" -p").append(databaseInfo.getPassword()).append(" ").append(databaseInfo.getDatabase())
			.append(" > ").append(filePath);
        return OSUtil.exec(cmd.toString());
	}
	
	public boolean revert(String binPath, String backupPath, String filePath) {
		if(DPUtil.empty(filePath)) return false;
		filePath = DPUtil.stringConcat(backupPath, filePath);
		DatabaseInfo databaseInfo = new DatabaseInfo(dataSource);
		if("MYSQL".equals(databaseInfo.getType())) {
			return revertMySQL(databaseInfo, binPath, filePath);
		}
		return false;
	}

	public boolean revertMySQL(DatabaseInfo databaseInfo, String binPath, String filePath) {
		StringBuilder cmd = new StringBuilder(binPath);
		cmd.append("mysql -h ").append(databaseInfo.getHost())
			.append(" -P ").append(databaseInfo.getPort()).append(" -u").append(databaseInfo.getUsername())
			.append(" -p").append(databaseInfo.getPassword()).append(" ").append(databaseInfo.getDatabase())
			.append(" < ").append(filePath);
        return OSUtil.exec(cmd.toString());
	}
	
	public boolean delete(String backupPath, String filePath) {
		filePath = DPUtil.stringConcat(backupPath, filePath);
		return FileUtil.delete(filePath);
	}
	
	public List<Map<String, Object>> listBackup(String backupPath) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(0);
		File file = new File(backupPath);
		if(!file.isDirectory()) return list;
		for(File listFile : file.listFiles()) {
			if(listFile.isFile()) {
				Map<String, Object> map = new HashMap<String, Object>(0);
				map.put("fileName", listFile.getName());
				map.put("lastModified", listFile.lastModified());
				list.add(map);
			}
		}
		return list;
	}
}

/**
 * 数据库配置信息
 */
class DatabaseInfo {
	
	private String type; // 数据库类型
	private String host;
	private String port;
	private String username;
	private String password;
	private String database;
	
	public DatabaseInfo() {}
	
	public DatabaseInfo(ComboPooledDataSource dataSource) {
		String url = dataSource.getJdbcUrl(); // JdbcUrl中必须携带端口号
		String regexUrl = "^jdbc\\\\?:(\\w+)\\\\?://([a-zA-Z0-9\\.]+)\\\\?:(\\d+)/([\\w\\-_]+)(\\?.*)?$";
		List<String> matches = DPUtil.getMatcher(regexUrl, url, true);
		this.type = matches.get(1).toUpperCase();
		this.host = matches.get(2);
		this.port = matches.get(3);
		this.database = matches.get(4);
		this.username = dataSource.getUser();
		this.password = dataSource.getPassword();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDatabase() {
		return database;
	}
	
	public void setDatabase(String database) {
		this.database = database;
	}
}
