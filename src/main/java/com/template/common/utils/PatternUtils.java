package com.template.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {

	public static boolean simpleMatch(String pattern,String original){
		if(original.length()<pattern.length()){
			return false;
		}
		pattern = pattern.replace("*", "");
		return original.indexOf(pattern)>-1;
	}
	
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(PatternUtils.simpleMatch("*as*", "s"));
	}
}
