package com.dhcc.piccbid.service.drugsAndInspectionStatistics;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsDto;
import com.dhcc.piccbid.entity.drugsAndInspectionStatistics.DrugsAndInspectionStatistics;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @date 2019-10-15 16:48:40
 * @version V1.0
 */
public interface DrugsAndInspectionStatisticsService extends BaseService<DrugsAndInspectionStatistics, String> {

	PageModel list(DrugsAndInspectionStatisticsDto dto);

	PageModel listForCostDetail(DrugsAndInspectionStatisticsDto dto);

	PageModel listForInselectionCostDetail(DrugsAndInspectionStatisticsDto dto);

	PageModel listForInspection(DrugsAndInspectionStatisticsDto dto);

}
