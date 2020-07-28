package com.dhcc.piccbid.service.findDict;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.findDict.FindDictDto;
import com.dhcc.piccbid.entity.findDict.FindDict;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-08-29 10:26:51
 * @version V1.0
 */
public interface FindDictService extends BaseService<FindDict, String> {

	PageModel list(FindDictDto dto);
	PageModel findDict(FindDictDto dto);
	PageModel fyType(FindDictDto dto);
	PageModel findpCategory(FindDictDto dto);
}
