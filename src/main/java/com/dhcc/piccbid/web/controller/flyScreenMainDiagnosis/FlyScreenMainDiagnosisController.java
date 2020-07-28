package com.dhcc.piccbid.web.controller.flyScreenMainDiagnosis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-24 17:15:53
 * @version V1.0
 */
@Controller
public class FlyScreenMainDiagnosisController {
	// 主诊断异常筛查分析
	@RequestMapping("/flyScreenMainDiagnosis/flyScreenMainDiagnosis")
	public String flyScreenMainDiagnosis() {
		return "flyScreenMainDiagnosis/flyScreenMainDiagnosis";
	}
	// 增加页面
	@RequestMapping("/flyScreenMainDiagnosis/add")
	public String add() {
		return "flyScreenMainDiagnosis/add";
	}

}
