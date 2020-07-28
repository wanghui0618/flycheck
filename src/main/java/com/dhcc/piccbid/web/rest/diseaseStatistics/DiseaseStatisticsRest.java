package com.dhcc.piccbid.web.rest.diseaseStatistics;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.piccbid.blh.diseaseStatistics.DiseaseStatisticsBlh;
import com.dhcc.piccbid.dto.diseaseStatistics.DiseaseStatisticsDto;
import com.dhcc.piccbid.entity.page.Page;


@RestController
@RequestMapping("/dhccApi/diseaseStatistics")
public class DiseaseStatisticsRest {
	
	   @Resource
	    private DiseaseStatisticsBlh diseaseStatisticsBlh;

	    @RequestMapping(value="/diseaseStatistics/list")
	    public Page getDiseaseStatistics(DiseaseStatisticsDto dto) {
	    	diseaseStatisticsBlh.getDiseaseStatistics(dto);
	    	Page page = new Page();
	        page.setData(dto.getPageModel().getPageData());
	        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
	        return page;
	    }

}
