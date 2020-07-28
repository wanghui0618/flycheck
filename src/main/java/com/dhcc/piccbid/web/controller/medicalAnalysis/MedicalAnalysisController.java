package com.dhcc.piccbid.web.controller.medicalAnalysis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangyue
 * @date 2019-08-08 17:39:46
 * @version V1.0
 */
@Controller
public class MedicalAnalysisController {

	@RequestMapping("/autonomousAnalysis/autonomousAnalysis")
	public String autonomousAnalysis() {
		return "autonomousAnalysis/autonomousAnalysis";
	}
	@RequestMapping("/autonomousAnalysis/yearData")
	public String yearData() {
		return "autonomousAnalysis/yearData";
	}
	@RequestMapping("/autonomousAnalysis/allTotalCase")
	public String allTotalCase() {
		return "autonomousAnalysis/allTotalCase";
	}
	@RequestMapping("/autonomousAnalysis/allDrugCost")
	public String allDrugCost() {
		return "autonomousAnalysis/allDrugCost";
	}
	@RequestMapping("/autonomousAnalysis/allServiceCostRank")
	public String allServiceCostRank() {
		return "autonomousAnalysis/allServiceCostRank";
	}
	@RequestMapping("/autonomousAnalysis/allMaterialCostRank")
	public String allMaterialCostRank() {
		return "autonomousAnalysis/allMaterialCostRank";
	}
	@RequestMapping("/autonomousAnalysis/singleDisease")
	public String SingleDisease() {
		return "autonomousAnalysis/singleDisease";
	}
	@RequestMapping("/autonomousAnalysis/allAvgDay")
	public String allAvgDay() {
		return "autonomousAnalysis/allAvgDay";
	}
	@RequestMapping("/autonomousAnalysis/allAvgCost")
	public String allAvgCost() {
		return "autonomousAnalysis/allAvgCost";
	}
	@RequestMapping("/autonomousAnalysis/abnormalCost")
	public String abnormalCost() {
		return "autonomousAnalysis/abnormalCost";
	}
	@RequestMapping("/autonomousAnalysis/abnormalCost2")
	public String abnormalCost2() {
		return "autonomousAnalysis/abnormalCost2";
	}
}
