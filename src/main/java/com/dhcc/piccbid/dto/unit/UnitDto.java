package com.dhcc.piccbid.dto.unit;

import java.util.List;
import com.dhcc.piccbid.entity.unit.Unit;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @date 2019-06-17 13:02:58
 * @version V1.0
 */
public class UnitDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private Unit unit;
	private List<Unit> units;

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}
}
