package com.dhcc.piccbid.service.mainDiagnosis.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.mainDiagnosis.MainDiagnosisDao;
import com.dhcc.piccbid.dao.mainDiagnosis.MainDiagnosisJdbcDao;
import com.dhcc.piccbid.dto.mainDiagnosis.MainDiagnosisDto;
import com.dhcc.piccbid.entity.mainDiagnosis.MainDiagnosis;
import com.dhcc.piccbid.service.mainDiagnosis.MainDiagnosisService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-11-08 16:53:31
 * @version V1.0
 */
@Service("mainDiagnosisService")
public class MainDiagnosisServiceImpl extends AbstractBaseService<MainDiagnosis, String> implements MainDiagnosisService {

	private MainDiagnosisDao mainDiagnosisDao;
	@Resource
	private MainDiagnosisJdbcDao jdbcDao;

	public MainDiagnosisServiceImpl(MainDiagnosisDao mainDiagnosisDao) {
		super(mainDiagnosisDao);
		this.mainDiagnosisDao = mainDiagnosisDao;
	}

	@Override
	public PageModel list(MainDiagnosisDto dto) {
		
		jdbcDao.list(dto);
		
		return dto.getPageModel();
		
	}
	
	protected Specification<MainDiagnosis> getListSpecification(final MainDiagnosisDto dto){
        return new Specification<MainDiagnosis>() {
            @Override
            public Predicate toPredicate(Root<MainDiagnosis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
