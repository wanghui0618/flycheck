package com.dhcc.piccbid.service.avgDays.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.avgDays.AvgDaysJDBCDao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.avgDays.AvgDaysDao;
import com.dhcc.piccbid.dto.avgDays.AvgDaysDto;
import com.dhcc.piccbid.entity.avgDays.AvgDays;
import com.dhcc.piccbid.service.avgDays.AvgDaysService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-07-31 10:28:01
 * @version V1.0
 */
@Service("avgDaysService")
public class AvgDaysServiceImpl extends AbstractBaseService<AvgDays, String> implements AvgDaysService {

	private AvgDaysDao avgDaysDao;
	@Resource
	private AvgDaysJDBCDao avgDaysJDBCDao;

	public AvgDaysServiceImpl(AvgDaysDao avgDaysDao) {
		super(avgDaysDao);
		this.avgDaysDao = avgDaysDao;
	}

	@Override
	public PageModel list(AvgDaysDto dto) {
		Specification<AvgDays> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<AvgDays> page = avgDaysDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	@Override
	public PageModel listVo(AvgDaysDto dto) {
		avgDaysJDBCDao.findAll(dto);
		return dto.getPageModel();
	}

	protected Specification<AvgDays> getListSpecification(final AvgDaysDto dto){
        return new Specification<AvgDays>() {
            @Override
            public Predicate toPredicate(Root<AvgDays> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
