package com.template.web.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.template.core.util.Global;
import com.template.web.model.SysResource;
import com.template.web.service.SysResourceService;

@Component
public class InitListener implements
		ApplicationListener<ApplicationEvent>, ServletContextAware {

	private static final Logger logger = LoggerFactory
			.getLogger(SysResourceService.class);

	private ServletContext sc;
	@Resource
	private SysResourceService sysResourceService;
	@Resource
	private BeetlGroupUtilConfiguration beetlConfig;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.sc = servletContext;
	}

	public void onApplicationEvent(ApplicationEvent event) {

		// ApplicationContext初始化或刷新完成后触发的事件（容器初始化完成后调用）
		if (event instanceof ContextRefreshedEvent) {
			
			ApplicationContext parentContext = ((ContextRefreshedEvent) event)
					.getApplicationContext().getParent();
			
			if (null == parentContext) {
				List<SysResource> resList = sysResourceService
						.findSysResourceListByParams(null);
				Map<String, SysResource> map = new HashMap<String, SysResource>();
				for (SysResource res : resList) {
					map.put(res.getUrl(), res);
				}
				if (null != sc) {
					sc.setAttribute("all_resource", map);
					logger.info("初始化所有资源到Application中");
				}

				// 设置共享变量以便在beetl模板中用
				Map<String, Object> sharedVars = new HashMap<String, Object>();
				sharedVars.put("adminPath", Global.getAdminPath());
				GroupTemplate template = beetlConfig.getGroupTemplate();
				template.setSharedVars(sharedVars);
			}

		} else if (event instanceof ContextClosedEvent) { // spring容器关闭(web容器关闭会触发spring容器关闭)
			ApplicationContext parentContext = ((ContextClosedEvent) event)
					.getApplicationContext().getParent();
			if(null == parentContext) {
				sc = null;
				logger.info("-----------------spring父容器关闭-----------------");
			}else{
				logger.info("-----------------spring子容器关闭-----------------");
			}
		}

	}

}
