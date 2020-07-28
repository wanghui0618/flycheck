package com.dhcc.piccbid.web.controller.itemCost;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemCostController {

	@RequestMapping("/itemCost/medicalCost")
	public String medicalCost() {
		return "itemCost/medicalCost";
	}
	@RequestMapping("/itemCost/diagnosisCost")
	public String diagnosisCost() {
		return "itemCost/diagnosisCost";
	}
	@RequestMapping("/itemCost/consumablesCost")
	public String consumablesCost() {
		return "itemCost/consumablesCost";
	}
}
