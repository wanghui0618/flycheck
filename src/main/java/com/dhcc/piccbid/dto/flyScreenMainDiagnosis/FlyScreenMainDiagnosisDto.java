package com.dhcc.piccbid.dto.flyScreenMainDiagnosis;

import java.util.List;
import com.dhcc.piccbid.entity.flyScreenMainDiagnosis.FlyScreenMainDiagnosis;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-24 17:15:53
 * @version V1.0
 */
public class FlyScreenMainDiagnosisDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FlyScreenMainDiagnosis flyScreenMainDiagnosis;
	private List<FlyScreenMainDiagnosis> flyScreenMainDiagnosiss;
	
	private String diagnosis;

	public FlyScreenMainDiagnosis getFlyScreenMainDiagnosis() {
		return flyScreenMainDiagnosis;
	}

	public void setFlyScreenMainDiagnosis(FlyScreenMainDiagnosis flyScreenMainDiagnosis) {
		this.flyScreenMainDiagnosis = flyScreenMainDiagnosis;
	}

	public List<FlyScreenMainDiagnosis> getFlyScreenMainDiagnosiss() {
		return flyScreenMainDiagnosiss;
	}

	public void setFlyScreenMainDiagnosiss(List<FlyScreenMainDiagnosis> flyScreenMainDiagnosiss) {
		this.flyScreenMainDiagnosiss = flyScreenMainDiagnosiss;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
}
