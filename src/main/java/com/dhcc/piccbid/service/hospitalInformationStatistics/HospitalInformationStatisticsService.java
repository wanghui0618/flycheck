package com.dhcc.piccbid.service.hospitalInformationStatistics;

import java.util.List;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import com.dhcc.piccbid.dto.hospitalInformationStatistics.HospitalInformationStatisticsDto;
import com.dhcc.piccbid.entity.decompositionHospital.DecompositionHospital;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import com.dhcc.piccbid.entity.hospitalInformationStatistics.HospitalInformationStatisticsVo;

public interface HospitalInformationStatisticsService extends BaseService<FlycheckMedical,String>{

	PageModel list(HospitalInformationStatisticsDto dto);

	//住院数据统计
	PageModel dataStatistics(HospitalInformationStatisticsDto dto);

	//门诊数据统计
	PageModel outpatientDataStatistics(HospitalInformationStatisticsDto dto);

	//住院各项目收费占比
	PageModel proportionChargingItems(HospitalInformationStatisticsDto dto);
	
	//门诊各项目收费占比
	PageModel proportionChargingItemsMed(HospitalInformationStatisticsDto dto);
	
	//月度平均住院天数
	PageModel averageLengthOfStay(HospitalInformationStatisticsDto dto);

	//住院药品/诊疗/耗材占比
	PageModel drugsDiagnosisTreatment(HospitalInformationStatisticsDto dto);

	//门诊药品/诊疗/耗材占比
	PageModel drugsDiagnosisTreatmentMed(HospitalInformationStatisticsDto dto);

	//住院科室费用top10
	PageModel departmentRanking(HospitalInformationStatisticsDto dto);

	//门诊科室费用top10
	PageModel departmentRankingMed(HospitalInformationStatisticsDto dto);

	//住院诊疗费用top10
	PageModel treatmentRanking(HospitalInformationStatisticsDto dto);

	//门诊诊疗费用top10
	PageModel treatmentRankingMed(HospitalInformationStatisticsDto dto);

	/**
	 * 方法名:exportBeHospitalized
	 * 方法功能描述:医院住院详细信息导出
	 * @param:@return
	 * @return:List<HospitalInformationStatisticsVo>
	 * @Author:王彤
	 * @Create Date:2020年1月7日
	 */
	List<HospitalInformationStatisticsVo> exportBeHospitalized(HospitalInformationStatisticsDto dto);



	/**
	 * 方法名:exportOutpatient
	 * 方法功能描述:医院门诊详细信息导出
	 * @param:@return
	 * @return:List<HospitalInformationStatisticsVo>
	 * @Author:王彤
	 * @Create Date:2020年1月7日
	 */
	List<HospitalInformationStatisticsVo> exportOutpatient(HospitalInformationStatisticsDto dto);


}
