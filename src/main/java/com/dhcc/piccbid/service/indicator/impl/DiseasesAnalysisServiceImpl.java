package com.dhcc.piccbid.service.indicator.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.indicator.DiseasesAnalysisJdbcDao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.indicator.DiseasesAnalysisDao;
import com.dhcc.piccbid.dto.indicator.DiseasesAnalysisDto;
import com.dhcc.piccbid.entity.indicator.DiseasesAnalysis;
import com.dhcc.piccbid.service.indicator.DiseasesAnalysisService;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-22 12:22:11
 * @version V1.0
 */
@Service("diseasesAnalysisService")
public class DiseasesAnalysisServiceImpl extends AbstractBaseService<DiseasesAnalysis, String> implements DiseasesAnalysisService {

	private DiseasesAnalysisDao diseasesAnalysisDao;
	@Resource
	private DiseasesAnalysisJdbcDao diseasesAnalysisJdbcDao;

	public DiseasesAnalysisServiceImpl(DiseasesAnalysisDao diseasesAnalysisDao) {
		super(diseasesAnalysisDao);
		this.diseasesAnalysisDao = diseasesAnalysisDao;
	}

	@Override
	public PageModel list(DiseasesAnalysisDto dto) {
		Specification<DiseasesAnalysis> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<DiseasesAnalysis> page = diseasesAnalysisDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	@Override
	public PageModel listVo(DiseasesAnalysisDto dto) {
		if(dto!=null&&dto.getDiseasesAnalysis()!=null) {
			if (dto.getDiseasesAnalysis().getOrgName() != null && dto.getDiseasesAnalysis().getOrgName().equals("hadding")) {
				diseasesAnalysisJdbcDao.listVo(dto);
			} else {
				diseasesAnalysisJdbcDao.listHos(dto);
			}
		}
		return dto.getPageModel();
	}

	@Override
	public PageModel listVoTest(DiseasesAnalysisDto dto) {
		if(dto!=null&&dto.getDiseasesAnalysis()!=null) {
			if (dto.getDiseasesAnalysis().getOrgName() != null ) {
				diseasesAnalysisJdbcDao.listHosTest(dto);
			}
		}
		return dto.getPageModel();
	}


	@Override
	public PageModel handdInfo(DiseasesAnalysisDto dto) {

		diseasesAnalysisJdbcDao.handdInfo(dto);

		return dto.getPageModel();
	}
	@Override
	public PageModel diseases(DiseasesAnalysisDto dto) {

		diseasesAnalysisJdbcDao.diseases(dto);

		return dto.getPageModel();
	}

	@Override
	public PageModel hopLevel(DiseasesAnalysisDto dto) {

		diseasesAnalysisJdbcDao.hopLevel(dto);

		return dto.getPageModel();
	}

	protected Specification<DiseasesAnalysis> getListSpecification(final DiseasesAnalysisDto dto){
		return new Specification<DiseasesAnalysis>() {
			@Override
			public Predicate toPredicate(Root<DiseasesAnalysis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				//构造查询条件

				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

}
