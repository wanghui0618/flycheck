package com.dhcc.piccbid.service.dataComparison.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.dataComparison.DataComparisonJdbcDao;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.dataComparison.DataComparisonDao;
import com.dhcc.piccbid.dto.dataComparison.DataComparisonDto;
import com.dhcc.piccbid.entity.dataComparison.DataComparison;
import com.dhcc.piccbid.service.dataComparison.DataComparisonService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-11-26 16:14:41
 * @version V1.0
 */
@Service("dataComparisonService")
public class DataComparisonServiceImpl extends AbstractBaseService<DataComparison, String> implements DataComparisonService {

	private DataComparisonDao dataComparisonDao;
	@Resource
	private DataComparisonJdbcDao dataComparisonJdbcDao;

	public DataComparisonServiceImpl(DataComparisonDao dataComparisonDao) {
		super(dataComparisonDao);
		this.dataComparisonDao = dataComparisonDao;
	}

	@Override
	public PageModel list(DataComparisonDto dto) {
		Specification<DataComparison> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<DataComparison> page = dataComparisonDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<DataComparison> getListSpecification(final DataComparisonDto dto){
        return new Specification<DataComparison>() {
            @Override
            public Predicate toPredicate(Root<DataComparison> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}


	@Override
	public PageModel table1(DataComparisonDto dto) {
		dataComparisonJdbcDao.table1(dto);
		return dto.getPageModel();
	}
	@Override
	public PageModel table2(DataComparisonDto dto) {
		dataComparisonJdbcDao.table2(dto);
		return dto.getPageModel();
	}
	@Override
	public PageModel table3(DataComparisonDto dto) {
		dataComparisonJdbcDao.table3(dto);
		return dto.getPageModel();
	}
	@Override
	public PageModel table45(DataComparisonDto dto) {
		dataComparisonJdbcDao.table45(dto);
		return dto.getPageModel();
	}
	@Override
	public PageModel table6(DataComparisonDto dto) {
		dataComparisonJdbcDao.table6(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel table7(DataComparisonDto dto) {
		dataComparisonJdbcDao.table7(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel table8_1(DataComparisonDto dto) {
		dataComparisonJdbcDao.table8_1(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel table8_2(DataComparisonDto dto) {
		dataComparisonJdbcDao.table8_2(dto);
		return dto.getPageModel();
	}

	@Override
	public SXSSFWorkbook exportExcelToSelf(DataComparisonDto dto) {
		return dataComparisonJdbcDao.exportExcelToSelf(dto);
	}

}
