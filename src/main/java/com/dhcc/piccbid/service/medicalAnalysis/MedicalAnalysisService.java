package com.dhcc.piccbid.service.medicalAnalysis;

import java.util.List;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.medicalAnalysis.MedicalAnalysisDto;
import com.dhcc.piccbid.dto.medicalAnalysis.MedicalResultVO;
import com.dhcc.piccbid.entity.medicalAnalysis.HospitalVo;
import com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangyue
 * @date 2019-08-08 17:39:46
 * @version V1.0
 */
public interface MedicalAnalysisService extends BaseService<MedicalAnalysis, String> {

	PageModel list(MedicalAnalysisDto dto);

	/** 
	* 方法名:          yearData
	* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
	* @param:         是包含汉字的字符串
	* @return:        其他非简体汉字返回 '0';
	* @Author:        姚凯
	* @Create Date:   2019年8月8日 下午5:45:23
	*/
	 PageModel yearData(MedicalAnalysis dto) ;
	PageModel yearData(MedicalAnalysisDto dto);
	
	 PageModel totalIllData(MedicalAnalysis dto) ;
	PageModel totalIllData(MedicalAnalysisDto dto);

	 PageModel drugCostData(MedicalAnalysis dto) ;
	PageModel drugCostData(MedicalAnalysisDto dto);

 PageModel serviceCostData(MedicalAnalysis dto) ;
	PageModel serviceCostData(MedicalAnalysisDto dto);

	PageModel materialCostData(MedicalAnalysis dto);
	PageModel materialCostData(MedicalAnalysisDto dto);

	PageModel monthlyTrendsData(MedicalAnalysis dto) ;
	PageModel monthlyTrendsData(MedicalAnalysisDto dto);

	
	
	/*static PageModel findHosNumber(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}
	PageModel findHosNumber(MedicalAnalysisDto dto);*/
	List<HospitalVo> findHosNumber();

	/** 
	* 方法名:          findMonNumber
	* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
	* @param:         是包含汉字的字符串
	* @return:        其他非简体汉字返回 '0';
	* @Author:        姚凯
	* @Create Date:   2019年8月15日 下午1:05:40
	*/
 PageModel findMonNumber(MedicalAnalysis dto) ;
	PageModel findMonNumber(MedicalAnalysisDto dto);

	PageModel findHosNumber(MedicalAnalysis dto) ;
	PageModel findHosNumber(MedicalAnalysisDto dto);

	/** 
	* 方法名:          singleDisease
	* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
	* @param:         是包含汉字的字符串
	* @return:        其他非简体汉字返回 '0';
	* @Author:        姚凯
	* @Create Date:   2019年8月26日 下午5:04:03
	*/
	PageModel singleDisease(MedicalAnalysis dto) ;
	PageModel singleDisease(MedicalAnalysisDto dto);

	/** 
	* 方法名:          abnormalCost
	* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
	* @param:         是包含汉字的字符串
	* @return:        其他非简体汉字返回 '0';
	* @Author:        姚凯
	* @Create Date:   2019年9月19日 下午3:32:16
	*/
	
	PageModel abnormalCost(MedicalAnalysis dto) ;
	PageModel abnormalCost(MedicalAnalysisDto dto);

	/** 
	* 方法名:          abnormalCost2
	* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
	* @param:         是包含汉字的字符串
	* @return:        其他非简体汉字返回 '0';
	* @Author:        姚凯
	* @Create Date:   2019年9月27日 下午5:48:51
	*/
	PageModel abnormalCost2(MedicalAnalysisDto dto);

	PageModel medicaStatisticsForHomePage(MedicalResultVO dto);

	
}
