package com.dhcc.piccbid.web.controller.physicalExaminationAdmission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-23 10:17:39
 * @version V1.0
 */
@Controller
public class PhysicalExaminationAdmissionController {

	@RequestMapping("/physicalExaminationAdmission/physicalExaminationAdmission")
	public String physicalExaminationAdmission() {
		return "physicalExaminationAdmission/physicalExaminationAdmission";
	}
	
	@RequestMapping("/physicalExaminationAdmission/mingXiPage")
	public String detailedInformation() {
		return "/physicalExaminationAdmission/mingXiPage";
	}

}
