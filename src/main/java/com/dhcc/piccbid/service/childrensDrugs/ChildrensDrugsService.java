package com.dhcc.piccbid.service.childrensDrugs;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.childrensDrugs.ChildrensDrugsDto;
import com.dhcc.piccbid.entity.childrensDrugs.ChildrensDrugs;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2019-11-23 13:32:56
 * @version V1.0
 */
public interface ChildrensDrugsService extends BaseService<ChildrensDrugs, String> {

	PageModel list(ChildrensDrugsDto dto);

	PageModel childrensDrugs(ChildrensDrugsDto dto);

	PageModel gather(ChildrensDrugsDto dto);

	XSSFWorkbook exportExcel(ChildrensDrugsDto dto);

	PageModel detail(ChildrensDrugsDto dto);
}
