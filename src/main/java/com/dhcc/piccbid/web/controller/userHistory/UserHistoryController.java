package com.dhcc.piccbid.web.controller.userHistory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangjieli
 * @date 2019-06-28 17:24:53
 * @version V1.0
 */
@Controller
public class UserHistoryController {

	@RequestMapping("/userHistory/userHistory")
	public String userHistory() {
		return "userHistory/userHistory";
	}

}
