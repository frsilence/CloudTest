package cn.ffhh.cloudtest.domain;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UserSave {
	@NotEmpty(message="工号不能为空")
	private String account;
	@NotEmpty(message="用户名不能为空")
	private String username;
	@NotEmpty(message="密码不能为空")
	private String password;
	@NotEmpty(message="邮箱不能为空")
	@Email(message="不是标准的邮箱地址")
	private String email;
	@NotNull(message="部门不能为空")
	private Integer department;
	@NotNull(message="激活状态不能为空")
	private Boolean freeze;
	@NotNull(message="角色不能为空")
	private List<Integer> roles;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getFreeze() {
		return freeze;
	}
	public void setFreeze(Boolean freeze) {
		this.freeze = freeze;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public List<Integer> getRoles() {
		return roles;
	}
	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserUpdate [account=" + account + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", department=" + department + ", freeze=" + freeze + ", roles=" + roles + "]";
	}
	
}
