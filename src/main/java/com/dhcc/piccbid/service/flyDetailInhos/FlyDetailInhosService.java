package com.dhcc.piccbid.service.flyDetailInhos;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyDetailInhos.FlyDetailInhosDto;
import com.dhcc.piccbid.entity.flyDetailInhos.FlyDetailInhos;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-29 16:37:25
 * @version V1.0
 */
public interface FlyDetailInhosService extends BaseService<FlyDetailInhos, String> {

	PageModel list(FlyDetailInhosDto dto);

	PageModel statisticsDrugs(FlyDetailInhosDto dto);

}
