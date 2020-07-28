package com.dhcc.piccbid.service.flyDetailInhos.impl;

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
import com.dhcc.piccbid.dao.flyDetailInhos.FlyDetailInhosDao;
import com.dhcc.piccbid.dao.flyDetailInhos.FlyDetailInhosJdbcDao;
import com.dhcc.piccbid.dto.flyDetailInhos.FlyDetailInhosDto;
import com.dhcc.piccbid.entity.flyDetailInhos.FlyDetailInhos;
import com.dhcc.piccbid.service.flyDetailInhos.FlyDetailInhosService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-29 16:37:25
 * @version V1.0
 */
@Service("flyDetailInhosService")
public class FlyDetailInhosServiceImpl extends AbstractBaseService<FlyDetailInhos, String> implements FlyDetailInhosService {

	private FlyDetailInhosDao flyDetailInhosDao;
	
	@Resource
	private FlyDetailInhosJdbcDao jdbcDao;

	public FlyDetailInhosServiceImpl(FlyDetailInhosDao flyDetailInhosDao) {
		super(flyDetailInhosDao);
		this.flyDetailInhosDao = flyDetailInhosDao;
	}

	@Override
	public PageModel list(FlyDetailInhosDto dto) {
		Specification<FlyDetailInhos> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<FlyDetailInhos> page = flyDetailInhosDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<FlyDetailInhos> getListSpecification(final FlyDetailInhosDto dto){
        return new Specification<FlyDetailInhos>() {
            @Override
            public Predicate toPredicate(Root<FlyDetailInhos> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel statisticsDrugs(FlyDetailInhosDto dto) {
		jdbcDao.statisticsDrugs(dto);
		return dto.getPageModel();
	}

}
