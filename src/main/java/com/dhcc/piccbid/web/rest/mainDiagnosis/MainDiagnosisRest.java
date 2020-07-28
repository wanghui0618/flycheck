package com.dhcc.piccbid.web.rest.mainDiagnosis;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.mainDiagnosis.MainDiagnosisBlh;
import com.dhcc.piccbid.dto.mainDiagnosis.MainDiagnosisDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-11-08 16:53:31
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/mainDiagnosis/mainDiagnosis")
public class MainDiagnosisRest {

	@Resource
	private MainDiagnosisBlh mainDiagnosisBlh;

	@PostMapping(value="list",consumes="application/json")
	public Page listRest(@RequestBody(required=false) MainDiagnosisDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public Page list(MainDiagnosisDto dto) {
		
		mainDiagnosisBlh.list(dto);
		
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public MainDiagnosisDto saveRest(@RequestBody MainDiagnosisDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public MainDiagnosisDto save(MainDiagnosisDto dto) {
		mainDiagnosisBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public MainDiagnosisDto deleteRest(@RequestBody MainDiagnosisDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public MainDiagnosisDto delete(MainDiagnosisDto dto) {
		mainDiagnosisBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public MainDiagnosisDto findByIdRest(@RequestBody MainDiagnosisDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public MainDiagnosisDto findById(MainDiagnosisDto dto) {
		mainDiagnosisBlh.findById(dto);
		return dto;
	}
}
