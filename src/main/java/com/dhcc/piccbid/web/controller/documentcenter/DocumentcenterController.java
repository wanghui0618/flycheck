package com.dhcc.piccbid.web.controller.documentcenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-04-11 15:54:57
 * @version V1.0
 */
@Controller
public class DocumentcenterController {

	@RequestMapping("/documentcenter/documentcenter")
	public String city() {
		return "documentCenter/documentType";
	}
	

	


}
