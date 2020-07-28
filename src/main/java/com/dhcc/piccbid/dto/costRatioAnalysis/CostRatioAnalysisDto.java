package com.dhcc.piccbid.dto.costRatioAnalysis;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;


/**
 * <p>标题: CostRatioAnalysisDto.java</p>
 * <p>业务描述:费用占比计算的DTO传输层</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年10月18日
 * @version V1.0
 */
public class CostRatioAnalysisDto extends BaseAbstractDto {
    private static final long serialVersionUID = 1L;
    // 当前页数
    int page;
    // 当前页个数
    int limit;

    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }
}
