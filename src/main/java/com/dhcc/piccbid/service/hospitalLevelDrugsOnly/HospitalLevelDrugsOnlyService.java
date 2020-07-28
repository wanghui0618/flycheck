package com.dhcc.piccbid.service.hospitalLevelDrugsOnly;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import com.dhcc.piccbid.dto.hospitalLevelDrugsOnlyDto.HospitalLevelDrugsOnlyDto;
import com.dhcc.piccbid.entity.hospitalLevelDrugsOnly.HospitalLevelDrugsOnly;

import java.util.List;
import java.util.Map;

/**
 * <p>标题: HospitalLevelDrugsOnlyService.java</p>
 * <p>业务描述:限医院等级用药Service接口</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2020</p>
 * @author 王彤
 * @date 2020年1月2日
 * @version V1.0
 */
public interface HospitalLevelDrugsOnlyService {

    /**
     * 方法名:getHospitalLevelDrugsOnly
     * 方法功能描述:查询 限医院等级用药情况
     * @param:HospitalLevelDrugsOnlyDto
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2020年1月2日
     */
    public PageModel getHospitalLevelDrugsOnly(HospitalLevelDrugsOnlyDto dto);

    /**
     * 方法名:getCountAndTotle
     * 方法功能描述: 计算总病例数和总金额接口
     * @param:HospitalLevelDrugsOnlyDto
     * @return:List<Map<String, Object>>
     * @Author:王彤
     * @Create Date:2019年11月25日
     */
    List<Map<String, Object>> getCountAndTotle(HospitalLevelDrugsOnlyDto dto);
    /**
     * 方法名:exportOutpatient
     * 方法功能描述:门诊数据导出
     * @param:@return
     * @return:List<HospitalLevelDrugsOnly>
     * @Author:王彤
     * @Create Date:2020年1月3日
     */
    List<HospitalLevelDrugsOnly> exportOutpatient(HospitalLevelDrugsOnlyDto dto);
    /**
     * 方法名:exportBeHospitalized
     * 方法功能描述:住院数据导出
     * @param:@return
     * @return:List<HospitalLevelDrugsOnly>
     * @Author:王彤
     * @Create Date:2020年1月3日
     */
    List<HospitalLevelDrugsOnly> exportBeHospitalized(HospitalLevelDrugsOnlyDto dto);

}
