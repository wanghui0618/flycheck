package com.dhcc.piccbid.dto.hospitalizationAnalysis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.hospitalizationAnalysis.HospitalizationAnalysis;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-17 12:54:10
 * @version V1.0
 */
public class HospitalizationAnalysisDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private HospitalizationAnalysis hospitalizationAnalysis;
	private List<HospitalizationAnalysis> hospitalizationAnalysiss;
	private BigDecimal zyts;
	private String num;
	private  String  admissionDate;
	private  String  dischargeDate;
	
	public BigDecimal getZyts() {
		return zyts;
	}

	public void setZyts(BigDecimal zyts) {
		this.zyts = zyts;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public HospitalizationAnalysis getHospitalizationAnalysis() {
		return hospitalizationAnalysis;
	}

	public void setHospitalizationAnalysis(HospitalizationAnalysis hospitalizationAnalysis) {
		this.hospitalizationAnalysis = hospitalizationAnalysis;
	}

	public List<HospitalizationAnalysis> getHospitalizationAnalysiss() {
		return hospitalizationAnalysiss;
	}

	public void setHospitalizationAnalysiss(List<HospitalizationAnalysis> hospitalizationAnalysiss) {
		this.hospitalizationAnalysiss = hospitalizationAnalysiss;
	}
}
