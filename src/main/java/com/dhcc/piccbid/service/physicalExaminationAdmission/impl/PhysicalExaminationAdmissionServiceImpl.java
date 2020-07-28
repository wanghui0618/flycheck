package com.dhcc.piccbid.service.physicalExaminationAdmission.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.physicalExaminationAdmission.PhysicalExaminationAdmissionDao;
import com.dhcc.piccbid.dao.physicalExaminationAdmission.PhysicalExaminationAdmissionJdbcDao;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
import com.dhcc.piccbid.dto.physicalExaminationAdmission.PhysicalExaminationAdmissionDto;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import com.dhcc.piccbid.entity.physicalExaminationAdmission.PhysicalExaminationAdmission;
import com.dhcc.piccbid.service.physicalExaminationAdmission.PhysicalExaminationAdmissionService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-23 10:17:39
 * @version V1.0
 */
@Service("physicalExaminationAdmissionService")
public class PhysicalExaminationAdmissionServiceImpl extends AbstractBaseService<PhysicalExaminationAdmission, String>
		implements PhysicalExaminationAdmissionService {
	@Resource
	private PhysicalExaminationAdmissionDao physicalExaminationAdmissionDao;
	@Resource
	private PhysicalExaminationAdmissionJdbcDao physicalExaminationAdmissionJdbcDao;
	@Resource
	private CommonService commonService;

	public PhysicalExaminationAdmissionServiceImpl(PhysicalExaminationAdmissionDao physicalExaminationAdmissionDao) {
		super(physicalExaminationAdmissionDao);
		this.physicalExaminationAdmissionDao = physicalExaminationAdmissionDao;
	}

	@Override
	public PageModel list(PhysicalExaminationAdmissionDto dto) {
		Specification<PhysicalExaminationAdmission> spec = getListSpecification(dto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		Page<PhysicalExaminationAdmission> page = physicalExaminationAdmissionDao.findAll(spec,
				dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}

	protected Specification<PhysicalExaminationAdmission> getListSpecification(
			final PhysicalExaminationAdmissionDto dto) {
		return new Specification<PhysicalExaminationAdmission>() {
			@Override
			public Predicate toPredicate(Root<PhysicalExaminationAdmission> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				// 构造查询条件

				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

	/**
	 * 体检式入院
	 */
	@Override
	public PageModel physicalExamination(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionJdbcDao.physicalExamination(dto);
		return dto.getPageModel();
	}

	@Override
	public PageModel sumTotalCount(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionJdbcDao.sumTotalCount(dto);
		return dto.getPageModel();
	}

	@Override
	public XSSFWorkbook exportExcelToSelf(PhysicalExaminationAdmissionDto dto) {
		return physicalExaminationAdmissionJdbcDao.exportExcelToSelf(dto);
	}

	@Override
	public PageModel countphysicalExaminationMx(PhysicalExaminationAdmissionDto dto) {
		physicalExaminationAdmissionJdbcDao.countphysicalExaminationMx(dto);
		return dto.getPageModel();
	}
}
