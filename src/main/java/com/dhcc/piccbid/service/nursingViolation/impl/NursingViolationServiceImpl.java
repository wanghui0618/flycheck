package com.dhcc.piccbid.service.nursingViolation.impl;

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
import com.dhcc.piccbid.dao.nursingViolation.NursingViolationDao;
import com.dhcc.piccbid.dao.nursingViolation.NursingViolationJdbcDao;
import com.dhcc.piccbid.dto.nursingViolation.NursingViolationDto;
import com.dhcc.piccbid.entity.nursingViolation.NursingViolation;
import com.dhcc.piccbid.service.nursingViolation.NursingViolationService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-29 16:10:27
 * @version V1.0
 */
@Service("nursingViolationService")
public class NursingViolationServiceImpl extends AbstractBaseService<NursingViolation, String> implements NursingViolationService {
	@Resource
	private NursingViolationDao nursingViolationDao;
	@Resource
	public NursingViolationJdbcDao nursingViolationJdbcDao;

	public NursingViolationServiceImpl(NursingViolationDao nursingViolationDao) {
		super(nursingViolationDao);
		this.nursingViolationDao = nursingViolationDao;
	}

	@Override
	public PageModel list(NursingViolationDto dto) {
		Specification<NursingViolation> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<NursingViolation> page = nursingViolationDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<NursingViolation> getListSpecification(final NursingViolationDto dto){
        return new Specification<NursingViolation>() {
            @Override
            public Predicate toPredicate(Root<NursingViolation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel nursingViolation(NursingViolationDto dto) {
		nursingViolationJdbcDao.nursingViolation(dto);
		return dto.getPageModel();
	}

}
