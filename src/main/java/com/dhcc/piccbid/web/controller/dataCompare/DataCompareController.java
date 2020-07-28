package com.dhcc.piccbid.web.controller.dataCompare;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author DongHuan
 * @date 2019-01-29 15:04:53
 * @version V1.0
 */
@Controller
public class DataCompareController {

	@RequestMapping("/dataCompare/dataCompare")
	public String costdetailHos() {
		return "dataCompare/dataCompare";
	}
	
}
