package com.dhcc.piccbid.service.unreasonableAdmission.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.piccbid.dao.unreasonableAdmission.UnreasonableAdmissionJdbcDao;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import com.dhcc.piccbid.entity.unreasonableAdmission.CaseInfo;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.unreasonableAdmission.UnreasonableAdmissionDao;
import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import com.dhcc.piccbid.entity.unreasonableAdmission.UnreasonableAdmission;
import com.dhcc.piccbid.service.unreasonableAdmission.UnreasonableAdmissionService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zhouwei
 * @date 2019-11-23 15:20:20
 * @version V1.0
 */
@Service("unreasonableAdmissionService")
public class UnreasonableAdmissionServiceImpl extends AbstractBaseService<UnreasonableAdmission, String> implements UnreasonableAdmissionService {
	@Resource
	private UnreasonableAdmissionDao unreasonableAdmissionDao;
	@Resource
	private CommonService commonService;
	@Resource
	private UnreasonableAdmissionJdbcDao unreasonableAdmissionJdbcDao;

	public UnreasonableAdmissionServiceImpl(UnreasonableAdmissionDao unreasonableAdmissionDao) {
		super(unreasonableAdmissionDao);
		this.unreasonableAdmissionDao = unreasonableAdmissionDao;
	}
	@Override
	public PageModel listUnreasonableAdmission(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionJdbcDao.listUnreasonableAdmission(dto);
		commonService.fillSqlPageModelData(dto.getPageModel(), UnreasonableAdmission.class,"tfm.hisid");
		return dto.getPageModel();
	}
	@Override
	public PageModel countUnreasonableAdmissions(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionJdbcDao.countUnreasonableAdmissions(dto);
		commonService.fillSqlPageModelData(dto.getPageModel(), UnreasonableAdmission.class,"1");
		return dto.getPageModel();
	}

	@Override
	public SXSSFWorkbook getData(UnreasonableAdmissionDto dto) {
		return unreasonableAdmissionJdbcDao.getData(dto);
	}

	@Override
	public PageModel caseInfoList(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionJdbcDao.caseInfoList(dto);
		commonService.fillSqlPageModelData(dto.getPageModel(), CaseInfo.class,"1");
		return dto.getPageModel();
	}

	@Override
	public PageModel findDict(UnreasonableAdmissionDto dto) {
		unreasonableAdmissionJdbcDao.findDict(dto);
		commonService.fillSqlPageModelData(dto.getPageModel(), FindDictVo.class,"*");
		return dto.getPageModel();
	}

	@Override
	public PageModel list(UnreasonableAdmissionDto dto) {
		Specification<UnreasonableAdmission> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<UnreasonableAdmission> page = unreasonableAdmissionDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}


	protected Specification<UnreasonableAdmission> getListSpecification(final UnreasonableAdmissionDto dto){
        return new Specification<UnreasonableAdmission>() {
            @Override
            public Predicate toPredicate(Root<UnreasonableAdmission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

}
