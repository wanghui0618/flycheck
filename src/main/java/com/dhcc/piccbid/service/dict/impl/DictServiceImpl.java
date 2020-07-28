package com.dhcc.piccbid.service.dict.impl;

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
import com.dhcc.piccbid.dao.dict.DictDao;
import com.dhcc.piccbid.dao.dict.DictJdbcDao;
import com.dhcc.piccbid.dto.dict.DictDto;
import com.dhcc.piccbid.entity.dict.Dict;
import com.dhcc.piccbid.entity.dict.DictRequestVo;
import com.dhcc.piccbid.entity.dict.DictResponseVo;
import com.dhcc.piccbid.service.dict.DictService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-15 09:58:13
 * @version V1.0
 */
@Service("dictService")
public class DictServiceImpl extends AbstractBaseService<Dict, String> implements DictService {

	private DictDao dictDao;
	@Resource
	private DictJdbcDao dictJdbcDao;

	public DictServiceImpl(DictDao dictDao) {
		super(dictDao);
		this.dictDao = dictDao;
	}

	@Override
	public List<DictResponseVo> findDict(DictRequestVo dictRequestVo) {

		return dictJdbcDao.findDict(dictRequestVo);
	}
	
	@Override
	public PageModel list(DictDto dto) {
		Specification<Dict> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<Dict> page = dictDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<Dict> getListSpecification(final DictDto dto){
        return new Specification<Dict>() {
            @Override
            public Predicate toPredicate(Root<Dict> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
