package com.dhcc.piccbid.service.physicalExaminationAdmission;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
import com.dhcc.piccbid.dto.physicalExaminationAdmission.PhysicalExaminationAdmissionDto;
import com.dhcc.piccbid.entity.physicalExaminationAdmission.PhysicalExaminationAdmission;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-23 10:17:39
 * @version V1.0
 */
public interface PhysicalExaminationAdmissionService extends BaseService<PhysicalExaminationAdmission, String> {

	PageModel list(PhysicalExaminationAdmissionDto dto);

	PageModel physicalExamination(PhysicalExaminationAdmissionDto dto);

	PageModel sumTotalCount(PhysicalExaminationAdmissionDto dto);

	PageModel countphysicalExaminationMx(PhysicalExaminationAdmissionDto dto);

	XSSFWorkbook exportExcelToSelf(PhysicalExaminationAdmissionDto dto);
}
