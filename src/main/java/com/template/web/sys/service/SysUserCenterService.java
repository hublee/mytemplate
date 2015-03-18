package com.template.web.sys.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.template.common.base.ServiceMybatis;
import com.template.common.beetl.function.OfficeFunctions;
import com.template.web.sys.model.SysOffice;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.utils.SysUserUtils;

@Service
public class SysUserCenterService extends ServiceMybatis<SysUser>{
	
	@Resource
	private OfficeFunctions officeFunctions;

	public SysUser getSysUserInfo(){
		SysUser sysUser = SysUserUtils.getCacheLoginUser();
		Map<Long, SysOffice> offices = officeFunctions.getAllOfficeMap();
		Long companyId = sysUser.getCompanyId();
		Long officeId = sysUser.getOfficeId();
		String orgStr = null;
		if (companyId.equals(officeId)) {
			orgStr = ((SysOffice) offices.get(companyId)).getName();
		} else {
			orgStr = ((SysOffice) offices.get(companyId)).getName() + " —— "
					+ ((SysOffice) offices.get(officeId)).getName();
		}
		sysUser.set("orgStr", orgStr);
		return sysUser;
	}
	
}
