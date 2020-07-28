package com.dhcc.piccbid.service.dictdiag;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.dictdiag.DictdiagDto;
import com.dhcc.piccbid.entity.dictdiag.Dictdiag;

import java.util.List;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-04-30 10:13:57
 * @version V1.0
 */
public interface DictdiagService extends BaseService<Dictdiag, String> {

	PageModel list(DictdiagDto dto);
	PageModel listSecondVo(DictdiagDto dto);
	String ztreeDiag(DictdiagDto dictdiagDto);
	void updateDiag(DictdiagDto dictdiagDto);
	List<Dictdiag> dictdiagList(DictdiagDto dictdiagDto);

}
