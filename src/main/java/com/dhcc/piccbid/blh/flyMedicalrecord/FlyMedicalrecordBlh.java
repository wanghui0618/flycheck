package com.dhcc.piccbid.blh.flyMedicalrecord;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.flymedical.FlymedicalDto;
import com.dhcc.piccbid.entity.flyMedicalrecord.FlyMedicalrecord;
import com.dhcc.piccbid.service.flymedical.FlymedicalService;

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
public class FlyMedicalrecordBlh extends BaseAbstractBlh<FlymedicalDto> {

	private static Log logger = LogFactory.getLog(FlyMedicalrecordBlh.class);

	@Resource
	private FlymedicalService flymedicalService;

	public FlyMedicalrecordBlh() {
		logger.debug("FlymedicalBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		FlymedicalDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=flymedicalService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void screenSameEntryAndExitDate(BaseAbstractDto basedto) {
		FlymedicalDto dto = super.getExactlyDto(basedto);
		//调用对应的service查询某个实体的方法
		PageModel pageModel = flymedicalService.screenSameEntryAndExitDate(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	public void listVo(BaseAbstractDto basedto) {
		FlymedicalDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=flymedicalService.listVo(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		FlymedicalDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		flymedicalService.save(dto.getFlymedical());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		FlymedicalDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		flymedicalService.delete(dto.getFlymedical().getBridgeId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		FlymedicalDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		FlyMedicalrecord entity = flymedicalService.findOne(dto.getFlymedical().getBridgeId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFlymedical(entity);
	}

	public void relateDiagnosis(BaseAbstractDto basedto) {
		FlymedicalDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=flymedicalService.relateDiagnosis(dto);
		dto.setPageModel(pageModel);
	}
}
