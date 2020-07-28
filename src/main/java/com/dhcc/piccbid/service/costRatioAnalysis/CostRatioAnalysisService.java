package com.dhcc.piccbid.service.costRatioAnalysis;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.costRatioAnalysis.CostRatioAnalysisDto;
/**
 * <p>标题: CostRatioAnalysisService.java</p>
 * <p>业务描述:CostRatioAnalysisService.java</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年10月18日
 * @version V1.0
 */
public interface CostRatioAnalysisService {



    /**
     * 方法名:getCostRatioAnalysis
     * 方法功能描述:查询住院明细表，计算占比接口
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年10月18日
     */
    public PageModel getCostRatioAnalysis(CostRatioAnalysisDto dto);

    /**
     * 方法名:getCostRatioAnalysisMenz
     * 方法功能描述:查询门诊明细表，计算占比接口
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年10月28日
     */
    public PageModel getCostRatioAnalysisMenz(CostRatioAnalysisDto dto);


}
