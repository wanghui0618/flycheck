package com.dhcc.piccbid.dto.nursingViolation;

import java.util.List;
import com.dhcc.piccbid.entity.nursingViolation.NursingViolation;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-29 16:10:27
 * @version V1.0
 */
public class NursingViolationDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private NursingViolation nursingViolation;
	private List<NursingViolation> nursingViolations;

	private String limitgrade;
	public String getLimitgrade() {
		return limitgrade;
	}

	public void setLimitgrade(String limitgrade) {
		this.limitgrade = limitgrade;
	}

	public NursingViolation getNursingViolation() {
		return nursingViolation;
	}

	public void setNursingViolation(NursingViolation nursingViolation) {
		this.nursingViolation = nursingViolation;
	}

	public List<NursingViolation> getNursingViolations() {
		return nursingViolations;
	}

	public void setNursingViolations(List<NursingViolation> nursingViolations) {
		this.nursingViolations = nursingViolations;
	}
}
