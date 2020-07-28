package com.dhcc.piccbid.blh.physicalExaminationAdmission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
import com.dhcc.piccbid.dto.physicalExaminationAdmission.PhysicalExaminationAdmissionDto;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import com.dhcc.piccbid.entity.physicalExaminationAdmission.PhysicalExaminationAdmission;
import com.dhcc.piccbid.service.physicalExaminationAdmission.PhysicalExaminationAdmissionService;

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
public class PhysicalExaminationAdmissionBlh extends BaseAbstractBlh<PhysicalExaminationAdmissionDto> {

	private static Log logger = LogFactory.getLog(PhysicalExaminationAdmissionBlh.class);

	@Resource
	private PhysicalExaminationAdmissionService physicalExaminationAdmissionService;

	public PhysicalExaminationAdmissionBlh() {
		logger.debug("PhysicalExaminationAdmissionBlh Init");
	}

	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * 
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		PhysicalExaminationAdmissionDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = physicalExaminationAdmissionService.list(dto);
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
		PhysicalExaminationAdmissionDto dto = super.getExactlyDto(basedto);
		// 调用对应的service保存方法
		physicalExaminationAdmissionService.save(dto.getPhysicalExaminationAdmission());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		PhysicalExaminationAdmissionDto dto = super.getExactlyDto(basedto);
		// 调用对应的service删除方法
		physicalExaminationAdmissionService.delete(dto.getPhysicalExaminationAdmission().getHisid());
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
		PhysicalExaminationAdmissionDto dto = super.getExactlyDto(basedto);

		// 调用对应的service查询某个实体的方法
		PhysicalExaminationAdmission entity = physicalExaminationAdmissionService
				.findOne(dto.getPhysicalExaminationAdmission().getHisid());
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPhysicalExaminationAdmission(entity);
	}

	/**
	 * 体检式入院
	 * 
	 * @param basedto
	 */
	public void physicalExamination(BaseAbstractDto basedto) {
		PhysicalExaminationAdmissionDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = physicalExaminationAdmissionService.physicalExamination(dto);
		dto.setPageModel(pageModel);
	}

	public void sumTotalCount(BaseAbstractDto basedto) {
		PhysicalExaminationAdmissionDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = physicalExaminationAdmissionService.sumTotalCount(dto);
		dto.setPageModel(pageModel);
	}

	public void countphysicalExaminationMx(BaseAbstractDto basedto) {
		PhysicalExaminationAdmissionDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = physicalExaminationAdmissionService.countphysicalExaminationMx(dto);
		dto.setPageModel(pageModel);
	}

	// 导出excel
	public XSSFWorkbook exportExcelToSelf(PhysicalExaminationAdmissionDto dto) {
		String param = dto.getParam();
		JSONObject json= (JSONObject)JSONObject.parse(param);
		if(json!=null) {
			dto.setHospitalId(json.getString("hospitalId"));
			dto.setHospitalName(json.getString("hospitalName"));
			dto.setBillDate(json.getString("billDate"));
			dto.setCode(json.getString("code"));
			dto.setCode1(json.getString("code1"));
			dto.setSumdrugs(json.getString("Sumdrugs"));
			dto.setJianchafei(json.getString("jianchafei"));
			dto.setAdmissionDiseaseId(json.getString("admissionDiseaseId"));
			dto.setAdmissionDiseaseName(json.getString("admissionDiseaseName"));
			dto.setAdmissionDate(json.getString("admissionDate"));
			dto.setDischargeDate(json.getString("dischargeDate"));
		}
		return physicalExaminationAdmissionService.exportExcelToSelf(dto);
	}

}
