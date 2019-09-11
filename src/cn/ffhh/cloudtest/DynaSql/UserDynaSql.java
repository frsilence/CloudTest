package cn.ffhh.cloudtest.DynaSql;

import org.apache.ibatis.jdbc.SQL;

import cn.ffhh.cloudtest.domain.UserSearch;


public class UserDynaSql {
	public String selectWithParamSql(UserSearch params) {
		return new SQL() {
			{
				SELECT("id,account,avatar,email,emailCode,username,"
						+ "departmentId,lastLoginTime,freeze,adduid,createTime,updateTime");
				FROM("users");
				if(params.getAccount()!=null && "".equals(params.getAccount())) {
					WHERE("account=#{account}");
				}
				if(params.getEmail()!=null && "".equals(params.getEmail())) {
					WHERE("email=#{email}");
				}
				if(params.getUsername()!=null && "".equals(params.getUsername())) {
					WHERE("username like #{username}");
				}
				if(params.getDepaInteger()!=null) {
					WHERE("departmentId=#{departmentId}");
				}
				if(params.getStartDate()!=null && params.getEndDate()!=null) {
					WHERE("createTime between #{startDate} and #{endDate}");
				}
				if(params.getFreeze()!=null) {
					WHERE("freeze=#{freeze}");
				}
				if(params.getRoleId()!=null) {
					WHERE("id in (select userId from users_role where roleId=#{roleId}) ");
					
				}
				
			}
		}.toString();
	}
}
