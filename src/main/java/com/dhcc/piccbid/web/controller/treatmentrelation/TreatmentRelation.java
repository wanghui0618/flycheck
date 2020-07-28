package com.dhcc.piccbid.web.controller.treatmentrelation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TreatmentRelation {
	@RequestMapping("/treatmentRelation/treatmentRelation")
	public String dictCityOrg() {
		return "treatment_relation/treatmentRelation";
	}
	@RequestMapping("/treatmentRelation/showRelation")
	public String treatmentShowRelation() {
		return "treatment_relation/treatmentinfo";
	}
}
