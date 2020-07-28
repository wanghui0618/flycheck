package com.dhcc.piccbid.blh.findDict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.findDict.FindDictDto;
import com.dhcc.piccbid.entity.findDict.FindDict;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import com.dhcc.piccbid.service.findDict.FindDictService;

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
public class FindDictBlh extends BaseAbstractBlh<FindDictDto> {

	private static Log logger = LogFactory.getLog(FindDictBlh.class);

	@Resource
	private FindDictService findDictService;

	public FindDictBlh() {
		logger.debug("FindDictBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		FindDictDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=findDictService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		FindDictDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		findDictService.save(dto.getFindDict());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		FindDictDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		findDictService.delete(dto.getFindDict().getName());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		FindDictDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		FindDict entity = findDictService.findOne(dto.getFindDict().getName());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFindDict(entity);
	}
	
	public Map<String, Object> findDict(BaseAbstractDto basedto) {
		Map<String, Object> result = new HashMap<String, Object>();
		FindDictDto dto = super.getExactlyDto(basedto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setPageNo(dto.getPage());
		dto.getPageModel().setPageSize(dto.getRows());
		PageModel pageModel=findDictService.findDict(dto);
		@SuppressWarnings("unchecked")
		List<FindDictVo> list=(List<FindDictVo>) pageModel.getPageData();
	    for (FindDictVo findDictVo : list) {
	    	if(findDictVo!=null){
				if(findDictVo.getName()==null){

				}else{
					findDictVo.setName(findDictVo.getName().trim());
				}
			}
		}
		result.put("total", pageModel.getTotals());  
        result.put("rows", list); 
        result.put("_pagelines",dto.getRows());
        result.put("_currpage", dto.getPage());
        return result;
	}
	public Map<String, Object> fyType(BaseAbstractDto basedto) {
		Map<String, Object> result = new HashMap<String, Object>();
		FindDictDto dto = super.getExactlyDto(basedto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setPageNo(dto.getPage());
		dto.getPageModel().setPageSize(dto.getRows());
		PageModel pageModel=findDictService.fyType(dto);
		@SuppressWarnings("unchecked")
		List<FindDictVo> list=(List<FindDictVo>) pageModel.getPageData();
	    for (FindDictVo findDictVo : list) {
	    	if(findDictVo!=null){
				if(findDictVo.getName()==null){

				}else{
					findDictVo.setName(findDictVo.getName().trim());
				}
			}
		}
		result.put("total", pageModel.getTotals());  
        result.put("rows", list); 
        result.put("_pagelines",dto.getRows());
        result.put("_currpage", dto.getPage());
        return result;
	}

	public Map<String, Object> findpCategory(FindDictDto basedto) {
		Map<String, Object> result = new HashMap<String, Object>();
		FindDictDto dto = super.getExactlyDto(basedto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setPageNo(dto.getPage());
		dto.getPageModel().setPageSize(dto.getRows());
		PageModel pageModel=findDictService.findpCategory(dto);
		@SuppressWarnings("unchecked")
		List<FindDictVo> list=(List<FindDictVo>) pageModel.getPageData();
	    for (FindDictVo findDictVo : list) {
	    	if(findDictVo!=null){
				if(findDictVo.getName()==null){

				}else{
					findDictVo.setName(findDictVo.getName().trim());
				}
			}
		}
		result.put("total", pageModel.getTotals());  
        result.put("rows", list); 
        result.put("_pagelines",dto.getRows());
        result.put("_currpage", dto.getPage());
        return result;
	}

}
