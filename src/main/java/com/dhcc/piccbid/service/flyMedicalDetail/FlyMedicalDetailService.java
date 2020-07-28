package com.dhcc.piccbid.service.flyMedicalDetail;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyMedicalDetail.FlyMedicalDetailDto;
import com.dhcc.piccbid.entity.flyMedicalDetail.FlyMedicalDetail;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-10-15 14:48:33
 * @version V1.0
 */
public interface FlyMedicalDetailService extends BaseService<FlyMedicalDetail, String> {

	PageModel list(FlyMedicalDetailDto dto);
	
	PageModel listVo(FlyMedicalDetailDto dto);

}
