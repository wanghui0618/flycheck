package com.dhcc.piccbid.dto.hospitalization;

import java.util.List;
import com.dhcc.piccbid.entity.hospitalization.CostdetailHos;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author DongHuan
 * @date 2019-01-29 15:04:53
 * @version V1.0
 */
public class CostdetailHosDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private CostdetailHos costdetailHos;
	private List<CostdetailHos> costdetailHoss;

	private String temp;
	
	public CostdetailHos getCostdetailHos() {
		return costdetailHos;
	}

	public void setCostdetailHos(CostdetailHos costdetailHos) {
		this.costdetailHos = costdetailHos;
	}

	public List<CostdetailHos> getCostdetailHoss() {
		return costdetailHoss;
	}

	public void setCostdetailHoss(List<CostdetailHos> costdetailHoss) {
		this.costdetailHoss = costdetailHoss;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	
}
