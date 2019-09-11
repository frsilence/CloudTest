package cn.ffhh.cloudtest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.ffhh.cloudtest.domain.User;
import cn.ffhh.cloudtest.domain.UserSearch;

public interface IUserDao {
	/**
	 *  add user
	 * @param user
	 * @return
	 */
	@Insert("insert into users (account,avatar,email,emailCode,password,"
			+ "username,departmentId,lastLoginTime,freeze,adduid) "
			+ " values(#{account},#{avatar},#{email},#{emailCode},#{password},"
			+ "#{username},#{department.id},#{lastLoginTime},#{freeze},"
			+ "#{adduid})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	public Integer save(User user);
	
	/**
	 * 	delete user
	 * @param userId
	 * @return
	 */
	@Delete("delete from users where id = #{userId}")
	public Integer delete(Integer userId);
	
	/**
	 * update user
	 * @param user
	 * @return
	 */
	@Update({"<script>",
		"update users",
		"<set>",
		"<if test='avatar!=null'> avatar=#{avatar}, </if>",
		"<if test='email!=null'>  email=#{email}, </if>",
		"<if test='password!=null'>  password=#{password}, </if>",
		"<if test='username!=null'>  username=#{username}, </if>",
		"<if test='department!=null'>  departmentId=#{department.id}, </if>",
		"<if test='lastLoginTime!=null'>  lastLoginTime=#{lastLoginTime}, </if>",
		"<if test='freeze!=null'>  freeze=#{freeze}, </if>",
		"</set>",
		"where id = #{id} </script>"})
	public Integer update(User user);
	
	/**
	 * findByUserId
	 * @param userId
	 * @return
	 */
	@Results(id = "userMap",value = {
		@Result(id = true,column="id",property="id"),
		@Result(column="account",property="account"),
		@Result(column="avatar",property="avatar"),
		@Result(column="email",property="email"),
		@Result(column="emailCode",property="emailCode"),
		@Result(column="password",property="password"),
		@Result(column="username",property="username"),
		@Result(column="lastLoginTime",property="lastLoginTime"),
		@Result(column="adduid",property="adduid"),
		@Result(column="freeze",property="freeze"),
		@Result(column="createTime",property="createTime"),
		@Result(column="updateTime",property="updateTime"),
		@Result(column="departmentId",property="department",
		one=@One(select="cn.ffhh.cloudtest.dao.IDepartmentDao.findByDepartmentId")),
		@Result(column="id",property="roles",
		many=@Many(select="cn.ffhh.cloudtest.dao.IRoleDao.findByUserId"))
	})
	@Select("select id,account,avatar,email,emailCode,username,departmentId,lastLoginTime,freeze,adduid from users where id = #{userId}")
	public User findByUserId(@Param("userId") Integer userId);
	
	/**
	 * findByUsername
	 * @param username
	 * @return
	 */
	@ResultMap("userMap")
	@Select("select id,account,avatar,email,emailCode,username,departmentId,lastLoginTime,freeze,adduid from users where username = #{username}")
	public User findByUsername(@Param("username") String username);
	
	/**
	 * findByEmail
	 * @param email
	 * @return
	 */
	@ResultMap("userMap")
	@Select("select id,account,avatar,email,emailCode,username,departmentId,lastLoginTime,freeze,adduid from users where email = #{email}")
	public User findByEmail(@Param("email") String email);
	
	/**
	 * findByAccount
	 * @param email
	 * @return
	 */
	@ResultMap("userMap")
	@Select("select id,account,password,avatar,email,emailCode,username,departmentId,lastLoginTime,freeze,adduid,createTime,updateTime from users where account = #{account}")
	public User findByAccount(@Param("account") String account);
	
	/**
	 * findallusers
	 * @return
	 */
	@ResultMap("userMap")
	@Select("select id,account,avatar,email,emailCode,username,departmentId,lastLoginTime,freeze,adduid,createTime,updateTime from users")
	public List<User> findAllUsers();
	
	/**
	 *  根据查询条件，多条件查询user表
	 * @param params
	 * @return
	 */
	@ResultMap("userMap")
	@SelectProvider(type=cn.ffhh.cloudtest.DynaSql.UserDynaSql.class,method="selectWithParamSql")
	public List<User> findBySearchInfo(UserSearch params);
	
	/**
	 * 给指定用户添加角色
	 * @param userId
	 * @param roleId
	 * @return
	 */
	@Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
	public Integer addRoleToUser(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
	
	/**
	 * 	情况用户的角色绑定
	 */
	@Delete("delete from users_role where userId = #{userId}")
	public void clearRoleByUserId(Integer userId);
	
	/**
	 *  根据角色Id查询用户信息
	 * @return
	 */
	@ResultMap("userMap")
	@Select("select id,account,password,avatar,email,emailCode,username,departmentId,lastLoginTime,freeze,adduid,createTime,updateTime from users_role where roleId=#{roleId}")
	public List<User> findByRoleId(Integer roleId);
}
