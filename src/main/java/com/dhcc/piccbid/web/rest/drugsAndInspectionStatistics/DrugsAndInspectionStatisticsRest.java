package com.dhcc.piccbid.web.rest.drugsAndInspectionStatistics;

import javax.annotation.Resource;

import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsBlh;
import com.dhcc.piccbid.dto.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsDto;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @date 2019-10-15 16:48:40
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/drugsAndInspectionStatistics/drugsAndInspectionStatistics")
public class DrugsAndInspectionStatisticsRest {

	@Resource
	private DrugsAndInspectionStatisticsBlh drugsAndInspectionStatisticsBlh;

	@RequestMapping(value="list",consumes="application/json")
	public Page listRest(@RequestBody(required=false) DrugsAndInspectionStatisticsDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public Page list(DrugsAndInspectionStatisticsDto dto) {
		drugsAndInspectionStatisticsBlh.list(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("listForCostDetail")
	public Page listForCostDetail(DrugsAndInspectionStatisticsDto dto) {
		drugsAndInspectionStatisticsBlh.listForCostDetail(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	@RequestMapping("listForInselectionCostDetail")
	public Page listForInselectionCostDetail(DrugsAndInspectionStatisticsDto dto) {
		drugsAndInspectionStatisticsBlh.listForInselectionCostDetail(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping(value="listForInspection",consumes="application/json")
	public Page listForInspectionRest(@RequestBody(required=false) DrugsAndInspectionStatisticsDto dto) {
		return this.list(dto);
	}
	@RequestMapping("listForInspection")
	public Page listForInspection(DrugsAndInspectionStatisticsDto dto) {
		drugsAndInspectionStatisticsBlh.listForInspection(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public DrugsAndInspectionStatisticsDto saveRest(@RequestBody DrugsAndInspectionStatisticsDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public DrugsAndInspectionStatisticsDto save(DrugsAndInspectionStatisticsDto dto) {
		drugsAndInspectionStatisticsBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public DrugsAndInspectionStatisticsDto deleteRest(@RequestBody DrugsAndInspectionStatisticsDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public DrugsAndInspectionStatisticsDto delete(DrugsAndInspectionStatisticsDto dto) {
		drugsAndInspectionStatisticsBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public DrugsAndInspectionStatisticsDto findByIdRest(@RequestBody DrugsAndInspectionStatisticsDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public DrugsAndInspectionStatisticsDto findById(DrugsAndInspectionStatisticsDto dto) {
		drugsAndInspectionStatisticsBlh.findById(dto);
		return dto;
	}
}
