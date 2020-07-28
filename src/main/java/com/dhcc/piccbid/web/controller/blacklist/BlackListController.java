package com.dhcc.piccbid.web.controller.blacklist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-05-27 11:34:10
 * @version V1.0
 */
@Controller
public class BlackListController {

	@RequestMapping("/blackList/blackList")
	public String blackList() {
		return "blackList/blackList";
	}
	
	@RequestMapping("/blackList/blackListInfo")
	public String blackListInfo() {
		return "blackList/blackListInfo";
	}
	
	@RequestMapping("/countUpMedical/countUpBlackList")
	public String countUpBlackList() {
		return "countUpMedical/countUpBlackList";
	}
	
	@RequestMapping("/countUpMedical/countUpViolation")
	public String countUpViolation() {
		return "countUpMedical/countUpViolation";
	}
}
