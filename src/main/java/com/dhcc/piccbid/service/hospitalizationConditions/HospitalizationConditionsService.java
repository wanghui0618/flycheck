package com.dhcc.piccbid.service.hospitalizationConditions;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.hospitalizationConditions.HospitalizationConditionsDto;
import com.dhcc.piccbid.entity.hospitalizationConditions.HospitalizationConditions;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-24 16:19:35
 * @version V1.0
 */
public interface HospitalizationConditionsService extends BaseService<HospitalizationConditions, String> {

	PageModel list(HospitalizationConditionsDto dto);

	PageModel hospitalizationConditions(HospitalizationConditionsDto dto);

	PageModel getlist(HospitalizationConditionsDto dto);

}
