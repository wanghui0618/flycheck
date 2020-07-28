package com.dhcc.piccbid.web.rest.dataComparison;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.dhcc.piccbid.entity.page.Page;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.dataComparison.DataComparisonBlh;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.dataComparison.DataComparisonDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-11-26 16:14:42
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/dataComparison/dataComparison")
public class DataComparisonRest {

	@Resource
	private DataComparisonBlh dataComparisonBlh;

	@PostMapping(value = "list", consumes = "application/json")
	public PageModel listRest(@RequestBody(required = false) DataComparisonDto dto) {
		return this.list(dto);
	}

	@RequestMapping("list")
	public PageModel list(DataComparisonDto dto) {
		dataComparisonBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value = "save", consumes = "application/json")
	public DataComparisonDto saveRest(@RequestBody DataComparisonDto dto) {
		return this.save(dto);
	}

	@PostMapping("save")
	public DataComparisonDto save(DataComparisonDto dto) {
		dataComparisonBlh.save(dto);
		return dto;
	}

	@PostMapping(value = "delete", consumes = "application/json")
	public DataComparisonDto deleteRest(@RequestBody DataComparisonDto dto) {
		return this.delete(dto);
	}

	@PostMapping("delete")
	public DataComparisonDto delete(DataComparisonDto dto) {
		dataComparisonBlh.delete(dto);
		return dto;
	}

	@PostMapping(value = "findById", consumes = "application/json")
	public DataComparisonDto findByIdRest(@RequestBody DataComparisonDto dto) {
		return this.findById(dto);
	}

	@PostMapping("findById")
	public DataComparisonDto findById(DataComparisonDto dto) {
		dataComparisonBlh.findById(dto);
		return dto;
	}

	@RequestMapping(value = "table1", consumes = "application/json")
	public Page table1Rest(@RequestBody DataComparisonDto dto) {
		return this.table1(dto);
	}

	@RequestMapping("table1")
	public Page table1(DataComparisonDto dto) {
		dataComparisonBlh.table1(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping(value = "table2", consumes = "application/json")
	public Page table2Rest(@RequestBody DataComparisonDto dto) {
		return this.table2(dto);
	}

	@RequestMapping("table2")
	public Page table2(DataComparisonDto dto) {
		dataComparisonBlh.table2(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping(value = "table3", consumes = "application/json")
	public Page table3Rest(@RequestBody DataComparisonDto dto) {
		return this.table3(dto);
	}

	@RequestMapping("table3")
	public Page table3(DataComparisonDto dto) {
		dataComparisonBlh.table3(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping(value = "table45", consumes = "application/json")
	public Page table45Rest(@RequestBody DataComparisonDto dto) {
		return this.table45(dto);
	}

	@RequestMapping("table45")
	public Page table45(DataComparisonDto dto) {
		dataComparisonBlh.table45(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping(value = "table6", consumes = "application/json")
	public Page table6Rest(@RequestBody DataComparisonDto dto) {
		return this.table6(dto);
	}

	@RequestMapping("table6")
	public Page table6(DataComparisonDto dto) {
		dataComparisonBlh.table6(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping(value = "table7", consumes = "application/json")
	public Page table7Rest(@RequestBody DataComparisonDto dto) {
		return this.table7(dto);
	}

	@RequestMapping("table7")
	public Page table7(DataComparisonDto dto) {
		dataComparisonBlh.table7(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping(value = "table8_1", consumes = "application/json")
	public Page table8Rest(@RequestBody DataComparisonDto dto) {
		return this.table8_1(dto);
	}

	@RequestMapping("table8_1")
	public Page table8_1(DataComparisonDto dto) {
		dataComparisonBlh.table8_1(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping(value = "table8_2", consumes = "application/json")
	public Page table8_2Rest(@RequestBody DataComparisonDto dto) {
		return this.table8_2(dto);
	}

	@RequestMapping("table8_2")
	public Page table8_2(DataComparisonDto dto) {
		dataComparisonBlh.table8_2(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	// 导出到excel
	@RequestMapping("exportExcelToSelf")
	public void exportExcelToSelf(HttpServletResponse response, DataComparisonDto dto) throws IOException {

		SXSSFWorkbook wb = dataComparisonBlh.exportExcelToSelf(dto);
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=HospitalDataComparison.xlsx");
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

	}
}
