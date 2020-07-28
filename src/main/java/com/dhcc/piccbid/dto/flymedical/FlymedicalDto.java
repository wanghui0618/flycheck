package com.dhcc.piccbid.dto.flymedical;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.flyMedicalrecord.FlyMedicalrecord;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 09:57:52
 * @version V1.0
 */
public class FlymedicalDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FlyMedicalrecord flymedical;
	private List<FlyMedicalrecord> flymedicals;
	
	private String year;// 同日期出入院情况筛查分析页面年度选择
	private String diagnosis;// 主要诊断

	public FlyMedicalrecord getFlymedical() {
		return flymedical;
	}

	public void setFlymedical(FlyMedicalrecord flymedical) {
		this.flymedical = flymedical;
	}

	public List<FlyMedicalrecord> getFlymedicals() {
		return flymedicals;
	}

	public void setFlymedicals(List<FlyMedicalrecord> flymedicals) {
		this.flymedicals = flymedicals;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
}
