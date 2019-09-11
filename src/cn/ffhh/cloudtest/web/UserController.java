package cn.ffhh.cloudtest.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.ffhh.cloudtest.Exception.DepartmentException;
import cn.ffhh.cloudtest.Exception.RoleException;
import cn.ffhh.cloudtest.Exception.UserException;
import cn.ffhh.cloudtest.domain.Department;
import cn.ffhh.cloudtest.domain.ResponseBean;
import cn.ffhh.cloudtest.domain.ResponseBodyFormat;
import cn.ffhh.cloudtest.domain.Role;
import cn.ffhh.cloudtest.domain.RoleSearch;
import cn.ffhh.cloudtest.domain.User;
import cn.ffhh.cloudtest.domain.UserSave;
import cn.ffhh.cloudtest.domain.UserSearch;
import cn.ffhh.cloudtest.domain.UserUpdate;
import cn.ffhh.cloudtest.service.IDepartmentService;
import cn.ffhh.cloudtest.service.IRoleService;
import cn.ffhh.cloudtest.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IRoleService roleService;
	
	/**
	 * 	返回用户管理页面
	 * @return
	 */
	@RequestMapping("/user_list")
	public String user_list() {
		return "/user/user_list";
	}
	
	/**
	 * 	返回用户编辑页面
	 * @return
	 */
	@RequestMapping("/user_edit")
	public String user_edit() {
		return "/user/user_edit";
	}
	
	/**
	 * 根据页面条件，返回检索并分页后的数据
	 * @param draw
	 * @param start
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/allusers")
	public @ResponseBody Map<String,Object> allUsers(@RequestParam("draw") Integer draw,
			@RequestParam("start") Integer start,@RequestParam("length")Integer pageSize) {
		
		List<User> users = new ArrayList<User>();
		UserSearch params = new UserSearch();
		params.setPageNum((start+pageSize)/pageSize);
		params.setPageSize(pageSize);
		try {
			users = userService.findUsersByPageInfo(params);
		} catch (UserException e) {
			e.printStackTrace();
		}
		PageInfo<User> info = new PageInfo<>(users);
		Map<String, Object> usersMap = new HashMap<String, Object>();
		usersMap.put("draw", draw);
		usersMap.put("recordsTotal", info.getTotal());
		usersMap.put("data", info.getList());
		usersMap.put("recordsFiltered", info.getTotal());
		return usersMap;
	}
	
	/**
	 * 用户信息删除
	 * @param uid
	 * @return
	 * @throws UserException
	 */
	@RequestMapping("/userdelete")
	public @ResponseBody ResponseBean<Object> deleteUser(@RequestParam("uid") Set<Integer> uid) throws UserException {
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("deleteNum", userService.deleteUser(uid));
		return new ResponseBean<Object>(data);
	}
	
	/**
	 * 	更新用户
	 * @param user
	 * @return
	 * @throws UserException 
	 */
	@RequestMapping("/userupdate")
	public @ResponseBody ResponseBean<Object> updateUser(@RequestBody @Validated UserUpdate userUpdate,BindingResult result) throws UserException {
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("updateNum", userService.updateUser(userUpdate));
		return new ResponseBean<Object>(data);
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @throws UserException 
	 */
	@RequestMapping("/adduser")
	public @ResponseBody ResponseBean<Object> addUser(@Validated UserSave userSave,BindingResult result) throws UserException {
		System.err.println(userSave);
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("userId", userService.addUser(userSave));
		return new ResponseBean<Object>(data);
	}
	
	/**
	 * 	获取用户可用角色
	 * @param uid
	 * @return
	 */
	@RequestMapping("/userotherrole")
	public @ResponseBody ResponseBodyFormat getUserOtherRole(Integer userId) {
		ResponseBodyFormat responseBodyFormat = new ResponseBodyFormat();
		Map<String, Object> data = new HashMap<String,Object>();
		List<Role> roles = new ArrayList<>();
		try {
			roles = userService.findOtherRolesByUserId(userId);
			data.put("roles", roles);
			responseBodyFormat.setStatus("success");
			responseBodyFormat.setMsg("获取用户可用角色成功");
			responseBodyFormat.setData(data);
		} catch (UserException e) {
			e.printStackTrace();
			responseBodyFormat.setStatus("error");
			responseBodyFormat.setMsg(e.getMessage());
		}
		return responseBodyFormat;
	}
	
	/**
	 * 查找并返回用户信息
	 * @param userId
	 * @return
	 * @throws UserException 
	 */
	@RequestMapping("userinformation")
	public @ResponseBody ResponseBean<Object> userinformation(@NotNull(message="uid不能为空") Integer userId) throws UserException {
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("user", userService.findByUserId(userId));
		return new ResponseBean<Object>(data);
	}
	
	/**
	 * 	查找并返回全部角色和部门信息
	 * @param userId
	 * @return
	 * @throws DepartmentException 
	 * @throws RoleException 
	 */
	@RequestMapping("updateinformation")
	public @ResponseBody ResponseBean<Object> updateinformation() throws DepartmentException, RoleException {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Department> departments = new ArrayList<Department>();
		List<Role> roles = new ArrayList<Role>();
		departments = departmentService.findAllDepartments();
		roles = roleService.findAllRole(new RoleSearch());
		data.put("departments", departments);
		data.put("roles", roles);
		return new ResponseBean<Object>(data);
	}
	
	@RequestMapping("/session")
	public @ResponseBody String getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = "";
		Authentication authentication = (Authentication) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Principal principal = (Principal) authentication.getPrincipal();
		username = principal.getName();
		return username;
	}
}
