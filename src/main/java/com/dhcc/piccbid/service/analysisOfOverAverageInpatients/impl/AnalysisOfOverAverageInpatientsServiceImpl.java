package com.dhcc.piccbid.service.analysisOfOverAverageInpatients.impl;

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
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsDao;
import com.dhcc.piccbid.dao.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsJdbcDao;
import com.dhcc.piccbid.dto.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsDto;
import com.dhcc.piccbid.entity.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatients;
import com.dhcc.piccbid.service.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author scy
 * @date 2019-10-24 15:16:38
 * @version V1.0
 */
@Service("analysisOfOverAverageInpatientsService")
public class AnalysisOfOverAverageInpatientsServiceImpl extends AbstractBaseService<AnalysisOfOverAverageInpatients, String>
		implements AnalysisOfOverAverageInpatientsService {
	@Resource
	private AnalysisOfOverAverageInpatientsDao analysisOfOverAverageInpatientsDao;
	@Resource
	private AnalysisOfOverAverageInpatientsJdbcDao analysisOfOverAverageInpatientsJdbcDao;

	public AnalysisOfOverAverageInpatientsServiceImpl(AnalysisOfOverAverageInpatientsDao emptyHangingBedAnalysisDao) {
		super(emptyHangingBedAnalysisDao);
		this.analysisOfOverAverageInpatientsDao = emptyHangingBedAnalysisDao;
	}

	@Override
	public PageModel list(AnalysisOfOverAverageInpatientsDto dto) {
		Specification<AnalysisOfOverAverageInpatients> spec = getListSpecification(dto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		Page<AnalysisOfOverAverageInpatients> page = analysisOfOverAverageInpatientsDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	protected Specification<AnalysisOfOverAverageInpatients> getListSpecification(final AnalysisOfOverAverageInpatientsDto dto) {
		return new Specification<AnalysisOfOverAverageInpatients>() {
			@Override
			public Predicate toPredicate(Root<AnalysisOfOverAverageInpatients> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				// 构造查询条件

				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

	@Override
	public PageModel getCount(AnalysisOfOverAverageInpatientsDto dto) {
		analysisOfOverAverageInpatientsJdbcDao.getCount(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel towYears(AnalysisOfOverAverageInpatientsDto dto) {
		analysisOfOverAverageInpatientsJdbcDao.towYears(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel countDiagnosisAndTreatmentItems(AnalysisOfOverAverageInpatientsDto dto) {
		analysisOfOverAverageInpatientsJdbcDao.countDiagnosisAndTreatmentItems(dto);
		return dto.getPageModel();
	}

}
