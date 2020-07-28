package com.dhcc.piccbid.service.home;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.home.MonitorPolygonalchartDto;
import com.dhcc.piccbid.dto.home.RegionalfundsDto;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
import com.dhcc.piccbid.entity.home.Regionalfunds;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-15 16:58:47
 * @version V1.0
 */
public interface RegionalfundsService extends BaseService<Regionalfunds, String> {

	PageModel list(RegionalfundsDto dto);
	PageModel listVo(RegionalfundsDto dto);
	PageModel listVo2(MonitorPolygonalchartDto dto);
	PageModel totalTimes(HospitalizationMonitorDto dto);
	/**
	 * @param dto
	 * @return
	 */
	void bigDataHomepage(RegionalfundsDto dto);

}
