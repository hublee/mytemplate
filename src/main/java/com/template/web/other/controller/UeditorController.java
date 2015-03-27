package com.template.web.other.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.MyActionEnter;

@Controller
public class UeditorController {

	@RequestMapping("ueditor")
	public @ResponseBody String config(String action,HttpServletRequest request)
			throws Exception {
		
		@SuppressWarnings("resource")
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		String baseState = new MyActionEnter(request, appContext.getResource(
				"classpath:/ueditor.config.json").getInputStream()).exec();
		return baseState;
		
//		String rootPath = request.getServletContext().getRealPath("/");
//		return new ActionEnter(request, rootPath).exec();
	}

}
