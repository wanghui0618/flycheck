package com.dhcc.piccbid.blh.flydetail;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.flydetail.FlydetailDto;
import com.dhcc.piccbid.entity.flydetail.Flydetail;
import com.dhcc.piccbid.service.flydetail.FlydetailService;

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
public class FlydetailBlh extends BaseAbstractBlh<FlydetailDto> {

	private static Log logger = LogFactory.getLog(FlydetailBlh.class);

	@Resource
	private FlydetailService flydetailService;

	public FlydetailBlh() {
		logger.debug("FlydetailBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		FlydetailDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=flydetailService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	public void listVo(BaseAbstractDto basedto) {
		FlydetailDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=flydetailService.listVo(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		FlydetailDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		flydetailService.save(dto.getFlydetail());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		FlydetailDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		flydetailService.delete(dto.getFlydetail().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		FlydetailDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		Flydetail entity = flydetailService.findOne(dto.getFlydetail().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFlydetail(entity);
	}
}
