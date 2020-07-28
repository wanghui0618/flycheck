package com.dhcc.piccbid.dto.excessiveTreat;

import java.util.List;
import com.dhcc.piccbid.entity.excessiveTreat.ExcessiveTreat;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-14 10:11:17
 * @version V1.0
 */
public class ExcessiveTreatDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private ExcessiveTreat excessiveTreat;
	private List<ExcessiveTreat> excessiveTreats;

	public ExcessiveTreat getExcessiveTreat() {
		return excessiveTreat;
	}

	public void setExcessiveTreat(ExcessiveTreat excessiveTreat) {
		this.excessiveTreat = excessiveTreat;
	}

	public List<ExcessiveTreat> getExcessiveTreats() {
		return excessiveTreats;
	}

	public void setExcessiveTreats(List<ExcessiveTreat> excessiveTreats) {
		this.excessiveTreats = excessiveTreats;
	}
}
