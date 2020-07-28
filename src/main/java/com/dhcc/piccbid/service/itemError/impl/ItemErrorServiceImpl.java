package com.dhcc.piccbid.service.itemError.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.itemError.ItemErrorJdbcDao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.itemError.ItemErrorDao;
import com.dhcc.piccbid.dto.itemError.ItemErrorDto;
import com.dhcc.piccbid.entity.itemError.ItemError;
import com.dhcc.piccbid.service.itemError.ItemErrorService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-28 09:13:42
 * @version V1.0
 */
@Service("itemErrorService")
public class ItemErrorServiceImpl extends AbstractBaseService<ItemError, String> implements ItemErrorService {

	private ItemErrorDao itemErrorDao;
	@Resource
	private ItemErrorJdbcDao itemErrorJdbcDao;

	public ItemErrorServiceImpl(ItemErrorDao itemErrorDao) {
		super(itemErrorDao);
		this.itemErrorDao = itemErrorDao;
	}

	@Override
	public PageModel list(ItemErrorDto dto) {
		Specification<ItemError> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<ItemError> page = itemErrorDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	@Override
	public PageModel listHop(ItemErrorDto dto) {
		itemErrorJdbcDao.listHop(dto);
		return dto.getPageModel();
	}
	@Override
	public PageModel listItem(ItemErrorDto dto) {
		itemErrorJdbcDao.listItem(dto);
		return dto.getPageModel();
	}
	
	protected Specification<ItemError> getListSpecification(final ItemErrorDto dto){
        return new Specification<ItemError>() {
            @Override
            public Predicate toPredicate(Root<ItemError> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
