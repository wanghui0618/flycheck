package com.dhcc.piccbid.service.sametimeHospital.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.sametimeHospital.SametimeHospitalDao;
import com.dhcc.piccbid.dto.sametimeHospital.SametimeHospitalDto;
import com.dhcc.piccbid.entity.sametimeHospital.SametimeHospital;
import com.dhcc.piccbid.service.sametimeHospital.SametimeHospitalService;

/**
 * <p>标题: SametimeHospitalServiceImpl.java</p>
 * <p>业务描述:同时间出入院筛选Service接口实现</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 贺和平
 * @date 2020年1月8日
 * @version V1.0
 */

@Service("sametimeHospitalService")
public class SametimeHospitalServiceImpl implements  SametimeHospitalService{

	
	@Resource
	SametimeHospitalDao sametimeHospitalDao;
	
	
	@Override
	public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(SametimeHospitalDto dto) {
		// TODO Auto-generated method stub
		return sametimeHospitalDao.getTotalNumberOfCasesAndTotalAmount(dto);
	}

	@Override
	public PageModel getSametimeHospital(SametimeHospitalDto dto) {
		// TODO Auto-generated method stub
		return sametimeHospitalDao.getSametimeHospital(dto);
	}

	@Override
	public PageModel getSametimeHospitalbyhisidTable(SametimeHospitalDto dto) {
		// TODO Auto-generated method stub
		return sametimeHospitalDao.getSametimeHospitalbyhisidTable(dto);
	}

	@Override
	public List<SametimeHospital> exportD(SametimeHospitalDto dto) {
		// TODO Auto-generated method stub
		return sametimeHospitalDao.exportD(dto);
	}

}
