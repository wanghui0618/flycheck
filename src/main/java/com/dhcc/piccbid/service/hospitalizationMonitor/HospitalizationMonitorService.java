package com.dhcc.piccbid.service.hospitalizationMonitor;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
//import com.dhcc.piccbid.dto.coststatistics.HospitalCostStatisticsDto;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
//import com.dhcc.piccbid.dto.medical.MedicalDto;
import com.dhcc.piccbid.entity.hospitalizationMonitor.HospitalizationMonitor;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-19 17:33:36
 * @version V1.0
 */
public interface HospitalizationMonitorService extends BaseService<HospitalizationMonitor, String> {

	PageModel list(HospitalizationMonitorDto dto);
	PageModel yesterday(HospitalizationMonitorDto dto);
	PageModel inTop(HospitalizationMonitorDto dto);
//	PageModel cityInfo(HospitalCostStatisticsDto dto);

}
