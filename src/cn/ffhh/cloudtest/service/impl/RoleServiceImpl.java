package cn.ffhh.cloudtest.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.ffhh.cloudtest.Exception.RoleException;
import cn.ffhh.cloudtest.dao.IRoleDao;
import cn.ffhh.cloudtest.dao.IUserDao;
import cn.ffhh.cloudtest.domain.Role;
import cn.ffhh.cloudtest.domain.RoleSave;
import cn.ffhh.cloudtest.domain.RoleSearch;
import cn.ffhh.cloudtest.domain.RoleUpdate;
import cn.ffhh.cloudtest.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional(rollbackFor = {RoleException.class,RuntimeException.class})
	public Integer addRole(RoleSave roleSave) throws RoleException {
		Integer saveId = null;
		try {
			Role role = new Role();
			role.setRoleName(roleSave.getRoleName());
			role.setRoleDesc(roleSave.getRoleDesc());
			//role.setAddUser(addUser);
			roleDao.save(role);
			saveId = role.getId();
			if(saveId==0) {
				throw new RoleException("角色添加失败");
			}
			return saveId;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleException("添加角色失败");
		}
		
	}

	@Override
	@Transactional(rollbackFor = {RoleException.class,RuntimeException.class})
	public Integer deleteRole(Set<Integer> roleIds) throws RoleException {
		Integer deleteNum = 0;
		try {
			for(Integer roleId:roleIds) {
				if(userDao.findByRoleId(roleId).size()==0) {
					deleteNum = deleteNum + roleDao.delete(roleId);
				}
			}
			return deleteNum;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleException("角色删除失败");
		}
		
	}

	@Override
	public Integer updateRole(RoleUpdate roleUpdate) throws RoleException {
		Integer saveNum = 0;
		try {
			Role role = new Role();
			role.setRoleName(roleUpdate.getRoleName());
			role.setRoleDesc(roleUpdate.getRoleDesc());
			saveNum = roleDao.update(role);
			return saveNum;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleException("角色信息更新失败");
		}
		
	}

	@Override
	public List<Role> findAllRole(RoleSearch params) throws RoleException {
		List<Role> roles = new ArrayList<Role>();
		try {
			if(params.getEndTime()!=null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(params.getEndTime());
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				params.setEndTime(calendar.getTime());
			}
			PageHelper.startPage(params.getPageNum(), params.getPageSize());
			roles = roleDao.findAllRolesBySearchParam(params);
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleException("角色查询失败");
		}
		
	}

	@Override
	public Role findByRoleId(Integer roleId) throws RoleException {
		Role role = new Role();
		try {
			role = roleDao.findByRoleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	
	
	
	

}

