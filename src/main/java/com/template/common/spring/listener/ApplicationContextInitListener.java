package com.template.common.spring.listener;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.template.common.beetl.util.BeetlUtils;
import com.template.common.constant.Constant;
import com.template.common.utils.Global;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.service.SysResourceService;

@Component
public class ApplicationContextInitListener implements ApplicationListener<ContextRefreshedEvent> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private SysResourceService sysResourceService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		ApplicationContext parentContext = ((ContextRefreshedEvent) event)
				.getApplicationContext().getParent();

		// 子容器初始化时(spring-mvc)
		if (null != parentContext) {
			//读取全部资源
			LinkedHashMap<String, SysResource> AllResourceMap = sysResourceService.getAllResourcesMap();
			// 设置共享变量
			BeetlUtils.addBeetlSharedVars("adminPath", Global.getAdminPath());
			BeetlUtils.addBeetlSharedVars("rootPath", "/"+Global.getCtxPath()+"/"+Global.getAdminPath());
			BeetlUtils.addBeetlSharedVars(Constant.CACHE_ALL_RESOURCE,AllResourceMap);
			logger.info("--------------------------------------------------------------------------");
			logger.info("初始化管理根路径:(key:adminPath,value:"
					+ BeetlUtils.getBeetlSharedVars("adminPath").toString() + ")");
			logger.info("初始化根路径:(key:rootPath,value:"
					+ BeetlUtils.getBeetlSharedVars("rootPath").toString() + ")");
			logger.info("初始化系统资源:(key:" + Constant.CACHE_ALL_RESOURCE
					+ ",value:Map<资源url, SysResource>)");
			logger.info("--------------------------------------------------------------------------");
		}
		
	}

}
