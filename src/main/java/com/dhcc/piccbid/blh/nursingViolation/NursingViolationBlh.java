package com.dhcc.piccbid.blh.nursingViolation;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.hospitalizationConditions.HospitalizationConditionsDto;
import com.dhcc.piccbid.dto.nursingViolation.NursingViolationDto;
import com.dhcc.piccbid.entity.nursingViolation.NursingViolation;
import com.dhcc.piccbid.service.nursingViolation.NursingViolationService;

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
public class NursingViolationBlh extends BaseAbstractBlh<NursingViolationDto> {

	private static Log logger = LogFactory.getLog(NursingViolationBlh.class);

	@Resource
	private NursingViolationService nursingViolationService;

	public NursingViolationBlh() {
		logger.debug("NursingViolationBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		NursingViolationDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=nursingViolationService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		NursingViolationDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		nursingViolationService.save(dto.getNursingViolation());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		NursingViolationDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		nursingViolationService.delete(dto.getNursingViolation().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		NursingViolationDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		NursingViolation entity = nursingViolationService.findOne(dto.getNursingViolation().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setNursingViolation(entity);
	}

	public void nursingViolation(NursingViolationDto basedto) {
		NursingViolationDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=nursingViolationService.nursingViolation(dto);
		dto.setPageModel(pageModel);
		
	}
}
