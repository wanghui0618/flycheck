package com.dhcc.piccbid.web.controller.medicalexcel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xtl
 */
@Controller
public class MedicalExcelController {
	/// medicalexcel/medicalexcelForm
	@RequestMapping("/medicalexcel")
	public String medical(HttpServletRequest request, String balanceDate, String inhosDate, String billingNo,
			String diagType, String idcard, String name, String orgCode, String sysStatus, String outhosDate,String typeNoSerach,String condition, String id) {
		String ss = "?balanceDate=" + balanceDate + "&inhosDate=" + inhosDate + "&billingNo=" + billingNo + "&diagType="
				+ diagType + "&idcard=" + idcard + "&name=" + name + "&orgCode=" + orgCode + "&sysStatus=" + sysStatus
				+ "&outhosDate=" + outhosDate +"&typeNoSerach="+typeNoSerach+"&condition="+condition+"&id="+id;
		request.getSession().setAttribute("excelInfo", ss);
		return "/medicalexcel/medicalexcelForm";
	}

}
