package com.dhcc.piccbid.blh.home;

import javax.annotation.Resource;

import com.dhcc.piccbid.dto.home.MonitorPolygonalchartDto;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
//import com.dhcc.piccbid.dto.medicalregister.MedicalRegisterDto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.home.RegionalfundsDto;
import com.dhcc.piccbid.entity.home.Regionalfunds;
import com.dhcc.piccbid.service.home.RegionalfundsService;

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
public class RegionalfundsBlh extends BaseAbstractBlh<RegionalfundsDto> {

	private static Log logger = LogFactory.getLog(RegionalfundsBlh.class);

	@Resource
	private RegionalfundsService regionalfundsService;

	public RegionalfundsBlh() {
		logger.debug("RegionalfundsBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		RegionalfundsDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=regionalfundsService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		RegionalfundsDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		regionalfundsService.save(dto.getRegionalfunds());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
//		RegionalfundsDto dto = super.getExactlyDto(basedto);
//		//调用对应的service删除方法
//		regionalfundsService.delete(dto.getRegionalfunds().get{$idName_1upper}());
//		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		RegionalfundsDto dto = super.getExactlyDto(basedto);

		//调用对应的service查询某个实体的方法
//		Regionalfunds entity = regionalfundsService.findOne(dto.getRegionalfunds().get{$idName_1upper}());
		//不要删除这行代码，调用set是为以后 service层要加缓存
//		dto.setRegionalfunds(entity);
	}

	public void listVo(BaseAbstractDto basedto) {
		RegionalfundsDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=regionalfundsService.listVo(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void listVo2(MonitorPolygonalchartDto dto) {
		//调用service查询方法
		PageModel pageModel=regionalfundsService.listVo2(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void totalTimes(HospitalizationMonitorDto dto) {
		//调用service查询方法
		PageModel pageModel=regionalfundsService.totalTimes(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	public void bigDataHomepage(BaseAbstractDto basedto) {
		RegionalfundsDto dto = super.getExactlyDto(basedto);
		regionalfundsService.bigDataHomepage(dto);
	}
}
