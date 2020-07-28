package com.dhcc.piccbid.service.hospitalLevelRmacy.impl;


import javax.annotation.Resource;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.hospitalLevelRmacy.HospitalLevelRmacyDao;
import com.dhcc.piccbid.dao.hospitalLevelRmacy.HospitalLevelRmacyJdbcDao;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.HospitalLevelRmacy;
import com.dhcc.piccbid.service.hospitalLevelRmacy.HospitalLevelRmacyService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-11-27 10:00:37
 * @version V1.0
 */
@Service("hospitalLevelRmacyService")
public class HospitalLevelRmacyServiceImpl extends AbstractBaseService<HospitalLevelRmacy, String> implements HospitalLevelRmacyService {

	private HospitalLevelRmacyDao hospitalLevelRmacyDao;
	@Resource
	private HospitalLevelRmacyJdbcDao hospitalLevelRmacyJdbcDao;

	public HospitalLevelRmacyServiceImpl(HospitalLevelRmacyDao hospitalLevelRmacyDao) {
		super(hospitalLevelRmacyDao);
		this.hospitalLevelRmacyDao = hospitalLevelRmacyDao;
	}
	
	//限医院等级用药主单
	@Override
	public PageModel limitedHospitalLevelZd(HospitalLevelRmacyDto dto) {
		hospitalLevelRmacyJdbcDao.limitedHospitalLevelZd(dto);
		return dto.getPageModel();
	}
	
	//限医院等级用药明细
	@Override
	public PageModel limitedHospitalLevelMx(HospitalLevelRmacyDto dto) {
		hospitalLevelRmacyJdbcDao.limitedHospitalLevelMx(dto);
		return dto.getPageModel();
	}
	
	//主单统计
	@Override
	public PageModel countMedical(HospitalLevelRmacyDto dto) {
		hospitalLevelRmacyJdbcDao.countMedical(dto);
		return dto.getPageModel();
	}
	
	//明细统计
	@Override
	public PageModel countMx(HospitalLevelRmacyDto dto) {
		hospitalLevelRmacyJdbcDao.countMx(dto);
		return dto.getPageModel();
	}
	
	//导出
	@Override
	public SXSSFWorkbook exportExcel(HospitalLevelRmacyDto dto) {
		return hospitalLevelRmacyJdbcDao.exportExcel(dto);
	}

	@Override
	public PageModel physicalExaminationMx(HospitalLevelRmacyDto dto) {
		hospitalLevelRmacyJdbcDao.physicalExaminationMx(dto);
		return dto.getPageModel();
	}

	

}
