package cn.ffhh.cloudtest.DynaSql;

import org.apache.ibatis.jdbc.SQL;

import cn.ffhh.cloudtest.domain.RoleSearch;

public class RoleDynaSql {
	public String selectWithParamSql(RoleSearch params) {
		return new SQL() {
			{
				SELECT("*");
				FROM("role");
				if(params.getRoleName()!=null && "".equals(params.getRoleName())) {
					WHERE("roleName=#{roleName}");
				};
				if(params.getStartTime()!=null && params.getEndTime()!=null) {
					WHERE("createTime between #{startDate} and #{endDate}");
				}
				
             };
		}.toString();
	}
}
