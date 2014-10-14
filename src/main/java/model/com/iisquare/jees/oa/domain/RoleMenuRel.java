package com.iisquare.jees.oa.domain;

/**
 * 角色与菜单关系实体类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class RoleMenuRel {
	private int roleId; // 角色主键
	private int menuId; // 菜单主键

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public RoleMenuRel() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + menuId;
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
		RoleMenuRel other = (RoleMenuRel) obj;
		if (menuId != other.menuId)
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}

}
