package com.iisquare.jees.oa.domain;

/**
 * 角色与资源关系实体类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class RoleResourceRel {
	private int roleId; // 角色主键
	private int resourceId; // 资源主键

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public RoleResourceRel() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + resourceId;
		result = prime * result + roleId;
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
		RoleResourceRel other = (RoleResourceRel) obj;
		if (resourceId != other.resourceId)
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}

}
