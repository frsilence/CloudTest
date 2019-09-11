package cn.ffhh.cloudtest.domain;

import java.sql.Timestamp;

public class Department {
	private Integer id;//部门ID
	private String departmentName;//部门名称
	private Integer parentId;//上一级ID
	private Integer level;//级别
	private Integer listOrder;//排序
	private User addUser;//添加人
	private Timestamp createTime;//创建时间
	private Timestamp updateTime;//更新时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
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
		return "Department [id=" + id + ", departmentName=" + departmentName + ", parentId=" + parentId + ", level="
				+ level + ", listOrder=" + listOrder + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	public String toDetailString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + ", parentId=" + parentId + ", level="
				+ level + ", listOrder=" + listOrder + ", addUser=" + addUser.getId() + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}
