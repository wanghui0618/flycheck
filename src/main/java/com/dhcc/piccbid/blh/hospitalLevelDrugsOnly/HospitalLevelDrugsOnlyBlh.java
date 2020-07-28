package com.dhcc.piccbid.blh.hospitalLevelDrugsOnly;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import com.dhcc.piccbid.dto.hospitalLevelDrugsOnlyDto.HospitalLevelDrugsOnlyDto;
import com.dhcc.piccbid.entity.decompositionHospital.DecompositionHospital;
import com.dhcc.piccbid.entity.hospitalLevelDrugsOnly.HospitalLevelDrugsOnly;
import com.dhcc.piccbid.service.decompositionHospital.DecompositionHospitalService;
import com.dhcc.piccbid.service.hospitalLevelDrugsOnly.HospitalLevelDrugsOnlyService;
import com.dhcc.piccbid.utils.CSVUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>标题: HospitalLevelDrugsOnlyBlh.java</p>
 * <p>业务描述:限医院等级用药Blh层</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2020</p>
 * @author 王彤
 * @date 2020年1月2日
 * @version V1.0
 */
@Component
public class HospitalLevelDrugsOnlyBlh {
    @Resource
    private HospitalLevelDrugsOnlyService hospitalLevelDrugsOnlyService;

    /**
     * 方法名:getHospitalLevelDrugsOnly
     * 方法功能描述:限医院等级用药 BLH方法
     * @param:HospitalLevelDrugsOnlyDto
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2020年1月2日
     */
    public PageModel getHospitalLevelDrugsOnly(HospitalLevelDrugsOnlyDto dto){
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return hospitalLevelDrugsOnlyService.getHospitalLevelDrugsOnly(dto);
    }

    /**
     * 方法名:getCountAndTotle
     * 方法功能描述:计算总病例数和总金额方法
     * @param:@return
     * @return:List<Map<String, Object>>
     * @Author:王彤
     * @Create Date:2020年1月2日
     */
    public List<Map<String, Object>> getCountAndTotle(HospitalLevelDrugsOnlyDto dto){
        return hospitalLevelDrugsOnlyService.getCountAndTotle(dto);
    }
    /**
     * 方法名:exportOutpatient
     * 方法功能描述:门诊导出
     * @param:@return
     * @return:HospitalLevelDrugsOnlyDto
     * @Author:王彤
     * @Create Date:2020年1月3日
     */
    public HospitalLevelDrugsOnlyDto exportOutpatient(HospitalLevelDrugsOnlyDto dto) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("hospitalId", "医院编码");
        map.put("hospitalName", "医院名称");
        map.put("dischargeDeptName", "就诊科室编码");
        map.put("dischargeDept", "就诊科室");
        map.put("patientName", "患者姓名");
        map.put("totalAmount", "医疗总费用");
        map.put("benefitType", "险种类型");
        map.put("itemId", "医保项目编码");
        map.put("itemName", "医保项目名称");
        map.put("itemIdHosp", "医院项目编码");
        map.put("itemNameHosp", "医院项目名称");
        map.put("westernMedicineFee", "西药费");
        map.put("chineseMedicineYinpian", "中药饮片费");
        map.put("chineseMedicineForm", "中成药费");
        // 路径及文件名
        // 输出文档路径及名称
        String savePath = WebContextHolder.getContext().getRequest().getServletContext().getRealPath("");
        String path = new File(savePath).getParentFile().getAbsolutePath();
        String basicPath = "/upload/fileExport/wordExport/";
        String outPutPath = path + basicPath;
        File document = new File(outPutPath);
        if (!document.exists()) {
            document.mkdirs();
        }
        SimpleDateFormat smt = new SimpleDateFormat("yyyyMMddHHmmss");
        String toDate = smt.format(new Date());
        String fileName =  "门诊数据_" + toDate + "_";
        // 删除文件
        CSVUtils.deleteFiles(outPutPath);
        // 生成文件
        List<HospitalLevelDrugsOnly> list=hospitalLevelDrugsOnlyService.exportOutpatient(dto);

        File csvFile = CSVUtils.createCSVFile(list, map, outPutPath, fileName);
        fileName = csvFile.getName();
        WebContextHolder.getContext().getRequest().getSession().setAttribute("csvFile", csvFile);
        dto.setFileName(fileName);
        dto.setOperateSuccess(true);
        return dto;
    }

    /**
     * 方法名:exportBeHospitalized
     * 方法功能描述:住院导出
     * @param:@return
     * @return:HospitalLevelDrugsOnlyDto
     * @Author:王彤
     * @Create Date:2020年1月3日
     */
    public HospitalLevelDrugsOnlyDto exportBeHospitalized(HospitalLevelDrugsOnlyDto dto) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("hospitalId", "医院编码");
        map.put("hospitalName", "医院名称");
        map.put("zyh", "住院号");
        map.put("dischargeDeptName", "就诊科室编码");
        map.put("dischargeDept", "就诊科室");
        map.put("patientName", "患者姓名");
        map.put("admissionDate", "入院日期");
        map.put("dischargeDate", "出院日期");
        map.put("zyts", "住院天数");
        map.put("totalAmount", "医疗总费用");
        map.put("benefitType", "险种类型");
        map.put("itemId", "医保项目编码");
        map.put("itemName", "医保项目名称");
        map.put("itemIdHosp", "医院项目编码");
        map.put("itemNameHosp", "医院项目名称");
        map.put("westernMedicineFee", "西药费");
        map.put("chineseMedicineYinpian", "中药饮片费");
        map.put("chineseMedicineForm", "中成药费");
        // 路径及文件名
        // 输出文档路径及名称
        String savePath = WebContextHolder.getContext().getRequest().getServletContext().getRealPath("");
        String path = new File(savePath).getParentFile().getAbsolutePath();
        String basicPath = "/upload/fileExport/wordExport/";
        String outPutPath = path + basicPath;
        File document = new File(outPutPath);
        if (!document.exists()) {
            document.mkdirs();
        }
        SimpleDateFormat smt = new SimpleDateFormat("yyyyMMddHHmmss");
        String toDate = smt.format(new Date());
        String fileName =  "住院数据_" + toDate + "_";
        // 删除文件
        CSVUtils.deleteFiles(outPutPath);
        // 生成文件
        List<HospitalLevelDrugsOnly> list=hospitalLevelDrugsOnlyService.exportBeHospitalized(dto);

        File csvFile = CSVUtils.createCSVFile(list, map, outPutPath, fileName);
        fileName = csvFile.getName();
        WebContextHolder.getContext().getRequest().getSession().setAttribute("csvFile", csvFile);
        dto.setFileName(fileName);
        dto.setOperateSuccess(true);
        return dto;
    }




}
