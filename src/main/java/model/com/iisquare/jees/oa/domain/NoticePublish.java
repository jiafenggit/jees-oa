package com.iisquare.jees.oa.domain;

/**
 * 通知公告发布对象实体类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class NoticePublish {
	private int id; // 主键
	private int noticeId; // 通知公告
	private int relId; // 部门、角色、用户
	private int type; // 关联对象类型（-1全部,0阅读用户，1部门，2角色，3用户）
	private int status; // 状态（0拒绝读，1允许读）
	private long createTime; // 阅读时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public int getRelId() {
		return relId;
	}

	public void setRelId(int relId) {
		this.relId = relId;
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public NoticePublish() {}

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
		NoticePublish other = (NoticePublish) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
