package cn.ffhh.cloudtest.domain;

import javax.validation.constraints.NotEmpty;

public class RoleUpdate {
	@NotEmpty(message="角色名称不能为空")
	private String roleName;
	@NotEmpty(message="角色描述不能为空")
	private String roleDesc;
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
	@Override
	public String toString() {
		return "RoleUpdate [roleName=" + roleName + ", roleDesc=" + roleDesc + "]";
	}
	
}
