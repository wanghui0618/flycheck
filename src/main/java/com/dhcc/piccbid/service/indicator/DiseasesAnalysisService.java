package com.dhcc.piccbid.service.indicator;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.indicator.DiseasesAnalysisDto;
import com.dhcc.piccbid.entity.indicator.DiseasesAnalysis;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-22 12:22:11
 * @version V1.0
 */
public interface DiseasesAnalysisService extends BaseService<DiseasesAnalysis, String> {

	PageModel list(DiseasesAnalysisDto dto);
	PageModel listVo(DiseasesAnalysisDto dto);

	PageModel listVoTest(DiseasesAnalysisDto dto);
	PageModel handdInfo(DiseasesAnalysisDto dto);
	PageModel diseases(DiseasesAnalysisDto dto);
	PageModel hopLevel(DiseasesAnalysisDto dto);


}
