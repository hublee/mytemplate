package com.template.common.mybatis.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;


public class CommonSqlProvider extends BaseProvider{
	
	public String beforeDeleteTreeStructureSql(Map<String, Object> params){
		final String tableNameOne = params.get("t0").toString();
		final String tableNameTwo = params.get("t1").toString();
		final String checkField = params.get("checkField").toString();
		return new SQL(){{
			SELECT("count(0)");
			FROM(tableNameOne+" t0");
			FROM(tableNameTwo+" t1");
			WHERE("t1.parent_ids like concat('%,',#{id},',%') or t1.id=#{id}");
			AND();
			WHERE("t0."+checkField+"=t1.id");
			
		}}.toString();
	}
	
}
