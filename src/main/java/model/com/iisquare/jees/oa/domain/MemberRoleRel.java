package com.iisquare.jees.oa.domain;

/**
 * 用户与角色关系实体类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class MemberRoleRel {
	private Integer idMember;
	private Integer idRole;
	
	public Integer getIdMember() {
		return idMember;
	}

	public void setIdMember(Integer idMember) {
		this.idMember = idMember;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public MemberRoleRel() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idMember == null) ? 0 : idMember.hashCode());
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
		MemberRoleRel other = (MemberRoleRel) obj;
		if (idMember == null) {
			if (other.idMember != null)
				return false;
		} else if (!idMember.equals(other.idMember))
			return false;
		if (idRole == null) {
			if (other.idRole != null)
				return false;
		} else if (!idRole.equals(other.idRole))
			return false;
		return true;
	}

}
