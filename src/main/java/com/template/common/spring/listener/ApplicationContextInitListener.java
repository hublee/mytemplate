package com.template.common.spring.listener;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.template.common.beetl.util.BeetlUtils;
import com.template.common.constant.Constant;
import com.template.common.utils.Global;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.service.SysResourceService;

@Component
public class ApplicationContextInitListener implements
		ApplicationListener<ContextRefreshedEvent>, ServletContextAware {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private ServletContext servletContext;

	@Resource
	private SysResourceService sysResourceService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		ApplicationContext parentContext = ((ContextRefreshedEvent) event)
				.getApplicationContext().getParent();
		
		// 子容器初始化时(spring-mvc)
		if (null != parentContext) {
			
			/*RequestMappingHandlerMapping rmhp = event.getApplicationContext()
					.getBean(RequestMappingHandlerMapping.class);
			
			Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
			
			Iterator<RequestMappingInfo> iterator = map.keySet().iterator();
			
			while(iterator.hasNext()){
				RequestMappingInfo info = iterator.next();
				Set<String> set = info.getPatternsCondition().getPatterns();
			}*/
			
			String ctxPath = servletContext.getContextPath();
			
			//读取全部资源
			LinkedHashMap<String, SysResource> AllResourceMap = sysResourceService.getAllResourcesMap();
			// 设置共享变量
			BeetlUtils.addBeetlSharedVars("adminPath", Global.getAdminPath());
			BeetlUtils.addBeetlSharedVars("rootPath", ctxPath+"/"+Global.getAdminPath());
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

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
