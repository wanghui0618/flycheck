package com.dhcc.piccbid.blh.hospitalInformationStatistics;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.utils.CSVUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.hospitalInformationStatistics.HospitalInformationStatisticsDto;
import com.dhcc.piccbid.entity.hospitalInformationStatistics.HospitalInformationStatisticsVo;
import com.dhcc.piccbid.service.hospitalInformationStatistics.HospitalInformationStatisticsService;

@Component
public class HospitalInformationStatisticsBlh extends BaseAbstractBlh<HospitalInformationStatisticsDto>{

	private static Log logger = LogFactory.getLog(HospitalInformationStatisticsBlh.class);
	
	@Resource
	private HospitalInformationStatisticsService hospitalInformationStatisticsService;
	
	public HospitalInformationStatisticsBlh() {
		logger.debug("HospitalInformationStatisticsBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.list(dto);
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 住院数据统计
	 * @param
	 * @return
	 */
	public void dataStatistics(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.dataStatistics(dto);
		dto.setPageModel(pageModel);
	}

	/**
	 * 门诊数据统计
	 * @param
	 * @return
	 */
	public void outpatientDataStatistics(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.outpatientDataStatistics(dto);
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 住院药品/诊疗/耗材占比
	 * @param
	 */
	public void drugsDiagnosisTreatment(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.drugsDiagnosisTreatment(dto);
		dto.setPageModel(pageModel);
	}

	/**
	 * 门诊药品/诊疗/耗材占比
	 * @param
	 */
	public void drugsDiagnosisTreatmentMed(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.drugsDiagnosisTreatmentMed(dto);
		dto.setPageModel(pageModel);
	}

	/**
	 * 住院各项目收费占比
	 * @param basedto
	 */
	public void proportionChargingItems(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.proportionChargingItems(dto);
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 门诊各项目收费占比
	 * @param basedto
	 */
	public void proportionChargingItemsMed(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.proportionChargingItemsMed(dto);
		dto.setPageModel(pageModel);
	}
	
	/***
	 * 月度平均住院天数
	 * @param basedto
	 */
	public void averageLengthOfStay(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.averageLengthOfStay(dto);
		dto.setPageModel(pageModel);
	}

	/**
	 * 住院科室费用top10
	 * @param
	 */
	public void departmentRanking(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.departmentRanking(dto);
		dto.setPageModel(pageModel);
	}

	/**
	 * 门诊科室费用top10
	 * @param
	 */
	public void departmentRankingMed(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.departmentRankingMed(dto);
		dto.setPageModel(pageModel);
	}

	/**
	 * 住院诊疗费用top10
	 * @param
	 */
	public void treatmentRanking(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.treatmentRanking(dto);
		dto.setPageModel(pageModel);
	}

	
	/**
	 * 门诊诊疗费用top10
	 * @param
	 */
	public void treatmentRankingMed(BaseAbstractDto basedto) {
		HospitalInformationStatisticsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalInformationStatisticsService.treatmentRankingMed(dto);
		dto.setPageModel(pageModel);
	}

	// 导出
	public HospitalInformationStatisticsDto exportH(HospitalInformationStatisticsDto dto) {
		String treatmentApproach=dto.getTreatmentApproach();
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		// 路径及文件名
		// 输出文档路径及名称
		String savePath = WebContextHolder.getContext().getRequest().getServletContext().getRealPath("");
		String path = new File(savePath).getParentFile().getAbsolutePath();
		String basicPath = "/upload/fileExport/wordExport/";
		String outPutPath = path + basicPath;
		File document = new File(outPutPath);
		if (!document.exists()) {
			document.mkdirs();
		}
		SimpleDateFormat smt = new SimpleDateFormat("yyyyMMddHHmmss");
		String toDate = smt.format(new Date());
		String fileName ="";
				// 删除文件
		CSVUtils.deleteFiles(outPutPath);
		// 生成文件
		List<HospitalInformationStatisticsVo> list = new ArrayList<>();
		if("BeHospitalized".equals(treatmentApproach )){
			map.put("month", "月份");
			map.put("hospitalizationTime", "住院人次");
			map.put("hospitalizationNum", "住院人数");
			map.put("hospitalizationTotalNum", "总住院日");
			map.put("hospitalizationTotalCost", "住院总费用(万元)");
			map.put("hospitalizationBasicPayment", "住院基本统筹支付(万元)");
			map.put("hospitalizationMedInsurance", "住院纳入医保范畴的费用(万元)");
			map.put("bedFee", "床日费(元)");
			map.put("averageCost", "次均费用(元)");
			fileName =  "医院详细信息_" + toDate + "_住院信息";
			list=hospitalInformationStatisticsService.exportBeHospitalized(dto);
		}else if("Outpatient".equals(treatmentApproach )){
			map.put("month", "月份");
			map.put("outpatientTime", "门诊人次");
			map.put("outpatientNum", "门诊人数");
			map.put("outpatientConsultation", "门诊复诊率");
			map.put("outpatientTotalCost", "门诊总费用(万元)");
			map.put("basicOverallPayment", "基本统筹支付(万元)");
			map.put("basicMedInsurance", "符合基本医疗保险的费用(万元)");
			fileName =  "医院详细信息_" + toDate + "_门诊信息";
			list = hospitalInformationStatisticsService.exportOutpatient(dto);
		}
		File csvFile = CSVUtils.createCSVFile(list, map, outPutPath, fileName);
		fileName = csvFile.getName();
		WebContextHolder.getContext().getRequest().getSession().setAttribute("csvFile", csvFile);
		dto.setFileName(fileName);
		dto.setOperateSuccess(true);
		return dto;
	}

}
