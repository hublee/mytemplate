package com.template.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;

import com.template.web.sys.model.SysOffice;
import com.template.web.sys.service.SysOfficeService;

@Component
public class OfficeFunction implements Function{
	
	@Resource
	private SysOfficeService sysOfficeService;
	
	private static final Map<Long, Object> map = new HashMap<Long, Object>();
	
	@Override
	public Object call(Object[] params, Context ctx) {
		List<SysOffice> list = sysOfficeService.select(null);
		for(SysOffice o : list){
			map.put(o.getId(), o);
		}
		return map;
	}

}
