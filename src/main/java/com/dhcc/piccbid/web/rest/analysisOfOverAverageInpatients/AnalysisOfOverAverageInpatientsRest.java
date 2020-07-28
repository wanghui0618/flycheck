package com.dhcc.piccbid.web.rest.analysisOfOverAverageInpatients;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsBlh;
import com.dhcc.piccbid.dto.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsDto;
import com.dhcc.piccbid.entity.page.Page;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author scy
 * @date 2019-10-24 15:16:38
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/emptyHangingBedAnalysis/emptyHangingBedAnalysis")
public class AnalysisOfOverAverageInpatientsRest {

	@Resource
	private AnalysisOfOverAverageInpatientsBlh analysisOfOverAverageInpatientsBlh;

	@PostMapping(value = "list", consumes = "application/json")
	public Page listRest(@RequestBody(required = false) AnalysisOfOverAverageInpatientsDto dto) {
		return this.list(dto);
	}

	@RequestMapping("list")
	public Page list(AnalysisOfOverAverageInpatientsDto dto) {
		analysisOfOverAverageInpatientsBlh.list(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value = "save", consumes = "application/json")
	public AnalysisOfOverAverageInpatientsDto saveRest(@RequestBody AnalysisOfOverAverageInpatientsDto dto) {
		return this.save(dto);
	}

	@PostMapping("save")
	public AnalysisOfOverAverageInpatientsDto save(AnalysisOfOverAverageInpatientsDto dto) {
		analysisOfOverAverageInpatientsBlh.save(dto);
		return dto;
	}

	@PostMapping(value = "delete", consumes = "application/json")
	public AnalysisOfOverAverageInpatientsDto deleteRest(@RequestBody AnalysisOfOverAverageInpatientsDto dto) {
		return this.delete(dto);
	}

	@PostMapping("delete")
	public AnalysisOfOverAverageInpatientsDto delete(AnalysisOfOverAverageInpatientsDto dto) {
		analysisOfOverAverageInpatientsBlh.delete(dto);
		return dto;
	}

	@PostMapping(value = "findById", consumes = "application/json")
	public AnalysisOfOverAverageInpatientsDto findByIdRest(@RequestBody AnalysisOfOverAverageInpatientsDto dto) {
		return this.findById(dto);
	}

	@PostMapping("findById")
	public AnalysisOfOverAverageInpatientsDto findById(AnalysisOfOverAverageInpatientsDto dto) {
		analysisOfOverAverageInpatientsBlh.findById(dto);
		return dto;
	}

	@RequestMapping("getCount")
	public Page getCount(AnalysisOfOverAverageInpatientsDto dto) {
		/* System.out.println("rest"+dto.getInhosDiag()); */
		analysisOfOverAverageInpatientsBlh.getCount(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("towYears")
	public Page towYears(AnalysisOfOverAverageInpatientsDto dto) {
		/* System.out.println("rest"+dto.getInhosDiag()); */
		analysisOfOverAverageInpatientsBlh.towYears(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("countDiagnosisAndTreatmentItems")
	public Page countDiagnosisAndTreatmentItems(AnalysisOfOverAverageInpatientsDto dto) {
		analysisOfOverAverageInpatientsBlh.countDiagnosisAndTreatmentItems(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
}
