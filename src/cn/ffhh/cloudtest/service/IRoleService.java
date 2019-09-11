package cn.ffhh.cloudtest.service;

import java.util.List;
import java.util.Set;

import cn.ffhh.cloudtest.Exception.RoleException;
import cn.ffhh.cloudtest.domain.Role;
import cn.ffhh.cloudtest.domain.RoleSave;
import cn.ffhh.cloudtest.domain.RoleSearch;
import cn.ffhh.cloudtest.domain.RoleUpdate;

public interface IRoleService {
	/**
	 * 添加角色信息
	 * @param role
	 * @throws RoleException
	 */
	public Integer addRole(RoleSave roleSave) throws RoleException;
	
	/**
	 *  删除指定角色id的角色信息记录
	 * @param roleIdSet
	 * @throws RoleException
	 */
	public Integer deleteRole(Set<Integer> roleIdSet) throws RoleException;
	
	/**
	 *  更新指定角色信息
	 * @param role
	 * @throws RoleException
	 */
	public Integer updateRole(RoleUpdate roleUpdate) throws RoleException;
	
	/**
	 * 根据搜索条件返回角色信息
	 * @param params
	 * @throws RoleException
	 */
	public List<Role> findAllRole(RoleSearch params) throws RoleException;
	
	/**
	 * 	根据角色Id返回角色信息
	 * @param roleId
	 * @return
	 * @throws RoleException
	 */
	public Role findByRoleId(Integer roleId) throws RoleException;
}
