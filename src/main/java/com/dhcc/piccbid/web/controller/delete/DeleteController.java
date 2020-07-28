package com.dhcc.piccbid.web.controller.delete;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-07-11 16:53:42
 * @version V1.0
 */
@Controller
public class DeleteController {

	@RequestMapping("/delete/delete")
	public String delete() {
		return "delete/delete";
	}
	
	@RequestMapping("/delete/deleteAdd")
	public String deleteAdd() {
		return "delete/deleteAdd";
	}
	
	@RequestMapping("/delete/deleteEdit")
	public String deleteEdit() {
		return "delete/deleteEdit";
	}

}
