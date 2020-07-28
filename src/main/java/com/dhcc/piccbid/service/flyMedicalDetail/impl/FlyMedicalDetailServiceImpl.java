package com.dhcc.piccbid.service.flyMedicalDetail.impl;

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
import com.dhcc.piccbid.dao.flyMedicalDetail.FlyMedicalDetailDao;
import com.dhcc.piccbid.dao.flyMedicalDetail.FlyMedicalDetailJdbcDao;
import com.dhcc.piccbid.dto.flyMedicalDetail.FlyMedicalDetailDto;
import com.dhcc.piccbid.entity.flyMedicalDetail.FlyMedicalDetail;
import com.dhcc.piccbid.entity.flyMedicalDetail.FlyMedicalDetailVo;
import com.dhcc.piccbid.entity.flyMedicalrecord.FlyMedicalrecord;
import com.dhcc.piccbid.service.flyMedicalDetail.FlyMedicalDetailService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-10-15 14:48:33
 * @version V1.0
 */
@Service("flyMedicalDetailService")
public class FlyMedicalDetailServiceImpl extends AbstractBaseService<FlyMedicalDetail, String> implements FlyMedicalDetailService {

	private FlyMedicalDetailDao flyMedicalDetailDao;
	
	@Resource
	private FlyMedicalDetailJdbcDao flyMedicalDetailJdbcDao;
	
	@Resource
	private CommonService commonService;

	public FlyMedicalDetailServiceImpl(FlyMedicalDetailDao flyMedicalDetailDao) {
		super(flyMedicalDetailDao);
		this.flyMedicalDetailDao = flyMedicalDetailDao;
	}

	@Override
	public PageModel list(FlyMedicalDetailDto dto) {
		Specification<FlyMedicalDetail> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<FlyMedicalDetail> page = flyMedicalDetailDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	/***
	 * 检查费收费金额次数统计
	 * @param dto
	 */
	@Override
	public PageModel listVo(FlyMedicalDetailDto dto) {
		flyMedicalDetailJdbcDao.list(dto);
		PageModel pageModel=dto.getPageModel();
		commonService.fillSqlPageModelData(pageModel,FlyMedicalDetailVo.class,"item_name_hosp");
		return dto.getPageModel();
	}
	
	protected Specification<FlyMedicalDetail> getListSpecification(final FlyMedicalDetailDto dto){
        return new Specification<FlyMedicalDetail>() {
            @Override
            public Predicate toPredicate(Root<FlyMedicalDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
