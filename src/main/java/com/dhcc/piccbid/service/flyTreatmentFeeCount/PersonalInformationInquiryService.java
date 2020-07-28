package com.dhcc.piccbid.service.flyTreatmentFeeCount;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.PersonalInformationInquiryDto;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.DetailedInformationVo;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.FlyMedical;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface PersonalInformationInquiryService extends BaseService<FlyMedical, String> {

    PageModel search(PersonalInformationInquiryDto dto);
    PageModel searchDetail(PersonalInformationInquiryDto dto);

    XSSFWorkbook exportExcel();
}
