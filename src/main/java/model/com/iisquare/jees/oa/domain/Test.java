package com.iisquare.jees.oa.domain;

public class Test {
	private Integer id; // 主键
	private String title; // 标题
	private String content; // 内容
	private Integer status; // 状态
	private Long timeCreate; // 添加时间
	private Long timeUpdate; // 修改时间
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Long timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Long getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(Long timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	public Test() {}

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
		Test other = (Test) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
