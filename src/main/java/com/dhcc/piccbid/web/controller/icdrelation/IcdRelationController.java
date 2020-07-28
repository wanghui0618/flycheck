package com.dhcc.piccbid.web.controller.icdrelation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-05-20 13:07:53
 * @version V1.0
 */
@Controller
public class IcdRelationController {

	@RequestMapping("/icdRelation/icdRelation")
	public String icdRelation() {
		return "icdRelation/icdRelation";
	}
	
	@RequestMapping("/icdRelation/icdRelation2")
	public String icdRelation2() {
		return "icdRelation/icdRelation2";
	}
	
	@RequestMapping("/icdRelation/icdRelationShow")
	public String icdRelationShow() {
		return "icdRelation/icdRelationShow";
	}

}
