package com.dhcc.piccbid.web.controller.unit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @date 2019-06-17 13:02:58
 * @version V1.0
 */
@Controller
public class UnitController {

	@RequestMapping("/unit/unit")
	public String unit() {
		return "unit/unit";
	}

	@RequestMapping("/unit/unitAdd")
	public String unitAdd() {
		return "unit/unitAdd";
	}

	@RequestMapping("/unit/unitAdd1")
	public String unitAdd1() {
		return "unit/unitAdd1";
	}

	@RequestMapping("/unit/unitAddnew")
	public String unitAddnew() {
		return "unit/unitAddnew";
	}

	@RequestMapping("/unit/unitAddnew1")
	public String unitAddnew1() {
		return "unit/unitAddnew1";
	}

}
