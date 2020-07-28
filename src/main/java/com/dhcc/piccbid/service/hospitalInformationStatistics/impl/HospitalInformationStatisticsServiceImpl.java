package com.dhcc.piccbid.service.hospitalInformationStatistics.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.hospitalInformationStatistics.HospitalInformationStatisticsDao;
import com.dhcc.piccbid.dao.hospitalInformationStatistics.HospitalInformationStatisticsJdbcDao;
import com.dhcc.piccbid.dto.hospitalInformationStatistics.HospitalInformationStatisticsDto;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import com.dhcc.piccbid.entity.hospitalInformationStatistics.HospitalInformationStatisticsVo;
import com.dhcc.piccbid.service.hospitalInformationStatistics.HospitalInformationStatisticsService;

@Service("hospitalInformationStatisticsService")
public class HospitalInformationStatisticsServiceImpl extends AbstractBaseService<FlycheckMedical,String> implements HospitalInformationStatisticsService{

	@Resource
	private CommonService commonService;
	@Resource
	private HospitalInformationStatisticsJdbcDao hospitalInformationStatisticsJdbcDao;
	@Resource
	private HospitalInformationStatisticsDao hospitalInformationStatisticsDao;
	
	public HospitalInformationStatisticsServiceImpl(HospitalInformationStatisticsDao hospitalInformationStatisticsDao) {
		super(hospitalInformationStatisticsDao);
		this.hospitalInformationStatisticsDao = hospitalInformationStatisticsDao;
	}

	@Override
	public PageModel list(HospitalInformationStatisticsDto dto) {
		Specification<FlycheckMedical> spec = getListSpecification(dto);
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        Page<FlycheckMedical> page = hospitalInformationStatisticsDao.findAll(spec, dto.getPageModel().getPageable());
        dto.getPageModel().setPage(page);
        return dto.getPageModel();
	}
	
	protected Specification<FlycheckMedical> getListSpecification(final HospitalInformationStatisticsDto dto){
        return new Specification<FlycheckMedical>() {
            @Override
            public Predicate toPredicate(Root<FlycheckMedical> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	//住院数据统计
	@Override
	public PageModel dataStatistics(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.dataStatistics(dto);
		return dto.getPageModel();
	}

	//门诊数据统计
	@Override
	public PageModel outpatientDataStatistics(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.outpatientDataStatistics(dto);
		return dto.getPageModel();
	}

	//住院各项目收费占比
	@Override
	public PageModel proportionChargingItems(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.proportionChargingItems(dto);
		return dto.getPageModel();
	}
	
	//门诊各项目收费占比
	@Override
	public PageModel proportionChargingItemsMed(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.proportionChargingItemsMed(dto);
		return dto.getPageModel();
	}
	
	//月度平均住院天数
	@Override
	public PageModel averageLengthOfStay(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.averageLengthOfStay(dto);
		return dto.getPageModel();
	}

	//住院药品/诊疗/耗材占比
	@Override
	public PageModel drugsDiagnosisTreatment(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.drugsDiagnosisTreatment(dto);
		return dto.getPageModel();
	}

	//门诊药品/诊疗/耗材占比
	@Override
	public PageModel drugsDiagnosisTreatmentMed(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.drugsDiagnosisTreatmentMed(dto);
		return dto.getPageModel();
	}

	//住院科室费用top10
	@Override
	public PageModel departmentRanking(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.departmentRanking(dto);
		return dto.getPageModel();
	}

	//门诊科室费用top10
	@Override
	public PageModel departmentRankingMed(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.departmentRankingMed(dto);
		return dto.getPageModel();
	}

	//住院诊疗费用top10
	@Override
	public PageModel treatmentRanking(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.treatmentRanking(dto);
		return dto.getPageModel();
	}

	//门诊诊疗费用top10
	@Override
	public PageModel treatmentRankingMed(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		hospitalInformationStatisticsJdbcDao.treatmentRankingMed(dto);
		return dto.getPageModel();
	}

	@Override
	public List<HospitalInformationStatisticsVo> exportBeHospitalized(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		return hospitalInformationStatisticsJdbcDao.exportBeHospitalized(dto);
	}

	@Override
	public List<HospitalInformationStatisticsVo> exportOutpatient(HospitalInformationStatisticsDto dto) {
		getDate(dto);
		return hospitalInformationStatisticsJdbcDao.exportOutpatient(dto);
	}

	public void getDate(HospitalInformationStatisticsDto dto){
		String balanceDate= dto.getBalanceDate();
		if(!StringUtils.isNullOrEmpty(balanceDate) && balanceDate != null && balanceDate != ""){
			String[] BalanceDateArray = balanceDate.split("forbid");
			for (int i=0;i<BalanceDateArray.length-1;i++){
				dto.setBalanceDatea(BalanceDateArray[i]);
				dto.setBalanceDateb(BalanceDateArray[i+1]);
			}
			dto.setBalanceDatea(dto.getBalanceDatea().trim());
			dto.setBalanceDateb(dto.getBalanceDateb().trim());
		}

	}

}
