package com.iisquare.jees.oa.domain;

/**
 * 用户与部门关系实体类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class MemberOrganizeRel {
	private int memberId; // 用户主键
	private int organizeId; // 组织主键
	private int dutyId; // 职务主键

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getOrganizeId() {
		return organizeId;
	}

	public void setOrganizeId(int organizeId) {
		this.organizeId = organizeId;
	}

	public int getDutyId() {
		return dutyId;
	}

	public void setDutyId(int dutyId) {
		this.dutyId = dutyId;
	}

	public MemberOrganizeRel() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + memberId;
		result = prime * result + organizeId;
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
		if (memberId != other.memberId)
			return false;
		if (organizeId != other.organizeId)
			return false;
		return true;
	}

}
