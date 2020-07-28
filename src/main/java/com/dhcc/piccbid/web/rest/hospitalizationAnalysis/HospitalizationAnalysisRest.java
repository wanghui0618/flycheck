package com.dhcc.piccbid.web.rest.hospitalizationAnalysis;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.hospitalizationAnalysis.HospitalizationAnalysisBlh;
import com.dhcc.piccbid.dto.hospitalizationAnalysis.HospitalizationAnalysisDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-17 12:54:11
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/hospitalizationAnalysis/hospitalizationAnalysis")
public class HospitalizationAnalysisRest {

	@Resource
	private HospitalizationAnalysisBlh hospitalizationAnalysisBlh;

	@PostMapping(value="list",consumes="application/json")
	public Page listRest(@RequestBody(required=false) HospitalizationAnalysisDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public Page list(HospitalizationAnalysisDto dto) {
		hospitalizationAnalysisBlh.list(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public HospitalizationAnalysisDto saveRest(@RequestBody HospitalizationAnalysisDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public HospitalizationAnalysisDto save(HospitalizationAnalysisDto dto) {
		hospitalizationAnalysisBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public HospitalizationAnalysisDto deleteRest(@RequestBody HospitalizationAnalysisDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public HospitalizationAnalysisDto delete(HospitalizationAnalysisDto dto) {
		hospitalizationAnalysisBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public HospitalizationAnalysisDto findByIdRest(@RequestBody HospitalizationAnalysisDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public HospitalizationAnalysisDto findById(HospitalizationAnalysisDto dto) {
		hospitalizationAnalysisBlh.findById(dto);
		return dto;
	}
	@RequestMapping("hospitalizationAnalysis")
	public Page hospitalizationAnalysis(HospitalizationAnalysisDto dto) {
		hospitalizationAnalysisBlh.hospitalizationAnalysis(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
}
