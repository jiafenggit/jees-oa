package com.iisquare.jees.oa.domain;

/**
 * 资源实体类（用于权限控制）
 * @author Ouyang <iisquare@163.com>
 *
 */
public class Resource {
	private Integer id;
	private String name;
	private String content;
	private Integer idCreate; // 创建者
	private Integer idUpdate; // 修改者
	private int sort; // 排序（从高到低）
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Resource() {}

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
		Resource other = (Resource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
