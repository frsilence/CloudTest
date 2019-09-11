package cn.ffhh.cloudtest.domain;

import java.sql.Timestamp;

public class Role {
	private Integer id;//角色ID，主键
	private String roleName;//角色名称
	private String roleDesc;//角色描述
	private User addUser;//添加人
	private Timestamp createTime;//创建时间
	private Timestamp updateTime;//更新时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public User getAddUser() {
		return addUser;
	}
	public void setAddUser(User addUser) {
		this.addUser = addUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", addUser=" + addUser
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
	
}
