package com.dhcc.piccbid.service.OverclockingAndAmountItem;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.OverclockingAndAmountItem.OverclockingItemDto;
import com.dhcc.piccbid.entity.flycheckMedicalDetail.FlycheckMedicalDetail;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;


public interface OverclockingItemService extends BaseService<FlycheckMedicalDetail,String>{
     PageModel searchList(OverclockingItemDto dto);

    XSSFWorkbook exportExcel();
}
