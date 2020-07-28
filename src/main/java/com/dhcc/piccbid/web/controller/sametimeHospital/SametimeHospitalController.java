package com.dhcc.piccbid.web.controller.sametimeHospital;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SametimeHospitalController {
	
	/**
     *  住院主单
     *  @author 贺和平
     *  @date 2019年11月23日
     * @return String
     */
	@RequestMapping("/sametimeHospital/sametimeHospital")
	public String show() {
		return "/sametimeHospital/sametimeHospital";
	}
	/**
     *  住院明细
     *  @author 贺和平
     *  @date 2019年12月24日
     * @return String
     */
	 @RequestMapping("/sametimeHospital/sametimeHospitalDetails")
	    public String abnormalfrequencytreatmentDetails() {
	        return "sametimeHospital/sametimeHospitalDetails";
	    }

}
