package com.dhcc.piccbid.service.mainDiagnosis;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.mainDiagnosis.MainDiagnosisDto;
import com.dhcc.piccbid.entity.mainDiagnosis.MainDiagnosis;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-11-08 16:53:31
 * @version V1.0
 */
public interface MainDiagnosisService extends BaseService<MainDiagnosis, String> {

	PageModel list(MainDiagnosisDto dto);

}
