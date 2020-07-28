package com.dhcc.piccbid.service.unreasonableAdmission;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import com.dhcc.piccbid.entity.unreasonableAdmission.UnreasonableAdmission;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zhouwei
 * @date 2019-11-23 15:20:20
 * @version V1.0
 */
public interface UnreasonableAdmissionService extends BaseService<UnreasonableAdmission, String> {

	PageModel list(UnreasonableAdmissionDto dto);
    PageModel	listUnreasonableAdmission(UnreasonableAdmissionDto dto);
    PageModel	countUnreasonableAdmissions(UnreasonableAdmissionDto dto);
    SXSSFWorkbook getData(UnreasonableAdmissionDto dto);
    PageModel caseInfoList(UnreasonableAdmissionDto dto);
    PageModel findDict(UnreasonableAdmissionDto dto);

}
