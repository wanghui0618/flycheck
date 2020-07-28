package com.dhcc.piccbid.web.rest.physicalExaminationAdmission;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.physicalExaminationAdmission.PhysicalExaminationAdmissionBlh;
import com.dhcc.piccbid.dto.physicalExaminationAdmission.PhysicalExaminationAdmissionDto;
import com.dhcc.piccbid.entity.page.Page;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-23 10:17:39
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission")
public class PhysicalExaminationAdmissionRest {

	@Resource
	private PhysicalExaminationAdmissionBlh physicalExaminationAdmissionBlh;

	@PostMapping(value = "list", consumes = "application/json")
	public PageModel listRest(@RequestBody(required = false) PhysicalExaminationAdmissionDto dto) {
		return this.list(dto);
	}

	@RequestMapping("list")
	public PageModel list(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value = "save", consumes = "application/json")
	public PhysicalExaminationAdmissionDto saveRest(@RequestBody PhysicalExaminationAdmissionDto dto) {
		return this.save(dto);
	}

	@PostMapping("save")
	public PhysicalExaminationAdmissionDto save(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionBlh.save(dto);
		return dto;
	}

	@PostMapping(value = "delete", consumes = "application/json")
	public PhysicalExaminationAdmissionDto deleteRest(@RequestBody PhysicalExaminationAdmissionDto dto) {
		return this.delete(dto);
	}

	@PostMapping("delete")
	public PhysicalExaminationAdmissionDto delete(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionBlh.delete(dto);
		return dto;
	}

	@PostMapping(value = "findById", consumes = "application/json")
	public PhysicalExaminationAdmissionDto findByIdRest(@RequestBody PhysicalExaminationAdmissionDto dto) {
		return this.findById(dto);
	}

	@PostMapping("findById")
	public PhysicalExaminationAdmissionDto findById(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionBlh.findById(dto);
		return dto;
	}

	@RequestMapping("physicalExamination")
	public Page countDiagnosisAndTreatmentItems(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionBlh.physicalExamination(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("sumTotalCount")
	public Page sumTotalCount(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionBlh.sumTotalCount(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		return page;
	}
	
	@RequestMapping("countphysicalExaminationMx")
	public Page countphysicalExaminationMx(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionBlh.countphysicalExaminationMx(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		return page;
	}

	// 导出到excel
	@RequestMapping("exportExcelToSelf")
	public XSSFWorkbook exportExcelToSelf(HttpServletResponse response,PhysicalExaminationAdmissionDto dto) throws IOException {
		XSSFWorkbook wb = physicalExaminationAdmissionBlh.exportExcelToSelf(dto);
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=体检式入院.xlsx");
		response.setContentType("application/msexcel");
		try {
			wb.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
