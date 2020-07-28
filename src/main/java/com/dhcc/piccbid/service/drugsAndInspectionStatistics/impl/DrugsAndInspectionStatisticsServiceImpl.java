package com.dhcc.piccbid.service.drugsAndInspectionStatistics.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.piccbid.dao.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsJDBCDao;
import com.dhcc.piccbid.entity.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsDao;
import com.dhcc.piccbid.dto.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsDto;
import com.dhcc.piccbid.entity.drugsAndInspectionStatistics.DrugsAndInspectionStatistics;
import com.dhcc.piccbid.service.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @date 2019-10-15 16:48:40
 * @version V1.0
 */
@Service("drugsAndInspectionStatisticsService")
public class DrugsAndInspectionStatisticsServiceImpl extends AbstractBaseService<DrugsAndInspectionStatistics, String> implements DrugsAndInspectionStatisticsService {

	private DrugsAndInspectionStatisticsDao drugsAndInspectionStatisticsDao;
	@Autowired
    private DrugsAndInspectionStatisticsJDBCDao drugsAndInspectionStatisticsJDBCDao;
	@Autowired
    private CommonService commonService;

	public DrugsAndInspectionStatisticsServiceImpl(DrugsAndInspectionStatisticsDao drugsAndInspectionStatisticsDao) {
		super(drugsAndInspectionStatisticsDao);
		this.drugsAndInspectionStatisticsDao = drugsAndInspectionStatisticsDao;
	}

	@Override
	public PageModel list(DrugsAndInspectionStatisticsDto dto) {
         PageModel pageModel=drugsAndInspectionStatisticsJDBCDao.list(dto);
        return pageModel;
	}

	@Override
	public PageModel listForCostDetail(DrugsAndInspectionStatisticsDto dto) {
         PageModel pageModel=drugsAndInspectionStatisticsJDBCDao.listForCostDetail(dto);
        return pageModel;
	}
	@Override
	public PageModel listForInselectionCostDetail(DrugsAndInspectionStatisticsDto dto) {
         PageModel pageModel=drugsAndInspectionStatisticsJDBCDao.listForInselectionCostDetail(dto);
        return pageModel;
	}

	@Override
	public PageModel listForInspection(DrugsAndInspectionStatisticsDto dto) {
         PageModel pageModel=drugsAndInspectionStatisticsJDBCDao.listForInspection(dto);
        return pageModel;
	}
	
	protected Specification<DrugsAndInspectionStatistics> getListSpecification(final DrugsAndInspectionStatisticsDto dto){
        return new Specification<DrugsAndInspectionStatistics>() {
            @Override
            public Predicate toPredicate(Root<DrugsAndInspectionStatistics> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
