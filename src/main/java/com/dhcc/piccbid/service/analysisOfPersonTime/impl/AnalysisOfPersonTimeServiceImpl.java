package com.dhcc.piccbid.service.analysisOfPersonTime.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.analysisOfPersonTime.AnalysisOfPersonTimeJdbcDao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.analysisOfPersonTime.AnalysisOfPersonTimeDao;
import com.dhcc.piccbid.dto.analysisOfPersonTime.AnalysisOfPersonTimeDto;
import com.dhcc.piccbid.entity.analysisOfPersonTime.AnalysisOfPersonTime;
import com.dhcc.piccbid.service.analysisOfPersonTime.AnalysisOfPersonTimeService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-23 15:00:27
 * @version V1.0
 */
@Service("analysisOfPersonTimeService")
public class AnalysisOfPersonTimeServiceImpl extends AbstractBaseService<AnalysisOfPersonTime, String> implements AnalysisOfPersonTimeService {

	private AnalysisOfPersonTimeDao analysisOfPersonTimeDao;
	@Resource
	private AnalysisOfPersonTimeJdbcDao analysisOfPersonTimeJdbcDao;

	public AnalysisOfPersonTimeServiceImpl(AnalysisOfPersonTimeDao analysisOfPersonTimeDao) {
		super(analysisOfPersonTimeDao);
		this.analysisOfPersonTimeDao = analysisOfPersonTimeDao;
	}

	@Override
	public PageModel list(AnalysisOfPersonTimeDto dto) {
		Specification<AnalysisOfPersonTime> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<AnalysisOfPersonTime> page = analysisOfPersonTimeDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<AnalysisOfPersonTime> getListSpecification(final AnalysisOfPersonTimeDto dto){
        return new Specification<AnalysisOfPersonTime>() {
            @Override
            public Predicate toPredicate(Root<AnalysisOfPersonTime> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}



	@Override
	public PageModel table1(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeJdbcDao.createTable1(dto);
		return dto.getPageModel();
	}
	@Override
	public PageModel table2(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeJdbcDao.createTable2(dto);
		return dto.getPageModel();
	}
	@Override
	public PageModel table3(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeJdbcDao.createTable3(dto);
		return dto.getPageModel();
	}
	@Override
	public PageModel table4(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeJdbcDao.createTable4(dto);
		return dto.getPageModel();
	}

}
