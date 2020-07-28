package com.dhcc.piccbid.service.hospitalizationAnalysis.impl;

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
import com.dhcc.piccbid.dao.hospitalizationAnalysis.HospitalizationAnalysisDao;
import com.dhcc.piccbid.dao.hospitalizationAnalysis.HospitalizationAnalysisJdbcDao;
import com.dhcc.piccbid.dto.hospitalizationAnalysis.HospitalizationAnalysisDto;
import com.dhcc.piccbid.entity.hospitalizationAnalysis.HospitalizationAnalysis;
import com.dhcc.piccbid.service.hospitalizationAnalysis.HospitalizationAnalysisService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-17 12:54:10
 * @version V1.0
 */
@Service("hospitalizationAnalysisService")
public class HospitalizationAnalysisServiceImpl extends AbstractBaseService<HospitalizationAnalysis, String> implements HospitalizationAnalysisService {
	@Resource
	private HospitalizationAnalysisJdbcDao hospitalizationAnalysisJdbcDao;
	@Resource
	private HospitalizationAnalysisDao hospitalizationAnalysisDao;

	public HospitalizationAnalysisServiceImpl(HospitalizationAnalysisDao hospitalizationAnalysisDao) {
		super(hospitalizationAnalysisDao);
		this.hospitalizationAnalysisDao = hospitalizationAnalysisDao;
	}

	@Override
	public PageModel list(HospitalizationAnalysisDto dto) {
		Specification<HospitalizationAnalysis> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<HospitalizationAnalysis> page = hospitalizationAnalysisDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<HospitalizationAnalysis> getListSpecification(final HospitalizationAnalysisDto dto){
        return new Specification<HospitalizationAnalysis>() {
            @Override
            public Predicate toPredicate(Root<HospitalizationAnalysis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                final HospitalizationAnalysis hospitalizationAnalysis = new HospitalizationAnalysis();
               
                //构造查询条件
                
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel hospitalizationAnalysis(HospitalizationAnalysisDto dto) {
		hospitalizationAnalysisJdbcDao.hospitalizationAnalysis(dto);
		
		return dto.getPageModel();
	}

}
