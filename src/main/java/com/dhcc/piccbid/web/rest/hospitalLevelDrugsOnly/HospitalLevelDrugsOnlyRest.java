package com.dhcc.piccbid.web.rest.hospitalLevelDrugsOnly;

import com.dhcc.piccbid.blh.hospitalLevelDrugsOnly.HospitalLevelDrugsOnlyBlh;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import com.dhcc.piccbid.dto.hospitalLevelDrugsOnlyDto.HospitalLevelDrugsOnlyDto;
import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>标题: HospitalLevelDrugsOnlyRest.java</p>
 * <p>获取前台js 的链接，并且返回json类型的数据</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2020</p>
 * @author 王彤
 * @date 2020年1月2日
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/HospitalLevelDrugsOnlyRest")
public class HospitalLevelDrugsOnlyRest {
    @Resource
    private HospitalLevelDrugsOnlyBlh hospitalLevelDrugsOnlyBlh;

    @RequestMapping(value="/getHospitalLevelDrugsOnly")
    public Page getHospitalLevelDrugsOnly(HospitalLevelDrugsOnlyDto dto) {
        hospitalLevelDrugsOnlyBlh.getHospitalLevelDrugsOnly(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    @RequestMapping(value="/getCountAndTotle")
    public List<Map<String, Object>> getCountAndTotle(HospitalLevelDrugsOnlyDto dto) {
        return hospitalLevelDrugsOnlyBlh.getCountAndTotle(dto);
    }

    @RequestMapping(value="/exportOutpatient")
    public HospitalLevelDrugsOnlyDto exportOutpatient(HospitalLevelDrugsOnlyDto dto) {
        return hospitalLevelDrugsOnlyBlh.exportOutpatient(dto);
    }

    @RequestMapping(value="/exportBeHospitalized")
    public HospitalLevelDrugsOnlyDto exportBeHospitalized(HospitalLevelDrugsOnlyDto dto) {
        return hospitalLevelDrugsOnlyBlh.exportBeHospitalized(dto);
    }

}
