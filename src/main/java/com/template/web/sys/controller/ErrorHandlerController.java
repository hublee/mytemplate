package com.template.web.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ErrorHandler")
public class ErrorHandlerController {

	@RequestMapping
	public String errorHandler(){
		return "error/error";
	}
	
}
