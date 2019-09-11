package cn.ffhh.cloudtest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.ffhh.cloudtest.domain.Permission;

public interface IPermissionDao {
	/**
	 *  add Permission
	 * @param Permission
	 * @return
	 */
	@Insert("insert into permission (permissionName,permissionDesc,types,partentId,url,"
			+ "method,listorder,display,iconname) "
			+ " value(#{permissionName},#{permissionDesc},#{types},#{partentId},#{url},"
			+ "#{method},#{listorder},#{display},#{iconname})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	public Integer save(Permission permission);
	
	/**
	 * 	delete Permission
	 * @param PermissionId
	 * @return
	 */
	@Delete("delete from permission where id = #{permissionId}")
	public Integer delete(Integer permissionId);
	
	/**
	 * update Permission
	 * @param Permission
	 * @return
	 */
	@Update({"<script>",
		"update Permission",
		"<set>",
		"<if test='permissionName!=null'> permissionName=#{permissionName}, </if>",
		"<if test='permissionDesc!=null'>  permissionDesc=#{permissionDesc}, </if>",
		"<if test='types!=null'>  types=#{types}, </if>",
		"<if test='partentId!=null'>  partentId=#{partentId}, </if>",
		"<if test='url!=null'>  url=#{url}, </if>",
		"<if test='method!=null'>  method=#{method}, </if>",
		"<if test='listorder!=null'>  listorder=#{listorder}, </if>",
		"<if test='iconname!=null'>  iconname=#{iconname}, </if>",
		"</set>",
		"where id = #{id} </script>"})
	public Integer update(Permission permission);
	
	/**
	 * findByPermissionId
	 * @param PermissionId
	 * @return
	 */
	@Select("select * from permission where id = #{permissionId}")
	public Permission findByPermissionId(@Param("permissionId") Integer permissionId);
	
	/**
	 * findByPermissionname
	 * @param Permissionname
	 * @return
	 */
	@Select("select * from permission where permissionName = #{permissionName}")
	public Permission findByPermissionname(@Param("permissionName") String permissionName);
	
	/**
	 * findByUrl
	 * @param url
	 * @return
	 */
	@Select("select * from permission where url = #{url}")
	public Permission findByUrl(@Param("url") String url);
	
	/**
	 *  findAllByPermissions
	 * @return
	 */
	@Select("select * from permission")
	public List<Permission> findAllPermissions();
	
}
