package com.dhcc.piccbid.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-08-02 10:27:08
 * @version V1.0
 */
@Controller
public class AdminController {

	@RequestMapping("/admin/admin")
	public String admin() {
		return "admin/admin";
	}
	
	@RequestMapping("/admin/MonitorNumber")
	public String MonitorNumber() {
		return "admin/MonitorNumber";
	}
	
	@RequestMapping("/admin/ProvinceNumber")
	public String ProvinceNumber() {
		return "admin/ProvinceNumber";
	}
	
	@RequestMapping("/admin/tcNumber")
	public String tcNumber() {
		return "admin/tcNumber";
	}
	
	@RequestMapping("/admin/yeardata")
	public String yeardata() {
		return "admin/yeardata";
	}
	
	@RequestMapping("/bigdata/bigdata")
	public String bigdata() {
		return "bigdata/bigdata";
	}

}
