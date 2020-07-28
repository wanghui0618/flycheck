package com.dhcc.piccbid.blh.hospitalizationMonitor;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
import com.dhcc.piccbid.entity.hospitalizationMonitor.HospitalizationMonitor;
import com.dhcc.piccbid.service.hospitalizationMonitor.HospitalizationMonitorService;

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
public class HospitalizationMonitorBlh extends BaseAbstractBlh<HospitalizationMonitorDto> {

	private static Log logger = LogFactory.getLog(HospitalizationMonitorBlh.class);

	@Resource
	private HospitalizationMonitorService hospitalizationMonitorService;

	public HospitalizationMonitorBlh() {
		logger.debug("HospitalizationMonitorBlh Init");
	}

	/**
	 *
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		HospitalizationMonitorDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=hospitalizationMonitorService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	/**
	 *
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		HospitalizationMonitorDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		hospitalizationMonitorService.save(dto.getHospitalizationMonitor());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 *
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		HospitalizationMonitorDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
//		hospitalizationMonitorService.delete(dto.getHospitalizationMonitor().get{$idName_1upper}());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 *
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *
	 */
	public void findById(BaseAbstractDto basedto) {
		HospitalizationMonitorDto dto = super.getExactlyDto(basedto);

		//调用对应的service查询某个实体的方法
//		HospitalizationMonitor entity = hospitalizationMonitorService.findOne(dto.getHospitalizationMonitor().get{$idName_1upper}());
		//不要删除这行代码，调用set是为以后 service层要加缓存
//		dto.setHospitalizationMonitor(entity);
	}

	public void yesterday(HospitalizationMonitorDto dto ){
		PageModel pageModel=hospitalizationMonitorService.yesterday(dto);
		dto.setPageModel(pageModel);
	}

	public void inTop(BaseAbstractDto basedto ){
		HospitalizationMonitorDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=hospitalizationMonitorService.inTop(dto);
		dto.setPageModel(pageModel);
	}

//	public void cityInfo(HospitalCostStatisticsDto dto ){
//		PageModel pageModel=hospitalizationMonitorService.cityInfo(dto);
//		dto.setPageModel(pageModel);
//	}
}
