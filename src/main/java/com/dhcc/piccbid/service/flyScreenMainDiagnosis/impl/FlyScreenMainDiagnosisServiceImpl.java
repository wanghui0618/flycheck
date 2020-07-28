package com.dhcc.piccbid.service.flyScreenMainDiagnosis.impl;

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
import com.dhcc.piccbid.dao.flyScreenMainDiagnosis.FlyScreenMainDiagnosisDao;
import com.dhcc.piccbid.dao.flyScreenMainDiagnosis.FlyScreenMainDiagnosisJdbcDao;
import com.dhcc.piccbid.dto.flyScreenMainDiagnosis.FlyScreenMainDiagnosisDto;
import com.dhcc.piccbid.entity.flyScreenMainDiagnosis.FlyScreenMainDiagnosis;
import com.dhcc.piccbid.service.flyScreenMainDiagnosis.FlyScreenMainDiagnosisService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-24 17:15:53
 * @version V1.0
 */
@Service("flyScreenMainDiagnosisService")
public class FlyScreenMainDiagnosisServiceImpl extends AbstractBaseService<FlyScreenMainDiagnosis, String> implements FlyScreenMainDiagnosisService {
	
	private FlyScreenMainDiagnosisDao flyScreenMainDiagnosisDao;
	@Resource
	private FlyScreenMainDiagnosisJdbcDao jdbcDao;

	public FlyScreenMainDiagnosisServiceImpl(FlyScreenMainDiagnosisDao flyScreenMainDiagnosisDao) {
		super(flyScreenMainDiagnosisDao);
		this.flyScreenMainDiagnosisDao = flyScreenMainDiagnosisDao;
	}

	@Override
	public PageModel list(FlyScreenMainDiagnosisDto dto) {
		jdbcDao.list(dto);
		return dto.getPageModel();
	}
	
	protected Specification<FlyScreenMainDiagnosis> getListSpecification(final FlyScreenMainDiagnosisDto dto){
        return new Specification<FlyScreenMainDiagnosis>() {
            @Override
            public Predicate toPredicate(Root<FlyScreenMainDiagnosis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
