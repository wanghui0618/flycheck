package com.dhcc.piccbid.service.abnormalfrequencytreatment.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.abnormalfrequencytreatment.AbnormalFrequencyTreatmentDao;
import com.dhcc.piccbid.dto.abnormalFrequencyTreatment.AbnormalFrequencyTreatmentDto;
import com.dhcc.piccbid.entity.abnormalfrequencytreatment.AbnormalFrequencyTreatment;
import com.dhcc.piccbid.service.abnormalfrequencytreatment.AbnormalFrequencyTreatmentService;


/**
 * <p>标题: AbnormalFrequencyTreatmentServiceImpl.java</p>
 * <p>业务描述:就诊次数异常Service接口</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 贺和平
 * @date 2019年11月23日
 * @version V1.0
 */

@Service("abnormalFrequencyTreatmentService")
public class AbnormalFrequencyTreatmentServiceImpl implements AbnormalFrequencyTreatmentService{
	
	 @Resource
	 private AbnormalFrequencyTreatmentDao abnormalFrequencyTreatmentDao;

	@Override
	public PageModel getFrequencyTreatment(AbnormalFrequencyTreatmentDto dto) {

		return abnormalFrequencyTreatmentDao.getFrequencyTreatment(dto);
	}

	@Override
	public PageModel getFrequencyTreatmentDetails(AbnormalFrequencyTreatmentDto dto) {
		return abnormalFrequencyTreatmentDao.getFrequencyTreatmentDetails(dto);
	}

	@Override
	public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalFrequencyTreatmentDto dto) {

		return abnormalFrequencyTreatmentDao.getTotalNumberOfCasesAndTotalAmount(dto);
	}

	@Override
	public PageModel getInsuredDataForm(AbnormalFrequencyTreatmentDto dto) {

		return abnormalFrequencyTreatmentDao.getInsuredDataForm(dto);
	}

	@Override
	public PageModel getFrequencyTreatmentmxbyhisidTable(AbnormalFrequencyTreatmentDto dto) {
		return abnormalFrequencyTreatmentDao.getFrequencyTreatmentmxbyhisidTable(dto);
	}

	@Override
	public List<AbnormalFrequencyTreatment> exportD(AbnormalFrequencyTreatmentDto dto) {
		
		return abnormalFrequencyTreatmentDao.exportD(dto);
	}

	@Override
	public List<AbnormalFrequencyTreatment> exportA(AbnormalFrequencyTreatmentDto dto) {
		
		return abnormalFrequencyTreatmentDao.exportA(dto);
	}

	@Override
	public List<Map<String, Object>> getBenefitType() {
		// TODO Auto-generated method stub
		return abnormalFrequencyTreatmentDao.getBenefitType();
	}
}
