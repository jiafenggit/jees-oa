package com.iisquare.jees.oa.domain;

/**
 * 角色与资源关系实体类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class RoleResourceRel {
	private Integer idRole;
	private Integer idResource;

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public Integer getIdResource() {
		return idResource;
	}

	public void setIdResource(Integer idResource) {
		this.idResource = idResource;
	}

	public RoleResourceRel() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idResource == null) ? 0 : idResource.hashCode());
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
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
		if (idResource == null) {
			if (other.idResource != null)
				return false;
		} else if (!idResource.equals(other.idResource))
			return false;
		if (idRole == null) {
			if (other.idRole != null)
				return false;
		} else if (!idRole.equals(other.idRole))
			return false;
		return true;
	}

}
