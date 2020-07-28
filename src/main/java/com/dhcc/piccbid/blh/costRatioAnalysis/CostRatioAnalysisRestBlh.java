package com.dhcc.piccbid.blh.costRatioAnalysis;


import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.costRatioAnalysis.CostRatioAnalysisDto;
import com.dhcc.piccbid.service.costRatioAnalysis.CostRatioAnalysisService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
/**
 * <p>标题: CostRatioAnalysisRestBlh.java</p>
 * <p>业务描述:CostRatioAnalysisRestBlh.java</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年10月18日
 * @version V1.0
 */
@Component
public class CostRatioAnalysisRestBlh extends BaseAbstractBlh<CostRatioAnalysisDto> {

    //引入CostRatioAnalysisService
    @Resource
    private CostRatioAnalysisService costRatioAnalysisService;

    /**
     * 方法名:getCostRatioAnalysis
     * 方法功能描述:住院明细计算方法的BLH，返回pageModel
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年10月28日
     */
    public PageModel getCostRatioAnalysis(CostRatioAnalysisDto dto){
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return costRatioAnalysisService.getCostRatioAnalysis(dto);
    }


    /**
     * 方法名:getCostRatioAnalysisMenz
     * 方法功能描述:门诊明细计算方法的BLH，返回pageModel
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年10月28日
     */
    public PageModel getCostRatioAnalysisMenz(CostRatioAnalysisDto dto){
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return costRatioAnalysisService.getCostRatioAnalysisMenz(dto);
    }

}
