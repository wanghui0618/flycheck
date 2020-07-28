package com.dhcc.piccbid.blh.flyTreatmentFeeCount;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.PersonalInformationInquiryDto;
import com.dhcc.piccbid.service.flyTreatmentFeeCount.PersonalInformationInquiryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class PersonalInformationInquiryBlh extends BaseAbstractBlh<PersonalInformationInquiryDto> {
    private static Log logger = LogFactory.getLog(PersonalInformationInquiryBlh.class);

    @Resource
    private PersonalInformationInquiryService personalInformationInquiryService;

    public PersonalInformationInquiryBlh() {
        logger.debug("PersonalInformationInquiryBlh Init");
    }

    /**
     *
     * 进入某个列表的入口方法（分页查询方法）
     * @param basedto
     */
    public void search(BaseAbstractDto basedto) {
        PersonalInformationInquiryDto dto = super.getExactlyDto(basedto);
        //调用service查询方法
        PageModel pageModel=personalInformationInquiryService.search(dto);
        //不要删除这行代码，调用set是为以后 service层要加缓存
        dto.setPageModel(pageModel);
    }
    public void searchDetail(BaseAbstractDto basedto) {
        PersonalInformationInquiryDto dto = super.getExactlyDto(basedto);
        //调用service查询方法
        PageModel pageModel=personalInformationInquiryService.searchDetail(dto);
        //不要删除这行代码，调用set是为以后 service层要加缓存
        dto.setPageModel(pageModel);
    }



    public XSSFWorkbook exportExcel() {
        return personalInformationInquiryService.exportExcel();
    }
}
