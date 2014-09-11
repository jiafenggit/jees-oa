package com.iisquare.jees.oa.domain;

/**
 * 用户与部门关系实体类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class MemberOrganizeRel {
	private Integer memberId;
	private Integer organizeId;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getOrganizeId() {
		return organizeId;
	}

	public void setOrganizeId(Integer organizeId) {
		this.organizeId = organizeId;
	}

	public MemberOrganizeRel() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result
				+ ((organizeId == null) ? 0 : organizeId.hashCode());
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
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (organizeId == null) {
			if (other.organizeId != null)
				return false;
		} else if (!organizeId.equals(other.organizeId))
			return false;
		return true;
	}

}
