package cn.ffhh.cloudtest.domain;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class UserUpdate {
	@NotNull(message="id不能为空")
	private Integer id;
	private String account;
	private String username;
	private String password;
	@Email(message="不是标准的邮箱地址")
	private String email;
	private Integer department;
	private Boolean freeze;
	private List<Integer> roles;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
		return "UserUpdate [id=" + id + ", account=" + account + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", department=" + department + ", freeze=" + freeze + ", roles=" + roles + "]";
	}
	
}
