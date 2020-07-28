package com.dhcc.piccbid.service.costRatioAnalysis.impl;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.costRatioAnalysis.CostRatioAnalysisDao;
import com.dhcc.piccbid.dto.costRatioAnalysis.CostRatioAnalysisDto;
import com.dhcc.piccbid.service.costRatioAnalysis.CostRatioAnalysisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>标题: CostRatioAnalysisServiceImpl.java</p>
 * <p>业务描述:CostRatioAnalysisServiceImpl.java</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年10月18日
 * @version V1.0
 */
@Service("costRatioAnalysisService")
public class CostRatioAnalysisServiceImpl implements CostRatioAnalysisService {
    @Resource
    private CostRatioAnalysisDao costRatioAnalysisDao;

    @Override
    public PageModel getCostRatioAnalysis(CostRatioAnalysisDto dto) {
        return costRatioAnalysisDao.getCostRatioAnalysis(dto);
    }

    @Override
    public PageModel getCostRatioAnalysisMenz(CostRatioAnalysisDto dto) {
        return costRatioAnalysisDao.getCostRatioAnalysisMenz(dto);
    }
}
