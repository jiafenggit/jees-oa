package com.iisquare.jees.oa.domain;

/**
 * 组织实体类
 * @author Ouyang<iisquare@163.com>
 *
 */
public class Organize {
	private Integer id; // 主键
	private String name; // 名称
	private Integer idParent; // 父级
	private Integer idCreate; // 创建者
	private Integer idUpdate; // 修改者
	private int sort; // 排序（从高到低）
	private int status; // 状态（-1删除，1正常）
	private long timeCreate; // 添加时间
	private long timeUpdate; // 修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdParent() {
		return idParent;
	}

	public void setIdParent(Integer idParent) {
		this.idParent = idParent;
	}

	public Integer getIdCreate() {
		return idCreate;
	}

	public void setIdCreate(Integer idCreate) {
		this.idCreate = idCreate;
	}

	public Integer getIdUpdate() {
		return idUpdate;
	}

	public void setIdUpdate(Integer idUpdate) {
		this.idUpdate = idUpdate;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
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

	public long getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(long timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	public Organize() {}
	
	public String getStatusText() {
		switch(this.status) {
		case -1 : return "已删除";
		case 1 : return "正常";
		default : return "未知";
		}
	}

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
		Organize other = (Organize) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
