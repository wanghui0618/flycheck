package com.dhcc.piccbid.web.rest.flyTreatmentFeeCount;

import com.dhcc.piccbid.blh.flyTreatmentFeeCount.PersonalInformationInquiryBlh;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.PersonalInformationInquiryDto;
import com.dhcc.piccbid.entity.page.Page;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@RestController
@RequestMapping("/dhccApi/flyTreatmentFeeCount/personalInformationInquiry")
public class PersonalInformationInquiryRest {

    @Autowired
   private PersonalInformationInquiryBlh personalInformationInquiryBlh;

    @RequestMapping("search")
    public Page list(PersonalInformationInquiryDto dto) {
        Page page=new Page();
        personalInformationInquiryBlh.search(dto);
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
    @RequestMapping("searchDetail")
    public Page searchDetail(PersonalInformationInquiryDto dto) {
        Page page=new Page();
        personalInformationInquiryBlh.searchDetail(dto);
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
    //导出到excel
    @RequestMapping("exportExcel")
    public XSSFWorkbook exportExcel(HttpServletResponse response) throws IOException {
        XSSFWorkbook wb =personalInformationInquiryBlh.exportExcel();
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=personalInformationInquiry.xls");
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
