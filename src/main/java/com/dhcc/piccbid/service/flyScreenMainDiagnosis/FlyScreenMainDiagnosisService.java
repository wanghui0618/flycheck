package com.dhcc.piccbid.service.flyScreenMainDiagnosis;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyScreenMainDiagnosis.FlyScreenMainDiagnosisDto;
import com.dhcc.piccbid.entity.flyScreenMainDiagnosis.FlyScreenMainDiagnosis;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-24 17:15:53
 * @version V1.0
 */
public interface FlyScreenMainDiagnosisService extends BaseService<FlyScreenMainDiagnosis, String> {

	PageModel list(FlyScreenMainDiagnosisDto dto);

}
