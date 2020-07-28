package com.dhcc.piccbid.service.flyAvgDay.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.flyAvgDay.FlyAvgDayDao;
import com.dhcc.piccbid.dao.flyAvgDay.FlyAvgDayJdbcDao;
import com.dhcc.piccbid.dto.flyAvgDay.FlyAvgDayDto;
import com.dhcc.piccbid.entity.flyAvgDay.FlyAvgDay;
import com.dhcc.piccbid.service.flyAvgDay.FlyAvgDayService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wy
 * @date 2019-10-15 14:35:33
 * @version V1.0
 */
@Service("flyAvgDayService")
public class FlyAvgDayServiceImpl extends AbstractBaseService<FlyAvgDay, String> implements FlyAvgDayService {

	@Autowired
	private FlyAvgDayDao flyAvgDayDao;
	@Autowired
	private FlyAvgDayJdbcDao flyAvgDayJdbcDao;
	@Resource
	private CommonService commonService;

	public FlyAvgDayServiceImpl(FlyAvgDayDao flyAvgDayDao) {
		super(flyAvgDayDao);
		this.flyAvgDayDao = flyAvgDayDao;
	}

	@Override
	public PageModel list(FlyAvgDayDto dto) {
		Specification<FlyAvgDay> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<FlyAvgDay> page = flyAvgDayDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<FlyAvgDay> getListSpecification(final FlyAvgDayDto dto){
        return new Specification<FlyAvgDay>() {
            @Override
            public Predicate toPredicate(Root<FlyAvgDay> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}
	
	
	public PageModel flyAvgDay(FlyAvgDayDto dto) {
		flyAvgDayJdbcDao.flyAvgDay(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}

}
