package com.dhcc.piccbid.blh.flyDetailInhos;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.flyDetailInhos.FlyDetailInhosDto;
import com.dhcc.piccbid.dto.flymedical.FlymedicalDto;
import com.dhcc.piccbid.entity.flyDetailInhos.FlyDetailInhos;
import com.dhcc.piccbid.service.flyDetailInhos.FlyDetailInhosService;

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
public class FlyDetailInhosBlh extends BaseAbstractBlh<FlyDetailInhosDto> {

	private static Log logger = LogFactory.getLog(FlyDetailInhosBlh.class);

	@Resource
	private FlyDetailInhosService flyDetailInhosService;

	public FlyDetailInhosBlh() {
		logger.debug("FlyDetailInhosBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		FlyDetailInhosDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=flyDetailInhosService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		FlyDetailInhosDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		flyDetailInhosService.save(dto.getFlyDetailInhos());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		FlyDetailInhosDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		flyDetailInhosService.delete(dto.getFlyDetailInhos().getHisid());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		FlyDetailInhosDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		FlyDetailInhos entity = flyDetailInhosService.findOne(dto.getFlyDetailInhos().getHisid());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFlyDetailInhos(entity);
	}

	public void statisticsDrugs(BaseAbstractDto basedto) {
		FlyDetailInhosDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyDetailInhosService.statisticsDrugs(dto);
		dto.setPageModel(pageModel);
	}
}
