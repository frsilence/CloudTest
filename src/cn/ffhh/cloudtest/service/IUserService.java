package cn.ffhh.cloudtest.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import cn.ffhh.cloudtest.Exception.UserException;
import cn.ffhh.cloudtest.domain.Role;
import cn.ffhh.cloudtest.domain.User;
import cn.ffhh.cloudtest.domain.UserSave;
import cn.ffhh.cloudtest.domain.UserSearch;
import cn.ffhh.cloudtest.domain.UserUpdate;

public interface IUserService extends UserDetailsService{
	/**
	 *     根据搜索信息检索用户信息
	 * @param params
	 * @return
	 */
	public List<User> findUsersByPageInfo(UserSearch params) throws UserException;
	
	/**
	 * 	新增用户
	 * @param user
	 * @throws UserException
	 */
	public Integer addUser(UserSave userSave) throws UserException;
	
	/**
	 * 	更新用户
	 * @param user
	 * @throws UserException
	 */
	public Integer updateUser(UserUpdate userUpdate) throws UserException;
	
	/**
	 * 	删除用户
	 * @param userId
	 * @throws UserException
	 */
	public Integer deleteUser(Set<Integer> userIdSet) throws UserException;
	
	/**
	 * 获取用户未绑定的角色
	 * @param userId
	 * @return
	 * @throws RoleException
	 */
	public List<Role> findOtherRolesByUserId(Integer userId) throws UserException;
	
	/**
	 * 将指定的角色绑定给用户
	 * @param roleIds
	 * @param userIds
	 */
	public void updateRoleToUser(Set<Integer> roleIds,Integer userId) throws UserException;
	
	/**
	 * 查询用户信息
	 * @param userId
	 * @return
	 */
	public User findByUserId(Integer userId) throws UserException;
	
	/**
	 * 根据角色id查找该角色所绑定的用户信息
	 * @param roleId
	 * @return
	 */
	public List<User> findByRoleId(Integer roleId) throws UserException;
}
