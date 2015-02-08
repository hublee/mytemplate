package com.template.common.spring.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.template.web.sys.service.SysLogService;

@Aspect
@Component
public class SystemLogAspect {

	@Resource
	private SysLogService sysLogService;

	// 本地异常日志记录对象
	private final static Logger LOGGER = LoggerFactory
			.getLogger(SystemLogAspect.class);

	// Controller层切点
	@Pointcut("@annotation(com.annotation.SystemControllerLog)")
	public void controllerAspect() {
	}

}
