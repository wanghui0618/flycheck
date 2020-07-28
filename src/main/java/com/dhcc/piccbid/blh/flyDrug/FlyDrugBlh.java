package com.dhcc.piccbid.blh.flyDrug;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.flyDrug.FlyDrugDto;
import com.dhcc.piccbid.dto.flyMedicalDetail.FlyMedicalDetailDto;
import com.dhcc.piccbid.entity.flyDrug.FlyDrug;
import com.dhcc.piccbid.service.flyDrug.FlyDrugService;

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
public class FlyDrugBlh extends BaseAbstractBlh<FlyDrugDto> {

	private static Log logger = LogFactory.getLog(FlyDrugBlh.class);

	@Resource
	private FlyDrugService flyDrugService;

	public FlyDrugBlh() {
		logger.debug("FlyDrugBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		FlyDrugDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=flyDrugService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		FlyDrugDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		flyDrugService.save(dto.getFlyDrug());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		FlyDrugDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		flyDrugService.delete(dto.getFlyDrug().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		FlyDrugDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		FlyDrug entity = flyDrugService.findOne(dto.getFlyDrug().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFlyDrug(entity);
	}
	public void listVo(BaseAbstractDto basedto) {
		FlyDrugDto dto =super.getExactlyDto(basedto);
		PageModel pageModel =flyDrugService.listVo(dto);
		dto.setPageModel(pageModel);
	}
	
}
