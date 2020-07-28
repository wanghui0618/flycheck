package com.dhcc.piccbid.web.controller.mainDiagnosis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-11-08 16:53:31
 * @version V1.0
 */
@Controller
public class MainDiagnosisController {

	@RequestMapping("/mainDiagnosis/mainDiagnosis")
	public String mainDiagnosis() {
		return "mainDiagnosis/mainDiagnosis";
	}

}
