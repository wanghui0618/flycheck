package com.dhcc.piccbid.service.drugcheck;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.drugcheck.DrugcheckDto;
import com.dhcc.piccbid.entity.drugcheck.Drugcheck;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-17 22:23:31
 * @version V1.0
 */
public interface DrugcheckService extends BaseService<Drugcheck, String> {

	PageModel list(DrugcheckDto dto);

}
