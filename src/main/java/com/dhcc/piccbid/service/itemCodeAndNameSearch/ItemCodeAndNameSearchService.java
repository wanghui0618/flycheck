package com.dhcc.piccbid.service.itemCodeAndNameSearch;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.itemCodeAndNameSearch.ItemCodeAndNameSearchDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author Yangsx
 * @date 2019-12-12 16:18:53
 * @version V1.0
 */
public interface ItemCodeAndNameSearchService {


	/***
	 * 根据项目编码或名称  查询项目信息 （名称模糊查询，编码精确查询）
	 * @param dto
	 */
	PageModel getItemInfoByCodeOrName(ItemCodeAndNameSearchDto dto);
	
	XSSFWorkbook exportExcel();
}
