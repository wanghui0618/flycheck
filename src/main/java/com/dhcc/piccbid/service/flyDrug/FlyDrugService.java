package com.dhcc.piccbid.service.flyDrug;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyDrug.FlyDrugDto;
import com.dhcc.piccbid.entity.flyDrug.FlyDrug;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-16 15:05:04
 * @version V1.0
 */
public interface FlyDrugService extends BaseService<FlyDrug, String> {

	PageModel list(FlyDrugDto dto);
	PageModel listVo(FlyDrugDto dto);
}
