package com.dhcc.piccbid.blh.dict;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.dict.DictDto;
import com.dhcc.piccbid.entity.dict.Dict;
import com.dhcc.piccbid.entity.dict.DictResponseVo;
import com.dhcc.piccbid.service.dict.DictService;

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
public class DictBlh extends BaseAbstractBlh<DictDto> {

	private static Log logger = LogFactory.getLog(DictBlh.class);

	@Resource
	private DictService dictService;

	public DictBlh() {
		logger.debug("DictBlh Init");
	}
	
	
	public void findDict(BaseAbstractDto basedto) {
		
		DictDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		List<DictResponseVo> list = dictService.findDict(dto.getDictRequestVo());
		
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setDictList(list);
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		DictDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=dictService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		DictDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		dictService.save(dto.getDict());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		DictDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		dictService.delete(dto.getDict().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		DictDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		Dict entity = dictService.findOne(dto.getDict().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setDict(entity);
	}
}
