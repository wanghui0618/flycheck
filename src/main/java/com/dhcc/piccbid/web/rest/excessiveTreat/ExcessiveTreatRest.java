package com.dhcc.piccbid.web.rest.excessiveTreat;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.excessiveTreat.ExcessiveTreatBlh;
import com.dhcc.piccbid.dto.excessiveTreat.ExcessiveTreatDto;
//import com.dhcc.piccbid.dto.indicationTreat.IndicationTreatDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-14 10:11:17
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/excessiveTreat/excessiveTreat")
public class ExcessiveTreatRest {

	@Resource
	private ExcessiveTreatBlh excessiveTreatBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) ExcessiveTreatDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(ExcessiveTreatDto dto) {
		excessiveTreatBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public ExcessiveTreatDto saveRest(@RequestBody ExcessiveTreatDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public ExcessiveTreatDto save(ExcessiveTreatDto dto) {
		excessiveTreatBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public ExcessiveTreatDto deleteRest(@RequestBody ExcessiveTreatDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public ExcessiveTreatDto delete(ExcessiveTreatDto dto) {
		excessiveTreatBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public ExcessiveTreatDto findByIdRest(@RequestBody ExcessiveTreatDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public ExcessiveTreatDto findById(ExcessiveTreatDto dto) {
		excessiveTreatBlh.findById(dto);
		return dto;
	}
	@RequestMapping("listVo")
	public Page listVo(ExcessiveTreatDto dto) {
		excessiveTreatBlh.listVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
}
