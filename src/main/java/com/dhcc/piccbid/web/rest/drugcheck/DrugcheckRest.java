package com.dhcc.piccbid.web.rest.drugcheck;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.piccbid.blh.drugcheck.DrugcheckBlh;
import com.dhcc.piccbid.dto.drugcheck.DrugcheckDto;
import com.dhcc.piccbid.entity.drugcheck.Data;
import com.dhcc.piccbid.entity.drugcheck.DrugCheckResponse;
import com.dhcc.piccbid.entity.page.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-17 22:23:32
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/drugcheck/drugcheck")
public class DrugcheckRest {

	@Resource
	private DrugcheckBlh drugcheckBlh;

	@PostMapping(value="list",consumes="application/json")
	public Page listRest(@RequestBody(required=false) DrugcheckDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public Page list(DrugcheckDto dto) {
		drugcheckBlh.list(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	
	//第一种写法返回list-string-json数据
	@RequestMapping(value = "/listAll",method = RequestMethod.GET)
	public String listAll(@RequestParam("id") String id,
			@RequestParam("name") String name) {
		System.out.println("id is:"+id);
		System.out.println("name is:"+name);
		List<String> list =new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		JSONArray json = (JSONArray) JSONArray.fromObject(list);
		return json.toString();
	}
	
	//第二种写法返回对象json数据
	@RequestMapping(value = "/listAll1",method = RequestMethod.GET)
	public String listAll1(@RequestParam("id") String id,
			@RequestParam("name") String name) {
		System.out.println("id is:"+id);
		System.out.println("name is:"+name);
		
		DrugCheckResponse drugCheckResponse = new DrugCheckResponse();
		Data data =new Data();
		data.setId("11");
		data.setName("张三");
		data.setValue("123456");
		drugCheckResponse.setStatus("1");
		drugCheckResponse.setData(data);
		JSONObject json = (JSONObject) JSONObject.fromObject(drugCheckResponse);
		return json.toString();
	}
	
	//第二种写法返回json-list-对象数据
	@RequestMapping(value = "/listAll2",method = RequestMethod.GET)
	public String listAll2(@RequestParam("id") String id,
			@RequestParam("name") String name) {
		System.out.println("id is:"+id);
		System.out.println("name is:"+name);
		
		List<DrugCheckResponse> list = new ArrayList<>();
		
		DrugCheckResponse drugCheckResponse1 = new DrugCheckResponse();
		Data data1 =new Data();
		data1.setId("11");
		data1.setName("张三");
		data1.setValue("123456");
		drugCheckResponse1.setStatus("1");
		drugCheckResponse1.setData(data1);
		
		DrugCheckResponse drugCheckResponse2 = new DrugCheckResponse();
		Data data2 =new Data();
		data2.setId("22");
		data2.setName("李四");
		data2.setValue("23456");
		drugCheckResponse2.setStatus("1");
		drugCheckResponse2.setData(data2);
		
		DrugCheckResponse drugCheckResponse3 = new DrugCheckResponse();
		Data data3 =new Data();
		data3.setId("33");
		data3.setName("王五");
		data3.setValue("34567");
		drugCheckResponse3.setStatus("1");
		drugCheckResponse3.setData(data3);
		list.add(drugCheckResponse1);
		list.add(drugCheckResponse2);
		list.add(drugCheckResponse3);
		
		JSONArray json = (JSONArray) JSONArray.fromObject(list);
		return json.toString();
	}
}