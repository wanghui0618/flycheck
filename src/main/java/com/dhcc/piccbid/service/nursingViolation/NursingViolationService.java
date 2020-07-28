package com.dhcc.piccbid.service.nursingViolation;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.nursingViolation.NursingViolationDto;
import com.dhcc.piccbid.entity.nursingViolation.NursingViolation;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-29 16:10:27
 * @version V1.0
 */
public interface NursingViolationService extends BaseService<NursingViolation, String> {

	PageModel list(NursingViolationDto dto);

	PageModel nursingViolation(NursingViolationDto dto);

}
