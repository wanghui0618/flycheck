package com.dhcc.piccbid.web.controller.flyMedicalDetail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-10-15 14:48:34
 * @version V1.0
 */
@Controller
public class FlyMedicalDetailController {

	@RequestMapping("/flyMedicalDetail/flyMedicalDetail")
	public String flyMedicalDetail() {
		return "flyMedicalDetail/flyMedicalDetail";
	}

}
