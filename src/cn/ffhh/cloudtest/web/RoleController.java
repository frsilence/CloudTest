package cn.ffhh.cloudtest.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.ffhh.cloudtest.Exception.RoleException;
import cn.ffhh.cloudtest.domain.ResponseBean;
import cn.ffhh.cloudtest.domain.Role;
import cn.ffhh.cloudtest.domain.RoleSave;
import cn.ffhh.cloudtest.domain.RoleSearch;
import cn.ffhh.cloudtest.domain.RoleUpdate;
import cn.ffhh.cloudtest.service.impl.RoleServiceImpl;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	
	@RequestMapping("/allroles")
	public @ResponseBody Map<String,Object> allRoles(@RequestParam("draw") Integer draw,
			@RequestParam("start") Integer start,@RequestParam("length")Integer pageSize) {
		Map<String, Object> roleMap = new HashMap<String, Object>();
		RoleSearch roleSearch = new RoleSearch();
		roleSearch.setPageNum((start+pageSize)/pageSize);
		roleSearch.setPageSize(pageSize);
		List<Role> roles = new ArrayList<Role>();
		try {
			roles = roleServiceImpl.findAllRole(roleSearch);
		} catch (RoleException e) {
			e.printStackTrace();
		}
		PageInfo<Role> info = new PageInfo<>(roles);
		roleMap.put("draw", draw);
		roleMap.put("recordsTotal", info.getTotal());
		roleMap.put("data", info.getList());
		roleMap.put("recordsFiltered", info.getTotal());
		return roleMap;
	}
	
	@RequestMapping("/addrole")
	public @ResponseBody ResponseBean<Object> addRole(@Validated RoleSave roleSave,BindingResult result) throws RoleException{
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("roleId", roleServiceImpl.addRole(roleSave));
		return new ResponseBean<Object>(data);
	}
	
	//以下未debug
	
	@RequestMapping("/deleterole")
	public @ResponseBody ResponseBean<Object> deleteRole(@RequestParam Set<Integer> roleIds) throws RoleException{
		System.err.println("rid::"+roleIds);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("deleteNum", roleServiceImpl.deleteRole(roleIds));
		return new ResponseBean<Object>(data);
	}
	
	@RequestMapping("/updaterole")
	public @ResponseBody ResponseBean<Object> updateRole(RoleUpdate roleUpdate) throws RoleException{
		Map<String, Object> data = new HashMap<>();
		data.put("updateNum", roleServiceImpl.updateRole(roleUpdate));
		return new ResponseBean<>(data);
	}
	
	@RequestMapping("/roleinformation")
	public @ResponseBody ResponseBean<Object> roleInformation(Integer roleId) throws RoleException{
		Map<String, Object> data = new HashMap<>();
		data.put("roleInfo", roleServiceImpl.findByRoleId(roleId));
		return new ResponseBean<>(data);
	}
	
}
