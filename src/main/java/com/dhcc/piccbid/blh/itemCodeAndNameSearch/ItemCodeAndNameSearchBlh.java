package com.dhcc.piccbid.blh.itemCodeAndNameSearch;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.itemCodeAndNameSearch.ItemCodeAndNameSearchDto;
import com.dhcc.piccbid.service.itemCodeAndNameSearch.ItemCodeAndNameSearchService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class ItemCodeAndNameSearchBlh extends BaseAbstractBlh<ItemCodeAndNameSearchDto> {

	private static Log logger = LogFactory.getLog(ItemCodeAndNameSearchBlh.class);

	@Resource
	private ItemCodeAndNameSearchService itemCodeAndNameSearchService;

	public ItemCodeAndNameSearchBlh() {
		logger.debug("ItemCodeAndNameSearchBlh Init");
	}


	/**
	 * 根据项目编码或名称  查询项目信息 （名称模糊查询，编码精确查询）
	 */
	public void getItemInfoByCodeOrName(BaseAbstractDto basedto) {
		ItemCodeAndNameSearchDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = itemCodeAndNameSearchService.getItemInfoByCodeOrName(dto);
		dto.setPageModel(pageModel);
	}
	public XSSFWorkbook exportExcel() {
        return itemCodeAndNameSearchService.exportExcel();
    }
}
