package com.dhcc.piccbid.service.statisticsInRecentTwoYears.impl;

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
import com.dhcc.piccbid.dao.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsDao;
import com.dhcc.piccbid.dao.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsJdbcDao;
import com.dhcc.piccbid.dto.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsDto;
import com.dhcc.piccbid.entity.medicaldetail.MedicalDetail;
import com.dhcc.piccbid.service.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-08 12:08:41
 * @version V1.0
 */
@Service("statisticsOfDiagnosisAndTreatmentItemsService")
public class StatisticsInRecentTwoYearsServiceImpl extends AbstractBaseService<MedicalDetail, String>
		implements StatisticsInRecentTwoYearsService {
	@Resource
	private StatisticsInRecentTwoYearsDao statisticsInRecentTwoYearsDao;

	@Resource
	private StatisticsInRecentTwoYearsJdbcDao statisticsInRecentTwoYearsJdbcDao;

	public StatisticsInRecentTwoYearsServiceImpl(StatisticsInRecentTwoYearsDao statisticsInRecentTwoYearsDao) {
		super(statisticsInRecentTwoYearsDao);
		this.statisticsInRecentTwoYearsDao = statisticsInRecentTwoYearsDao;
	}

	@Override
	public PageModel list(StatisticsInRecentTwoYearsDto dto) {
		Specification<MedicalDetail> spec = getListSpecification(dto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		Page<MedicalDetail> page = statisticsInRecentTwoYearsDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	protected Specification<MedicalDetail> getListSpecification(final StatisticsInRecentTwoYearsDto dto) {
		return new Specification<MedicalDetail>() {
			@Override
			public Predicate toPredicate(Root<MedicalDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				// 构造查询条件

				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

	/**
	 * 近两年统计
	 */
	@Override
	public PageModel Dosage(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsJdbcDao.Dosage(dto);
		return dto.getPageModel();
	}

	/**
	 * 超平均住院人数
	 */
	@Override
	public PageModel getCount(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsJdbcDao.getCount(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel getlist(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsJdbcDao.getlist(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel getlistByinhosDiag(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsJdbcDao.getlistByinhosDiag(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel statisticalAnalysisByInsuranceType(StatisticsInRecentTwoYearsDto dto) {
		statisticsInRecentTwoYearsJdbcDao.statisticalAnalysisByInsuranceType(dto);
		return dto.getPageModel();
	}

}
