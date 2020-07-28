package com.dhcc.piccbid.web.controller.abnormalanesthesia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AbnormalAnesthesiaController {
	
	/**
     *  住院主单
     *  @author 贺和平
     *  @date 2020年1月11日
     * @return String
     */
	@RequestMapping("/abnormalanesthesia//abnormalanesthesia")
	public String show() {
		return "/abnormalanesthesia/abnormalanesthesia";
	}
	/**
     *  住院明细
    *  @author 贺和平
     *  @date 2020年1月11日
     * @return String
     */
	 @RequestMapping("/abnormalanesthesia/abnormalanesthesiaDetails")
	    public String abnormalfrequencytreatmentDetails() {
	        return "/abnormalanesthesia/abnormalanesthesiaDetails";
	    }


}
