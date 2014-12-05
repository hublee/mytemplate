//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import com.template.common.base.ServiceMybatis;
import com.template.web.sys.mapper.SysAreaMapper;
import com.template.web.sys.model.SysArea;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * @author 
 */

@Service("sysAreaService")
public class SysAreaService extends ServiceMybatis<SysArea>{

	@Resource
	private SysAreaMapper sysAreaMapper;
	

}
