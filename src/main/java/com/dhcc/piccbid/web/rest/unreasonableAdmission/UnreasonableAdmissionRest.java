package com.dhcc.piccbid.web.rest.unreasonableAdmission;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhcc.piccbid.dto.findDict.FindDictDto;
import com.dhcc.piccbid.entity.page.Page;
import com.dhcc.piccbid.service.unreasonableAdmission.UnreasonableAdmissionService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.unreasonableAdmission.UnreasonableAdmissionBlh;
import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zhouwei
 * @date 2019-11-23 15:20:20
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/unreasonableAdmission/unreasonableAdmission")
public class UnreasonableAdmissionRest {

	@Resource
	private UnreasonableAdmissionBlh unreasonableAdmissionBlh;
	@Resource
	private UnreasonableAdmissionService unreasonableAdmissionService;
	@PostMapping(value = "caseInfos",consumes = "application/json")
	public Page listcaseInfosRests(@RequestBody(required = false) UnreasonableAdmissionDto dto){
		return this.caseInfos(dto);
	}
	@RequestMapping("caseInfos")
	public Page caseInfos(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionBlh.caseInfoList(dto);
		Page page=new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}

	@PostMapping(value = "countUnreasonableAdmissions",consumes = "application/json")
	public Page listUnreasonableAdmissionRests(@RequestBody(required = false) UnreasonableAdmissionDto dto){
		return this.countUnreasonableAdmissions(dto);
	}
	@RequestMapping("countUnreasonableAdmissions")
	public Page countUnreasonableAdmissions(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionBlh.countUnreasonableAdmissions(dto);
		Page page=new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}
	@PostMapping(value = "listUnreasonableAdmission",consumes = "application/json")
	public Page listUnreasonableAdmissionRest(@RequestBody(required = false) UnreasonableAdmissionDto dto){
		return this.listUnreasonableAdmission(dto);
	}
	@RequestMapping("listUnreasonableAdmission")
	public Page listUnreasonableAdmission(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionBlh.listUnreasonableAdmission(dto);
		Page page=new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}
	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) UnreasonableAdmissionDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public UnreasonableAdmissionDto saveRest(@RequestBody UnreasonableAdmissionDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public UnreasonableAdmissionDto save(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public UnreasonableAdmissionDto deleteRest(@RequestBody UnreasonableAdmissionDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public UnreasonableAdmissionDto delete(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public UnreasonableAdmissionDto findByIdRest(@RequestBody UnreasonableAdmissionDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public UnreasonableAdmissionDto findById(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionBlh.findById(dto);
		return dto;
	}

	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletResponse response,UnreasonableAdmissionDto dto) throws IOException {
		SXSSFWorkbook wb =unreasonableAdmissionBlh.getData(dto);
		OutputStream out = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=UnreasonableAdmission.xls");
		response.setContentType("application/msexcel");
		try {
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
	@RequestMapping("fyType")
	public Map<String, Object> findDict(UnreasonableAdmissionDto dto) {
		return unreasonableAdmissionBlh.findDict(dto);
	}
}
