package cn.ffhh.cloudtest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.ffhh.cloudtest.domain.Department;
public interface IDepartmentDao {
	/**
	 *  add department
	 * @param department
	 * @return
	 */
	@Insert("insert into department (departmentName,parentId,level,listOrder,adduid)"
			+ " values (#{departmentName},#{parentId},#{level},#{listOrder},#{addUser.id})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	public Integer save(Department department);
	
	/**
	 * 	delete department
	 * @param departmentId
	 * @return
	 */
	@Delete("delete from department where id = #{departmentId}")
	public Integer delete(Integer departmentId);
	
	/**
	 * update department
	 * @param department
	 * @return
	 */
	@Update({"<script>",
		"update department",
		"<set>",
		"<if test='departmentName!=null'> departmentName=#{departmentName}, </if>",
		"<if test='parentId!=null'>  parentId=#{parentId}, </if>",
		"<if test='level!=null'>  level=#{level}, </if>",
		"<if test='listOrder!=null'>  listOrder=#{listOrder}, </if>",
		"</set>",
		"where id = #{id} </script>"})
	public Integer update(Department department);
	
	/**
	 * findByDepartmentId
	 * @param userId
	 * @return
	 */
	@Results(id="departmentMap",value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="departmentName",property="departmentName"),
			@Result(column="parentId",property="parentId"),
			@Result(column="level",property="level"),
			@Result(column="listOrder",property="listOrder"),
			@Result(column="adduid",property="addUser",
			one = @One(select="cn.ffhh.cloudtest.dao.IUserDao.findByUserId"))
			})
	@Select("select * from department where id = #{departmentId}")
	public Department findDetailByDepartmentDeId(@Param("departmentId") Integer departmentId);
	
	@Select("select * from department where id = #{departmentId}")
	public Department findByDepartmentId(@Param("departmentId") Integer departmentId);
	
	/**
	 * findByDepartmentname
	 * @param departmentName
	 * @return
	 */
	@ResultMap("departmentMap")
	@Select("select * from department where departmentName = #{departmentName}")
	public Department findByDepartmentname(@Param("departmentName") String departmentName);
	
	/**
	 *  findBydepartments
	 * @return
	 */
	@ResultMap("departmentMap")
	@Select("select * from department")
	public List<Department> findAllDepartments();
	
}
