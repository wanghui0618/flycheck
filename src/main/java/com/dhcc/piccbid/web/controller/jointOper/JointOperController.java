package com.dhcc.piccbid.web.controller.jointOper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-10-11 17:40:01
 * @version V1.0
 */
@Controller
public class JointOperController {

	@RequestMapping("/matchCheck/jointOper")
	public String jointOper() {
		return "matchCheck/jointOper";
	}

	@RequestMapping("/matchCheck/jointOperInfo")
	public String jointOperInfo() {
		return "matchCheck/jointOperInfo";
	}
}
