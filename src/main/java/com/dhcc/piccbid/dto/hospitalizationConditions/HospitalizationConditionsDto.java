package com.dhcc.piccbid.dto.hospitalizationConditions;

import java.util.List;
import com.dhcc.piccbid.entity.hospitalizationConditions.HospitalizationConditions;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-24 16:19:35
 * @version V1.0
 */
public class HospitalizationConditionsDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private HospitalizationConditions hospitalizationConditions;
	private List<HospitalizationConditions> hospitalizationConditionss;

	private String admissionDiseaseName;
	private String admissionDiseaseId;
	public String getAdmissionDiseaseName() {
		return admissionDiseaseName;
	}

	public void setAdmissionDiseaseName(String admissionDiseaseName) {
		this.admissionDiseaseName = admissionDiseaseName;
	}

	public String getAdmissionDiseaseId() {
		return admissionDiseaseId;
	}

	public void setAdmissionDiseaseId(String admissionDiseaseId) {
		this.admissionDiseaseId = admissionDiseaseId;
	}

	public String getLimitdays() {
		return limitdays;
	}

	public void setLimitdays(String limitdays) {
		this.limitdays = limitdays;
	}

	public String getLimitgrade() {
		return limitgrade;
	}

	public void setLimitgrade(String limitgrade) {
		this.limitgrade = limitgrade;
	}

	private String limitdays;
	
	private String limitgrade;
	
	public HospitalizationConditions getHospitalizationConditions() {
		return hospitalizationConditions;
	}

	public void setHospitalizationConditions(HospitalizationConditions hospitalizationConditions) {
		this.hospitalizationConditions = hospitalizationConditions;
	}

	public List<HospitalizationConditions> getHospitalizationConditionss() {
		return hospitalizationConditionss;
	}

	public void setHospitalizationConditionss(List<HospitalizationConditions> hospitalizationConditionss) {
		this.hospitalizationConditionss = hospitalizationConditionss;
	}
}
