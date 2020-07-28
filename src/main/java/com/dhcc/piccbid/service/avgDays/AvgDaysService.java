package com.dhcc.piccbid.service.avgDays;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.avgDays.AvgDaysDto;
import com.dhcc.piccbid.entity.avgDays.AvgDays;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-07-31 10:28:01
 * @version V1.0
 */
public interface AvgDaysService extends BaseService<AvgDays, String> {

	PageModel list(AvgDaysDto dto);

	PageModel listVo(AvgDaysDto dto);

}
