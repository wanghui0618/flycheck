package com.dhcc.piccbid.service.dataCompare;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liufaxing
 * @date 2019-11-24 12:40:05
 * @version V1.0
 */
public interface DataCompareService extends BaseService<FlycheckMedical, String> {

	PageModel list(FlyGeneralOverviewDto dto);

	PageModel dataCompareDtoFind(FlyGeneralOverviewDto dto);
	
	PageModel dataCompareDtoFind1(FlyGeneralOverviewDto dto);
}
