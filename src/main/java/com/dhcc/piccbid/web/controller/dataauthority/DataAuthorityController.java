package com.dhcc.piccbid.web.controller.dataauthority;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @date 2019-06-25 09:36:34
 * @version V1.0
 */
@Controller
public class DataAuthorityController {

	@RequestMapping("/dataauthority/dataauthority")
	public String dataAuthority() {
		return "dataauthority/dataauthority";
	}

	@RequestMapping("/dataauthority/dataauthorityAdd")
	public String dataauthorityAdd() {
		return "dataauthority/dataauthorityAdd";
	}

}
