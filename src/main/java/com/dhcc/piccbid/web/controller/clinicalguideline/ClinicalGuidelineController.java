package com.dhcc.piccbid.web.controller.clinicalguideline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @date 2019-05-28 19:58:38
 * @version V1.0
 */
@Controller
public class ClinicalGuidelineController {
	@RequestMapping("/clinicalguideline/clinicalguideline")
	public String clinicalGuideline() {
		return "clinicalguideline/clinicalguideline";
	}
	@RequestMapping("/clinicalguideline/clinicalguidelineAdd")
	public String clinicalGuidelineAdd() {
		return "clinicalguideline/clinicalguidelineAdd";
	}
	@RequestMapping("/clinicalguideline/clinicalguidelineEdit")
	public String clinicalGuidelineEdit() {
		return "clinicalguideline/clinicalguidelineEdit";
	}
}
