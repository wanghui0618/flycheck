package com.dhcc.piccbid.web.controller.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DoctorController {
	
	@RequestMapping("/doctor/doctor")
	public String doctor() {
		return "/doctor/doctor";
	}
	
	@RequestMapping("/doctor/doctorform")
	public String doctorform() {
		return "doctor/doctorform";
	}

}
