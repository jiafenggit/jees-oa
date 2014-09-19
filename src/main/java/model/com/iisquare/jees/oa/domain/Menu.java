package com.iisquare.jees.oa.domain;

/**
 * 菜单实体类
 * @author Ouyang<iisquare@163.com>
 *
 */
public class Menu {
	private Integer id; // 主键
	private String name; // 名称
	private Integer parentId; // 父级
	private Integer createId; // 创建者
	private Integer updateId; // 修改者
	private String icon; // 图标CSS类名
	private String url; // 链接地址
	private String target; // 打开方式
	private Integer sort; // 排序（从高到低）
	private Integer status; // 状态（0禁用，1正常）
	private Long createTime; // 添加时间
	private Long updateTime; // 修改时间

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Menu() {}
	
	public String getStatusText() {
		switch(this.status) {
		case 0 : return "禁用";
		case 1 : return "正常";
		default : return "未知";
		}
	}
	
	public String getTargetText() {
		if("_self".equals(this.target)) return "当前页打开";
		if("_blank".equals(this.target)) return "新窗口打开";
		if("_tab".equals(this.target)) return "Tab内容打开";
		return "Tab框架打开";
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
		Menu other = (Menu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
