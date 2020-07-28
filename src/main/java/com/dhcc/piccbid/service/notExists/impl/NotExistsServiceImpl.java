package com.dhcc.piccbid.service.notExists.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.notExists.NotExistsDao;
import com.dhcc.piccbid.dao.notExists.NotExistsJdbcDao;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.PersonalInformationInquiryDto;
import com.dhcc.piccbid.dto.notExists.NotExistsDto;
import com.dhcc.piccbid.dto.physicalExaminationAdmission.PhysicalExaminationAdmissionDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;
import com.dhcc.piccbid.entity.notExists.NotExists;
import com.dhcc.piccbid.entity.notExists.NotExistsDetail;
import com.dhcc.piccbid.service.notExists.NotExistsService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-11-23 13:31:41
 * @version V1.0
 */
@Service("notExistsService")
public class NotExistsServiceImpl extends AbstractBaseService<NotExistsDetail, String> implements NotExistsService {

	private NotExistsDao notExistsDao;
	@Resource
	private NotExistsJdbcDao notExistsJdbcDao;
	@Resource
	private CommonService commonService;

	public NotExistsServiceImpl(NotExistsDao notExistsDaoDao) {
		super(notExistsDaoDao);
		this.notExistsDao = notExistsDaoDao;
	}

	/*@Override
	public PageModel list(NotExistsDto dto) {
		Specification<NotExists> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		//Page<NotExistsDetail> page = notExistsDao.findAll(spec, dto.getPageModel().getPageable());
		//dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<NotExists> getListSpecification(final NotExistsDto dto){
        return new Specification<NotExists>() {
            @Override
            public Predicate toPredicate(Root<NotExists> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	

	@Override
	public PageModel countabnormalHospitalStay(NotExistsDto dto) {
		notExistsJdbcDao.countabnormalHospitalStay(dto);
		
		return dto.getPageModel();
	}
	
	
    @Override
    public PageModel searchDetail(NotExistsDto dto) {
    	notExistsJdbcDao.searchDetail(dto);
        return dto.getPageModel();
    }
*/
    
	@Override
	public PageModel notExists(NotExistsDto dto) {
		notExistsJdbcDao.notExsits(dto);
		return dto.getPageModel();
	}
	
	
	@Override
	public PageModel notExists1(NotExistsDto dto) {
		notExistsJdbcDao.notExsits1(dto);
		return dto.getPageModel();
	}
	
	
	@Override
	public PageModel countabnormalHospitalStay1(NotExistsDto dto) {
		notExistsJdbcDao.countabnormalHospitalStay1(dto);
		
		return dto.getPageModel();
	}
	
    @Override
    public PageModel searchMutex(NotExistsDto dto) {
    	notExistsJdbcDao.searchMutex(dto);
        return dto.getPageModel();
    }
    @Override
    public PageModel searchMutex1(NotExistsDto dto) {
    	notExistsJdbcDao.searchMutex1(dto);
        return dto.getPageModel();
    }
    
    @Override
	public SXSSFWorkbook exportExcelToSelf(HttpServletRequest request,NotExistsDto dto) {
		return notExistsJdbcDao.exportExcelToSelf(request,dto);
	}
    
    @Override
    public XSSFWorkbook exportExcel() {
        return notExistsJdbcDao.exportExcel();
    }
}
