package com.dhcc.piccbid.web.rest.diseaseAndDiagnosticStatistics;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsBlh;
import com.dhcc.piccbid.dto.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsDto;
import com.dhcc.piccbid.entity.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatistics;
import com.dhcc.piccbid.entity.page.Page;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author scy
 * @date 2019-10-16 17:08:09
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics")
public class DiseaseAndDiagnosticStatisticsRest {

	@Resource
	private DiseaseAndDiagnosticStatisticsBlh diseaseAndDiagnosticStatisticsBlh;

	@PostMapping(value = "list", consumes = "application/json")
	public Page listRest(@RequestBody(required = false) DiseaseAndDiagnosticStatisticsDto dto) {
		return this.list(dto);
	}

	@RequestMapping("list")
	public Page list(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsBlh.list(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		System.out.print(page.getData());
		return page;
	}

	@PostMapping(value = "save", consumes = "application/json")
	public DiseaseAndDiagnosticStatisticsDto saveRest(@RequestBody DiseaseAndDiagnosticStatisticsDto dto) {
		return this.save(dto);
	}

	@PostMapping("save")
	public DiseaseAndDiagnosticStatisticsDto save(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsBlh.save(dto);
		return dto;
	}

	@PostMapping(value = "delete", consumes = "application/json")
	public DiseaseAndDiagnosticStatisticsDto deleteRest(@RequestBody DiseaseAndDiagnosticStatisticsDto dto) {
		return this.delete(dto);
	}

	@PostMapping("delete")
	public DiseaseAndDiagnosticStatisticsDto delete(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsBlh.delete(dto);
		return dto;
	}

	@PostMapping(value = "findById", consumes = "application/json")
	public DiseaseAndDiagnosticStatisticsDto findByIdRest(@RequestBody DiseaseAndDiagnosticStatisticsDto dto) {
		return this.findById(dto);
	}

	@PostMapping("findById")
	public DiseaseAndDiagnosticStatisticsDto findById(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsBlh.findById(dto);
		return dto;
	}

	@RequestMapping("getlist")
	public Page getlist(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsBlh.getlist(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("getlistByinhosDiag")
	public Page getlistByinhosDiag(DiseaseAndDiagnosticStatisticsDto dto) {
		/* System.out.println("rest"+dto.getInhosDiag()); */
		diseaseAndDiagnosticStatisticsBlh.getlistByinhosDiag(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("getlistByinhosDate")
	public Page getlistByinhosDate(DiseaseAndDiagnosticStatisticsDto dto) {
		/* System.out.println("rest"+dto.getInhosDiag()); */
		diseaseAndDiagnosticStatisticsBlh.getlistByinhosDate(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("statisticalAnalysisByInsuranceType")
	public Page statisticalAnalysisByInsuranceType(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsBlh.statisticalAnalysisByInsuranceType(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

}
