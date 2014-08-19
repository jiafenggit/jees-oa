package com.iisquare.jees.oa.domain;

/**
 * 用户与部门关系实体类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class MemberOrganizeRel {
	private Integer idMember;
	private Integer idOrganize;

	public Integer getIdMember() {
		return idMember;
	}

	public void setIdMember(Integer idMember) {
		this.idMember = idMember;
	}

	public Integer getIdOrganize() {
		return idOrganize;
	}

	public void setIdOrganize(Integer idOrganize) {
		this.idOrganize = idOrganize;
	}

	public MemberOrganizeRel() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idMember == null) ? 0 : idMember.hashCode());
		result = prime * result
				+ ((idOrganize == null) ? 0 : idOrganize.hashCode());
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
		MemberOrganizeRel other = (MemberOrganizeRel) obj;
		if (idMember == null) {
			if (other.idMember != null)
				return false;
		} else if (!idMember.equals(other.idMember))
			return false;
		if (idOrganize == null) {
			if (other.idOrganize != null)
				return false;
		} else if (!idOrganize.equals(other.idOrganize))
			return false;
		return true;
	}

}
