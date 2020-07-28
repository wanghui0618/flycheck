package com.dhcc.piccbid.blh.diseaseStatistics;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.diseaseStatistics.DiseaseStatisticsDto;
import com.dhcc.piccbid.service.diseaseStatistics.DiseaseStatisticsService;

@Component
public class DiseaseStatisticsBlh {

	@Resource
    private DiseaseStatisticsService diseaseStatisticsService;



    /**
     * @Title:
     * @Description: TODO(机构和机构类型字典)
     * @param @return    参数
     * @return List<Map<String,Object>>    返回类型
     * @throws
     */
    public PageModel getDiseaseStatistics(DiseaseStatisticsDto dto){
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return diseaseStatisticsService.getDiseaseStatistics(dto);
    }
}
