package com.template.web.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.template.core.utils.BeetlUtils;
import com.template.core.utils.Global;
import com.template.web.model.SysResource;
import com.template.web.service.SysResourceService;

@Component
public class InitListener implements ApplicationListener<ApplicationEvent> {

	private static final Logger logger = LoggerFactory
			.getLogger(InitListener.class);

	@Resource
	private SysResourceService sysResourceService;

	public void onApplicationEvent(ApplicationEvent event) {

		// ApplicationContext初始化或刷新完成后触发的事件（容器初始化完成后调用）
		if (event instanceof ContextRefreshedEvent) {

			ApplicationContext parentContext = ((ContextRefreshedEvent) event)
					.getApplicationContext().getParent();

			// 子容器初始化时(spring-mvc)
			if (null != parentContext) {
				List<SysResource> resList = sysResourceService
						.findSysResourceListByParams(null);
				Map<String, SysResource> AllResourceMap = new HashMap<String, SysResource>();
				/*for (SysResource res : resList) {
					AllResourceMap.put(res.getUrl(), res);
				}*/
				
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

		} else if (event instanceof ContextClosedEvent) { // spring容器关闭(web容器关闭会触发spring容器关闭)
			ApplicationContext parentContext = ((ContextClosedEvent) event)
					.getApplicationContext().getParent();
			if (null != parentContext) {
				logger.info("-----------------spring子容器关闭-----------------");
			} else {
				logger.info("-----------------spring父容器关闭-----------------");
			}
		}

	}

}
