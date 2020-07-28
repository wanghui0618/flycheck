package com.dhcc.piccbid.dto.hospitalInformationStatistics;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import com.dhcc.piccbid.entity.flycheckMedicalMz.FlycheckMedicalMz;
import com.dhcc.piccbid.entity.hospitalInformationStatistics.HospitalInformationStatisticsVo;

public class HospitalInformationStatisticsDto extends BaseAbstractDto{

	private static final long serialVersionUID = 1L;
	
	private HospitalInformationStatisticsVo hospitalInformationStatisticsVo;
	private FlycheckMedical flycheckMedical;
	private FlycheckMedicalMz flycheckMedicalMz;
	private String selectedYear;

	private String treatmentApproach;//就诊途径
	private String medicalName;//人群类别
	private String hospitalCode;//医院编码
	private String hospitalName;//医院名称
	private String balanceDate;//结算时间
	private String balanceDatea;//结算时间a
	private String balanceDateb;//结算时间b

	private String fileName;//导出文件名称

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTreatmentApproach() {
		return treatmentApproach;
	}

	public void setTreatmentApproach(String treatmentApproach) {
		this.treatmentApproach = treatmentApproach;
	}

	public String getMedicalName() {
		return medicalName;
	}

	public void setMedicalName(String medicalName) {
		this.medicalName = medicalName;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getBalanceDatea() {
		return balanceDatea;
	}

	public void setBalanceDatea(String balanceDatea) {
		this.balanceDatea = balanceDatea;
	}

	public String getBalanceDateb() {
		return balanceDateb;
	}

	public void setBalanceDateb(String balanceDateb) {
		this.balanceDateb = balanceDateb;
	}

	public String getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(String selectedYear) {
		this.selectedYear = selectedYear;
	}

	public FlycheckMedical getFlycheckMedical() {
		return flycheckMedical;
	}

	public void setFlycheckMedical(FlycheckMedical flycheckMedical) {
		this.flycheckMedical = flycheckMedical;
	}

	public FlycheckMedicalMz getFlycheckMedicalMz() {
		return flycheckMedicalMz;
	}

	public void setFlycheckMedicalMz(FlycheckMedicalMz flycheckMedicalMz) {
		this.flycheckMedicalMz = flycheckMedicalMz;
	}

	public HospitalInformationStatisticsVo getHospitalInformationStatisticsVo() {
		return hospitalInformationStatisticsVo;
	}

	public void setHospitalInformationStatisticsVo(HospitalInformationStatisticsVo hospitalInformationStatisticsVo) {
		this.hospitalInformationStatisticsVo = hospitalInformationStatisticsVo;
	}

	@Override
	public String toString() {
		return "HospitalInformationStatisticsDto{" +
				"hospitalInformationStatisticsVo=" + hospitalInformationStatisticsVo +
				", flycheckMedical=" + flycheckMedical +
				", flycheckMedicalMz=" + flycheckMedicalMz +
				", selectedYear='" + selectedYear + '\'' +
				", medicalName='" + medicalName + '\'' +
				", hospitalCode='" + hospitalCode + '\'' +
				", hospitalName='" + hospitalName + '\'' +
				", balanceDate='" + balanceDate + '\'' +
				", balanceDatea='" + balanceDatea + '\'' +
				", balanceDateb='" + balanceDateb + '\'' +
				'}';
	}
}
