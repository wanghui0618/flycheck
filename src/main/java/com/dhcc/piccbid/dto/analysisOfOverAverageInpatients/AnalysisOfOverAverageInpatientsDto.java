package com.dhcc.piccbid.dto.analysisOfOverAverageInpatients;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatients;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author scy
 * @date 2019-10-24 15:16:38
 * @version V1.0
 */
public class AnalysisOfOverAverageInpatientsDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private AnalysisOfOverAverageInpatients emptyHangingBedAnalysis;
	private List<AnalysisOfOverAverageInpatients> emptyHangingBedAnalysiss;
	private String patientName;// 姓名
	private String admissionDiseaseName;// 诊断类型
	private String type;// 住院/门诊
	private String year;// 住院/门诊

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAdmissionDiseaseName() {
		return admissionDiseaseName;
	}

	public void setAdmissionDiseaseName(String admissionDiseaseName) {
		this.admissionDiseaseName = admissionDiseaseName;
	}

	public AnalysisOfOverAverageInpatients getEmptyHangingBedAnalysis() {
		return emptyHangingBedAnalysis;
	}

	public void setEmptyHangingBedAnalysis(AnalysisOfOverAverageInpatients emptyHangingBedAnalysis) {
		this.emptyHangingBedAnalysis = emptyHangingBedAnalysis;
	}

	public List<AnalysisOfOverAverageInpatients> getEmptyHangingBedAnalysiss() {
		return emptyHangingBedAnalysiss;
	}

	public void setEmptyHangingBedAnalysiss(List<AnalysisOfOverAverageInpatients> emptyHangingBedAnalysiss) {
		this.emptyHangingBedAnalysiss = emptyHangingBedAnalysiss;
	}
}
