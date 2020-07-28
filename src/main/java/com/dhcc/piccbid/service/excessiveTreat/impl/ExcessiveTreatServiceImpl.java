package com.dhcc.piccbid.service.excessiveTreat.impl;

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
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.excessiveTreat.ExcessiveTreatDao;
import com.dhcc.piccbid.dao.excessiveTreat.ExcessiveTreatJdbcDao;
import com.dhcc.piccbid.dto.excessiveTreat.ExcessiveTreatDto;
import com.dhcc.piccbid.entity.excessiveTreat.ExcessiveTreat;
//import com.dhcc.piccbid.entity.indicationTreat.IndicationTreat;
import com.dhcc.piccbid.service.excessiveTreat.ExcessiveTreatService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-14 10:11:17
 * @version V1.0
 */
@Service("excessiveTreatService")
public class ExcessiveTreatServiceImpl extends AbstractBaseService<ExcessiveTreat, String> implements ExcessiveTreatService {

	private ExcessiveTreatDao excessiveTreatDao;
	
	@Resource
	private ExcessiveTreatJdbcDao excessiveTreatJdbcDao;
	
	@Resource
	private CommonService commonService;

	public ExcessiveTreatServiceImpl(ExcessiveTreatDao excessiveTreatDao) {
		super(excessiveTreatDao);
		this.excessiveTreatDao = excessiveTreatDao;
	}

	@Override
	public PageModel list(ExcessiveTreatDto dto) {
		Specification<ExcessiveTreat> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<ExcessiveTreat> page = excessiveTreatDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<ExcessiveTreat> getListSpecification(final ExcessiveTreatDto dto){
        return new Specification<ExcessiveTreat>() {
            @Override
            public Predicate toPredicate(Root<ExcessiveTreat> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel listVo(ExcessiveTreatDto dto) {
		excessiveTreatJdbcDao.list(dto);
		PageModel pageModel=dto.getPageModel();
		commonService.fillSqlPageModelData(pageModel,ExcessiveTreat.class,"id");
		return dto.getPageModel();
	}

}
