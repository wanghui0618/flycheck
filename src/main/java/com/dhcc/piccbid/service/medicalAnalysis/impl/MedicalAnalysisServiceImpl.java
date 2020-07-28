package com.dhcc.piccbid.service.medicalAnalysis.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dto.medicalAnalysis.MedicalResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.medicalAnalysis.MedicalAnalysisDao;
import com.dhcc.piccbid.dao.medicalAnalysis.MedicalAnalysisJdbcDao;
//import com.dhcc.piccbid.dto.medical.MedicalDto;
import com.dhcc.piccbid.dto.medicalAnalysis.MedicalAnalysisDto;
import com.dhcc.piccbid.entity.home.HomeVo;
//import com.dhcc.piccbid.entity.medical.MedicalNumVo;
import com.dhcc.piccbid.entity.medicalAnalysis.HospitalVo;
import com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis;
import com.dhcc.piccbid.entity.medicalAnalysis.MoneyVo;
import com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangyue
 * @date 2019-08-08 17:39:46
 * @version V1.0
 */
@Service("medicalAnalysisService")
public class MedicalAnalysisServiceImpl extends AbstractBaseService<MedicalAnalysis, String> implements MedicalAnalysisService {

	@Autowired
	private MedicalAnalysisDao medicalAnalysisDao;
	@Autowired
	private MedicalAnalysisJdbcDao medicalAnalysisJdbcDao;
	@Resource
	private CommonService commonService;
	public MedicalAnalysisServiceImpl(MedicalAnalysisDao medicalAnalysisDao) {
		super(medicalAnalysisDao);
		this.medicalAnalysisDao = medicalAnalysisDao;
	}

	@Override
	public PageModel list(MedicalAnalysisDto dto) {
		Specification<MedicalAnalysis> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<MedicalAnalysis> page = medicalAnalysisDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<MedicalAnalysis> getListSpecification(final MedicalAnalysisDto dto){
        return new Specification<MedicalAnalysis>() {
            @Override
            public Predicate toPredicate(Root<MedicalAnalysis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}
	
	@Override
	public PageModel yearData(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.yearData(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}
	
	
	public PageModel singleDisease(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.singleDisease(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}
	
	public PageModel abnormalCost(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.abnormalCost(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}
	public PageModel abnormalCost2(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.abnormalCost2(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}

	public PageModel medicaStatisticsForHomePage(MedicalResultVO dto) {
		medicalAnalysisJdbcDao.medicaStatisticsForHomePage(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}
	@Override
	public PageModel totalIllData(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.totalIllData(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}
	
	@Override
	public PageModel drugCostData(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.drugCostData(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}
	
	@Override
	public PageModel serviceCostData(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.serviceCostData(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}
	
	@Override
	public PageModel materialCostData(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.materialCostData(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}


	@Override
	public PageModel monthlyTrendsData(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.monthlyTrendsData(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),MedicalNumVo.class,"medicalname");
		return dto.getPageModel();
	}
	
	@Override
	/*public List<HospitalVo> findHosNumber() {
		// TODO Auto-generated method stub
		return medicalAnalysisJdbcDao.findHosNumber();
	}*/
	
	
	public PageModel findHosNumber(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.findHosNumber(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),HospitalVo.class,null);
		return dto.getPageModel();
	}
	
	/*@Override
	public List<HomeVo> findCityNumber() {
		// TODO Auto-generated method stub
		return cityJdbcDao.findCityNumber();
	}*/

	/* (非 Javadoc)   
	* <p>Title: findHosNumber</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#findHosNumber(com.dhcc.piccbid.dto.medicalAnalysis.MedicalAnalysisDto)   
	*/
	
	@Override
	public PageModel findMonNumber(MedicalAnalysisDto dto) {
		medicalAnalysisJdbcDao.findMonNumber(dto);
/*		commonService.fillSqlPageModelData(dto.getPageModel(),MoneyVo.class,null);*/
		return dto.getPageModel();
	}

	/* (非 Javadoc)   
	* <p>Title: findHosNumber</p>   
	* <p>Description: </p>   
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#findHosNumber()   
	*/
	@Override
	public List<HospitalVo> findHosNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: yearData</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#yearData(com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis)   
	*/
	@Override
	public PageModel yearData(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: totalIllData</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#totalIllData(com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis)   
	*/
	@Override
	public PageModel totalIllData(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: drugCostData</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#drugCostData(com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis)   
	*/
	@Override
	public PageModel drugCostData(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: serviceCostData</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#serviceCostData(com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis)   
	*/
	@Override
	public PageModel serviceCostData(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: materialCostData</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#materialCostData(com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis)   
	*/
	@Override
	public PageModel materialCostData(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: monthlyTrendsData</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#monthlyTrendsData(com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis)   
	*/
	@Override
	public PageModel monthlyTrendsData(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: findMonNumber</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#findMonNumber(com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis)   
	*/
	@Override
	public PageModel findMonNumber(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: findHosNumber</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#findHosNumber(com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis)   
	*/
	@Override
	public PageModel findHosNumber(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: singleDisease</p>   
	* <p>Description: </p>   
	* @param dto
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#singleDisease(com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis)   
	*/
	@Override
	public PageModel singleDisease(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel abnormalCost(MedicalAnalysis dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)   
	* <p>Title: findHosNumber</p>   
	* <p>Description: </p>   
	* @return   
	* @see com.dhcc.piccbid.service.medicalAnalysis.MedicalAnalysisService#findHosNumber()   
	*/
	/*public List<HospitalVo> findHosNumber() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
