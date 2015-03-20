package com.template.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.Test;

import com.template.common.excel.template.PoiTemplate;


/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
})*/
public class TestExcel {
	
	@Test
	public void testExportExcel() throws IOException{
		String temp = ""; //输入path
		String outPath = ""; //输出path
		PoiTemplate poiTemplate = new PoiTemplate(temp,outPath);
		List<String> list = new ArrayList<String>();
		for(int i=0;i<100;i++){
			list.add("test"+i);
		}
		poiTemplate.addValue("username", "张三");
		poiTemplate.addValue("name", "韩流");
		poiTemplate.addValue("list", list);
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
