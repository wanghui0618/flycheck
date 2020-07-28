package com.dhcc.piccbid.service.dict;

import java.util.List;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.dict.DictDto;
import com.dhcc.piccbid.entity.dict.Dict;
import com.dhcc.piccbid.entity.dict.DictRequestVo;
import com.dhcc.piccbid.entity.dict.DictResponseVo;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-15 09:58:13
 * @version V1.0
 */
public interface DictService extends BaseService<Dict, String> {

	PageModel list(DictDto dto);
	public List<DictResponseVo> findDict(DictRequestVo dictRequestVo);
}
