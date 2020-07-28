package com.dhcc.piccbid.service.itemCodeAndNameSearch.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.itemCodeAndNameSearch.ItemCodeAndNameSearchJDBCDao;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.itemCodeAndNameSearch.ItemCodeAndNameSearchDao;
import com.dhcc.piccbid.dto.itemCodeAndNameSearch.ItemCodeAndNameSearchDto;
//import com.dhcc.piccbid.entity.itemCodeAndNameSearch.ItemCodeAndNameSearch;
import com.dhcc.piccbid.service.itemCodeAndNameSearch.ItemCodeAndNameSearchService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author Yangsx
 * @date 2019-12-12 16:18:53
 * @version V1.0
 */
@Service("itemCodeAndNameSearchService")
public class ItemCodeAndNameSearchServiceImpl implements ItemCodeAndNameSearchService {

	@Resource
	private ItemCodeAndNameSearchJDBCDao itemCodeAndNameSearchJDBCDao;
	/***
	 * 根据项目编码或名称  查询项目信息 （名称模糊查询，编码精确查询）
	 * @param dto
	 */
	@Override
	public PageModel getItemInfoByCodeOrName(ItemCodeAndNameSearchDto dto) {
		itemCodeAndNameSearchJDBCDao.getItemInfoByCodeOrName(dto);
		PageModel pageModel = dto.getPageModel();
		return pageModel;
	}

	 @Override
	    public XSSFWorkbook exportExcel() {
	        return itemCodeAndNameSearchJDBCDao.exportExcel();
	    }

}
