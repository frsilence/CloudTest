package cn.ffhh.cloudtest.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ffhh.cloudtest.Exception.DepartmentException;
import cn.ffhh.cloudtest.domain.DepartmentSave;
import cn.ffhh.cloudtest.domain.DepartmentUpdate;
import cn.ffhh.cloudtest.domain.ResponseBean;
import cn.ffhh.cloudtest.service.IDepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private IDepartmentService departmentService;
	
	@RequestMapping("/alldepartment")
	public @ResponseBody ResponseBean<Object> allDepartments() throws DepartmentException{
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("departments", departmentService.findAllDepartments());
		return new ResponseBean<>(data);
	}
	
	@RequestMapping("/adddepartment")
	public @ResponseBody ResponseBean<Object> addDepartment(DepartmentSave departmentSave){
		return new ResponseBean<>();
	}
	
	@RequestMapping("/updatedepartment")
	public @ResponseBody ResponseBean<Object> updatedepartment(DepartmentUpdate departmentUpdate){
		return new ResponseBean<>();
	}
	
	@RequestMapping("/deletedepartment")
	public @ResponseBody ResponseBean<Object> deletedepartment(Set<Integer> depIds){
		return new ResponseBean<>();	
	}
	
	@RequestMapping("/departmentinfo")
	public @ResponseBody ResponseBean<Object> departmentinfo(Integer depId){
		return new ResponseBean<>();
	}
}
