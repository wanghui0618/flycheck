package com.dhcc.piccbid.web.controller.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-07-08 10:32:41
 * @version V1.0
 */
@Controller
public class RegisterController {

	@RequestMapping("/register/register")
	public String register() {
		return "register/register";
	}
	
	@RequestMapping("/register/registerinfo")
	public String registerinfo() {
		return "register/registerinfo";
	}
	
	@RequestMapping("/register/registerview")
	public String registerview() {
		return "register/registerview";
	}

}
