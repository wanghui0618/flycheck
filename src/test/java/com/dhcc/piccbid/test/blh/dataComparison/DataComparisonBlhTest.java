package com.dhcc.piccbid.test.blh.dataComparison;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.utils.JsonUtils;
import com.dhcc.piccbid.blh.dataComparison.DataComparisonBlh;
import com.dhcc.piccbid.dto.dataComparison.DataComparisonDto;
import com.dhcc.piccbid.entity.dataComparison.DataComparison;
import com.dhcc.framework.test.DhccApplicationTest;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
public class DataComparisonBlhTest extends DhccApplicationTest {

	@Resource
	private DataComparisonBlh dataComparisonBlh;

	@Test
	public void listTest() {
		DataComparisonDto dto=new DataComparisonDto();
		PageModel pageModel=new PageModel(1, 10);
		dto.setPageModel(pageModel);
		DataComparison obj=new DataComparison();
		//设置对象属性作为查询条件
		
		dto.setDataComparison(obj);
		
		dataComparisonBlh.list(dto);
		System.out.println(JsonUtils.toJson(pageModel));
	}
	
	@Test
	@Rollback(true)
	public void saveTest() {
		DataComparison obj=new DataComparison();
		//添加对象属性,不设置主键
		
		DataComparisonDto dto=new DataComparisonDto();
		dto.setDataComparison(obj);
		dataComparisonBlh.save(dto);
	}

	@Test
	public void updateTest() {
		DataComparison obj=new DataComparison();
		//设置对象属性，包括主键
		
		DataComparisonDto dto=new DataComparisonDto();
		dto.setDataComparison(obj);
		dataComparisonBlh.save(dto);
		System.out.println("dto.getOpFlg(): "+dto.getOpFlg());
	}
	
	@Test
	public void deleteTest() {
		DataComparison obj=new DataComparison();
		//设置对象主键

		DataComparisonDto dto=new DataComparisonDto();
		dto.setDataComparison(obj);
		dataComparisonBlh.delete(dto);
		System.out.println("dto.getOpFlg(): "+dto.getOpFlg());
	}
	
	@Test
	public void findByIdTest() {
		DataComparison obj=new DataComparison();
		//设置对象主键
		
		DataComparisonDto dto=new DataComparisonDto();
		dto.setDataComparison(obj);
		dataComparisonBlh.findById(dto);
		System.out.println("dto.getOpFlg(): "+dto.getOpFlg());
	}
	
}
