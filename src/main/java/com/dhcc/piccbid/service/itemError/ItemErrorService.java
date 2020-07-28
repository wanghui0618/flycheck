package com.dhcc.piccbid.service.itemError;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.itemError.ItemErrorDto;
import com.dhcc.piccbid.entity.itemError.ItemError;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-28 09:13:41
 * @version V1.0
 */
public interface ItemErrorService extends BaseService<ItemError, String> {

	PageModel list(ItemErrorDto dto);
	PageModel listHop(ItemErrorDto dto);
	PageModel listItem(ItemErrorDto dto);

}
