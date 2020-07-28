package com.dhcc.piccbid.service.excessiveTreat;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.excessiveTreat.ExcessiveTreatDto;
import com.dhcc.piccbid.entity.excessiveTreat.ExcessiveTreat;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-14 10:11:17
 * @version V1.0
 */
public interface ExcessiveTreatService extends BaseService<ExcessiveTreat, String> {

	PageModel list(ExcessiveTreatDto dto);
	
	PageModel listVo(ExcessiveTreatDto dto);

}
