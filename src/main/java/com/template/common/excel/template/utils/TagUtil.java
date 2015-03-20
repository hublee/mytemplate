package com.template.common.excel.template.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;

import com.template.common.excel.template.tags.Tag;

/**
 * 标签处理工具类
 */
public class TagUtil {

	public static final String KEY_TAG = "#";

	private Map<String, Tag> tagMap = Collections.emptyMap();
	
	public void setRegisterTag(Map<String, Tag> tagMap){
		this.tagMap = tagMap;
	}

	/**
	 * 注册标签类
	 * @param clazz
	 */
	public void setRegisterSingleTag(Class<?> clazz){
		Tag tag;
		try {
			tag = (Tag) clazz.newInstance();
			tagMap.put(tag.getTagName(), tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 注册指定包中的标签类
	 * @param tagPackage
	 */
	public void setRegisterTagPackages(String tagPackage){
		Collection<Class<?>> classs = PackageUtil.getClasses(tagPackage, true);
		for(Class<?> clazz : classs){
			if(Tag.class.isAssignableFrom(clazz)) {
				setRegisterSingleTag(clazz);
			}
		}
	}
	
	/**
	 * 获取字符串中对应的标签对象
	 * @param str
	 * @return
	 */
	public Tag getTag(String str) {
		String tagName = null;
		if(str != null){
			int keytag = str.indexOf(KEY_TAG);
			if (keytag < 0)
				return null;
			if (!(keytag < str.length() - 1))
				return null;
			String tagRight = str.substring(keytag + 1, str.length());
			if (tagRight.startsWith(KEY_TAG))
				return null;
			StringTokenizer st = new StringTokenizer(str, " ");
			if (st.hasMoreTokens()) {
				tagName = st.nextToken();
			}
		}
		Tag tag = (Tag) tagMap.get(tagName);
		return tag;
	}
	
}
