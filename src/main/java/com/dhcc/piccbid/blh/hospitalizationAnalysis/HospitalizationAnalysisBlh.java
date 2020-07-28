package com.dhcc.piccbid.blh.hospitalizationAnalysis;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.flyAvgDay.FlyAvgDayDto;
import com.dhcc.piccbid.dto.hospitalizationAnalysis.HospitalizationAnalysisDto;
import com.dhcc.piccbid.entity.hospitalizationAnalysis.HospitalizationAnalysis;
import com.dhcc.piccbid.service.hospitalizationAnalysis.HospitalizationAnalysisService;

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
public class HospitalizationAnalysisBlh extends BaseAbstractBlh<HospitalizationAnalysisDto> {

	private static Log logger = LogFactory.getLog(HospitalizationAnalysisBlh.class);

	@Resource
	private HospitalizationAnalysisService hospitalizationAnalysisService;

	public HospitalizationAnalysisBlh() {
		logger.debug("HospitalizationAnalysisBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		HospitalizationAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=hospitalizationAnalysisService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		HospitalizationAnalysisDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		hospitalizationAnalysisService.save(dto.getHospitalizationAnalysis());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		HospitalizationAnalysisDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		hospitalizationAnalysisService.delete(dto.getHospitalizationAnalysis().getHisid());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		HospitalizationAnalysisDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		HospitalizationAnalysis entity = hospitalizationAnalysisService.findOne(dto.getHospitalizationAnalysis().getHisid());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setHospitalizationAnalysis(entity);
	}

	public void hospitalizationAnalysis(HospitalizationAnalysisDto basedto) {
		HospitalizationAnalysisDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=hospitalizationAnalysisService.hospitalizationAnalysis(dto);
		dto.setPageModel(pageModel);
		
	}
}
