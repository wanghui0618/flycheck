package com.dhcc.piccbid.blh.hospitalviolation;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.hospitalviolation.HospitalViolationDto;
import com.dhcc.piccbid.entity.hospitalviolation.HospitalViolation;
import com.dhcc.piccbid.entity.violationdetails.SysVerifyVo;
import com.dhcc.piccbid.service.hospitalviolation.HospitalViolationService;

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
public class HospitalViolationBlh extends BaseAbstractBlh<HospitalViolationDto> {

	private static Log logger = LogFactory.getLog(HospitalViolationBlh.class);

	@Resource
	private HospitalViolationService hospitalViolationService;

	public HospitalViolationBlh() {
		logger.debug("HospitalViolationBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		HospitalViolationDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=hospitalViolationService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		HospitalViolationDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		hospitalViolationService.save(dto.getHospitalViolation());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		HospitalViolationDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		hospitalViolationService.delete(dto.getHospitalViolation().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		HospitalViolationDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		HospitalViolation entity = hospitalViolationService.findOne(dto.getHospitalViolation().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setHospitalViolation(entity);
	}
	public void listVo(BaseAbstractDto basedto) {
		HospitalViolationDto dto = super.getExactlyDto(basedto);
		
		//调用service查询方法
		PageModel pageModel=hospitalViolationService.listVo(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public List<SysVerifyVo> violationSpread(HospitalViolationDto dto) {
		return hospitalViolationService.violationSpread(dto);
	}

	public XSSFWorkbook exportExcelToSelf(String status,String handdingInsName) {
		return hospitalViolationService.exportExcelToSelf(status,handdingInsName);
	}
	
	public void hopVio(BaseAbstractDto basedto) {
		HospitalViolationDto dto = super.getExactlyDto(basedto);
		
		//调用service查询方法
		PageModel pageModel=hospitalViolationService.hopVio(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	public void medVio(BaseAbstractDto basedto) {
		HospitalViolationDto dto = super.getExactlyDto(basedto);
		
		//调用service查询方法
		PageModel pageModel=hospitalViolationService.medVio(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
}
