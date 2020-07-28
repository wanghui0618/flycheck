package com.dhcc.piccbid.service.flyMedicalInhos;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyMedicalInhos.FlyMedicalInhosDto;
import com.dhcc.piccbid.entity.flyMedicalInhos.FlyMedicalInhos;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-31 11:40:47
 * @version V1.0
 */
public interface FlyMedicalInhosService extends BaseService<FlyMedicalInhos, String> {

	PageModel list(FlyMedicalInhosDto dto);

	PageModel findByYear(FlyMedicalInhosDto dto);

	PageModel findByMonth(FlyMedicalInhosDto dto);

}
