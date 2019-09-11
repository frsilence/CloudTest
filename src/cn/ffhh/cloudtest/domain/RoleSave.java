package cn.ffhh.cloudtest.domain;

import javax.validation.constraints.NotBlank;

public class RoleSave {
	@NotBlank(message="角色名称不能为空")
	private String roleName;//角色名称
	
	@NotBlank(message="角色描述不能为空")
	private String roleDesc;//角色描述
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
		return "RoleSave [roleName=" + roleName + ", roleDesc=" + roleDesc + "]";
	}
	
	
}
