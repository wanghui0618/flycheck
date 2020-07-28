package com.dhcc.piccbid.service.hospitalviolation.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.hospitalviolation.HospitalViolationDao;
import com.dhcc.piccbid.dao.hospitalviolation.HospitalViolationJdbcDao;
import com.dhcc.piccbid.dto.hospitalviolation.HospitalViolationDto;
import com.dhcc.piccbid.entity.hospitalviolation.HospitalViolation;
import com.dhcc.piccbid.entity.violationdetails.SysVerifyVo;
import com.dhcc.piccbid.service.hospitalviolation.HospitalViolationService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-05-27 17:22:38
 * @version V1.0
 */
@Service("hospitalViolationService")
public class HospitalViolationServiceImpl extends AbstractBaseService<HospitalViolation, String> implements HospitalViolationService {

	private HospitalViolationDao hospitalViolationDao;
	@Autowired
	private HospitalViolationJdbcDao hospitalViolationJdbcDao ;
	public HospitalViolationServiceImpl(HospitalViolationDao hospitalViolationDao) {
		super(hospitalViolationDao);
		this.hospitalViolationDao = hospitalViolationDao;
	}

	@Override
	public PageModel list(HospitalViolationDto dto) {
		Specification<HospitalViolation> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<HospitalViolation> page = hospitalViolationDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<HospitalViolation> getListSpecification(final HospitalViolationDto dto){
        return new Specification<HospitalViolation>() {
            @Override
            public Predicate toPredicate(Root<HospitalViolation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel listVo(HospitalViolationDto dto) {
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		hospitalViolationJdbcDao.listHospitalViolationVo(dto);
		return dto.getPageModel();
	}

	@Override
	public List<SysVerifyVo> violationSpread(HospitalViolationDto dto) {
		return hospitalViolationJdbcDao.violationSpread(dto);
	}

	@Override
	public XSSFWorkbook exportExcelToSelf(String status,String handdingInsName) {
		return hospitalViolationJdbcDao.exportExcelToSelf(status,handdingInsName);
	}
	
	@Override
	public PageModel hopVio(HospitalViolationDto dto) {
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		hospitalViolationJdbcDao.hopVio(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel medVio(HospitalViolationDto dto) {
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		hospitalViolationJdbcDao.medVio(dto);
		return dto.getPageModel();
	}

}
