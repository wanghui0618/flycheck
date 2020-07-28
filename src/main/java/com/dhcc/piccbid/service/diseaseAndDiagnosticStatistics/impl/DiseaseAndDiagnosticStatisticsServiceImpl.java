package com.dhcc.piccbid.service.diseaseAndDiagnosticStatistics.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsDao;
import com.dhcc.piccbid.dao.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsJdbcDao;
import com.dhcc.piccbid.dto.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsDto;
import com.dhcc.piccbid.entity.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatistics;
import com.dhcc.piccbid.service.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author scy
 * @date 2019-10-16 17:08:09
 * @version V1.0
 */
@Service("diseaseAndDiagnosticStatisticsService")
public class DiseaseAndDiagnosticStatisticsServiceImpl extends
		AbstractBaseService<DiseaseAndDiagnosticStatistics, String> implements DiseaseAndDiagnosticStatisticsService {
	@Autowired
	private DiseaseAndDiagnosticStatisticsDao diseaseAndDiagnosticStatisticsDao;
	@Autowired
	private DiseaseAndDiagnosticStatisticsJdbcDao diseaseAndDiagnosticStatisticsJdbcDao;

	public DiseaseAndDiagnosticStatisticsServiceImpl(
			DiseaseAndDiagnosticStatisticsDao diseaseAndDiagnosticStatisticsDao) {
		super(diseaseAndDiagnosticStatisticsDao);
		this.diseaseAndDiagnosticStatisticsDao = diseaseAndDiagnosticStatisticsDao;
	}

	@Override
	public PageModel list(DiseaseAndDiagnosticStatisticsDto dto) {
		Specification<DiseaseAndDiagnosticStatistics> spec = getListSpecification(dto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		Page<DiseaseAndDiagnosticStatistics> page = diseaseAndDiagnosticStatisticsDao.findAll(spec,
				dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	protected Specification<DiseaseAndDiagnosticStatistics> getListSpecification(
			final DiseaseAndDiagnosticStatisticsDto dto) {
		return new Specification<DiseaseAndDiagnosticStatistics>() {
			@Override
			public Predicate toPredicate(Root<DiseaseAndDiagnosticStatistics> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				// 构造查询条件

				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

	@Override
	public PageModel getlist(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsJdbcDao.getlist(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel getlistByinhosDiag(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsJdbcDao.getlistByinhosDiag(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel getlistByinhosDate(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsJdbcDao.getlistByinhosDate(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel statisticalAnalysisByInsuranceType(DiseaseAndDiagnosticStatisticsDto dto) {
		diseaseAndDiagnosticStatisticsJdbcDao.statisticalAnalysisByInsuranceType(dto);
		return dto.getPageModel();
	}
}
