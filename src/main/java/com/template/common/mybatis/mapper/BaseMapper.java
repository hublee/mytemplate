package com.template.common.mybatis.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.template.common.mybatis.provider.CommonSqlProvider;

@Repository("baseMapper")
public interface BaseMapper{

	@SelectProvider(type=CommonSqlProvider.class,method="beforeDeleteTreeStructureSql")
	int beforeDeleteTreeStructure(Map<String, Object> params);
	
	
	
}
