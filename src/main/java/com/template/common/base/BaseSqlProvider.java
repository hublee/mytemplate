package com.template.common.base;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class BaseSqlProvider {

	public String beforeDeleteSql(Map<String, Object> params){
		String id = params.get("id").toString();
		//String tableNameOne = params.get("id").toString();
		return new SQL(){{
			SELECT("count(0)");
			FROM("sys_user");
			WHERE("id=2 and username=#{id}");
		}}.toString();
		//return "select count(0) from sys_user where id=2 and username="+id;
		/*return "SELECT COUNT(0) FROM SYS_OFFICE t1, sys_area t2 WHERE (t2.parent_ids LIKE '%,"+id+",%' OR t2.id="+id+")"+  
				"AND t1.area_id = t2.id";
*/	}
	
	/*SELECT COUNT(0) FROM SYS_OFFICE t1, sys_area t2 WHERE (t2.parent_ids LIKE '%,2,%' OR t2.id=2)  
	AND t1.area_id = t2.id */
	
}
