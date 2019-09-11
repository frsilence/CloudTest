package cn.ffhh.cloudtest.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.ffhh.cloudtest.Exception.UserException;
import cn.ffhh.cloudtest.dao.IDepartmentDao;
import cn.ffhh.cloudtest.dao.IRoleDao;
import cn.ffhh.cloudtest.dao.IUserDao;
import cn.ffhh.cloudtest.domain.Department;
import cn.ffhh.cloudtest.domain.Role;
import cn.ffhh.cloudtest.domain.User;
import cn.ffhh.cloudtest.domain.UserSave;
import cn.ffhh.cloudtest.domain.UserSearch;
import cn.ffhh.cloudtest.domain.UserUpdate;
import cn.ffhh.cloudtest.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IDepartmentDao departmentDao;
	@Autowired
	private BCryptPasswordEncoder bcryptpw;
	
	@Override
	public List<User> findUsersByPageInfo(UserSearch params) throws UserException {
		try {
			if(params.getUsername()!=null&&params.getUsername()!="") {
				params.setUsername("%"+params.getUsername()+"%");
			}
			if(params.getEndDate()!=null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(params.getEndDate());
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				params.setEndDate(calendar.getTime());
			}
			PageHelper.startPage(params.getPageNum(), params.getPageSize());
			List<User> users = userDao.findBySearchInfo(params);
			System.err.println("users:"+users);
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("获取user数据信息出错");
		}
	}

	@Override
	@Transactional(rollbackFor= {UserException.class,RuntimeException.class})
	public Integer addUser(UserSave userSave) throws UserException {
		//输入信息判断合格
		if(userSave==null) {
			throw new UserException("用户信息为空，无法保存");
		}
		if(userDao.findByAccount(userSave.getAccount())!=null) {
			throw new UserException("用户工号已存在");
		}
		if(userDao.findByEmail(userSave.getEmail())!=null) {
			throw new UserException("邮箱已存在");
		}
		//保存
		//检验是否保存成功
		Integer saveUserId;
		try {
			User user = new User();
			user.setAccount(userSave.getAccount());
			user.setAdduid(0);
			user.setAvatar("...");
			user.setEmail(userSave.getEmail());
			user.setDepartment(departmentDao.findByDepartmentId(userSave.getDepartment()));
			user.setFreeze(userSave.getFreeze());
			user.setPassword(bcryptpw.encode(userSave.getPassword()));
			userDao.save(user);
			saveUserId = user.getId();
			System.err.println(saveUserId);
			if(saveUserId==0 || userDao.findByUserId(user.getId())==null) {
				throw new UserException("保存失败");
			}else {
				for(Integer roleId:userSave.getRoles()) {
					userDao.addRoleToUser(saveUserId, roleId);
				}
				return saveUserId;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("保存失败");
		}
		
	}
	
	@Override
	@Transactional(rollbackFor = {UserException.class,RuntimeException.class})
	public Integer updateUser(UserUpdate userUpdate) throws UserException {
		Integer updateNum = 0;
		try {
			User user  = new User();
			user.setId(userUpdate.getId());
			user.setAccount(userUpdate.getAccount());
			user.setUsername(userUpdate.getUsername());
			if(!"".equals(userUpdate.getPassword().replace(" ", ""))) {
				user.setPassword(bcryptpw.encode(user.getPassword()));
			}
			user.setEmail(userUpdate.getEmail());
			user.setFreeze(userUpdate.getFreeze());
			if(userUpdate.getDepartment()!=null) {
				Department department = departmentDao.findByDepartmentId(userUpdate.getDepartment());
				user.setDepartment(department);
			}
			updateNum = userDao.update(user);
			userDao.clearRoleByUserId(user.getId());
			if(userUpdate.getRoles()!=null) {
				for(Integer roleId:userUpdate.getRoles()) {
					userDao.addRoleToUser(user.getId(), roleId);
				}
			}
			return updateNum;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("用户信息更新失败");
		}
	}
	
	@Override
	@Transactional(rollbackFor = {UserException.class,RuntimeException.class})
	public Integer deleteUser(Set<Integer> userIdSet) throws UserException {
		Integer deleteNum = 0;
		try {
			for(Integer userId:userIdSet) {
				userDao.clearRoleByUserId(userId);
				deleteNum = deleteNum + userDao.delete(userId);
			}
			return deleteNum;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new UserException("删除用户失败");
		}
	}

	/**
	 * 用户登录处理（Spring Security）
	 */
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		User user = userDao.findByAccount(account);
		System.out.println(user);
		List<Role> roles = new ArrayList<Role>();
		if(user!=null) {
			roles = user.getRoles();
		}
		List<SimpleGrantedAuthority> authorities = getAuthorities(roles);
		org.springframework.security.core.userdetails.User securityUser = 
				new org.springframework.security.core.userdetails.User(user.getAccount(),
						user.getPassword(),!user.getFreeze(),true,true,true,authorities);
		return securityUser;
	}

	private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Role role:roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}
	
	@Override
	public List<Role> findOtherRolesByUserId(Integer userId) throws UserException {
		List<Role> roles =  new ArrayList<Role>();
		try {
			roles = roleDao.findOtherRolesByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("获取角色信息失败");
		}
		return roles;
	}
	@Override
	@Transactional(rollbackFor = {UserException.class,RuntimeException.class})
	public void updateRoleToUser(Set<Integer> roleIds, Integer userId) throws UserException{
		try {
			User user = userDao.findByUserId(userId);
			if(user!=null) {
				userDao.clearRoleByUserId(userId);
				for(Integer roleId:roleIds) {
					userDao.addRoleToUser(userId, roleId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("更新用户角色失败");
		}
		
	}

	@Override
	public User findByUserId(Integer userId) throws UserException{
		User user = new User();
		try {
			user = userDao.findByUserId(userId);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("用户信息查询失败");
		}
	}

	@Override
	public List<User> findByRoleId(Integer roleId) throws UserException {
		List<User> users = new ArrayList<>();
		try {
			users = userDao.findByRoleId(roleId);
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("用户信息查询失败");
		}
	}
	
	
	


}
