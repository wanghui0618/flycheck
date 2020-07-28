package com.dhcc.piccbid.service.flyDrug.impl;

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
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.flyDrug.FlyDrugDao;
import com.dhcc.piccbid.dao.flyDrug.FlyDrugJdbcDao;
import com.dhcc.piccbid.dto.flyDrug.FlyDrugDto;
import com.dhcc.piccbid.entity.flyDrug.FlyDrug;
import com.dhcc.piccbid.entity.flyDrug.FlyDrugDetailVo;
import com.dhcc.piccbid.entity.flyMedicalDetail.FlyMedicalDetailVo;
import com.dhcc.piccbid.service.flyDrug.FlyDrugService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-16 15:05:04
 * @version V1.0
 */
@Service("flyDrugService")
public class FlyDrugServiceImpl extends AbstractBaseService<FlyDrug, String> implements FlyDrugService {

	private FlyDrugDao flyDrugDao;
	@Resource
	private FlyDrugJdbcDao flyDrugJdbcDao;
	@Resource
	private CommonService commonService;

	public FlyDrugServiceImpl(FlyDrugDao flyDrugDao) {
		super(flyDrugDao);
		this.flyDrugDao = flyDrugDao;
	}

	@Override
	public PageModel list(FlyDrugDto dto) {
		Specification<FlyDrug> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<FlyDrug> page = flyDrugDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<FlyDrug> getListSpecification(final FlyDrugDto dto){
        return new Specification<FlyDrug>() {
            @Override
            public Predicate toPredicate(Root<FlyDrug> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel listVo(FlyDrugDto dto) {
		/*flyDrugJdbcDao.list(dto);
		PageModel pageModel=dto.getPageModel();
		commonService.fillSqlPageModelData(pageModel, FlyDrugDetailVo.class,"rownum");
		return dto.getPageModel();*/
		 PageModel pageModel=flyDrugJdbcDao.list(dto);
	        return pageModel;
	}

	
	

}
