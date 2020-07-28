package com.dhcc.piccbid.dto.flyMedicalInhos;

import java.util.List;
import com.dhcc.piccbid.entity.flyMedicalInhos.FlyMedicalInhos;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-31 11:40:47
 * @version V1.0
 */
public class FlyMedicalInhosDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FlyMedicalInhos flyMedicalInhos;
	private List<FlyMedicalInhos> flyMedicalInhoss;
	
	private String year;// 年份

	public FlyMedicalInhos getFlyMedicalInhos() {
		return flyMedicalInhos;
	}

	public void setFlyMedicalInhos(FlyMedicalInhos flyMedicalInhos) {
		this.flyMedicalInhos = flyMedicalInhos;
	}

	public List<FlyMedicalInhos> getFlyMedicalInhoss() {
		return flyMedicalInhoss;
	}

	public void setFlyMedicalInhoss(List<FlyMedicalInhos> flyMedicalInhoss) {
		this.flyMedicalInhoss = flyMedicalInhoss;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
