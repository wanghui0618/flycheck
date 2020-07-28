package com.dhcc.piccbid.dto.diseaseAndDiagnosticStatistics;

import java.util.List;
import com.dhcc.piccbid.entity.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatistics;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author scy
 * @date 2019-10-16 17:08:09
 * @version V1.0
 */
public class DiseaseAndDiagnosticStatisticsDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private DiseaseAndDiagnosticStatistics diseaseAndDiagnosticStatistics;
	private List<DiseaseAndDiagnosticStatistics> diseaseAndDiagnosticStatisticss;
	private String admissionDiseaseName;
	private String year;
	private String month;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getAdmissionDiseaseName() {
		return admissionDiseaseName;
	}

	public void setAdmissionDiseaseName(String admissionDiseaseName) {
		this.admissionDiseaseName = admissionDiseaseName;
	}

	public DiseaseAndDiagnosticStatistics getDiseaseAndDiagnosticStatistics() {
		return diseaseAndDiagnosticStatistics;
	}

	public void setDiseaseAndDiagnosticStatistics(DiseaseAndDiagnosticStatistics diseaseAndDiagnosticStatistics) {
		this.diseaseAndDiagnosticStatistics = diseaseAndDiagnosticStatistics;
	}

	public List<DiseaseAndDiagnosticStatistics> getDiseaseAndDiagnosticStatisticss() {
		return diseaseAndDiagnosticStatisticss;
	}

	public void setDiseaseAndDiagnosticStatisticss(
			List<DiseaseAndDiagnosticStatistics> diseaseAndDiagnosticStatisticss) {
		this.diseaseAndDiagnosticStatisticss = diseaseAndDiagnosticStatisticss;
	}
}
