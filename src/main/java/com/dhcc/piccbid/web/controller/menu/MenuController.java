package com.dhcc.piccbid.web.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author WJL
 * @date 2019-06-12 09:51:27
 * @version V1.0
 */
@Controller
public class MenuController {

	@RequestMapping("/menu/menu")
	public String menu() {
		return "menu/menu";
	}
	@RequestMapping("/menu/menuAdd")
	public String menuAdd() {
		return "menu/menuAdd";
	}
	@RequestMapping("/menu/menuAdd2")
	public String menuAdd2() {
		return "menu/menuAdd2";
	}
	

}
