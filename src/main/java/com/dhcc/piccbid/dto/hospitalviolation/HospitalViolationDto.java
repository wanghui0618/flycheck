package com.dhcc.piccbid.dto.hospitalviolation;

import java.util.List;
import com.dhcc.piccbid.entity.hospitalviolation.HospitalViolation;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-05-27 17:22:38
 * @version V1.0
 */
public class HospitalViolationDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private HospitalViolation hospitalViolation;
	private List<HospitalViolation> hospitalViolations;
	private String status;//状态---机审0 --终审1
	private String comType;//判断跳转来源
	private String handdingInsCode;//统筹区编码
	private String handdingInsName;//统筹区名称
	private String type;//金额或明细

	
	
	public String getHanddingInsName() {
		return handdingInsName;
	}

	public void setHanddingInsName(String handdingInsName) {
		this.handdingInsName = handdingInsName;
	}

	public String getHanddingInsCode() {
		return handdingInsCode;
	}

	public void setHanddingInsCode(String handdingInsCode) {
		this.handdingInsCode = handdingInsCode;
	}

	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}

	public HospitalViolation getHospitalViolation() {
		return hospitalViolation;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setHospitalViolation(HospitalViolation hospitalViolation) {
		this.hospitalViolation = hospitalViolation;
	}

	public List<HospitalViolation> getHospitalViolations() {
		return hospitalViolations;
	}

	public void setHospitalViolations(List<HospitalViolation> hospitalViolations) {
		this.hospitalViolations = hospitalViolations;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
