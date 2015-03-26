package com.template.web.other.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.MyActionEnter;

@Controller
@RequestMapping("ueditor")
public class UeditorController {

	@RequestMapping
	@ResponseBody
	public String config(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		
//		request.setCharacterEncoding("utf-8");
//		response.setHeader("Content-Type", "text/html");
		@SuppressWarnings("resource")
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		String baseState = new MyActionEnter(request, appContext.getResource(
				"classpath:/ueditor.config.json").getInputStream()).exec();
		return baseState;
		
		/*String rootPath = "E:/develop_software/Tomcat/apache-tomcat-8.0.20/webapps/mytemplate/WEB-INF/classes";
		return new ActionEnter(request, rootPath).exec();*/
	}

}
