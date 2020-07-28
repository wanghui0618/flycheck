package com.dhcc.piccbid.blh.dataCompare;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import com.dhcc.piccbid.service.flyGeneralOverview.FlyGeneralOverviewService;

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
public class DataCompareBlh extends BaseAbstractBlh<FlyGeneralOverviewDto> {

	private static Log logger = LogFactory.getLog(DataCompareBlh.class);

	@Resource
	private FlyGeneralOverviewService flyGeneralOverviewService;

	public DataCompareBlh() {
		logger.debug("FlyGeneralOverviewBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=flyGeneralOverviewService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		flyGeneralOverviewService.save(dto.getFlyGeneralOverview());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		flyGeneralOverviewService.delete(dto.getFlyGeneralOverview().getHisid());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		FlycheckMedical entity = flyGeneralOverviewService.findOne(dto.getFlyGeneralOverview().getHisid());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFlyGeneralOverview(entity);
	}
	
	public void FlyGeneralOverviewDtoFind(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FlyGeneralOverviewDtoFind(dto);
		dto.setPageModel(pageModel);
	}
	
	public void FlyGeneralOverviewDtoFind1(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FlyGeneralOverviewDtoFind1(dto);
		dto.setPageModel(pageModel);
	}
}
