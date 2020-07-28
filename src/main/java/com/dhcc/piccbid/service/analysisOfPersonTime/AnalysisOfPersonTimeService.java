package com.dhcc.piccbid.service.analysisOfPersonTime;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.analysisOfPersonTime.AnalysisOfPersonTimeDto;
import com.dhcc.piccbid.entity.analysisOfPersonTime.AnalysisOfPersonTime;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-23 15:00:27
 * @version V1.0
 */
public interface AnalysisOfPersonTimeService extends BaseService<AnalysisOfPersonTime, String> {

	PageModel list(AnalysisOfPersonTimeDto dto);
	PageModel table1(AnalysisOfPersonTimeDto dto);
	PageModel table2(AnalysisOfPersonTimeDto dto);
	PageModel table3(AnalysisOfPersonTimeDto dto);
	PageModel table4(AnalysisOfPersonTimeDto dto);


}
