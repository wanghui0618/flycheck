package com.dhcc.piccbid.blh.analysisOfPersonTime;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.analysisOfPersonTime.AnalysisOfPersonTimeDto;
import com.dhcc.piccbid.entity.analysisOfPersonTime.AnalysisOfPersonTime;
import com.dhcc.piccbid.service.analysisOfPersonTime.AnalysisOfPersonTimeService;

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
public class AnalysisOfPersonTimeBlh extends BaseAbstractBlh<AnalysisOfPersonTimeDto> {

	private static Log logger = LogFactory.getLog(AnalysisOfPersonTimeBlh.class);

	@Resource
	private AnalysisOfPersonTimeService analysisOfPersonTimeService;

	public AnalysisOfPersonTimeBlh() {
		logger.debug("AnalysisOfPersonTimeBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		AnalysisOfPersonTimeDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=analysisOfPersonTimeService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		AnalysisOfPersonTimeDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		analysisOfPersonTimeService.save(dto.getAnalysisOfPersonTime());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		AnalysisOfPersonTimeDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		analysisOfPersonTimeService.delete(dto.getAnalysisOfPersonTime().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		AnalysisOfPersonTimeDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		AnalysisOfPersonTime entity = analysisOfPersonTimeService.findOne(dto.getAnalysisOfPersonTime().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setAnalysisOfPersonTime(entity);
	}


	public void table1(BaseAbstractDto basedto) {
		AnalysisOfPersonTimeDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=analysisOfPersonTimeService.table1(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	public void table2(BaseAbstractDto basedto) {
		AnalysisOfPersonTimeDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=analysisOfPersonTimeService.table2(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	public void table3(BaseAbstractDto basedto) {
		AnalysisOfPersonTimeDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=analysisOfPersonTimeService.table3(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	public void table4(BaseAbstractDto basedto) {
		AnalysisOfPersonTimeDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=analysisOfPersonTimeService.table4(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
}
