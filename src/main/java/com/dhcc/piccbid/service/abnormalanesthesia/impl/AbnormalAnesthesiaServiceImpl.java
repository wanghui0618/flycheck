package com.dhcc.piccbid.service.abnormalanesthesia.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.abnormalanesthesia.AbnormalAnesthesiaDao;
import com.dhcc.piccbid.dto.abnormalanesthesia.AbnormalAnesthesiaDto;
import com.dhcc.piccbid.entity.abnormalanesthesia.AbnormalAnesthesia;
import com.dhcc.piccbid.service.abnormalanesthesia.AbnormalAnesthesiaService;

@Service("abnormalAnesthesiaService")
public class AbnormalAnesthesiaServiceImpl implements AbnormalAnesthesiaService{

	@Resource
	private  AbnormalAnesthesiaDao  abnormalAnesthesiaDao;
	
	
	@Override
	public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalAnesthesiaDto dto) {
		// TODO Auto-generated method stub
		return abnormalAnesthesiaDao.getTotalNumberOfCasesAndTotalAmount(dto);
	}

	@Override
	public PageModel getAbnormalAnesthesia(AbnormalAnesthesiaDto dto) {
		// TODO Auto-generated method stub
		return abnormalAnesthesiaDao.getAbnormalAnesthes(dto);
	}

	@Override
	public PageModel getAbnormalAnesthesiabyhisidTable(AbnormalAnesthesiaDto dto) {
		// TODO Auto-generated method stub
		return abnormalAnesthesiaDao.getAbnormalAnesthesbyhisidTable(dto);
	}

	@Override
	public List<AbnormalAnesthesia> exportD(AbnormalAnesthesiaDto dto) {
		// TODO Auto-generated method stub
		return abnormalAnesthesiaDao.exportD(dto);
	}

}
