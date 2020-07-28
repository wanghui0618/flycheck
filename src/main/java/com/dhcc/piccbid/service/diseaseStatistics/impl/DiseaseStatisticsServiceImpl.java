package com.dhcc.piccbid.service.diseaseStatistics.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.diseaseStatistics.DiseaseStatisticsDao;
import com.dhcc.piccbid.dto.diseaseStatistics.DiseaseStatisticsDto;
import com.dhcc.piccbid.service.diseaseStatistics.DiseaseStatisticsService;

@Service("diseaseStatisticsService")
public class DiseaseStatisticsServiceImpl implements DiseaseStatisticsService{

	@Resource
	private DiseaseStatisticsDao  diseaseStatisticsDao;
	
	@Override
	public PageModel getDiseaseStatistics(DiseaseStatisticsDto dto) {
		
		return diseaseStatisticsDao.getDiseaseStatistics(dto);
	}

}
