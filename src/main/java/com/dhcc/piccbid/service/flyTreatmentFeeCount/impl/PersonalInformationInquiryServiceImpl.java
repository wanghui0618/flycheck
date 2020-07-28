package com.dhcc.piccbid.service.flyTreatmentFeeCount.impl;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.data.repository.BaseRepository;
import com.dhcc.piccbid.dao.flyTreatmentFeeCount.PersonalInformationInquiryDao;
import com.dhcc.piccbid.dao.flyTreatmentFeeCount.PersonalInformationInquiryJdbc;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.PersonalInformationInquiryDto;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.DetailedInformationVo;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.FlyMedical;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.PersonalInformationInquiryVo;
import com.dhcc.piccbid.service.flyTreatmentFeeCount.PersonalInformationInquiryService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personalInformationInquiryService")
public class PersonalInformationInquiryServiceImpl extends AbstractBaseService<FlyMedical, String> implements PersonalInformationInquiryService {

    @Autowired
    private CommonService commonService;
    @Autowired
    PersonalInformationInquiryDao personalInformationInquiryDao;
    @Autowired
    PersonalInformationInquiryJdbc personalInformationInquiryJdbc;
    public PersonalInformationInquiryServiceImpl(BaseRepository<FlyMedical, String> baseRepository) {
        super(baseRepository);
    }

    @Override
    public PageModel search(PersonalInformationInquiryDto dto) {
        personalInformationInquiryJdbc.search(dto);
        PageModel pageModel = dto.getPageModel();
         commonService.fillSqlPageModelData(pageModel, PersonalInformationInquiryVo.class,null);
        return dto.getPageModel();
    }

    @Override
    public PageModel searchDetail(PersonalInformationInquiryDto dto) {
        personalInformationInquiryJdbc.searchDetail(dto);
        PageModel pageModel = dto.getPageModel();
        //commonService.fillSqlPageModelData(pageModel, DetailedInformationVo.class,null);
        return dto.getPageModel();
    }

    @Override
    public XSSFWorkbook exportExcel() {
        return personalInformationInquiryJdbc.exportExcel();
    }
}
