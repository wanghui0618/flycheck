package com.dhcc.piccbid.service.childrensDrugs.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.piccbid.dao.childrensDrugs.ChildrensDrugsDaoJdbcDao;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.childrensDrugs.ChildrensDrugsDao;
import com.dhcc.piccbid.dto.childrensDrugs.ChildrensDrugsDto;
import com.dhcc.piccbid.entity.childrensDrugs.ChildrensDrugs;
import com.dhcc.piccbid.service.childrensDrugs.ChildrensDrugsService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2019-11-23 13:32:56
 * @version V1.0
 */
@Service("childrensDrugsService")
public class ChildrensDrugsServiceImpl extends AbstractBaseService<ChildrensDrugs, String> implements ChildrensDrugsService {

	@Resource
	private ChildrensDrugsDao childrensDrugsDao;

	@Resource
	private ChildrensDrugsDaoJdbcDao childrensDrugsDaoJdbcDao;

	@Resource
	private CommonService commonService;

	public ChildrensDrugsServiceImpl(ChildrensDrugsDao childrensDrugsDao) {
		super(childrensDrugsDao);
		this.childrensDrugsDao = childrensDrugsDao;
	}

	@Override
	public PageModel list(ChildrensDrugsDto dto) {
		Specification<ChildrensDrugs> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<ChildrensDrugs> page = childrensDrugsDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	@Override
	public PageModel childrensDrugs(ChildrensDrugsDto dto) {
		childrensDrugsDaoJdbcDao.childrensDrugs(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel gather(ChildrensDrugsDto dto) {
		childrensDrugsDaoJdbcDao.gather(dto);
		return dto.getPageModel();
	}

	@Override
	public XSSFWorkbook exportExcel(ChildrensDrugsDto dto) {
		return childrensDrugsDaoJdbcDao.exportExcel(dto);
	}



	protected Specification<ChildrensDrugs> getListSpecification(final ChildrensDrugsDto dto){
        return new Specification<ChildrensDrugs>() {
            @Override
            public Predicate toPredicate(Root<ChildrensDrugs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel detail(ChildrensDrugsDto dto) {
		childrensDrugsDaoJdbcDao.detail(dto);
		return dto.getPageModel();
	}

}
