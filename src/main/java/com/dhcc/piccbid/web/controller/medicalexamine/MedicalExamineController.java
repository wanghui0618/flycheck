package com.dhcc.piccbid.web.controller.medicalexamine;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ZWY
 * @date 2019-05-22 15:50:59
 * @version V1.0
 */
@Controller
public class MedicalExamineController {

	@RequestMapping("/medicalexamine/medicalExamine")
	public String medicalExamine() {
		return "medicalexamine/medicalExamine";
	}

}
