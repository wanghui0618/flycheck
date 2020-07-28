package com.dhcc.piccbid.blh.hospitalLevelRmacy;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.FlyCheckMedicalVo;
import com.dhcc.piccbid.service.hospitalLevelRmacy.HospitalLevelRmacyService;

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
public class HospitalLevelRmacyBlh extends BaseAbstractBlh<HospitalLevelRmacyDto> {

	private static Log logger = LogFactory.getLog(HospitalLevelRmacyBlh.class);

	@Resource
	private HospitalLevelRmacyService hospitalLevelRmacyService;

	public HospitalLevelRmacyBlh() {
		logger.debug("HospitalLevelRmacyBlh Init");
	}
	
	
	/***
	 * 限医院等级用药主单
	 * @param basedto
	 */
	public void limitedHospitalLevelZd(BaseAbstractDto basedto) {
		HospitalLevelRmacyDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalLevelRmacyService.limitedHospitalLevelZd(dto);
		dto.setPageModel(pageModel);
	}
	
	/***
	 * 限医院等级用药明细
	 * @param basedto
	 */
	public void limitedHospitalLevelMx(BaseAbstractDto basedto) {
		HospitalLevelRmacyDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalLevelRmacyService.limitedHospitalLevelMx(dto);
		dto.setPageModel(pageModel);
	}
	
	/***
	 * 限医院等级用药明细
	 * @param basedto
	 */
	public void physicalExaminationMx(BaseAbstractDto basedto) {
		HospitalLevelRmacyDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalLevelRmacyService.physicalExaminationMx(dto);
		dto.setPageModel(pageModel);
	}
	
	/***
	 * 主单统计
	 * @param basedto
	 */
	public void countMedical(BaseAbstractDto basedto) {
		HospitalLevelRmacyDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalLevelRmacyService.countMedical(dto);
		dto.setPageModel(pageModel);
	}
	
	/***
	 * 明细统计
	 * @param basedto
	 */
	public void countMx(BaseAbstractDto basedto) {
		HospitalLevelRmacyDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = hospitalLevelRmacyService.countMx(dto);
		dto.setPageModel(pageModel);
	}
	
	/**
     * 导出
     * @return
     */
    public SXSSFWorkbook exportExcel(BaseAbstractDto basedto) {
    	HospitalLevelRmacyDto dto = super.getExactlyDto(basedto);
    	String queryParams=dto.getQueryParams();
		JSONObject json= (JSONObject)JSONObject.parse(queryParams);
		if(json!=null) {
			if(dto.getFlyCheckMedicalVo()==null) {
				dto.setFlyCheckMedicalVo(new FlyCheckMedicalVo());
			}
			dto.setType(json.getString("type"));
			dto.getFlyCheckMedicalVo().setHospitalName(json.getString("flyCheckMedicalVo.hospitalName"));
			dto.getFlyCheckMedicalVo().setHospitalId(json.getString("flyCheckMedicalVo.hospitalId"));
			dto.getFlyCheckMedicalVo().setAdmissionDiseaseName(json.getString("flyCheckMedicalVo.admissionDiseaseName"));
			dto.getFlyCheckMedicalVo().setAdmissionDiseaseId(json.getString("flyCheckMedicalVo.admissionDiseaseId"));
			dto.getFlyCheckMedicalVo().setBillDate(json.getString("flyCheckMedicalVo.billDate"));
			dto.getFlyCheckMedicalVo().setpLevel(json.getString("flyCheckMedicalVo.pLevel"));
			dto.getFlyCheckMedicalVo().setAdmissionDate(json.getString("flyCheckMedicalVo.admissionDate"));
			dto.getFlyCheckMedicalVo().setDischargeDate(json.getString("flyCheckMedicalVo.dischargeDate"));
		}
        return hospitalLevelRmacyService.exportExcel(dto);
    }
	
	
}
