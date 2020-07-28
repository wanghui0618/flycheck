package com.dhcc.piccbid.service.procedure;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.procedure.ProcedureDto;
import com.dhcc.piccbid.entity.procedure.Procedure;

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
public interface ProcedureService extends BaseService<Procedure, String> {

	PageModel list(ProcedureDto dto);
	PageModel listVo(ProcedureDto dto);
	boolean execute(ProcedureDto ProcedureDto);
	PageModel executeAll(ProcedureDto dto);
	boolean flag();
	boolean check(ProcedureDto dto);
	boolean stop();

}
