package com.dhcc.piccbid.web.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-14 15:42:22
 * @version V1.0
 */
@Controller
public class RoleController {

	@RequestMapping("/role/role")
	public String role() {
		return "role/role";
	}

	@RequestMapping("/role/roleAdd")
	public String roleAdd() {
		return "role/roleAdd";
	}

	@RequestMapping("/role/roleAutho")
	public String roleAutho() {
		return "role/roleAutho";
	}

}
