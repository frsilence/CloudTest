package cn.ffhh.cloudtest.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import cn.ffhh.cloudtest.Exception.UserException;
import cn.ffhh.cloudtest.check.IdCheck;
import cn.ffhh.cloudtest.check.UserCheck;
import cn.ffhh.cloudtest.domain.Department;
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
@RequestMapping("/user_vali")
public class UserController_vali {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IRoleService roleService;
	
	@InitBinder
	protected void InitBinder(WebDataBinder binder) {
		//binder.setValidator(new UserCheck());
		binder.replaceValidators(new UserCheck(),new IdCheck());
	}
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
	
	@RequestMapping("/userdelete")
	public @ResponseBody ResponseBodyFormat deleteUser(@RequestParam("uid") Set<Integer> uid,BindingResult result) {
		System.err.println("uid:"+uid);
		System.err.println(result.getAllErrors());
		ResponseBodyFormat responseBodyFormat = new ResponseBodyFormat();
		Map<String, Object> data = new HashMap<String, Object>();
		Integer deleteNum;
		try {
			deleteNum = userService.deleteUser(uid);
			data.put("deleteNum", deleteNum);
			responseBodyFormat.setStatus("success");
			responseBodyFormat.setMsg("用户信息删除成功");
			responseBodyFormat.setData(data);
		} catch (UserException e) {
			e.printStackTrace();
			responseBodyFormat.setStatus("error");
			responseBodyFormat.setMsg(e.getMessage());
		}
		return responseBodyFormat;
	}
	
	/**
	 * 	更新用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/userupdate")
	public @ResponseBody ResponseBodyFormat updateUser(@RequestBody @Validated UserUpdate userUpdate,BindingResult result) {
		System.err.println(userUpdate);
		System.err.println("error:"+result.getErrorCount());
		System.err.println("error:"+result.getAllErrors());
		for(ObjectError error:result.getAllErrors()) {
			System.err.println(error.getCode()+"::"+error.getDefaultMessage()+"::"+error.getObjectName()+"::"+error.getCodes());
		}
		User user  = new User();
		user.setId(userUpdate.getId());
		user.setAccount(userUpdate.getAccount());
		user.setUsername(userUpdate.getUsername());
		if(userUpdate!=null&&!"".equals(userUpdate.getPassword().replace(" ", ""))) {
			user.setPassword(userUpdate.getPassword());
		}
		user.setEmail(userUpdate.getEmail());
		user.setFreeze(userUpdate.getFreeze());
		ResponseBodyFormat responseBodyFormat = new ResponseBodyFormat();
		Map<String, Object> data = new HashMap<String, Object>();
		Integer updateNum;
		try {
			updateNum = userService.updateUser(userUpdate);
			data.put("updateNum", updateNum);
			responseBodyFormat.setStatus("success");
			responseBodyFormat.setMsg("用户信息更新成功");
			responseBodyFormat.setData(data);
		} catch (UserException e) {
			e.printStackTrace();
			responseBodyFormat.setStatus("error");
			responseBodyFormat.setMsg(e.getMessage());
		}
		return responseBodyFormat;
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/adduser")
	public @ResponseBody ResponseBodyFormat addUser(User user) {
		ResponseBodyFormat responseBodyFormat = new ResponseBodyFormat();
		try {
			UserSave userSave =  new UserSave();
			userService.addUser(userSave);
			responseBodyFormat.setStatus("success");
			responseBodyFormat.setMsg("新增用户成功");
		} catch (UserException e) {
			e.printStackTrace();
			responseBodyFormat.setStatus("error");
			responseBodyFormat.setMsg(e.getMessage());
		}
		return responseBodyFormat;
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
	 */
	@RequestMapping("userinformation")
	public @ResponseBody ResponseBodyFormat userinformation(Integer userId) {
		ResponseBodyFormat responseBodyFormat = new ResponseBodyFormat();
		Map<String, Object> data = new HashMap<String,Object>();
		User user = new User();
		try {
			user = userService.findByUserId(userId);
			data.put("user", user);
			responseBodyFormat.setStatus("success");
			responseBodyFormat.setMsg("查询用户信息成功");
			responseBodyFormat.setData(data);
		} catch (UserException e) {
			e.printStackTrace();
			responseBodyFormat.setStatus("error");
			responseBodyFormat.setMsg(e.getMessage());
		}
		return responseBodyFormat;
	}
	
	/**
	 * 	查找并返回全部角色和部门信息
	 * @param userId
	 * @return
	 */
	@RequestMapping("updateinformation")
	public @ResponseBody ResponseBodyFormat updateinformation() {
		ResponseBodyFormat responseBodyFormat = new ResponseBodyFormat();
		Map<String, Object> data = new HashMap<String,Object>();
		List<Department> departments = new ArrayList<Department>();
		List<Role> roles = new ArrayList<Role>();
		try {
			departments = departmentService.findAllDepartments();
			roles = roleService.findAllRole(new RoleSearch());
			data.put("departments", departments);
			data.put("roles", roles);
			responseBodyFormat.setStatus("success");
			responseBodyFormat.setMsg("查询部门和角色信息成功");
			responseBodyFormat.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			responseBodyFormat.setStatus("error");
			responseBodyFormat.setMsg(e.getMessage());
		}
		return responseBodyFormat;
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
