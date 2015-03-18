package com.template.web.sys.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.template.common.base.ServiceMybatis;
import com.template.common.beetl.function.OfficeFunctions;
import com.template.common.utils.IPUtils;
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
		if (companyId.equals(officeId)) { //机构
			orgStr = ((SysOffice) offices.get(companyId)).getName();
		} else {
			orgStr = ((SysOffice) offices.get(companyId)).getName() + " —— "
					+ ((SysOffice) offices.get(officeId)).getName();
		}
		String curIP = IPUtils.getClientAddress(SysUserUtils.getCurRequest());
		String ipEx = ""; 
		if(!StringUtils.equals(sysUser.getLoginIp(), curIP)) ipEx = "(当前IP:"+curIP+"，请注意!)";
		sysUser.set("orgStr", orgStr);
		sysUser.set("ipEx", ipEx);
		return sysUser;
	}
	
}
