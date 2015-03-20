package com.template.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.template.common.excel.template.PoiTemplate;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-config.xml",
        "classpath:spring/spring-config-beetl.xml"
})
public class TestExcel {
	
	@Resource 
	private PoiTemplate poiTemplate;

	@Test
	public void testExportExcel() throws IOException{
		String temp = "C:/Documents and Settings/Administrator/桌面/新建文件夹/poiTemplate/src/test/resources/test.xlsx";
		String outPath = "C:/Documents and Settings/Administrator/桌面/新建文件夹/poiTemplate/src/test/resources/test1.xlsx";
		poiTemplate.setPoiTemplate(temp, outPath);
		poiTemplate.addValue("username", "张三");
		poiTemplate.addValue("name", "韩流");
		poiTemplate.writeExcel();
		
		String out2 = "C:/Documents and Settings/Administrator/桌面/新建文件夹/poiTemplate/src/test/resources/test2.xlsx";
		poiTemplate.setPoiTemplate(temp, out2);
		poiTemplate.addValue("username", "这是test2导出的username");
		poiTemplate.addValue("name", "这是test2导出的name");
		poiTemplate.writeExcel();
	}
	
	public static void main(String[] args) {
		DynaBean context = new LazyDynaBean();
		context.set("1", "aa");
		System.out.println(context.get("1"));
		context.set("1", "bb");
		System.out.println(context.get("1"));
	}
	
}
