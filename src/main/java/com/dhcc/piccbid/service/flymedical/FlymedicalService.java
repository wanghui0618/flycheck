package com.dhcc.piccbid.service.flymedical;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flymedical.FlymedicalDto;
import com.dhcc.piccbid.entity.flyMedicalrecord.FlyMedicalrecord;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 09:57:52
 * @version V1.0
 */
public interface FlymedicalService extends BaseService<FlyMedicalrecord, String> {

	PageModel list(FlymedicalDto dto);
	
	PageModel listVo(FlymedicalDto dto);

	PageModel screenSameEntryAndExitDate(FlymedicalDto dto);

	PageModel relateDiagnosis(FlymedicalDto dto);

}
