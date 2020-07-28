package com.dhcc.piccbid.service.abnormalfrequencytreatment;

import java.util.List;
import java.util.Map;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.abnormalFrequencyTreatment.AbnormalFrequencyTreatmentDto;
import com.dhcc.piccbid.entity.abnormalfrequencytreatment.AbnormalFrequencyTreatment;
/**
 * <p>标题: AbnormalFrequencyTreatmentService.java</p>
 * <p>业务描述:就诊次数异常Service接口</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 贺和平
 * @date 2019年11月23日
 * @version V1.0
 */

public interface AbnormalFrequencyTreatmentService {
	
	 /**
     * 方法名:getFrequencyTreatment
     * 方法功能描述:就诊频次 方法
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2019年11月23日
     */
    public PageModel getFrequencyTreatment(AbnormalFrequencyTreatmentDto dto);

    /**
     * 方法名:getFrequencyTreatmentDetails
     * 方法功能描述:就诊次数右边 方法
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2019年12月24日
     */
    public PageModel getFrequencyTreatmentDetails(AbnormalFrequencyTreatmentDto dto);

    /**
     * 方法名:getTotalNumberOfCasesAndTotalAmount
     * 方法功能描述:就诊次数异常 计算总病例数和总金额方法
     * @param:@return
     * @return:List<Map<String, Object>>
     * @Author:贺和平
     * @Create Date:2019年11月25日
     */
    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalFrequencyTreatmentDto dto);

    /**
     * 方法名:getInsuredDataForm
     * 方法功能描述:参保人信息展示查询接口
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2019年11月25日
     */
    public PageModel getInsuredDataForm(AbnormalFrequencyTreatmentDto dto);


    /**
     * 方法名:getFrequencyTreatmentmxbyhisidTable
     * 方法功能描述:病例明细详情查询,根据病人的hisid查询住院明细结算表
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2019年12月24日
     */
    public PageModel getFrequencyTreatmentmxbyhisidTable(AbnormalFrequencyTreatmentDto dto);

    /**
     * 方法名:exportD
     * 方法功能描述:数据导出查询方法
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2019年12月25日
     */
    List<AbnormalFrequencyTreatment> exportD(AbnormalFrequencyTreatmentDto dto);


    /**
     * 方法名:exportA
     * 方法功能描述:数据导出少量查询方法
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2019年12月25日
     */
    List<AbnormalFrequencyTreatment> exportA(AbnormalFrequencyTreatmentDto dto);
    
    /**
     * 方法名:getBenefitType
     * 方法功能描述:医保类型
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2020年1月3日
     */
    List<Map<String, Object>> getBenefitType(); 

}
