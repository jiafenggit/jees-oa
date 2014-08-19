package com.iisquare.jees.oa.domain;

/**
 * 通知公告发布对象实体类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class NoticePublish {
	private Integer id; // 主键
	private Integer idNotice; // 通知公告
	private Integer idRel; // 部门、角色、用户
	private int type; // 关联对象类型（0阅读用户，1部门，2角色，3用户）
	private int status; // 状态（0拒绝读，1允许读）
	private long timeCreate; // 阅读时间
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdNotice() {
		return idNotice;
	}

	public void setIdNotice(Integer idNotice) {
		this.idNotice = idNotice;
	}

	public Integer getIdRel() {
		return idRel;
	}

	public void setIdRel(Integer idRel) {
		this.idRel = idRel;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(long timeCreate) {
		this.timeCreate = timeCreate;
	}

	public NoticePublish() {}

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
		NoticePublish other = (NoticePublish) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
