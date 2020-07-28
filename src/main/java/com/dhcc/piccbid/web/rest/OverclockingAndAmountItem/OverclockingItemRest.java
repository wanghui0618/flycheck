package com.dhcc.piccbid.web.rest.OverclockingAndAmountItem;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.blh.OverclockingAndAmountItem.OverclockingItemBlh;
import com.dhcc.piccbid.dto.OverclockingAndAmountItem.OverclockingItemDto;
import com.dhcc.piccbid.entity.OverclockingAndAmountItem.OverclockingItemVo;
import com.dhcc.piccbid.entity.page.Page;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/dhccApi/OverclockingAndAmountItem/OverclockingAndAmountItem")
public class OverclockingItemRest  {

    @Autowired
    private OverclockingItemBlh overclockingItemBlh;

    @RequestMapping("searchList")
    public Page searchItem(OverclockingItemDto dto){
        Page page=new Page();
        overclockingItemBlh.searchList(dto);
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    //导出到excel
    @RequestMapping("exportExcel")
    public XSSFWorkbook exportExcel(HttpServletResponse response) throws IOException {
        XSSFWorkbook wb =overclockingItemBlh.exportExcel();
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=超量项目.xls");
        response.setContentType("application/msexcel");
        try {
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;

    }
}
