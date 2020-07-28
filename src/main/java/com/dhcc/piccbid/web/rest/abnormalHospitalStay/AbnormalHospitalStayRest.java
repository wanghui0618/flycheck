package com.dhcc.piccbid.web.rest.abnormalHospitalStay;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.abnormalHospitalStay.AbnormalHospitalStayBlh;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.findDict.FindDictDto;
import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import com.dhcc.piccbid.entity.page.Page;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-11-23 13:31:41
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/abnormalHospitalStay/abnormalHospitalStay")
public class AbnormalHospitalStayRest {

	@Resource
	private AbnormalHospitalStayBlh abnormalHospitalStayBlh;

	@PostMapping(value = "list", consumes = "application/json")
	public PageModel listRest(@RequestBody(required = false) AbnormalHospitalStayDto dto) {
		return this.list(dto);
	}

	@RequestMapping("list")
	public PageModel list(AbnormalHospitalStayDto dto) {
		abnormalHospitalStayBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value = "save", consumes = "application/json")
	public AbnormalHospitalStayDto saveRest(@RequestBody AbnormalHospitalStayDto dto) {
		return this.save(dto);
	}

	@PostMapping("save")
	public AbnormalHospitalStayDto save(AbnormalHospitalStayDto dto) {
		abnormalHospitalStayBlh.save(dto);
		return dto;
	}

	@PostMapping(value = "delete", consumes = "application/json")
	public AbnormalHospitalStayDto deleteRest(@RequestBody AbnormalHospitalStayDto dto) {
		return this.delete(dto);
	}

	@PostMapping("delete")
	public AbnormalHospitalStayDto delete(AbnormalHospitalStayDto dto) {
		abnormalHospitalStayBlh.delete(dto);
		return dto;
	}

	@PostMapping(value = "findById", consumes = "application/json")
	public AbnormalHospitalStayDto findByIdRest(@RequestBody AbnormalHospitalStayDto dto) {
		return this.findById(dto);
	}

	@PostMapping("findById")
	public AbnormalHospitalStayDto findById(AbnormalHospitalStayDto dto) {
		abnormalHospitalStayBlh.findById(dto);
		return dto;
	}

	@RequestMapping("abnormalHospitalStay")
	public Page abnormalHospitalStay(AbnormalHospitalStayDto dto) {
		abnormalHospitalStayBlh.abnormalHospitalStay(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value = "countabnormalHospitalStay", consumes = "application/json")
	public Page countabnormalHospitalStay(@RequestBody(required = false) UnreasonableAdmissionDto dto) {
		return this.countabnormalHospitalStay(dto);
	}

	@RequestMapping("countabnormalHospitalStay")
	public Page countabnormalHospitalStay(AbnormalHospitalStayDto dto) {
		abnormalHospitalStayBlh.countabnormalHospitalStay(dto);
		Page page = new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}

	@PostMapping(value = "detaileTable", consumes = "application/json")
	public Page detaileTableRest(@RequestBody(required = false) AbnormalHospitalStayDto dto) {
		return this.detaileTable(dto);
	}
	
	@RequestMapping("detaileTable")
	public Page detaileTable(AbnormalHospitalStayDto dto) {
		abnormalHospitalStayBlh.detaileTable(dto);
		Page page = new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}
	
	//导出到excel
	@RequestMapping("exportExcelToSelf")
	public void  exportExcelToSelf(HttpServletResponse response,AbnormalHospitalStayDto dto) throws IOException {
		
		SXSSFWorkbook wb =abnormalHospitalStayBlh.exportExcelToSelf(dto);
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=AbnormalHospitalStay.xls");
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
		
	}
	
	
}
