package com.dhcc.piccbid.service.dataCompare.impl;

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
import com.dhcc.piccbid.dao.dataCompare.DataCompareDao;
import com.dhcc.piccbid.dao.dataCompare.DataCompareJdbcDao;
import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import com.dhcc.piccbid.service.dataCompare.DataCompareService;
import com.dhcc.piccbid.service.flyGeneralOverview.FlyGeneralOverviewService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liufaxing
 * @date 2019-11-24 12:40:05
 * @version V1.0
 */
@Service("dataCompareService")
public class DataCompareServiceImpl extends AbstractBaseService<FlycheckMedical, String> implements DataCompareService {
	
	@Resource
	private DataCompareDao dataCompareDao;
	@Resource
	private DataCompareJdbcDao dataCompareJdbcDao;

	public DataCompareServiceImpl(DataCompareDao dataCompareDao) {
		super(dataCompareDao);
		this.dataCompareDao = dataCompareDao;
	}

	@Override
	public PageModel list(FlyGeneralOverviewDto dto) {
		Specification<FlycheckMedical> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<FlycheckMedical> page = dataCompareDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<FlycheckMedical> getListSpecification(final FlyGeneralOverviewDto dto){
        return new Specification<FlycheckMedical>() {
            @Override
            public Predicate toPredicate(Root<FlycheckMedical> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}
	
	@Override
	public PageModel dataCompareDtoFind(FlyGeneralOverviewDto dto) {
		dataCompareJdbcDao.FlyGeneralOverviewDtoFind(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel dataCompareDtoFind1(FlyGeneralOverviewDto dto) {
		dataCompareJdbcDao.FlyGeneralOverviewDtoFind1(dto);
		return dto.getPageModel();
	}

}
