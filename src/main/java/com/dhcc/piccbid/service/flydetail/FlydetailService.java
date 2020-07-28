package com.dhcc.piccbid.service.flydetail;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flydetail.FlydetailDto;
import com.dhcc.piccbid.dto.flymedical.FlymedicalDto;
import com.dhcc.piccbid.entity.flydetail.Flydetail;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 14:19:36
 * @version V1.0
 */
public interface FlydetailService extends BaseService<Flydetail, String> {

	PageModel list(FlydetailDto dto);
	
	PageModel listVo(FlydetailDto dto);

}
