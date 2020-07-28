package com.dhcc.piccbid.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-04-11 15:54:57
 * @version V1.0
 */
@Controller
public class ViewController {

	@RequestMapping("/view/viewnumber")
	public String viewnumber() {
		return "view/viewnumber";
	}
	
	@RequestMapping("/view/viewquality")
	public String viewquality() {
		return "view/viewquality";
	}
	
	@RequestMapping("/view/viewtegrity")
	public String viewtegrity() {
		return "view/viewtegrity";
	}
	
	@RequestMapping("/view/todaynumber")
	public String todaynumber() {
		return "view/todaynumber";
	}
	
	@RequestMapping("/view/qualityCount")
	public String qualityCount() {
		return "view/qualityCount";
	}
	
	@RequestMapping("/view/tegrityCount")
	public String tegrityCount() {
		return "view/tegrityCount";
	}
	
	@RequestMapping("/view/tegritynumber")
	public String tegritynumber() {
		return "view/tegritynumber";
	}
	
	@RequestMapping("/view/qualitynumber")
	public String qualitynumber() {
		return "view/qualitynumber";
	}
	
	@RequestMapping("/view/updateCount")
	public String updateCount() {
		return "view/updateCount";
	}
	
	

	


}
