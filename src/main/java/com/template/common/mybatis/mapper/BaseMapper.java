package com.template.common.mybatis.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.template.common.mybatis.provider.BaseSqlProvider;

@Repository("baseMapper")
public interface BaseMapper<T>{

	@SelectProvider(type=BaseSqlProvider.class,method="beforeDeleteTreeStructureSql")
	public int beforeDeleteTreeStructure(Map<String, Object> params);
	
}
