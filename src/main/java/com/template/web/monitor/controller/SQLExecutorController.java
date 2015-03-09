package com.template.web.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("${adminPath}/monitor/db")
public class SQLExecutorController {

	@RequestMapping(value="sql",method=RequestMethod.GET)
	public String showSQLForm(){
		return "sys/monitor/db/sqlForm";
	}
	
	@RequestMapping(value="sql",method=RequestMethod.POST)
	public String executeSQL(Model model){
		
		return "";
	}
	
}
