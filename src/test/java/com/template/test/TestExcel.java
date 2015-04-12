package com.template.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.Test;

import com.gohuinuo.common.excel.template.PoiTemplate;
import com.gohuinuo.common.excel.template.utils.PoiUtil;


/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
})*/
public class TestExcel {
	
	@Test
	public void testExportExcel() throws IOException{
		String temp = "E:/develop_software/eclipse64/workspace/mytemplate/src/main/webapp/testExcel/test.xlsx"; //输入path
		String outPath = "E:/develop_software/eclipse64/workspace/mytemplate/src/main/webapp/testExcel/test1.xlsx"; //输出path
		PoiTemplate poiTemplate = new PoiTemplate(temp,outPath);
		List<String> list = new ArrayList<String>();
		for(int i=0;i<2;i++){
			list.add("test"+i);
		}
		poiTemplate.addValue("username", "张三");
		poiTemplate.addValue("name", "韩流");
		poiTemplate.addValue("list", list);
		poiTemplate.writeExcel();
	}
	
	@Test
	public void test1() throws FileNotFoundException{
		String out = "E:/develop_software/eclipse64/workspace/mytemplate/src/main/webapp/testExcel/test2.xls";
		Map<String, String> map = new HashMap<String, String>();
		map.put("姓名", "name");
		map.put("年龄", "age");
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", "张三");
		data.put("age", "34");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(data);
		
		PoiUtil.writeExcel(list, new FileOutputStream(new File(out)), map);
		
	}
	
	public static void main(String[] args) {
		DynaBean context = new LazyDynaBean();
		context.set("1", "aa");
		System.out.println(context.get("1"));
		context.set("1", "bb");
		System.out.println(context.get("1"));
	}
	
}
