package com.dhcc.piccbid.service.statisticsInRecentTwoYears;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsDto;
import com.dhcc.piccbid.entity.medicaldetail.MedicalDetail;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-08 12:08:41
 * @version V1.0
 */
public interface StatisticsInRecentTwoYearsService extends BaseService<MedicalDetail, String> {

	PageModel list(StatisticsInRecentTwoYearsDto dto);

	PageModel Dosage(StatisticsInRecentTwoYearsDto dto);

	PageModel getCount(StatisticsInRecentTwoYearsDto dto);

	PageModel getlist(StatisticsInRecentTwoYearsDto dto);

	PageModel getlistByinhosDiag(StatisticsInRecentTwoYearsDto dto);

	PageModel statisticalAnalysisByInsuranceType(StatisticsInRecentTwoYearsDto dto);

}
