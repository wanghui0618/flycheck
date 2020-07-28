package com.dhcc.piccbid.web.controller.flymedical;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 09:57:52
 * @version V1.0
 */
@Controller
public class FlymedicalController {

	@RequestMapping("/flymedical/flymedical")
	public String flymedical() {
		return "flymedical/flymedical";
	}
	
	@RequestMapping("/flymedical/flymedicalinfo")
	public String flymedicalinfo() {
		return "flymedical/flymedicalinfo";
	}
	// 同日期出入院情况筛查分析
	@RequestMapping("/flymedical/screenSameEntryAndExitDate")
	public String screenSameEntryAndExitDate() {
		return "flymedical/screenSameEntryAndExitDate";
	}

}
