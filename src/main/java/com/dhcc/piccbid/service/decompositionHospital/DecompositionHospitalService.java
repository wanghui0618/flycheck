package com.dhcc.piccbid.service.decompositionHospital;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import com.dhcc.piccbid.entity.decompositionHospital.DecompositionHospital;

import java.util.List;
import java.util.Map;

/**
 * <p>标题: DecompositionHospitalService.java</p>
 * <p>业务描述:分解住院Service接口</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年11月23日
 * @version V1.0
 */
public interface DecompositionHospitalService {
    /**
     * 方法名:getDecompositionHospital
     * 方法功能描述:查询 分解住院情况
     * @param:DecompositionHospitalDto
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年11月23日
     */
    public PageModel getDecompositionHospital(DecompositionHospitalDto dto);

    /**
     * 方法名:getDecompositionHospitalDetails
     * 方法功能描述:查询 分解住院情况详情
     * @param:DecompositionHospitalDto
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月24日
     */
    public PageModel getDecompositionHospitalDetails(DecompositionHospitalDto dto);

    /**
     * 方法名:getTotalNumberOfCasesAndTotalAmount
     * 方法功能描述:分解住院 计算总病例数和总金额接口
     * @param:DecompositionHospitalDto
     * @return:List<Map<String, Object>>
     * @Author:王彤
     * @Create Date:2019年11月25日
     */
    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(DecompositionHospitalDto dto);

    /**
     * 方法名:getInsuredDataForm
     * 方法功能描述:参保人信息展示查询接口
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年11月25日
     */
    public PageModel getInsuredDataForm(DecompositionHospitalDto dto);


    /**
     * 方法名:getDetailsHospitalTable
     * 方法功能描述:病例信息详情接口
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月24日
     */
    public PageModel getDetailsHospitalTable(DecompositionHospitalDto dto);

    /**
     * 方法名:exportD
     * 方法功能描述:数据导出
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月25日
     */
    List<DecompositionHospital> exportD(DecompositionHospitalDto dto);


    /**
     * 方法名:exportA
     * 方法功能描述:数据导出
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月25日
     */
    List<DecompositionHospital> exportA(DecompositionHospitalDto dto);



}
