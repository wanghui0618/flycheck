package com.dhcc.piccbid.service.drugcheck.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.drugcheck.DrugcheckDao;
import com.dhcc.piccbid.dto.drugcheck.DrugcheckDto;
import com.dhcc.piccbid.entity.drugcheck.Drugcheck;
import com.dhcc.piccbid.service.drugcheck.DrugcheckService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-17 22:23:31
 * @version V1.0
 */
@Service("drugcheckService")
public class DrugcheckServiceImpl extends AbstractBaseService<Drugcheck, String> implements DrugcheckService {

	private DrugcheckDao drugcheckDao;

	public DrugcheckServiceImpl(DrugcheckDao drugcheckDao) {
		super(drugcheckDao);
		this.drugcheckDao = drugcheckDao;
	}

	@Override
	public PageModel list(DrugcheckDto dto) {
		Specification<Drugcheck> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<Drugcheck> page = drugcheckDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<Drugcheck> getListSpecification(final DrugcheckDto dto){
        return new Specification<Drugcheck>() {
            @Override
            public Predicate toPredicate(Root<Drugcheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
