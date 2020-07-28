package com.dhcc.piccbid.service.OverclockingAndAmountItem.Impl;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.data.repository.BaseRepository;
import com.dhcc.piccbid.dao.OverclockingAndAmountItem.OverclockingItemJDBC;
import com.dhcc.piccbid.dto.OverclockingAndAmountItem.OverclockingItemDto;
import com.dhcc.piccbid.entity.OverclockingAndAmountItem.OverclockingItemVo;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.PersonalInformationInquiryVo;
import com.dhcc.piccbid.entity.flycheckMedicalDetail.FlycheckMedicalDetail;
import com.dhcc.piccbid.service.OverclockingAndAmountItem.OverclockingItemService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OverclockingItemService")
public class OverclockingItemServiceImpl extends AbstractBaseService<FlycheckMedicalDetail,String> implements OverclockingItemService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private OverclockingItemJDBC overclockingItemJDBC;


    public OverclockingItemServiceImpl(BaseRepository<FlycheckMedicalDetail, String> baseRepository) {
        super(baseRepository);
    }

    @Override
    public PageModel searchList(OverclockingItemDto dto) {

        overclockingItemJDBC.searchList(dto);
        PageModel pageModel = dto.getPageModel();
       // commonService.fillSqlPageModelData(pageModel, OverclockingItemVo.class,null);
        return dto.getPageModel();
    }
    @Override
    public XSSFWorkbook exportExcel() {
        return overclockingItemJDBC.exportExcel();
    }
}
