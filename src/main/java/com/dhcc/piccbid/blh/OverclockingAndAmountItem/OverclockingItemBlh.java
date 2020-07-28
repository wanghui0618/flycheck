package com.dhcc.piccbid.blh.OverclockingAndAmountItem;


import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.OverclockingAndAmountItem.OverclockingItemDto;
import com.dhcc.piccbid.service.OverclockingAndAmountItem.OverclockingItemService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OverclockingItemBlh   extends BaseAbstractBlh<OverclockingItemDto> {

    @Autowired
    private OverclockingItemService overclockingItemService;


    public void searchList(BaseAbstractDto basedto) {
        OverclockingItemDto dto = super.getExactlyDto(basedto);
        //调用service查询方法
        PageModel pageModel=overclockingItemService.searchList(dto);
        //不要删除这行代码，调用set是为以后 service层要加缓存
        dto.setPageModel(pageModel);

    }

    public XSSFWorkbook exportExcel() {
        return overclockingItemService.exportExcel();
    }
}
