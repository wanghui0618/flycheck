package com.dhcc.piccbid.service.hospitalizationAnalysis;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.hospitalizationAnalysis.HospitalizationAnalysisDto;
import com.dhcc.piccbid.entity.hospitalizationAnalysis.HospitalizationAnalysis;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-17 12:54:10
 * @version V1.0
 */
public interface HospitalizationAnalysisService extends BaseService<HospitalizationAnalysis, String> {

	PageModel list(HospitalizationAnalysisDto dto);

	PageModel hospitalizationAnalysis(HospitalizationAnalysisDto dto);

}
