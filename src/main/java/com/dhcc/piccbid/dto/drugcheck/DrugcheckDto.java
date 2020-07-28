package com.dhcc.piccbid.dto.drugcheck;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.drugcheck.Drugcheck;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-17 22:23:31
 * @version V1.0
 */
public class DrugcheckDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private Drugcheck drugcheck;
	private List<Drugcheck> drugchecks;

	public Drugcheck getDrugcheck() {
		return drugcheck;
	}

	public void setDrugcheck(Drugcheck drugcheck) {
		this.drugcheck = drugcheck;
	}

	public List<Drugcheck> getDrugchecks() {
		return drugchecks;
	}

	public void setDrugchecks(List<Drugcheck> drugchecks) {
		this.drugchecks = drugchecks;
	}
}
