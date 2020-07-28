package com.dhcc.piccbid.service.abnormalfrequencytreatment.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.abnormalfrequencytreatment.AbnormalFrequencyTreatmentZYDao;
import com.dhcc.piccbid.dto.abnormalFrequencyTreatment.AbnormalFrequencyTreatmentZYDto;
import com.dhcc.piccbid.entity.abnormalfrequencytreatment.AbnormalFrequencyTreatmentZY;
import com.dhcc.piccbid.service.abnormalfrequencytreatment.AbnormalFrequencyTreatmentZYService;


/**
 * <p>标题: AbnormalFrequencyTreatmentZYServiceImpl.java</p>
 * <p>业务描述:住院就诊次数异常Service接口</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 贺和平
 * @date 2020年1月6日
 * @version V1.0
 */

@Service("abnormalFrequencyTreatmentZYService")
public class AbnormalFrequencyTreatmentZYServiceImpl implements AbnormalFrequencyTreatmentZYService{

	@Resource
	 private AbnormalFrequencyTreatmentZYDao abnormalFrequencyTreatmentZYDao;
	
	@Override
	public PageModel getFrequencyTreatment(AbnormalFrequencyTreatmentZYDto dto) {
		// TODO Auto-generated method stub
		return abnormalFrequencyTreatmentZYDao.getFrequencyTreatment(dto);
	}

	@Override
	public PageModel getFrequencyTreatmentDetails(AbnormalFrequencyTreatmentZYDto dto) {
		// TODO Auto-generated method stub
		return abnormalFrequencyTreatmentZYDao.getFrequencyTreatmentDetails(dto);
	}

	@Override
	public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalFrequencyTreatmentZYDto dto) {
		// TODO Auto-generated method stub
		return abnormalFrequencyTreatmentZYDao.getTotalNumberOfCasesAndTotalAmount(dto);
	}

	@Override
	public PageModel getFrequencyTreatmentmxbyhisidTable(AbnormalFrequencyTreatmentZYDto dto) {
		// TODO Auto-generated method stub
		return abnormalFrequencyTreatmentZYDao.getFrequencyTreatmentmxbyhisidTable(dto);
	}

	@Override
	public List<AbnormalFrequencyTreatmentZY> exportD(AbnormalFrequencyTreatmentZYDto dto) {
		// TODO Auto-generated method stub
		return abnormalFrequencyTreatmentZYDao.exportD(dto);
	}

	@Override
	public List<AbnormalFrequencyTreatmentZY> exportA(AbnormalFrequencyTreatmentZYDto dto) {
		// TODO Auto-generated method stub
		return abnormalFrequencyTreatmentZYDao.exportA(dto);
	}

	@Override
	public List<Map<String, Object>> getBenefitType() {
		// TODO Auto-generated method stub
		return abnormalFrequencyTreatmentZYDao.getBenefitType();
	}
	

}
