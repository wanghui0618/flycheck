package com.dhcc.piccbid.service.hospitalizationConditions.impl;

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
import com.dhcc.piccbid.dao.hospitalizationConditions.HospitalizationConditionsDao;
import com.dhcc.piccbid.dao.hospitalizationConditions.HospitalizationConditionsJdbcDao;
import com.dhcc.piccbid.dto.hospitalizationConditions.HospitalizationConditionsDto;
import com.dhcc.piccbid.entity.hospitalizationConditions.HospitalizationConditions;
import com.dhcc.piccbid.service.hospitalizationConditions.HospitalizationConditionsService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-24 16:19:35
 * @version V1.0
 */
@Service("hospitalizationConditionsService")
public class HospitalizationConditionsServiceImpl extends AbstractBaseService<HospitalizationConditions, String> implements HospitalizationConditionsService {
	@Resource
	private HospitalizationConditionsJdbcDao hospitalizationConditionsJdbcDao;
	
	@Resource
	private HospitalizationConditionsDao hospitalizationConditionsDao;
	
	
	public HospitalizationConditionsServiceImpl(HospitalizationConditionsDao hospitalizationConditionsDao) {
		super(hospitalizationConditionsDao);
		this.hospitalizationConditionsDao = hospitalizationConditionsDao;
	}

	@Override
	public PageModel list(HospitalizationConditionsDto dto) {
		Specification<HospitalizationConditions> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<HospitalizationConditions> page = hospitalizationConditionsDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<HospitalizationConditions> getListSpecification(final HospitalizationConditionsDto dto){
        return new Specification<HospitalizationConditions>() {
            @Override
            public Predicate toPredicate(Root<HospitalizationConditions> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel hospitalizationConditions(HospitalizationConditionsDto dto) {
		hospitalizationConditionsJdbcDao.hospitalizationConditions(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel getlist(HospitalizationConditionsDto dto) {
		hospitalizationConditionsJdbcDao.getlist(dto);
		return dto.getPageModel();
	}

}
