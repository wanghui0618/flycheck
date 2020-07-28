package com.dhcc.piccbid.web.rest.statisticsInRecentTwoYears;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsBlh;
import com.dhcc.piccbid.dto.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsDto;
import com.dhcc.piccbid.entity.page.Page;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-08 12:08:41
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems")
public class StatisticsInRecentTwoYearsRest {

	@Resource
	private StatisticsInRecentTwoYearsBlh statisticsInRecentTwoYearsBlh;

	@PostMapping(value = "list", consumes = "application/json")
	public PageModel listRest(@RequestBody(required = false) StatisticsInRecentTwoYearsDto dto) {
		return this.list(dto);
	}

	@RequestMapping("list")
	public PageModel list(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value = "save", consumes = "application/json")
	public StatisticsInRecentTwoYearsDto saveRest(@RequestBody StatisticsInRecentTwoYearsDto dto) {
		return this.save(dto);
	}

	@PostMapping("save")
	public StatisticsInRecentTwoYearsDto save(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsBlh.save(dto);
		return dto;
	}

	@PostMapping(value = "delete", consumes = "application/json")
	public StatisticsInRecentTwoYearsDto deleteRest(@RequestBody StatisticsInRecentTwoYearsDto dto) {
		return this.delete(dto);
	}

	@PostMapping("delete")
	public StatisticsInRecentTwoYearsDto delete(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsBlh.delete(dto);
		return dto;
	}

	@PostMapping(value = "findById", consumes = "application/json")
	public StatisticsInRecentTwoYearsDto findByIdRest(@RequestBody StatisticsInRecentTwoYearsDto dto) {
		return this.findById(dto);
	}

	@PostMapping("findById")
	public StatisticsInRecentTwoYearsDto findById(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsBlh.findById(dto);
		return dto;
	}

	@RequestMapping("dosage")
	public Page Dosage(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsBlh.Dosage(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("getCount")
	public Page getCount(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsBlh.getCount(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("getlist")
	public Page getlist(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsBlh.getlist(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("getlistByinhosDiag")
	public Page getlistByinhosDiag(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsBlh.getlistByinhosDiag(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("statisticalAnalysisByInsuranceType")
	public Page statisticalAnalysisByInsuranceType(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsBlh.statisticalAnalysisByInsuranceType(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
}
