package com.dhcc.piccbid.blh.statisticsInRecentTwoYears;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsDto;
import com.dhcc.piccbid.entity.medicaldetail.MedicalDetail;
import com.dhcc.piccbid.service.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsService;

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
public class StatisticsInRecentTwoYearsBlh extends BaseAbstractBlh<StatisticsInRecentTwoYearsDto> {

	private static Log logger = LogFactory.getLog(StatisticsInRecentTwoYearsBlh.class);

	@Resource
	private StatisticsInRecentTwoYearsService statisticsInRecentTwoYearsService;

	public StatisticsInRecentTwoYearsBlh() {
		logger.debug("MedicalDetailBlh Init");
	}

	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * 
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		StatisticsInRecentTwoYearsDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = statisticsInRecentTwoYearsService.list(dto);
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
		StatisticsInRecentTwoYearsDto dto = super.getExactlyDto(basedto);
		// 调用对应的service保存方法
		statisticsInRecentTwoYearsService.save(dto.getMedicalDetail());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		StatisticsInRecentTwoYearsDto dto = super.getExactlyDto(basedto);
		// 调用对应的service删除方法
		statisticsInRecentTwoYearsService.delete(dto.getMedicalDetail().getId());
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
		StatisticsInRecentTwoYearsDto dto = super.getExactlyDto(basedto);

		// 调用对应的service查询某个实体的方法
		MedicalDetail entity = statisticsInRecentTwoYearsService.findOne(dto.getMedicalDetail().getId());
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setMedicalDetail(entity);
	}

	/**
	 * 
	 * 近两年项目统计
	 * 
	 * @param: basedto
	 * 
	 */
	public void Dosage(BaseAbstractDto basedto) {
		StatisticsInRecentTwoYearsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = statisticsInRecentTwoYearsService.Dosage(dto);
		dto.setPageModel(pageModel);
	}

	/**
	 * 
	 * 近两年用药量统计
	 * 
	 * @param: basedto
	 * 
	 */
	public void getCount(BaseAbstractDto basedto) {
		StatisticsInRecentTwoYearsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = statisticsInRecentTwoYearsService.getCount(dto);
		dto.setPageModel(pageModel);
	}

	public void getlist(BaseAbstractDto basedto) {
		StatisticsInRecentTwoYearsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = statisticsInRecentTwoYearsService.getlist(dto);
		dto.setPageModel(pageModel);
	}

	public void getlistByinhosDiag(BaseAbstractDto basedto) {
		StatisticsInRecentTwoYearsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = statisticsInRecentTwoYearsService.getlistByinhosDiag(dto);
		dto.setPageModel(pageModel);
	}

	public void statisticalAnalysisByInsuranceType(BaseAbstractDto basedto) {
		StatisticsInRecentTwoYearsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = statisticsInRecentTwoYearsService.statisticalAnalysisByInsuranceType(dto);
		dto.setPageModel(pageModel);
	}
}
