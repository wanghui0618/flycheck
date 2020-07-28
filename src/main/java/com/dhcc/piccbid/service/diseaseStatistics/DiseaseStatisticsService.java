package com.dhcc.piccbid.service.diseaseStatistics;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.diseaseStatistics.DiseaseStatisticsDto;

public interface DiseaseStatisticsService {

	public PageModel getDiseaseStatistics(DiseaseStatisticsDto dto);
}
