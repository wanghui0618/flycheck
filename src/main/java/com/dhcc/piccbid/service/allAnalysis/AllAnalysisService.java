package com.dhcc.piccbid.service.allAnalysis;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.allAnalysis.AllAnalysisDto;
import com.dhcc.piccbid.dto.menu.MenuDto;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysis;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangjieli
 * @date 2019-08-25 16:54:00
 * @version V1.0
 */
public interface AllAnalysisService extends BaseService<AllAnalysis, String> {

	PageModel list(AllAnalysisDto dto);

	/**
	 * @param dto
	 * @return
	 */
	PageModel search1(AllAnalysisDto dto);

	/**
	 * @param dto
	 * @return
	 */
	PageModel search2(String gid,AllAnalysisDto dto);

}
