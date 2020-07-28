package com.dhcc.piccbid.blh.drugsAndInspectionStatistics;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsDto;
import com.dhcc.piccbid.entity.drugsAndInspectionStatistics.DrugsAndInspectionStatistics;
import com.dhcc.piccbid.service.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsService;

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
public class DrugsAndInspectionStatisticsBlh extends BaseAbstractBlh<DrugsAndInspectionStatisticsDto> {

	private static Log logger = LogFactory.getLog(DrugsAndInspectionStatisticsBlh.class);

	@Resource
	private DrugsAndInspectionStatisticsService drugsAndInspectionStatisticsService;

	public DrugsAndInspectionStatisticsBlh() {
		logger.debug("DrugsAndInspectionStatisticsBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		DrugsAndInspectionStatisticsDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=drugsAndInspectionStatisticsService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}


	public void listForCostDetail(BaseAbstractDto basedto) {
		DrugsAndInspectionStatisticsDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=drugsAndInspectionStatisticsService.listForCostDetail(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void listForInselectionCostDetail(BaseAbstractDto basedto) {
		DrugsAndInspectionStatisticsDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=drugsAndInspectionStatisticsService.listForInselectionCostDetail(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}


		public void listForInspection(BaseAbstractDto basedto) {
		DrugsAndInspectionStatisticsDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=drugsAndInspectionStatisticsService.listForInspection(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		DrugsAndInspectionStatisticsDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		drugsAndInspectionStatisticsService.save(dto.getDrugsAndInspectionStatistics());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		DrugsAndInspectionStatisticsDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		drugsAndInspectionStatisticsService.delete(dto.getDrugsAndInspectionStatistics().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		DrugsAndInspectionStatisticsDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		DrugsAndInspectionStatistics entity = drugsAndInspectionStatisticsService.findOne(dto.getDrugsAndInspectionStatistics().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setDrugsAndInspectionStatistics(entity);
	}
}
