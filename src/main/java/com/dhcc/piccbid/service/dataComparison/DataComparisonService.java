package com.dhcc.piccbid.service.dataComparison;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.dataComparison.DataComparisonDto;
import com.dhcc.piccbid.entity.dataComparison.DataComparison;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-11-26 16:14:41
 * @version V1.0
 */
public interface DataComparisonService extends BaseService<DataComparison, String> {

	PageModel list(DataComparisonDto dto);
	PageModel table1(DataComparisonDto dto);
	PageModel table2(DataComparisonDto dto);
	PageModel table3(DataComparisonDto dto);
	PageModel table45(DataComparisonDto dto);
	PageModel table6(DataComparisonDto dto);
	PageModel table7(DataComparisonDto dto);
	PageModel table8_1(DataComparisonDto dto);
	PageModel table8_2(DataComparisonDto dto);
	SXSSFWorkbook exportExcelToSelf(DataComparisonDto dto);
}
