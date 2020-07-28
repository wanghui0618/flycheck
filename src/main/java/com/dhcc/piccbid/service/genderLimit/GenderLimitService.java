package com.dhcc.piccbid.service.genderLimit;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.genderLimit.GenderLimitDto;
import com.dhcc.piccbid.entity.genderLimit.GenderLimit;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2020-01-03 10:32:21
 * @version V1.0
 */
public interface GenderLimitService extends BaseService<GenderLimit, String> {

	PageModel list(GenderLimitDto dto);

	PageModel genderLimit(GenderLimitDto dto);

	PageModel gather(GenderLimitDto dto);

	XSSFWorkbook exportExcel(GenderLimitDto dto);

	PageModel detail(GenderLimitDto dto);

}
