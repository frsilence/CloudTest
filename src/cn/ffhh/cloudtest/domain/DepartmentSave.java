package cn.ffhh.cloudtest.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DepartmentSave {
	@NotEmpty(message="部门名称不能为空")
	private String departmentName;//部门名称
	@NotNull(message="上级部门未指定")
	private Integer parentId;//上一级ID
	@NotNull(message="级别未指定")
	private Integer level;//级别
	@NotNull(message="排序未指定")
	private Integer listOrder;//排序
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
		return "DepartmentSave [departmentName=" + departmentName + ", parentId=" + parentId + ", level=" + level
				+ ", listOrder=" + listOrder + "]";
	}
	

}
