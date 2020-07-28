package com.dhcc.piccbid.web.controller.consumablerelation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConsumableRelation {
	@RequestMapping("/consumableRelation/consumableRelation")
	public String dictCityOrg() {
		return "consumable_relation/consumableRelation";
	}
	@RequestMapping("/consumableRelation/showRelation")
	public String consumableShowRelation() {
		return "consumable_relation/consumableinfo";
	}
}
