package com.dhcc.piccbid.dto.mainDiagnosis;

import java.util.List;
import com.dhcc.piccbid.entity.mainDiagnosis.MainDiagnosis;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-11-08 16:53:31
 * @version V1.0
 */
public class MainDiagnosisDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private MainDiagnosis mainDiagnosis;
	private List<MainDiagnosis> mainDiagnosiss;
	
	private String diagnosis;

	public MainDiagnosis getMainDiagnosis() {
		return mainDiagnosis;
	}

	public void setMainDiagnosis(MainDiagnosis mainDiagnosis) {
		this.mainDiagnosis = mainDiagnosis;
	}

	public List<MainDiagnosis> getMainDiagnosiss() {
		return mainDiagnosiss;
	}

	public void setMainDiagnosiss(List<MainDiagnosis> mainDiagnosiss) {
		this.mainDiagnosiss = mainDiagnosiss;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
}
