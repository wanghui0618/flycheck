package com.dhcc.piccbid.web.controller.medicalupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ZWY
 * @date 2019-04-22 15:45:01
 * @version V1.0
 */
@Controller
public class MedicalUploadController {

	@RequestMapping("/medicalUpload/medical")
	public String medical() {
		return "medicalUpload/medical";
	}
	@RequestMapping("/medicalUpload/medicalform")
	public String medicalform() {
		return "medicalUpload/medicalform";
	}
	@RequestMapping("/medicalUpload/medicalDetailInfo")
	public String medicalDetailInfo() {
		return "medicalUpload/medicalDetailInfo";
	}
	@RequestMapping("/medicalUpload/medicalUpload")
	public String medicalUpload() {
		return "medicalUpload/medicalUpload";
	}
	
	@RequestMapping("/datastatistics/datastatisticsinfo")
	public String datastatisticsinfo() {
		return "datastatistics/datastatisticsinfo";
	}
	
	@RequestMapping("/datastatistics/datastatisticsform")
	public String datastatisticsform() {
		return "datastatistics/datastatisticsform";
	}
	
	@RequestMapping("/datastatistics/datastatisticsview")
	public String datastatisticsview() {
		return "datastatistics/datastatisticsview";
	}
	
	
}
