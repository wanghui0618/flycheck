package com.dhcc.piccbid.web.rest.flymedical;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.piccbid.blh.flyMedicalrecord.FlyMedicalrecordBlh;
import com.dhcc.piccbid.dto.flymedical.FlymedicalDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 09:57:52
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/flymedical/flymedical")
public class FlymedicalRest {

	@Resource
	private FlyMedicalrecordBlh flymedicalBlh;

	@PostMapping(value="list",consumes="application/json")
	public Page listRest(@RequestBody(required=false) FlymedicalDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public Page list(FlymedicalDto dto) {
		flymedicalBlh.list(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	@RequestMapping("screenSameEntryAndExitDate")
	public Page screenSameEntryAndExitDate(FlymedicalDto dto) {
		flymedicalBlh.screenSameEntryAndExitDate(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("listVo")
	public Page listVo(FlymedicalDto dto) {
		flymedicalBlh.listVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public FlymedicalDto saveRest(@RequestBody FlymedicalDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlymedicalDto save(FlymedicalDto dto) {
		flymedicalBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlymedicalDto deleteRest(@RequestBody FlymedicalDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlymedicalDto delete(FlymedicalDto dto) {
		flymedicalBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlymedicalDto findByIdRest(@RequestBody FlymedicalDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlymedicalDto findById(FlymedicalDto dto) {
		flymedicalBlh.findById(dto);
		return dto;
	}
	
	/*
	 * 通过主要诊断查找信息
	 */
	@RequestMapping("relateDiagnosis")
	public Page relateDiagnosis(FlymedicalDto dto) {
		flymedicalBlh.relateDiagnosis(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
}
