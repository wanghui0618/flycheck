package com.dhcc.piccbid.blh.analysisOfOverAverageInpatients;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsDto;
import com.dhcc.piccbid.entity.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatients;
import com.dhcc.piccbid.service.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsService;

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
public class AnalysisOfOverAverageInpatientsBlh extends BaseAbstractBlh<AnalysisOfOverAverageInpatientsDto> {

	private static Log logger = LogFactory.getLog(AnalysisOfOverAverageInpatientsBlh.class);

	@Resource
	private AnalysisOfOverAverageInpatientsService analysisOfOverAverageInpatientsService ;

	public AnalysisOfOverAverageInpatientsBlh() {
		logger.debug("EmptyHangingBedAnalysisBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		AnalysisOfOverAverageInpatientsDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=analysisOfOverAverageInpatientsService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		AnalysisOfOverAverageInpatientsDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		analysisOfOverAverageInpatientsService.save(dto.getEmptyHangingBedAnalysis());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		AnalysisOfOverAverageInpatientsDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		analysisOfOverAverageInpatientsService.delete(dto.getEmptyHangingBedAnalysis().getHisid());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		AnalysisOfOverAverageInpatientsDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		AnalysisOfOverAverageInpatients entity = analysisOfOverAverageInpatientsService.findOne(dto.getEmptyHangingBedAnalysis().getHisid());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setEmptyHangingBedAnalysis(entity);
	}
	
	public void getCount(BaseAbstractDto basedto) {
		AnalysisOfOverAverageInpatientsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=analysisOfOverAverageInpatientsService.getCount(dto);
		dto.setPageModel(pageModel);
	}
	
	public void towYears(BaseAbstractDto basedto) {
		AnalysisOfOverAverageInpatientsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=analysisOfOverAverageInpatientsService.towYears(dto);
		dto.setPageModel(pageModel);
	}
	public void countDiagnosisAndTreatmentItems(BaseAbstractDto basedto) {
		AnalysisOfOverAverageInpatientsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=analysisOfOverAverageInpatientsService.countDiagnosisAndTreatmentItems(dto);
		dto.setPageModel(pageModel);
	}
}
