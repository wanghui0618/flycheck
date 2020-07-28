package com.dhcc.piccbid.web.controller.resultappeal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-06-03 19:37:36
 * @version V1.0
 */
@Controller
public class ResultAppealController {

	@RequestMapping("/resultAppeal/resultAppeal")
	public String resultAppeal() {
		return "resultAppeal/resultAppeal";
	}
	@RequestMapping("/resultAppeal/resultAppealH")
	public String resultAppealH() {
		return "resultAppeal/resultAppealH";
	}
	@RequestMapping("/resultAppeal/resultAppealC")
	public String resultAppealC() {
		return "resultAppeal/resultAppealC";
	}
	@RequestMapping("/resultAppeal/resultAppealInfoH")
	public String resultAppealInfoH() {
		return "resultAppeal/resultAppealInfoH";
	}
	
	@RequestMapping("/resultAppeal/resultAppealInfoMainH")
	public String resultAppealInfoMainH() {
		return "resultAppeal/resultAppealInfoMainH";
	}
	@RequestMapping("/resultAppeal/resultAppealInfoC")
	public String resultAppealInfoC() {
		return "resultAppeal/resultAppealInfoC";
	}
	
	@RequestMapping("/resultAppeal/resultAppealInfoMainC")
	public String resultAppealInfoMainC() {
		return "resultAppeal/resultAppealInfoMainC";
	}
	@RequestMapping("/resultAppeal/resultAppealInfo")
	public String resultAppealInfo() {
		return "resultAppeal/resultAppealInfo";
	}
	
	@RequestMapping("/resultAppeal/resultAppealInfoMain")
	public String resultAppealInfoMain() {
		return "resultAppeal/resultAppealInfoMain";
	}
	//jpp终审回显
	@RequestMapping("/resultAppeal/resultAppealInfo-jpp-zs")
	public String resultAppealInfoZs() {
		return "resultAppeal/resultAppealInfo-jpp-zs";
	}
	
	@RequestMapping("/resultAppeal/resultCount")
	public String resultCountInfo() {
		return "resultAppeal/resultCount";
	}
	
	@RequestMapping("/resultAppeal/medicalAuditForm2")
	public String medicalAuditForm2() {
		return "resultAppeal/medicalAuditForm2";
	}
	
	@RequestMapping("/resultAppeal/showForm")
	public String showForm() {
		return "resultAppeal/showForm";
	}
}
