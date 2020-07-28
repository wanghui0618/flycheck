package com.dhcc.piccbid.service.allAnalysis.impl;

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
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dao.allAnalysis.AllAnalysisDao;
import com.dhcc.piccbid.dao.allAnalysis.AllAnalysisJDBCDao;
import com.dhcc.piccbid.dto.allAnalysis.AllAnalysisDto;
import com.dhcc.piccbid.dto.menu.MenuDto;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysis;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysis2;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysisVo;
import com.dhcc.piccbid.entity.menu.Menu;
import com.dhcc.piccbid.service.allAnalysis.AllAnalysisService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangjieli
 * @date 2019-08-25 16:54:00
 * @version V1.0
 */
@Service("allAnalysisService")
public class AllAnalysisServiceImpl extends AbstractBaseService<AllAnalysis, String> implements AllAnalysisService {

	private AllAnalysisDao allAnalysisDao;
	@Resource
	private AllAnalysisJDBCDao allAnalysisJDBCDao;
	private CommonService commonService;
	
	JdbcTemplateWrapper jdbcTemplateWrapper;

	public AllAnalysisServiceImpl(AllAnalysisDao allAnalysisDao) {
		super(allAnalysisDao);
		this.allAnalysisDao = allAnalysisDao;
	}

	@Override
	public PageModel list(AllAnalysisDto dto) {
		Specification<AllAnalysis> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<AllAnalysis> page = allAnalysisDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
    @Override
    public PageModel search1(AllAnalysisDto dto) {
        allAnalysisJDBCDao.search1(dto);
       /* PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel,AllAnalysis.class,"ID");*/
        return dto.getPageModel();
    }
    
    @Override
    public PageModel search2(String gid,AllAnalysisDto dto) {
        allAnalysisJDBCDao.search2(gid,dto);
       /* PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel,AllAnalysis2.class,"ID");*/
        return dto.getPageModel();
    }
    
	protected Specification<AllAnalysis> getListSpecification(final AllAnalysisDto dto){
        return new Specification<AllAnalysis>() {
            @Override
            public Predicate toPredicate(Root<AllAnalysis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
