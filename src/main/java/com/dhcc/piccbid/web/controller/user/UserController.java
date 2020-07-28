package com.dhcc.piccbid.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-09 12:41:57
 * @version V1.0
 */
@Controller
public class UserController {

	@RequestMapping("/user/user")
	public String user() {
		return "user/user";
	}
	@RequestMapping("/user/userform")
	public String userform() {
		return "user/userform";
	}
	@RequestMapping("/user/userformedit")
	public String userformedit() {
		return "user/userformedit";
	}
	@RequestMapping("/user/userAuditing")
	public String userAuditing() {
		return "user/userAuditing";
	}
/*	@RequestMapping("/log/log")
	public String log() {
		return "log/log";
	}*/
	@RequestMapping("/user/register")
	public String register() {
		return "user/register";
	}
	@RequestMapping("/user/userRegister")
	public String userRegister() {
		return "user/userRegister";
	}
	@RequestMapping("/user/forget")
	public String forget() {
		return "user/forget";
	}
	@RequestMapping("/user/setNewPassword")
	public String setNewPassword() {
		return "user/setNewPassword";
	}
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@RequestMapping("/set/password")
	public String password() {
		return "set/password";
	}
	@RequestMapping("/set/info")
	public String info() {
		return "set/info";
	}
	@RequestMapping("/user/userAutho")
	public String userAutho() {
		return "user/userAutho";
	}
	@RequestMapping("/user/newLogin")
	public String userNewLongin() {
		return "user/newLogin";
	}
	
}

