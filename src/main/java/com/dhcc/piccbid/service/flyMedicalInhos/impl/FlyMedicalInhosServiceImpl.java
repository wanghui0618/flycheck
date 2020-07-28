package com.dhcc.piccbid.service.flyMedicalInhos.impl;

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
import com.dhcc.piccbid.dao.flyMedicalInhos.FlyMedicalInhosDao;
import com.dhcc.piccbid.dao.flyMedicalInhos.FlyMedicalInhosJdbcDao;
import com.dhcc.piccbid.dto.flyMedicalInhos.FlyMedicalInhosDto;
import com.dhcc.piccbid.entity.flyMedicalInhos.FlyMedicalInhos;
import com.dhcc.piccbid.service.flyMedicalInhos.FlyMedicalInhosService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-31 11:40:47
 * @version V1.0
 */
@Service("flyMedicalInhosService")
public class FlyMedicalInhosServiceImpl extends AbstractBaseService<FlyMedicalInhos, String> implements FlyMedicalInhosService {

	private FlyMedicalInhosDao flyMedicalInhosDao;
	
	@Resource
	private FlyMedicalInhosJdbcDao jdbcDao;

	public FlyMedicalInhosServiceImpl(FlyMedicalInhosDao flyMedicalInhosDao) {
		super(flyMedicalInhosDao);
		this.flyMedicalInhosDao = flyMedicalInhosDao;
	}

	@Override
	public PageModel list(FlyMedicalInhosDto dto) {
		Specification<FlyMedicalInhos> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<FlyMedicalInhos> page = flyMedicalInhosDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<FlyMedicalInhos> getListSpecification(final FlyMedicalInhosDto dto){
        return new Specification<FlyMedicalInhos>() {
            @Override
            public Predicate toPredicate(Root<FlyMedicalInhos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel findByYear(FlyMedicalInhosDto dto) {
		
		jdbcDao.findByYear(dto);
		
		return dto.getPageModel();
		
	}

	@Override
	public PageModel findByMonth(FlyMedicalInhosDto dto) {
		
		jdbcDao.findByMonth(dto);
		
		return dto.getPageModel();
		
	}

}
