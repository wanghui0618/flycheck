package com.dhcc.piccbid.service.findDict.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.findDict.FindDictDao;
import com.dhcc.piccbid.dao.findDict.FindDictJdbcDao;
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
 * @author heqiang
 * @date 2019-08-29 10:26:51
 * @version V1.0
 */
@Service("findDictService")
public class FindDictServiceImpl extends AbstractBaseService<FindDict, String> implements FindDictService {

	private FindDictDao findDictDao;

	@Resource
	private FindDictJdbcDao findDictJdbcDao;

	@Resource
	private CommonService commonService;

	public FindDictServiceImpl(FindDictDao findDictDao) {
		super(findDictDao);
		this.findDictDao = findDictDao;
	}

	@Override
	public PageModel list(FindDictDto dto) {
		Specification<FindDict> spec = getListSpecification(dto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		Page<FindDict> page = findDictDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	protected Specification<FindDict> getListSpecification(final FindDictDto dto) {
		return new Specification<FindDict>() {
			@Override
			public Predicate toPredicate(Root<FindDict> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				// 构造查询条件

				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

	@Override
	public PageModel findDict(FindDictDto dto) {
		findDictJdbcDao.findDict(dto);
		commonService.fillSqlPageModelData(dto.getPageModel(), FindDictVo.class, "*");
		return dto.getPageModel();
	}

	@Override
	public PageModel fyType(FindDictDto dto) {
		findDictJdbcDao.fyType(dto);
		commonService.fillSqlPageModelData(dto.getPageModel(), FindDictVo.class, "*");
		return dto.getPageModel();
	}

	@Override
	public PageModel findpCategory(FindDictDto dto) {
		findDictJdbcDao.findpCategory(dto);
		commonService.fillSqlPageModelData(dto.getPageModel(), FindDictVo.class, "*");
		return dto.getPageModel();
	}
}
