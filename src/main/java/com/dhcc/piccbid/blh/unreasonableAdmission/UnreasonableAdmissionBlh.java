package com.dhcc.piccbid.blh.unreasonableAdmission;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.FlyCheckMedicalVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import com.dhcc.piccbid.entity.unreasonableAdmission.UnreasonableAdmission;
import com.dhcc.piccbid.service.unreasonableAdmission.UnreasonableAdmissionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class UnreasonableAdmissionBlh extends BaseAbstractBlh<UnreasonableAdmissionDto> {

	private static Log logger = LogFactory.getLog(UnreasonableAdmissionBlh.class);

	@Resource
	private UnreasonableAdmissionService unreasonableAdmissionService;

	public UnreasonableAdmissionBlh() {
		logger.debug("UnreasonableAdmissionBlh Init");
	}

	public Map<String, Object> findDict(UnreasonableAdmissionDto basedto) {
		Map<String, Object> result = new HashMap<String, Object>();
		UnreasonableAdmissionDto dto = super.getExactlyDto(basedto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setPageNo(dto.getPages());
		dto.getPageModel().setPageSize(dto.getRows());
		PageModel pageModel=unreasonableAdmissionService.findDict(dto);
		@SuppressWarnings("unchecked")
		List<FindDictVo> list=(List<FindDictVo>) pageModel.getPageData();
		for (FindDictVo findDictVo : list) {
			if(findDictVo!=null){
				if(findDictVo.getName()==null){

				}else{
					findDictVo.setName(findDictVo.getName().trim());
				}
			}
		}
		result.put("total", pageModel.getTotals());
		result.put("rows", list);
		result.put("_pagelines",dto.getRows());
		result.put("_currpage", dto.getPages());
		return result;
	}

	public SXSSFWorkbook getData(BaseAbstractDto basedto) {
		UnreasonableAdmissionDto dto = super.getExactlyDto(basedto);
		String queryParams=dto.getParam();
		JSONObject json= (JSONObject)JSONObject.parse(queryParams);
		if(json!=null) {
			if(dto.getUnreasonableAdmission()==null) {
				dto.setUnreasonableAdmission(new UnreasonableAdmission());
			}
			dto.getUnreasonableAdmission().setHospitalId(json.getString("unreasonableAdmission.hospitalId"));
			dto.getUnreasonableAdmission().setHospitalName(json.getString("unreasonableAdmission.hospitalName"));
			dto.getUnreasonableAdmission().setBillDateStr(json.getString("unreasonableAdmission.billDateStr"));
			dto.getUnreasonableAdmission().setAdmissionDateStr(json.getString("unreasonableAdmission.admissionDateStr"));
			dto.getUnreasonableAdmission().setDischargeDateStr(json.getString("unreasonableAdmission.dischargeDateStr"));
			dto.getUnreasonableAdmission().setCostOfTreatment(json.getString("unreasonableAdmission.costOfTreatment"));
			dto.getUnreasonableAdmission().setInspectionFeeRatio(json.getString("unreasonableAdmission.inspectionFeeRatio"));
			dto.getUnreasonableAdmission().setProportionOfMedicines(json.getString("unreasonableAdmission.proportionOfMedicines"));
			dto.getUnreasonableAdmission().setCode1(json.getString("unreasonableAdmission.code1"));
			dto.getUnreasonableAdmission().setCode2(json.getString("unreasonableAdmission.code2"));
			dto.getUnreasonableAdmission().setCode3(json.getString("unreasonableAdmission.code3"));
		}
		return unreasonableAdmissionService.getData(dto);
	}
	public void caseInfoList(UnreasonableAdmissionDto basedto){
		UnreasonableAdmissionDto dto = super.getExactlyDto(basedto);
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		unreasonableAdmissionService.caseInfoList(dto);
	}
	/*
	*
	* 查询不合理病例
	* */

	public void  listUnreasonableAdmission(UnreasonableAdmissionDto basedto){
		UnreasonableAdmissionDto dto = super.getExactlyDto(basedto);
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		unreasonableAdmissionService.listUnreasonableAdmission(dto);
	}
	public void  countUnreasonableAdmissions(UnreasonableAdmissionDto basedto){
		UnreasonableAdmissionDto dto = super.getExactlyDto(basedto);
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		unreasonableAdmissionService.countUnreasonableAdmissions(dto);
	}
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		UnreasonableAdmissionDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=unreasonableAdmissionService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		UnreasonableAdmissionDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		unreasonableAdmissionService.save(dto.getUnreasonableAdmission());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		UnreasonableAdmissionDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		unreasonableAdmissionService.delete(dto.getUnreasonableAdmission().getHisid());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		UnreasonableAdmissionDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		UnreasonableAdmission entity = unreasonableAdmissionService.findOne(dto.getUnreasonableAdmission().getHisid());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setUnreasonableAdmission(entity);
	}
}
