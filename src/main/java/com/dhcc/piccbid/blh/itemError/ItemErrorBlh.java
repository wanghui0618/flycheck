package com.dhcc.piccbid.blh.itemError;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.itemError.ItemErrorDto;
import com.dhcc.piccbid.entity.itemError.ItemError;
import com.dhcc.piccbid.service.itemError.ItemErrorService;

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
public class ItemErrorBlh extends BaseAbstractBlh<ItemErrorDto> {

	private static Log logger = LogFactory.getLog(ItemErrorBlh.class);

	@Resource
	private ItemErrorService itemErrorService;

	public ItemErrorBlh() {
		logger.debug("ItemErrorBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		ItemErrorDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=itemErrorService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void listHop(BaseAbstractDto basedto) {
		ItemErrorDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=itemErrorService.listHop(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	public void listItem(BaseAbstractDto basedto) {
		ItemErrorDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=itemErrorService.listItem(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		ItemErrorDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		itemErrorService.save(dto.getItemError());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		ItemErrorDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		itemErrorService.delete(dto.getItemError().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		ItemErrorDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		ItemError entity = itemErrorService.findOne(dto.getItemError().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setItemError(entity);
	}
}
