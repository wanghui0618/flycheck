package com.dhcc.piccbid.service.genderLimit.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.genderLimit.GenderLimitJdbcDao;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.genderLimit.GenderLimitDao;
import com.dhcc.piccbid.dto.genderLimit.GenderLimitDto;
import com.dhcc.piccbid.entity.genderLimit.GenderLimit;
import com.dhcc.piccbid.service.genderLimit.GenderLimitService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2020-01-03 10:32:21
 * @version V1.0
 */
@Service("genderLimitService")
public class GenderLimitServiceImpl extends AbstractBaseService<GenderLimit, String> implements GenderLimitService {

	@Resource
	private GenderLimitDao genderLimitDao;

	@Resource
	private GenderLimitJdbcDao genderLimitJdbcDao;

	public GenderLimitServiceImpl(GenderLimitDao genderLimitDao) {
		super(genderLimitDao);
		this.genderLimitDao = genderLimitDao;
	}

	@Override
	public PageModel list(GenderLimitDto dto) {
		Specification<GenderLimit> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<GenderLimit> page = genderLimitDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	@Override
	public PageModel genderLimit(GenderLimitDto dto) {
		genderLimitJdbcDao.genderLimit(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel gather(GenderLimitDto dto) {
		genderLimitJdbcDao.gather(dto);
		return dto.getPageModel();
	}

	@Override
	public XSSFWorkbook exportExcel(GenderLimitDto dto) {
		return genderLimitJdbcDao.exportExcel(dto);
	}

	@Override
	public PageModel detail(GenderLimitDto dto) {
		genderLimitJdbcDao.detail(dto);
		return dto.getPageModel();
	}

	protected Specification<GenderLimit> getListSpecification(final GenderLimitDto dto){
        return new Specification<GenderLimit>() {
            @Override
            public Predicate toPredicate(Root<GenderLimit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}


}
