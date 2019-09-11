package cn.ffhh.cloudtest.domain;

import javax.validation.constraints.NotNull;

public class DepartmentUpdate {
	@NotNull(message="未指定部门ID")
	private Integer id;//部门ID
	private String departmentName;//部门名称
	private Integer parentId;//上一级ID
	private Integer level;//级别
	private Integer listOrder;//排序
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
	@Override
	public String toString() {
		return "DepartmentUpdate [id=" + id + ", departmentName=" + departmentName + ", parentId=" + parentId
				+ ", level=" + level + ", listOrder=" + listOrder + "]";
	}
	
}
