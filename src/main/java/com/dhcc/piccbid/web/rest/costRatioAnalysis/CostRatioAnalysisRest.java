package com.dhcc.piccbid.web.rest.costRatioAnalysis;

import com.dhcc.piccbid.blh.costRatioAnalysis.CostRatioAnalysisRestBlh;
import com.dhcc.piccbid.dto.costRatioAnalysis.CostRatioAnalysisDto;
import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>标题: CostRatioAnalysisRest.java</p>
 * <p>获取前台js 的链接，并且返回json类型的数据</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年10月18日
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/CostRatioAnalysisRest")
public class CostRatioAnalysisRest  {
    @Resource
    private CostRatioAnalysisRestBlh costRatioAnalysisRestBlh;

    @RequestMapping(value="/getCostRatioAnalysis")
    public Page getCostRatioAnalysis(CostRatioAnalysisDto dto) {
        costRatioAnalysisRestBlh.getCostRatioAnalysis(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    @RequestMapping(value="/getCostRatioAnalysisMenz")
    public Page getCostRatioAnalysisMenz(CostRatioAnalysisDto dto) {
        costRatioAnalysisRestBlh.getCostRatioAnalysisMenz(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }



}
