package com.dhcc.piccbid.service.abnormalHospitalStay.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dao.abnormalHospitalStay.AbnormalHospitalStayDao;
import com.dhcc.piccbid.dao.abnormalHospitalStay.AbnormalHospitalStayJdbcDao;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.findDict.FindDictDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStayVo;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import com.dhcc.piccbid.service.abnormalHospitalStay.AbnormalHospitalStayService;

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
@Service("abnormalHospitalStayService")
public class AbnormalHospitalStayServiceImpl extends AbstractBaseService<AbnormalHospitalStay, String> implements AbnormalHospitalStayService {

	private AbnormalHospitalStayDao abnormalHospitalStayDao;
	@Resource
	private AbnormalHospitalStayJdbcDao abnormalHospitalStayJdbcDao;
	@Resource
	private CommonService commonService;

	public AbnormalHospitalStayServiceImpl(AbnormalHospitalStayDao abnormalHospitalStayDao) {
		super(abnormalHospitalStayDao);
		this.abnormalHospitalStayDao = abnormalHospitalStayDao;
	}

	@Override
	public PageModel list(AbnormalHospitalStayDto dto) {
		Specification<AbnormalHospitalStay> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<AbnormalHospitalStay> page = abnormalHospitalStayDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<AbnormalHospitalStay> getListSpecification(final AbnormalHospitalStayDto dto){
        return new Specification<AbnormalHospitalStay>() {
            @Override
            public Predicate toPredicate(Root<AbnormalHospitalStay> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	@Override
	public PageModel abnormalHospitalStay(AbnormalHospitalStayDto dto) {
		abnormalHospitalStayJdbcDao.abnormalHospitalStay(dto);
		
		return dto.getPageModel();
	}

	@Override
	public PageModel countabnormalHospitalStay(AbnormalHospitalStayDto dto) {
		abnormalHospitalStayJdbcDao.countabnormalHospitalStay(dto);
		
		return dto.getPageModel();
	}

	@Override
	public SXSSFWorkbook exportExcelToSelf(AbnormalHospitalStayDto dto) {
		
		return abnormalHospitalStayJdbcDao.exportExcelToSelf(dto);
	}

	@Override
	public PageModel detaileTable(AbnormalHospitalStayDto dto) {
    abnormalHospitalStayJdbcDao.detaileTable(dto);
		return dto.getPageModel();
	}


}
