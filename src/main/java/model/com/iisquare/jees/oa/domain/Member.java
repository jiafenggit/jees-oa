package com.iisquare.jees.oa.domain;

/**
 * 用户实体类
 * @author Ouyang<iisquare@163.com>
 *
 */
public class Member {
	private Integer id; // 主键
	private String serial; // 编号
	private String name; // 昵称
	private Integer idCreate; // 创建者
	private Integer idUpdate; // 修改者
	private String password; // 密码
	private String salt; // 盐
	private int sort; // 排序（从高到低）
	private int status; // 状态（-1删除，0禁用，1正常）
	private long timeCreate; // 注册时间
	private long timeUpdate; // 修改时间
	private long timeActive; // 最后活动时间
	private String ipCreate; // 注册IP
	private String ipActive; // 最后活动IP

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public long getTimeActive() {
		return timeActive;
	}

	public void setTimeActive(long timeActive) {
		this.timeActive = timeActive;
	}

	public String getIpCreate() {
		return ipCreate;
	}

	public void setIpCreate(String ipCreate) {
		this.ipCreate = ipCreate;
	}

	public String getIpActive() {
		return ipActive;
	}

	public void setIpActive(String ipActive) {
		this.ipActive = ipActive;
	}

	public Member() {}
	
	public String getStatusText() {
		switch(this.status) {
		case -1 : return "已删除";
		case 0 : return "禁用";
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
		Member other = (Member) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
