package com.iisquare.jees.oa.domain;

/**
 * 日志设置实体类
 * @author Ouyang<iisquare@163.com>
 *
 */
public class LogSetting {
	private int id; // 资源主键
	private int enable; // 是否启用
	private int referer; // 是否记录来源地址
	private int requestUrl; // 是否记录请求地址
	private int requestParam; // 是否记录请求参数
	private int sessionId; // 是否记录会话ID
	private int sessionValue; // 是否记录会话值
	private int responseView; // 是否记录响应视图
	private int responseData; // 是否记录响应数据
	private int operateId; // 操作者主键
	private long operateTime; // 操作时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getReferer() {
		return referer;
	}

	public void setReferer(int referer) {
		this.referer = referer;
	}

	public int getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(int requestUrl) {
		this.requestUrl = requestUrl;
	}

	public int getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(int requestParam) {
		this.requestParam = requestParam;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getSessionValue() {
		return sessionValue;
	}

	public void setSessionValue(int sessionValue) {
		this.sessionValue = sessionValue;
	}

	public int getResponseView() {
		return responseView;
	}

	public void setResponseView(int responseView) {
		this.responseView = responseView;
	}

	public int getResponseData() {
		return responseData;
	}

	public void setResponseData(int responseData) {
		this.responseData = responseData;
	}

	public int getOperateId() {
		return operateId;
	}

	public void setOperateId(int operateId) {
		this.operateId = operateId;
	}

	public long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(long operateTime) {
		this.operateTime = operateTime;
	}

	public LogSetting() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogSetting other = (LogSetting) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
