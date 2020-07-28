package com.dhcc.piccbid.web.controller.medicalregister;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-07-24 10:32:34
 * @version V1.0
 */
@Controller
public class MedicalRegisterController {
	//在院初审
	@RequestMapping("/medicalregister/medical-oh")
	public String medicalRegister() {
		return "medicalregister/medical-oh";
	}
	//在院终审
	@RequestMapping("/medicalregister/medical-oh-zs")
	public String medicalRegister1() {
		return "medicalregister/medical-oh-zs";
	}
	//在院初审下拉明细框
	@RequestMapping("/medicalregister/showForm")
	public String medicalRegister2() {
		return "medicalregister/showForm";
	}
	//在院终审下拉明细框
	@RequestMapping("/medicalregister/showFormZs")
	public String medicalRegister3() {
		return "medicalregister/showFormZs";
	}

	//实时违规病例反馈
	@RequestMapping("/realTime/violatedCase")
	public String violatedCase() {
		return "realTime/violatedCase";
	}
	
	//实时违规病例反馈下拉明细框
	@RequestMapping("/realTime/showForm")
	public String violatedCaseShowForm() {
		return "realTime/showForm";
	}
	//终审稽核回显
	@RequestMapping("/medicalregister/medicalAuditForm-oh-zs")
	public String medicalAuditForm() {
		return "/medicalregister/medicalAuditForm-oh-zs";
	}
}
