package com.dhcc.piccbid.blh.hospitalizationConditions;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.hospitalizationAnalysis.HospitalizationAnalysisDto;
import com.dhcc.piccbid.dto.hospitalizationConditions.HospitalizationConditionsDto;
import com.dhcc.piccbid.entity.hospitalizationConditions.HospitalizationConditions;
import com.dhcc.piccbid.service.hospitalizationConditions.HospitalizationConditionsService;

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
public class HospitalizationConditionsBlh extends BaseAbstractBlh<HospitalizationConditionsDto> {

	private static Log logger = LogFactory.getLog(HospitalizationConditionsBlh.class);

	@Resource
	private HospitalizationConditionsService hospitalizationConditionsService;

	public HospitalizationConditionsBlh() {
		logger.debug("HospitalizationConditionsBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		HospitalizationConditionsDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=hospitalizationConditionsService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		HospitalizationConditionsDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		hospitalizationConditionsService.save(dto.getHospitalizationConditions());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		HospitalizationConditionsDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		hospitalizationConditionsService.delete(dto.getHospitalizationConditions().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		HospitalizationConditionsDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		HospitalizationConditions entity = hospitalizationConditionsService.findOne(dto.getHospitalizationConditions().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setHospitalizationConditions(entity);
	}

	public void hospitalizationConditions(HospitalizationConditionsDto basedto) {
		HospitalizationConditionsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=hospitalizationConditionsService.hospitalizationConditions(dto);
		dto.setPageModel(pageModel);
		
	}

	public void getlist(HospitalizationConditionsDto basedto) {
		HospitalizationConditionsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=hospitalizationConditionsService.getlist(dto);
		dto.setPageModel(pageModel);
	}
}
