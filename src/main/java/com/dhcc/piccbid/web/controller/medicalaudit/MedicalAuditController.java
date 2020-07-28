package com.dhcc.piccbid.web.controller.medicalaudit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ZWY
 * @date 2019-06-07 11:04:35
 * @version V1.0
 */
@Controller
public class MedicalAuditController {
 
	@RequestMapping("/medicalaudit/medicalAudit")
	public String medicalAudit() {
		return "medicalaudit/medicalAudit";
	}
	@RequestMapping("/medical/medicalAuditForm")
	public String medicalAuditForm() {
		return "medical/medicalAuditForm";
	}
	@RequestMapping("/medical/medicalAuditForm-jpp-zs")
	public String medicalAuditFormZs() {
		return "medical/medicalAuditForm-jpp-zs";
	}
	@RequestMapping("/medical/medicalAuditForm-jpp-zs-zbl")
	public String medicalAuditFormZsZbl() {
		return "medical/medicalAuditForm-jpp-zs-zbl";
	}
	@RequestMapping("/medical/medicalAuditForm-jpp-zs-zbl-oh")
	public String medicalAuditFormZsZblOh() {
		return "medical/medicalAuditForm-jpp-zs-zbl-oh";
	}
	@RequestMapping("/medical/medicalAuditForm1")
	public String medicalAuditForm1() {
		return "medical/medicalAuditForm1";
	}
	@RequestMapping("/medical/medicalAuditForm2")
	public String medicalAuditForm2() {
		return "medical/medicalAuditForm2";
	}
}
