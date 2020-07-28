package com.dhcc.piccbid.service.home.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.home.RegionalfundsJdbcDao;
import com.dhcc.piccbid.dto.home.MonitorPolygonalchartDto;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.home.RegionalfundsDao;
import com.dhcc.piccbid.dto.home.RegionalfundsDto;
import com.dhcc.piccbid.entity.home.Regionalfunds;
import com.dhcc.piccbid.service.home.RegionalfundsService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-15 16:58:47
 * @version V1.0
 */
@Service("regionalfundsService")
public class RegionalfundsServiceImpl extends AbstractBaseService<Regionalfunds, String> implements RegionalfundsService {

	private RegionalfundsDao regionalfundsDao;
	@Resource
	private RegionalfundsJdbcDao regionalfundsJdbcDao;

	public RegionalfundsServiceImpl(RegionalfundsDao regionalfundsDao) {
		super(regionalfundsDao);
		this.regionalfundsDao = regionalfundsDao;
	}

	@Override
	public PageModel list(RegionalfundsDto dto) {
		Specification<Regionalfunds> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<Regionalfunds> page = regionalfundsDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<Regionalfunds> getListSpecification(final RegionalfundsDto dto){
        return new Specification<Regionalfunds>() {
            @Override
            public Predicate toPredicate(Root<Regionalfunds> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel listVo(RegionalfundsDto dto) {
		regionalfundsJdbcDao.listVo(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel listVo2(MonitorPolygonalchartDto dto) {
		regionalfundsJdbcDao.monitorPolygonalchart(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel totalTimes(HospitalizationMonitorDto dto) {
		regionalfundsJdbcDao.totalTimes(dto);
		return dto.getPageModel();
	}

	/* (non-Javadoc)
	 * @see com.dhcc.piccbid.service.home.RegionalfundsService#bigDataHomepage(com.dhcc.piccbid.dto.home.RegionalfundsDto)
	 */
	@Override
	public void bigDataHomepage(RegionalfundsDto dto) {
		// TODO Auto-generated method stub
		regionalfundsJdbcDao.bigDataHomepage(dto);
	}
	

}
