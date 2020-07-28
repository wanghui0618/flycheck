package com.dhcc.piccbid.dto.procedure;

import java.util.List;
import com.dhcc.piccbid.entity.procedure.Procedure;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-06 11:48:54
 * @version V1.0
 */
public class ProcedureDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private Procedure procedure;
	private List<Procedure> procedures;

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	public List<Procedure> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}
}
