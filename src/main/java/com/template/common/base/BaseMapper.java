package com.template.common.base;

import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository("baseMapper")
public interface BaseMapper<T extends BaseEntity>{

	@SelectProvider(type=BaseSqlProvider.class,method="beforeDeleteSql")
	public <M extends BaseEntity> int beforeDelete(Map<String, Object> params);
	
}
