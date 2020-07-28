package com.dhcc.piccbid.blh.flyMedicalInhos;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.flyMedicalInhos.FlyMedicalInhosDto;
import com.dhcc.piccbid.entity.flyMedicalInhos.FlyMedicalInhos;
import com.dhcc.piccbid.service.flyMedicalInhos.FlyMedicalInhosService;

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
public class FlyMedicalInhosBlh extends BaseAbstractBlh<FlyMedicalInhosDto> {

	private static Log logger = LogFactory.getLog(FlyMedicalInhosBlh.class);

	@Resource
	private FlyMedicalInhosService flyMedicalInhosService;

	public FlyMedicalInhosBlh() {
		logger.debug("FlyMedicalInhosBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		FlyMedicalInhosDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=flyMedicalInhosService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		FlyMedicalInhosDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		flyMedicalInhosService.save(dto.getFlyMedicalInhos());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		FlyMedicalInhosDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		flyMedicalInhosService.delete(dto.getFlyMedicalInhos().getBridgeId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		FlyMedicalInhosDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		FlyMedicalInhos entity = flyMedicalInhosService.findOne(dto.getFlyMedicalInhos().getBridgeId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFlyMedicalInhos(entity);
	}

	public void findByYear(FlyMedicalInhosDto dto) {
		
		PageModel model = flyMedicalInhosService.findByYear(dto);
		dto.setPageModel(model);
		
	}

	public void findByMonth(FlyMedicalInhosDto dto) {
		
		PageModel model = flyMedicalInhosService.findByMonth(dto);
		dto.setPageModel(model);
		
	}
}
