package com.dhcc.piccbid.blh.medicalAnalysis;

import java.util.List;

import javax.annotation.Resource;

import com.dhcc.piccbid.dto.medicalAnalysis.MedicalResultVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
//import com.dhcc.piccbid.dto.medical.MedicalDto;
import com.dhcc.piccbid.dto.medicalAnalysis.MedicalAnalysisDto;
import com.dhcc.piccbid.entity.home.HomeVo;
import com.dhcc.piccbid.entity.medicalAnalysis.HospitalVo;
import com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis;
import com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService;

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
public class MedicalAnalysisBlh extends BaseAbstractBlh<MedicalAnalysisDto> {

	private static Log logger = LogFactory.getLog(MedicalAnalysisBlh.class);

	@Resource
	private MedicalAnalysisService medicalAnalysisService;

	public MedicalAnalysisBlh() {
		logger.debug("MedicalAnalysisBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		MedicalAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=medicalAnalysisService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		MedicalAnalysisDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		medicalAnalysisService.save(dto.getMedicalAnalysis());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		MedicalAnalysisDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		medicalAnalysisService.delete(dto.getMedical().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		MedicalAnalysisDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		MedicalAnalysis entity = medicalAnalysisService.findOne(dto.getMedical().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setMedicalAnalysis(entity);
	}
	
	//.........
		public void yearData(BaseAbstractDto basedto) {
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.yearData(dto);
			dto.setPageModel(pageModel);
		}
		
		//.........
		public void totalIllData(BaseAbstractDto basedto) {
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.totalIllData(dto);
			dto.setPageModel(pageModel);
		}
		
		//.........
		public void drugCostData(BaseAbstractDto basedto) {
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.drugCostData(dto);
			dto.setPageModel(pageModel);
		}
		//.........
		public void serviceCostData(BaseAbstractDto basedto) {
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.serviceCostData(dto);
			dto.setPageModel(pageModel);
		}

		//.........
		public void materialCostData(BaseAbstractDto basedto) {
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.materialCostData(dto);
			dto.setPageModel(pageModel);
		}

		
		//.........
		public void monthlyTrendsData(BaseAbstractDto basedto) {
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.monthlyTrendsData(dto);
			dto.setPageModel(pageModel);
		}
		
		/*public List<HospitalVo> findHosNumber() {
			// TODO Auto-generated method stub
			return medicalAnalysisService.findHosNumber();
		}*/
		
		public void findHosNumber(BaseAbstractDto basedto) {
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.findHosNumber(dto);
			dto.setPageModel(pageModel);
		}
		/** 
		* 方法名:          findHosNumber
		* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
		* @param:         是包含汉字的字符串
		* @return:        其他非简体汉字返回 '0';
		* @Author:        姚凯
		* @Create Date:   2019年8月15日 上午11:31:51
		*/
		
		public void findMonNumber(BaseAbstractDto basedto) {
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.findMonNumber(dto);
			dto.setPageModel(pageModel);
		}
		
		public void singleDisease(BaseAbstractDto basedto) {
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.singleDisease(dto);
			dto.setPageModel(pageModel);
		}
		
		public void abnormalCost(BaseAbstractDto basedto) {   ///****////
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.abnormalCost(dto);
			dto.setPageModel(pageModel);
		}
		public void abnormalCost2(BaseAbstractDto basedto) {   ///****////
			MedicalAnalysisDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=medicalAnalysisService.abnormalCost2(dto);
			dto.setPageModel(pageModel);
		}
		public void medicaStatisticsForHomePage(MedicalResultVO dto) {
			PageModel pageModel=medicalAnalysisService.medicaStatisticsForHomePage(dto);
			dto.setPageModel(pageModel);
		}
}
