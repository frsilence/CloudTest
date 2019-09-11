package cn.ffhh.cloudtest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ffhh.cloudtest.Exception.DepartmentException;
import cn.ffhh.cloudtest.dao.IDepartmentDao;
import cn.ffhh.cloudtest.domain.Department;
import cn.ffhh.cloudtest.domain.DepartmentSave;
import cn.ffhh.cloudtest.domain.DepartmentUpdate;
import cn.ffhh.cloudtest.service.IDepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
	private IDepartmentDao departmentDao;
	@Override()
	public List<Department> findAllDepartments() throws DepartmentException {
		List<Department> departments = new ArrayList<Department>();
		try {
			departments = departmentDao.findAllDepartments();
			return departments;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DepartmentException("部门信息查询失败");
		}
	}
	@Override
	public Integer addDepartment(DepartmentSave departmentSave) throws DepartmentException {
		if(departmentDao.findByDepartmentname(departmentSave.getDepartmentName())!=null) {
			throw new DepartmentException("存在相同的部门名称，保存失败");
		}
		int saveId;
		try {
			Department department = new Department();
			department.setDepartmentName(departmentSave.getDepartmentName());
			department.setLevel(departmentSave.getLevel());
			department.setListOrder(departmentSave.getListOrder());
			department.setParentId(departmentSave.getParentId());
			departmentDao.save(department);
			saveId = department.getId();
			if(departmentDao.findByDepartmentId(saveId)==null) {
				throw new DepartmentException("部门信息新增失败");
			}
			return saveId;
		} catch (Exception e) {
			throw new DepartmentException("部门信息新增失败");
		}
	}
	@Override
	public Integer updateDepartment(DepartmentUpdate departmentUpdate) throws DepartmentException {
		return null;
	}
	@Override
	public Integer deleteDepartment(Set<Integer> depIds) throws DepartmentException {
		return null;
	}
	
	
}
