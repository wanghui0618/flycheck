package com.dhcc.piccbid.web.controller.scheduledtask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-06-28 11:09:28
 * @version V1.0
 */
@Controller
public class ScheduledTaskController {

	@RequestMapping("/scheduledtask/scheduledTask")
	public String scheduledTask() {
		return "scheduledTask/scheduledTask";
	}
	@RequestMapping("/scheduledtask/scheduledTaskinfo")
	public String scheduledTaskinfo() {
		return "scheduledTask/scheduledTaskinfo";
	}
	@RequestMapping("scheduledTask/SysMedicalDataSelect")
	public String sysMedicalDataSelect() {
		return "scheduledTask/SysMedicalDataSelect";
	}
	
}
