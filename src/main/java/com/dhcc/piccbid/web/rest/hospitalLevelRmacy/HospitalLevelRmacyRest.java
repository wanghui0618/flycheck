package com.dhcc.piccbid.web.rest.hospitalLevelRmacy;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.piccbid.blh.hospitalLevelRmacy.HospitalLevelRmacyBlh;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-11-27 10:00:37
 * @version V1.0
 */
import com.dhcc.piccbid.entity.page.Page;
@RestController
@RequestMapping("/dhccApi/hospitalLevelRmacy/hospitalLevelRmacy")
public class HospitalLevelRmacyRest {

	@Resource
	private HospitalLevelRmacyBlh hospitalLevelRmacyBlh;
	
	
	//限医院等级用药主单
	@RequestMapping("limitedHospitalLevelZd")
    public Page limitedHospitalLevelZd(HospitalLevelRmacyDto dto) {
    	Page page = new Page();
    	hospitalLevelRmacyBlh.limitedHospitalLevelZd(dto);
    	page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	
	//限医院等级用药明细
	@RequestMapping("limitedHospitalLevelMx")
    public Page limitedHospitalLevelMx(HospitalLevelRmacyDto dto) {
    	Page page = new Page();
    	hospitalLevelRmacyBlh.limitedHospitalLevelMx(dto);
    	page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	
	//体检式入院明细
	@RequestMapping("physicalExaminationMx")
    public Page physicalExaminationMx(HospitalLevelRmacyDto dto) {
    	Page page = new Page();
    	hospitalLevelRmacyBlh.physicalExaminationMx(dto);
    	page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	
	//主单统计
	@RequestMapping("countMedical")
    public Page countMedical(HospitalLevelRmacyDto dto) {
    	Page page = new Page();
    	hospitalLevelRmacyBlh.countMedical(dto);
    	page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	
	//明细统计
	@RequestMapping("countMx")
    public Page countMx(HospitalLevelRmacyDto dto) {
    	Page page = new Page();
    	hospitalLevelRmacyBlh.countMx(dto);
    	page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	
	//导出到excel
	@RequestMapping("exportExcel")
	public void exportExcel(HospitalLevelRmacyDto dto,HttpServletResponse response) throws IOException {
		SXSSFWorkbook wb =hospitalLevelRmacyBlh.exportExcel(dto);
		response.setContentType("application/x-msdownload");
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=hospitalLevelRmacy.xls");
		response.flushBuffer();
		OutputStream out=response.getOutputStream();
		try {
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
		

	}


	
}
