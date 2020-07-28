package com.dhcc.piccbid.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-09 12:41:57
 * @version V1.0
 */
@Controller
public class HomeController {

	@RequestMapping("/home/home")
	public String home() {
		return "home/home";
	}

	@RequestMapping("/")
	public String index() {
		return "login";
	}

	@RequestMapping("/indexAdmin")
	public String indexHospital() {
		return "indexAdmin";
	}

	@RequestMapping("/indexHome")
	public String indexHome() {
		return "indexHome";
	}

	@RequestMapping("/indexCity")
	public String indexAdmin() {
		return "indexCity";
	}

	@RequestMapping("home/decomposeHosp")
	public String decomposeHosp() {
		return "decomposeHosp/decomposeHosp";
	}

	@RequestMapping("/indexAuditing")
	public String indexAuditing() {
		return "main/indexAuditing";
	}

	@RequestMapping("/indexBasicservice")
	public String indexBasicservice() {
		return "main/indexBasicservice";
	}

	@RequestMapping("/indexDataAnalytics")
	public String indexContrast() {
		return "main/indexDataAnalytics";
	}

	// 八大系统-基础资料系统
	@RequestMapping("/indexDatabase")
	public String indexDatabase() {
		return "main/indexDatabase";
	}

	@RequestMapping("/indexDatatrans")
	public String indexDatatrans() {
		return "main/indexDatatrans";
	}

	@RequestMapping("/indexMonitor")
	public String indexMonitor() {
		return "main/indexMonitor";
	}

	// 八大系统-权限管理系统
	@RequestMapping("/indexOauth")
	public String indexOauth() {
		return "main/indexOauth";
	}

	// 八大系统-大数据分析系统
	@RequestMapping("/indexStatistics")
	public String indexStatistics() {
		return "main/indexStatistics";
	}

	@RequestMapping("/home/homeAuditing")
	public String homeAuditing() {
		return "home/homeAuditing";
	}

	@RequestMapping("/home/datastandard")
	public String datastandard() {
		return "home/datastandard";
	}

	@RequestMapping("/home/medicalcontroller")
	public String medicalcontroller() {
		return "home/medicalcontroller";
	}

	@RequestMapping("/home/illnesscontroller")
	public String illnesscontroller() {
		return "home/illnesscontroller";
	}

	// 数据采集平台系统主页
	@RequestMapping("/home/homeDatatrans")
	public String homeDatatrans() {
		return "home/homeDatatrans";
	}

	// 权限管理系统的主页
	@RequestMapping("/home/homeOauth")
	public String homeOauth() {
		return "home/homeOauth";
	}

	// 基础资料系统主页
	@RequestMapping("/home/homeBasicservice")
	public String homeBasicservice() {
		return "home/homeBasicservice";
	}

	//基础业务系统增加页
	@RequestMapping("/home/homeBasicservice2")
	public String homeBasicservice2() {
		return "home/homeBasicservice2";
	}
	
	// 大数据分析的主页
	@RequestMapping("/home/homeBigData")
	public String homeBigData() {
		return "home/homeBigData";
	}

	// 基础资料系统-主页
	@RequestMapping("/indexDatabaseHome")
	public String indexDatabaseHome() {
		return "home/homeDatabase";
	}
	
	//政策仿真
	@RequestMapping("/assistDecision/policyList")
	public String policyList() {
		return "assistDecision/policyList";
	}
	@RequestMapping("/assistDecision/policyBase")
	public String policyBase() {
		return "assistDecision/policyBase";
	}
	@RequestMapping("/assistDecision/policyAdd")
	public String policyAdd() {
		return "assistDecision/policyAdd";
	}
	@RequestMapping("/assistDecision/policyReport")
	public String policyReport() {
		return "assistDecision/policyReport";
	}
}
