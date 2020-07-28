package com.dhcc.piccbid.blh.procedure;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.procedure.ProcedureDto;
import com.dhcc.piccbid.entity.procedure.Procedure;
import com.dhcc.piccbid.service.procedure.ProcedureService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class ProcedureBlh extends BaseAbstractBlh<ProcedureDto> {

	private static Log logger = LogFactory.getLog(ProcedureBlh.class);

	@Resource
	private ProcedureService procedureService;

	public ProcedureBlh() {
		logger.debug("ProcedureBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		ProcedureDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=procedureService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void listVo(BaseAbstractDto basedto) {
		ProcedureDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=procedureService.listVo(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}


	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		ProcedureDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		procedureService.save(dto.getProcedure());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		ProcedureDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		procedureService.delete(dto.getProcedure().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		ProcedureDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		Procedure entity = procedureService.findOne(dto.getProcedure().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setProcedure(entity);
	}

	public void executeAll(BaseAbstractDto basedto) {
		ProcedureDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=procedureService.executeAll(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public boolean execute(BaseAbstractDto basedto) {
		ProcedureDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel;
		boolean success=procedureService.execute(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		return success;
	}

	public boolean flag() {
		//调用service查询方法
		PageModel pageModel;
		boolean success=procedureService.flag();
		//不要删除这行代码，调用set是为以后 service层要加缓存
		return success;
	}

	public boolean check(BaseAbstractDto basedto) {
		ProcedureDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel;
		boolean success=procedureService.check(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		return success;
	}


	public boolean stop() {
		//调用service查询方法
		PageModel pageModel;
		boolean success=procedureService.stop();
		//不要删除这行代码，调用set是为以后 service层要加缓存
		return success;
	}
}
