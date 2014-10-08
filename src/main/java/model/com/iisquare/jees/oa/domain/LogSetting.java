package com.iisquare.jees.oa.domain;

/**
 * 日志设置实体类
 * @author Ouyang<iisquare@163.com>
 *
 */
public class LogSetting {
	private Integer id; // 资源主键
	private Integer enable; // 是否启用
	private Integer referer; // 是否记录来源地址
	private Integer requestUrl; // 是否记录请求地址
	private Integer requestParam; // 是否记录请求参数
	private Integer sessionId; // 是否记录会话ID
	private Integer sessionValue; // 是否记录会话值
	private Integer responseView; // 是否记录响应视图
	private Integer responseData; // 是否记录响应数据
	private Integer operateId; // 操作者主键
	private Long operateTime; // 操作时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getReferer() {
		return referer;
	}

	public void setReferer(Integer referer) {
		this.referer = referer;
	}

	public Integer getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(Integer requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Integer getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(Integer requestParam) {
		this.requestParam = requestParam;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getSessionValue() {
		return sessionValue;
	}

	public void setSessionValue(Integer sessionValue) {
		this.sessionValue = sessionValue;
	}

	public Integer getResponseView() {
		return responseView;
	}

	public void setResponseView(Integer responseView) {
		this.responseView = responseView;
	}

	public Integer getResponseData() {
		return responseData;
	}

	public void setResponseData(Integer responseData) {
		this.responseData = responseData;
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

	public LogSetting() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
