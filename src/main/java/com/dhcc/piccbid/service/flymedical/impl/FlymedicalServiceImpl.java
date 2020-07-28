package com.dhcc.piccbid.service.flymedical.impl;

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
import com.dhcc.piccbid.dao.flymedical.FlymedicalDao;
import com.dhcc.piccbid.dao.flymedical.FlymedicalJdbcDao;
import com.dhcc.piccbid.dto.flymedical.FlymedicalDto;
import com.dhcc.piccbid.entity.flyMedicalrecord.FlyMedicalrecord;
import com.dhcc.piccbid.service.flymedical.FlymedicalService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 09:57:52
 * @version V1.0
 */
@Service("flymedicalService")
public class FlymedicalServiceImpl extends AbstractBaseService<FlyMedicalrecord, String> implements FlymedicalService {

	private FlymedicalDao flymedicalDao;
	
	@Resource
	private FlymedicalJdbcDao flymedicalJdbcDao;
	
	@Resource
	private CommonService commonService;

	public FlymedicalServiceImpl(FlymedicalDao flymedicalDao) {
		super(flymedicalDao);
		this.flymedicalDao = flymedicalDao;
	}

	@Override
	public PageModel list(FlymedicalDto dto) {
		Specification<FlyMedicalrecord> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<FlyMedicalrecord> page = flymedicalDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	@Override
	public PageModel screenSameEntryAndExitDate(FlymedicalDto dto) {
		flymedicalJdbcDao.screenSameEntryAndExitDate(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel relateDiagnosis(FlymedicalDto dto) {
		flymedicalJdbcDao.relateDiagnosis(dto);
		return dto.getPageModel();
		
	}
	
	@Override
	public PageModel listVo(FlymedicalDto dto) {
		flymedicalJdbcDao.list(dto);
		PageModel pageModel=dto.getPageModel();
		commonService.fillSqlPageModelData(pageModel,FlyMedicalrecord.class,"ADMISSION_NO");
		return dto.getPageModel();
	}
	
	protected Specification<FlyMedicalrecord> getListSpecification(final FlymedicalDto dto){
        return new Specification<FlyMedicalrecord>() {
            @Override
            public Predicate toPredicate(Root<FlyMedicalrecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
