package cn.ffhh.cloudtest.service;

import java.util.List;
import java.util.Set;

import cn.ffhh.cloudtest.Exception.DepartmentException;
import cn.ffhh.cloudtest.domain.Department;
import cn.ffhh.cloudtest.domain.DepartmentSave;
import cn.ffhh.cloudtest.domain.DepartmentUpdate;

public interface IDepartmentService {
	/**
	 * 查询返回所有部门信息
	 * @return
	 */
	public List<Department> findAllDepartments() throws DepartmentException;
	
	public Integer addDepartment(DepartmentSave departmentSave) throws DepartmentException;
	
	public Integer updateDepartment(DepartmentUpdate departmentUpdate) throws DepartmentException;
	
	public Integer deleteDepartment(Set<Integer> depIds) throws DepartmentException;
}
