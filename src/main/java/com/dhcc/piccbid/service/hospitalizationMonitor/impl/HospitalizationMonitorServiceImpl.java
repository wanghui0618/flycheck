package com.dhcc.piccbid.service.hospitalizationMonitor.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.hospitalizationMonitor.HospitalizationMonitorJdbcDao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.hospitalizationMonitor.HospitalizationMonitorDao;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
import com.dhcc.piccbid.entity.hospitalizationMonitor.HospitalizationMonitor;
import com.dhcc.piccbid.service.hospitalizationMonitor.HospitalizationMonitorService;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-19 17:33:36
 * @version V1.0
 */
@Service("hospitalizationMonitorService")
public class HospitalizationMonitorServiceImpl extends AbstractBaseService<HospitalizationMonitor, String> implements HospitalizationMonitorService {

	private HospitalizationMonitorDao hospitalizationMonitorDao;
	@Resource
	private HospitalizationMonitorJdbcDao hospitalizationMonitorJdbcDao;

	public HospitalizationMonitorServiceImpl(HospitalizationMonitorDao hospitalizationMonitorDao) {
		super(hospitalizationMonitorDao);
		this.hospitalizationMonitorDao = hospitalizationMonitorDao;
	}

	@Override
	public PageModel list(HospitalizationMonitorDto dto) {
		Specification<HospitalizationMonitor> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<HospitalizationMonitor> page = hospitalizationMonitorDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	protected Specification<HospitalizationMonitor> getListSpecification(final HospitalizationMonitorDto dto){
        return new Specification<HospitalizationMonitor>() {
            @Override
            public Predicate toPredicate(Root<HospitalizationMonitor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	public PageModel yesterday(HospitalizationMonitorDto dto){
		hospitalizationMonitorJdbcDao.yesterday(dto);
		return dto.getPageModel();
	};


	public PageModel inTop(HospitalizationMonitorDto dto){
		hospitalizationMonitorJdbcDao.inTop(dto);
		return dto.getPageModel();
	};

//	public PageModel cityInfo(HospitalCostStatisticsDto dto){
////		if (dto.getHospitalCostStatistics()!=null&&dto.getHospitalCostStatistics().getOrgName()!=null){
////			if(dto.getHospitalCostStatistics().getOrgName().equals("violation")){
////				hospitalizationMonitorJdbcDao.violation(dto);
////				return dto.getPageModel();
////			}
////		}
//		hospitalizationMonitorJdbcDao.cityInfo(dto);
//		return dto.getPageModel();
//	};

}
