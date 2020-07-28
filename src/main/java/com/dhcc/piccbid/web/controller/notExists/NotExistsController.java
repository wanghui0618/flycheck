package com.dhcc.piccbid.web.controller.notExists;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-11-23 13:31:41
 * @version V1.0
 */
@Controller
public class NotExistsController {

	@RequestMapping("/notExists/notExists")
	public String abnormalHospitalStay() {
		return "notExists/notExists";
	}
	
	 @RequestMapping("/notExistsmx/notExistsmx")
	    public String detailedInformation() {
	        return "notExists/notExistsmx";
	   }

}
