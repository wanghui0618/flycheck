package com.dhcc.piccbid.service.flydetail.impl;

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
import com.dhcc.piccbid.dao.flydetail.FlydetailDao;
import com.dhcc.piccbid.dao.flydetail.FlydetailJdbcDao;
import com.dhcc.piccbid.dto.flydetail.FlydetailDto;
import com.dhcc.piccbid.entity.flydetail.Flydetail;
import com.dhcc.piccbid.service.flydetail.FlydetailService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 14:19:36
 * @version V1.0
 */
@Service("flydetailService")
public class FlydetailServiceImpl extends AbstractBaseService<Flydetail, String> implements FlydetailService {

	private FlydetailDao flydetailDao;
	
	@Resource
	private FlydetailJdbcDao flydetailJdbcDao;
	
	@Resource
	private CommonService commonService;

	public FlydetailServiceImpl(FlydetailDao flydetailDao) {
		super(flydetailDao);
		this.flydetailDao = flydetailDao;
	}

	@Override
	public PageModel list(FlydetailDto dto) {
		Specification<Flydetail> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<Flydetail> page = flydetailDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel listVo(FlydetailDto dto) {
		flydetailJdbcDao.list(dto);
		PageModel pageModel=dto.getPageModel();
		commonService.fillSqlPageModelData(pageModel,Flydetail.class,"ITEM_CODE_HOS");
		return dto.getPageModel();
	}
	
	protected Specification<Flydetail> getListSpecification(final FlydetailDto dto){
        return new Specification<Flydetail>() {
            @Override
            public Predicate toPredicate(Root<Flydetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
