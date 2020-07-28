package com.dhcc.piccbid.web.rest.hospitalizationConditions;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.hospitalizationConditions.HospitalizationConditionsBlh;
import com.dhcc.piccbid.dto.hospitalizationAnalysis.HospitalizationAnalysisDto;
import com.dhcc.piccbid.dto.hospitalizationConditions.HospitalizationConditionsDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-24 16:19:35
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/hospitalizationConditions/hospitalizationConditions")
public class HospitalizationConditionsRest {

	@Resource
	private HospitalizationConditionsBlh hospitalizationConditionsBlh;

	@PostMapping(value="list",consumes="application/json")
	public Page listRest(@RequestBody(required=false) HospitalizationConditionsDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public Page list(HospitalizationConditionsDto dto) {
		hospitalizationConditionsBlh.list(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public HospitalizationConditionsDto saveRest(@RequestBody HospitalizationConditionsDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public HospitalizationConditionsDto save(HospitalizationConditionsDto dto) {
		hospitalizationConditionsBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public HospitalizationConditionsDto deleteRest(@RequestBody HospitalizationConditionsDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public HospitalizationConditionsDto delete(HospitalizationConditionsDto dto) {
		hospitalizationConditionsBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public HospitalizationConditionsDto findByIdRest(@RequestBody HospitalizationConditionsDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public HospitalizationConditionsDto findById(HospitalizationConditionsDto dto) {
		hospitalizationConditionsBlh.findById(dto);
		return dto;
	}
	
	@RequestMapping("getconditions")
	public Page hospitalizationConditions(HospitalizationConditionsDto dto) {
		hospitalizationConditionsBlh.hospitalizationConditions(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
		
	}
	@RequestMapping("getlist")
	public Page getList(HospitalizationConditionsDto dto) {
		hospitalizationConditionsBlh.getlist(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
		
	}
}
