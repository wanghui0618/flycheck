package com.dhcc.piccbid.blh.diseaseAndDiagnosticStatistics;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsDto;
import com.dhcc.piccbid.dto.flyAvgDay.FlyAvgDayDto;
import com.dhcc.piccbid.entity.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatistics;
import com.dhcc.piccbid.service.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsService;

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
public class DiseaseAndDiagnosticStatisticsBlh extends BaseAbstractBlh<DiseaseAndDiagnosticStatisticsDto> {

	private static Log logger = LogFactory.getLog(DiseaseAndDiagnosticStatisticsBlh.class);

	@Resource
	private DiseaseAndDiagnosticStatisticsService diseaseAndDiagnosticStatisticsService;

	public DiseaseAndDiagnosticStatisticsBlh() {
		logger.debug("DiseaseAndDiagnosticStatisticsBlh Init");
	}

	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * 
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		DiseaseAndDiagnosticStatisticsDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = diseaseAndDiagnosticStatisticsService.list(dto);
		System.out.println(pageModel.getPageData());
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * 
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		DiseaseAndDiagnosticStatisticsDto dto = super.getExactlyDto(basedto);
		// 调用对应的service保存方法
		diseaseAndDiagnosticStatisticsService.save(dto.getDiseaseAndDiagnosticStatistics());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		DiseaseAndDiagnosticStatisticsDto dto = super.getExactlyDto(basedto);
		// 调用对应的service删除方法
		diseaseAndDiagnosticStatisticsService.delete(dto.getDiseaseAndDiagnosticStatistics().getHisid());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 * 
	 * 根据ID查询实体的方法
	 * 
	 * @param: basedto
	 * 
	 */
	public void findById(BaseAbstractDto basedto) {
		DiseaseAndDiagnosticStatisticsDto dto = super.getExactlyDto(basedto);

		// 调用对应的service查询某个实体的方法
		DiseaseAndDiagnosticStatistics entity = diseaseAndDiagnosticStatisticsService
				.findOne(dto.getDiseaseAndDiagnosticStatistics().getHisid());
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setDiseaseAndDiagnosticStatistics(entity);
	}

	public void getlist(BaseAbstractDto basedto) {
		DiseaseAndDiagnosticStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = diseaseAndDiagnosticStatisticsService.getlist(dto);
		dto.setPageModel(pageModel);
	}

	public void getlistByinhosDiag(BaseAbstractDto basedto) {
		DiseaseAndDiagnosticStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = diseaseAndDiagnosticStatisticsService.getlistByinhosDiag(dto);
		dto.setPageModel(pageModel);
	}

	public void getlistByinhosDate(BaseAbstractDto basedto) {
		DiseaseAndDiagnosticStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = diseaseAndDiagnosticStatisticsService.getlistByinhosDate(dto);
		dto.setPageModel(pageModel);
	}

	public void statisticalAnalysisByInsuranceType(BaseAbstractDto basedto) {
		DiseaseAndDiagnosticStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = diseaseAndDiagnosticStatisticsService.statisticalAnalysisByInsuranceType(dto);
		dto.setPageModel(pageModel);
	}

}
