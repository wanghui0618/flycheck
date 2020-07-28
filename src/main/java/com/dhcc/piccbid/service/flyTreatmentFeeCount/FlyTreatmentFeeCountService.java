package com.dhcc.piccbid.service.flyTreatmentFeeCount;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.FlyTreatmentFeeCountDto;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.FlyTreatmentFeeCount;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zfm
 * @date 2019-10-17 22:02:46
 * @version V1.0
 */
public interface FlyTreatmentFeeCountService extends BaseService<FlyTreatmentFeeCount, String> {

	PageModel list(FlyTreatmentFeeCountDto dto);
    PageModel ultrasoundlist(FlyTreatmentFeeCountDto dto);


}
