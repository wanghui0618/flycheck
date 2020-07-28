package com.dhcc.piccbid.web.controller.orgdirectory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ll
 * @date 2019-01-22 12:54:00
 * @version V1.0
 */
@Controller
public class OrgDirectoryController {

	@RequestMapping("/orgdirectory/orgDirectory")
	public String orgDirectory() {
		return "hospitalLevel/rancheck";
	}
	@RequestMapping("/ordirectory/orgCheckInfo")
	public String orgCheckInfo() {
		return "hospitalLevel/orgCheckInfo";
		
	}
}
