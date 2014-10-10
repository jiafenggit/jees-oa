package com.iisquare.jees.oa.domain;

/**
 * 上传文件记录实体类
 * @author Ouyang<iisquare@163.com>
 *
 */
public class Upload {
	
	private Integer id;
	private String url;
	private String uri;
	private Integer operateId; // 操作者
	private Integer operateIp; // 操作者IP
	private Long operateTime; // 操作时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public Integer getOperateIp() {
		return operateIp;
	}

	public void setOperateIp(Integer operateIp) {
		this.operateIp = operateIp;
	}

	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

	public Upload() {}

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
		Upload other = (Upload) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
