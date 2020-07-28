package com.dhcc.piccbid.service.flyGeneralOverview.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.flyGeneralOverview.FlyGeneralOverviewDao;
import com.dhcc.piccbid.dao.flyGeneralOverview.FlyGeneralOverviewJdbcDao;
import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.entity.dict.DictRequestVo;
import com.dhcc.piccbid.entity.dict.DictResponseVo;
import com.dhcc.piccbid.entity.flycheckMedical.FlyGeneralOverviewVo;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
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
@Service("flyGeneralOverviewService")
public class FlyGeneralOverviewServiceImpl extends AbstractBaseService<FlycheckMedical, String> implements FlyGeneralOverviewService {
	
	@Resource
	private FlyGeneralOverviewDao flyGeneralOverviewDao;
	@Resource
	private FlyGeneralOverviewJdbcDao flyGeneralOverviewJdbcDao;

	public FlyGeneralOverviewServiceImpl(FlyGeneralOverviewDao flyGeneralOverviewDao) {
		super(flyGeneralOverviewDao);
		this.flyGeneralOverviewDao = flyGeneralOverviewDao;
	}

	@Override
	public PageModel list(FlyGeneralOverviewDto dto) {
		Specification<FlycheckMedical> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<FlycheckMedical> page = flyGeneralOverviewDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	@Override
	public SXSSFWorkbook getData(FlyGeneralOverviewDto dto) {
		return flyGeneralOverviewJdbcDao.getData(dto);
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
	public PageModel FlyGeneralOverviewDtoFind(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewJdbcDao.FlyGeneralOverviewDtoFind(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel FlyGeneralOverviewDtoFind1(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewJdbcDao.FlyGeneralOverviewDtoFind1(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel FlyGeneralOverviewDtoFind2(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewJdbcDao.FlyGeneralOverviewDtoFind2(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel FlyGeneralOverviewDtoFind3(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewJdbcDao.FlyGeneralOverviewDtoFind3(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel FlyGeneralOverviewDtoFind4(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewJdbcDao.FlyGeneralOverviewDtoFind4(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel FlyGeneralOverviewDtoFind5(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewJdbcDao.FlyGeneralOverviewDtoFind5(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel FlyGeneralOverviewDtoFind6(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewJdbcDao.FlyGeneralOverviewDtoFind6(dto);
		return dto.getPageModel();
	}
	public PageModel FindMedicalSumAmount(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewJdbcDao.FindMedicalSumAmount(dto);
		return dto.getPageModel();
	}

	@Override
	public List<FlyGeneralOverviewVo> findHospitalName(FlyGeneralOverviewVo flyGeneralOverviewVo) {

		return flyGeneralOverviewJdbcDao.findHospitalName(flyGeneralOverviewVo);
	}
	
	@Override
	public List<FlyGeneralOverviewVo> findHospitalId(FlyGeneralOverviewVo flyGeneralOverviewVo) {

		return flyGeneralOverviewJdbcDao.findHospitalId(flyGeneralOverviewVo);
	}

}
