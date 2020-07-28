package com.dhcc.piccbid.web.controller.bigDataAntiFraud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BigDataAntiFraud {
	//蒋攀攀
	@RequestMapping("/bigDataAntiFraud/overtreatment")
	public String overtreatment() {
		return "bigDataAntiFraud/overtreatment";
	}
	@RequestMapping("/bigDataAntiFraud/overtreatmentInfo")
	public String overtreatmentInfo() {
		return "bigDataAntiFraud/overtreatmentInfo";
	}
	@RequestMapping("/bigDataAntiFraud/drugPreferenceAnalysis")
	public String drugPreferenceAnalysis() {
		return "bigDataAntiFraud/drugPreferenceAnalysis";
	}
	@RequestMapping("/bigDataAntiFraud/overtreatmentInfo2")
	public String overtreatmentInfo2() {
		return "bigDataAntiFraud/overtreatmentInfo2";
	}
	@RequestMapping("/bigDataAntiFraud/bloodGroupConflict")
	public String bloodGroupConflict() {
		return "bigDataAntiFraud/bloodGroupConflict";
	}
	@RequestMapping("/bigDataAntiFraud/abnormalNumberProjects")
	public String abnormalNumberProjects() {
		return "bigDataAntiFraud/abnormalNumberProjects";
	}
	@RequestMapping("/bigDataAntiFraud/overtreatmentInfo3")
	public String overtreatmentInfo3() {
		return "bigDataAntiFraud/overtreatmentInfo3";
	}
	@RequestMapping("/bigDataAntiFraud/overtreatmentInfo4")
	public String overtreatmentInfo4() {
		return "bigDataAntiFraud/overtreatmentInfo4";
	}
	//张伟义
	@RequestMapping("/bigDataAntiFraud/hospitalDrugsAbuse")
	public String hospitalDrugsAbuse() {
		return "bigDataAntiFraud/hospitalDrugsAbuse";
	}
	@RequestMapping("/bigDataAntiFraud/hospitalProjectAbuse")
	public String hospitalProjectAbuse() {
		return "bigDataAntiFraud/hospitalProjectAbuse";
	}
	@RequestMapping("/bigDataAntiFraud/behaviorAbnormal")
	public String behaviorAbnormal() {
		return "bigDataAntiFraud/behaviorAbnormal";
	}
	//张健雄
	@RequestMapping("/bigDataAntiFraud/extendedHospitalization")
	public String extendedHospitalization() {
		return "bigDataAntiFraud/extendedHospitalization";
	}
	@RequestMapping("/bigDataAntiFraud/sameUnitAbnormaTreatment")
	public String sameUnitAbnormaTreatment() {
		return "bigDataAntiFraud/sameUnitAbnormaTreatment";
	}
	@RequestMapping("/bigDataAntiFraud/abnormalStay")
	public String abnormalStay() {
		return "bigDataAntiFraud/abnormalStay";
	}
	@RequestMapping("/bigDataAntiFraud/illnesisUseItemViolation")
	public String illnesisUseItemViolation() {
		return "bigDataAntiFraud/illnesisUseItemViolation";
	}
	@RequestMapping("/bigDataAntiFraud/illnesisUseItemViolationInfo")
	public String illnesisUseItemViolationInfo() {
		return "bigDataAntiFraud/illnesisUseItemViolationInfo";
	}
	@RequestMapping("/bigDataAntiFraud/operationUseItemViolation")
	public String operationUseItemViolation() {
		return "bigDataAntiFraud/operationUseItemViolation";
	}
	@RequestMapping("/bigDataAntiFraud/operationUseItemViolationInfo")
	public String operationUseItemViolationInfo() {
		return "bigDataAntiFraud/operationUseItemViolationInfo";
	}

}
