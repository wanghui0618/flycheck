package com.dhcc.piccbid.web.controller.singleDisease;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xekeyong
 * @date 2019-08-07 16:48:48
 * @version V1.0
 */
@Controller
public class SingleDiseaseController {

	@RequestMapping("/singleDisease/singleDisease")
	public String singleDisease() {
		return "singleDisease/singleDisease";
	}

	@RequestMapping("/singleDisease/singleDiseaseInfo")
	public String singleDiseaseInfo() {
		return "singleDisease/singleDiseaseInfo";
	}

	@RequestMapping("/singleDisease/singleDiseaseInfo-view")
	public String singleDiseaseInfoView() {
		return "singleDisease/singleDiseaseInfo-view";
	}
}
