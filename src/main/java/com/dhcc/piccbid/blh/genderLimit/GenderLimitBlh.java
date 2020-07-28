package com.dhcc.piccbid.blh.genderLimit;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.genderLimit.GenderLimitDto;
import com.dhcc.piccbid.entity.genderLimit.GenderLimit;
import com.dhcc.piccbid.service.genderLimit.GenderLimitService;

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
public class GenderLimitBlh extends BaseAbstractBlh<GenderLimitDto> {

	private static Log logger = LogFactory.getLog(GenderLimitBlh.class);

	@Resource
	private GenderLimitService genderLimitService;

	public GenderLimitBlh() {
		logger.debug("GenderLimitBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		GenderLimitDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=genderLimitService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		GenderLimitDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		genderLimitService.save(dto.getGenderLimit());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		GenderLimitDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		genderLimitService.delete(dto.getGenderLimit().getHisid());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		GenderLimitDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		GenderLimit entity = genderLimitService.findOne(dto.getGenderLimit().getHisid());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setGenderLimit(entity);
	}

	/**
	 * 限性别用药
	 * @param basedto
	 */
	public void genderLimit(BaseAbstractDto basedto) {
		GenderLimitDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = genderLimitService.genderLimit(dto);
		dto.setPageModel(pageModel);
	}

	/**
	 * 汇总
	 * @param basedto
	 */
	public void gather(BaseAbstractDto basedto) {
		GenderLimitDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = genderLimitService.gather(dto);
		dto.setPageModel(pageModel);
	}

	/**
	 * 导出
	 * @return
	 */
	public XSSFWorkbook exportExcel(BaseAbstractDto basedto) {
		GenderLimitDto dto = super.getExactlyDto(basedto);
		String param = dto.getParam();
		JSONObject jsonObject= (JSONObject)JSONObject.parse(param);
		if(jsonObject!=null) {
			if(dto.getGenderLimit()==null) {
				dto.setGenderLimit(new GenderLimit());
			}
			GenderLimit genderLimit = dto.getGenderLimit();
			dto.setBalanceDate(jsonObject.getString("balanceDate"));
			dto.setAdmissionDate(jsonObject.getString("admissionDate"));
			dto.setDischargeDate(jsonObject.getString("dischargeDate"));
			genderLimit.setAdmissionDiseaseId(jsonObject.getString("genderLimit.admissionDiseaseId"));
			genderLimit.setAdmissionDiseaseName(jsonObject.getString("genderLimit.admissionDiseaseName"));
			genderLimit.setHospitalId(jsonObject.getString("genderLimit.hospitalId"));
			genderLimit.setHospitalName(jsonObject.getString("genderLimit.hospitalName"));
			genderLimit.setItemId(jsonObject.getString("genderLimit.itemId"));
			genderLimit.setItemName(jsonObject.getString("genderLimit.itemName"));
			genderLimit.setPatientGender(jsonObject.getString("genderLimit.patientGender"));
		}
		return genderLimitService.exportExcel(dto);
	}

	/**
	 * 明细
	 * @param basedto
	 */
	public void detail(BaseAbstractDto basedto) {
		GenderLimitDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=genderLimitService.detail(dto);
		dto.setPageModel(pageModel);
	}

}
