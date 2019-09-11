package cn.ffhh.cloudtest.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.ffhh.cloudtest.domain.Role;
import cn.ffhh.cloudtest.domain.RoleSearch;
public interface IRoleDao {
	/**
	 *  add role
	 * @param role
	 * @return
	 */
	@Insert("insert into role (roleName,roleDesc,adduid)"
			+ " value(#{roleName},#{roleDesc},#{addUser.id})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	public Integer save(Role role);
	
	/**
	 * 	delete role
	 * @param roleId
	 * @return
	 */
	@Delete("delete from role where id = #{roleId}")
	public Integer delete(Integer roleId);
	
	/**
	 * update role
	 * @param role
	 * @return
	 */
	@Update({"<script>",
		"update role",
		"<set>",
		"<if test='roleName!=null'> roleName=#{roleName}, </if>",
		"<if test='roleDesc!=null'>  roleDesc=#{roleDesc}, </if>",
		"<if test='addUser!=null'>  adduid=#{addUser.id}, </if>",
		"</set>",
		"where id = #{id} </script>"})
	public Integer update(Role role);
	
	/**
	 * findByRoleId
	 * @param roleId
	 * @return
	 */
	@Results(id="roleMap",value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="adduid",property="addUser",
			one=@One(select="cn.ffhh.cloudtest.dao.IUserDao.findByUserId"))
	})
	@Select("select * from role where id = #{roleId}")
	public Role findByRoleDetailId(@Param("roleId") Integer roleId);
	
	/**
	 * findByRoleId
	 * @param roleId
	 * @return
	 */
	@Select("select * from role where id = #{roleId}")
	public Role findByRoleId(@Param("roleId") Integer roleId);
	
	/**
	 * findByRoleName
	 * @param roleName
	 * @return
	 */
	@ResultMap("roleMap")
	@Select("select * from role where rolename = #{rolename}")
	public Role findByRoleName(@Param("rolename") String rolename);
	
	/**
	 * findAllRoles
	 * @return
	 */
	@ResultMap("roleMap")
	@Select("select * from role")
	public List<Role> findAllRoles();
	
	/**
	 * 根据用户id去users_role表查询用户的角色信息
	 * @param userId
	 * @return
	 */
	@Select("select role.* from users_role inner join role on users_role.roleId = role.id where users_role.userId=#{userID}")
	public List<Role> findByUserId(Integer userId);
	
	/**
	 * 根据用户ID获取未拥有的角色
	 * @param userId
	 * @return
	 */
	@Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
	public List<Role> findOtherRolesByUserId(Integer userId);
	/**
	 *  获取用户所拥有的角色ID集合
	 * @param userId
	 * @return
	 */
	@Select("select roleId from users_role where userId = #{userId}")
	public Set<Integer> findRoleIdByUserId(Integer userId);
	
	/**
	 * 根据搜索条件搜索角色信息
	 * @param params
	 * @return
	 */
	@SelectProvider(type = cn.ffhh.cloudtest.DynaSql.RoleDynaSql.class,method = "selectWithParamSql")
	public List<Role> findAllRolesBySearchParam(RoleSearch params);
}
