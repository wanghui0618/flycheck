package com.dhcc.piccbid.dto.statisticsInRecentTwoYears;

import java.util.List;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.medical.MedicalVo;
import com.dhcc.piccbid.entity.medicaldetail.MedicalDetail;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-08 12:08:41
 * @version V1.0
 */
public class StatisticsInRecentTwoYearsDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private MedicalDetail medicalDetail;
	private List<MedicalDetail> medicalDetails;
	private MedicalVo medicalVo;
	private List<MedicalVo> medicalVos;
	private String year;
	private String type;
	private String month;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String inDiagnosisName;

	public String getInDiagnosisName() {
		return inDiagnosisName;
	}

	public void setInDiagnosisName(String inDiagnosisName) {
		this.inDiagnosisName = inDiagnosisName;
	}

	public MedicalVo getMedicalVo() {
		return medicalVo;
	}

	public void setMedicalVo(MedicalVo medicalVo) {
		this.medicalVo = medicalVo;
	}

	public List<MedicalVo> getMedicalVos() {
		return medicalVos;
	}

	public void setMedicalVos(List<MedicalVo> medicalVos) {
		this.medicalVos = medicalVos;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public MedicalDetail getMedicalDetail() {
		return medicalDetail;
	}

	public void setMedicalDetail(MedicalDetail medicalDetail) {
		this.medicalDetail = medicalDetail;
	}

	public List<MedicalDetail> getMedicalDetails() {
		return medicalDetails;
	}

	public void setMedicalDetails(List<MedicalDetail> medicalDetails) {
		this.medicalDetails = medicalDetails;
	}
}
