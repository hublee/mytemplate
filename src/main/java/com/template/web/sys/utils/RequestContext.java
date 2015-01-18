package com.template.web.sys.utils;

public class RequestContext {

	private static ThreadLocal context = new ThreadLocal() {
		 protected synchronized Object initialValue() {
			return new RequestContext();
		}
	};
	
	public static RequestContext get() {  
        return (RequestContext) context.get();  
    } 
	
	
	
	
	
}
