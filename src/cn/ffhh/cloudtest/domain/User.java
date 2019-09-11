package cn.ffhh.cloudtest.domain;

import java.sql.Timestamp;
import java.util.List;

public class User {
	private Integer id;//无意义，主键
	private String account;//账号
	private String avatar;//头像保存路径
	private String email;//邮箱地址
	private String emailCode;//邮箱验证码
	private String password;//密码
	private String username;//用户名
	private Department department;//所属部门
	private List<Role> roles;//角色
	private Timestamp lastLoginTime;//最后登录时间
	private Integer adduid;//添加人ID
	private Boolean freeze;//是否冻结，默认为false
	private String freezeStr;//是否冻结字符串表示
	private Timestamp createTime;//创建时间
	private Timestamp updateTime;//更新时间
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getAdduid() {
		return adduid;
	}
	public void setAdduid(Integer adduid) {
		this.adduid = adduid;
	}
	public Boolean getFreeze() {
		return freeze;
	}
	public void setFreeze(Boolean freeze) {
		this.freeze = freeze;
	}
	public String getFreezeStr() {
		return freeze ? "是":"否";
	}
	public void setFreezeStr(String freezeStr) {
		this.freezeStr = freezeStr;
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
		return "User [id=" + id + ", account=" + account + ", avatar=" + avatar + ", email=" + email + ", emailCode="
				+ emailCode + ", password=" + password + ", username=" + username + ", department=" + department
				+ ", roles=" + roles + ", lastLoginTime=" + lastLoginTime + ", adduid=" + adduid + ", freeze=" + freeze
				+ ", freezeStr=" + freezeStr + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
	
	
}
