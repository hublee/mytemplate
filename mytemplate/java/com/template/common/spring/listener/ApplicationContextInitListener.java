package com.template.common.spring.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.template.common.beetl.util.BeetlUtils;
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
			List<SysResource> resList = sysResourceService.select(new SysResource());
			Map<String, SysResource> AllResourceMap = new HashMap<String, SysResource>();
			for (SysResource res : resList) {
				AllResourceMap.put(res.getUrl(), res);
			}
			
			// 设置共享变量
			BeetlUtils.addBeetlSharedVars("adminPath", Global.getAdminPath());
			BeetlUtils.addBeetlSharedVars(Global.getSysResourceKey(),AllResourceMap);
			logger.info("--------------------------------------------------------------------------");
			logger.info("初始化管理根路径:(key:adminPath,value:"
					+ Global.getAdminPath() + ")");
			logger.info("初始化系统资源:(key:" + Global.getSysResourceKey()
					+ ",value:Map<资源url, SysResource>)");
			logger.info("--------------------------------------------------------------------------");
		}
		
	}

}
