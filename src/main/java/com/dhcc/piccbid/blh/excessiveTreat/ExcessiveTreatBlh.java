package com.dhcc.piccbid.blh.excessiveTreat;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.excessiveTreat.ExcessiveTreatDto;
import com.dhcc.piccbid.entity.excessiveTreat.ExcessiveTreat;
import com.dhcc.piccbid.service.excessiveTreat.ExcessiveTreatService;

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
public class ExcessiveTreatBlh extends BaseAbstractBlh<ExcessiveTreatDto> {

	private static Log logger = LogFactory.getLog(ExcessiveTreatBlh.class);

	@Resource
	private ExcessiveTreatService excessiveTreatService;

	public ExcessiveTreatBlh() {
		logger.debug("ExcessiveTreatBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		ExcessiveTreatDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=excessiveTreatService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		ExcessiveTreatDto dto = super.getExactlyDto(basedto);
		
		 String logic = WebContextHolder.getContext().getRequest().getParameter("excessiveTreat.logic");
		 dto.getExcessiveTreat().setLogic(logic);
		 String logicSql1 = WebContextHolder.getContext().getRequest().getParameter("excessiveTreat.logicSql1");
		 dto.getExcessiveTreat().setLogicSql1(logicSql1);
		 String logicSql2 = WebContextHolder.getContext().getRequest().getParameter("excessiveTreat.logicSql2");
		 dto.getExcessiveTreat().setLogicSql2(logicSql2);
		 String logicSqlPara = WebContextHolder.getContext().getRequest().getParameter("excessiveTreat.logicSqlPara");
		 dto.getExcessiveTreat().setLogicSqlPara(logicSqlPara);
		//调用对应的service保存方法
		excessiveTreatService.save(dto.getExcessiveTreat());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		ExcessiveTreatDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		excessiveTreatService.delete(dto.getExcessiveTreat().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		ExcessiveTreatDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		ExcessiveTreat entity = excessiveTreatService.findOne(dto.getExcessiveTreat().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setExcessiveTreat(entity);
	}
	
	public void listVo(BaseAbstractDto basedto) {
		ExcessiveTreatDto dto = super.getExactlyDto(basedto);
		
		PageModel pageModel=excessiveTreatService.listVo(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
}
