package cn.ffhh.cloudtest.domain;

import java.util.Date;

public class UserSearch {
	private String account;
	private String email;
	private String username;
	private Integer depaInteger;
	private Date startDate;
	private Date endDate;
	private Integer roleId;
	private Boolean freeze;
	private Integer pageNum;
	private Integer pageSize;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getDepaInteger() {
		return depaInteger;
	}
	public void setDepaInteger(Integer depaInteger) {
		this.depaInteger = depaInteger;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Boolean getFreeze() {
		return freeze;
	}
	public void setFreeze(Boolean freeze) {
		this.freeze = freeze;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
