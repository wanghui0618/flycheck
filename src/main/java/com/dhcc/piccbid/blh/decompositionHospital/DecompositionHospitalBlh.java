package com.dhcc.piccbid.blh.decompositionHospital;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import com.dhcc.piccbid.entity.decompositionHospital.DecompositionHospital;
import com.dhcc.piccbid.service.decompositionHospital.DecompositionHospitalService;
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
 * <p>标题: DecompositionHospitalBlh.java</p>
 * <p>业务描述:分解住院Blh层</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年11月23日
 * @version V1.0
 */
@Component
public class DecompositionHospitalBlh extends BaseAbstractBlh<DecompositionHospitalDto> {
    @Resource
    private DecompositionHospitalService decompositionHospitalService;

    /**
     * 方法名:getDecompositionHospital
     * 方法功能描述:分解住院 BLH方法
     * @param:DecompositionHospitalDto
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年11月23日
     */
    public PageModel getDecompositionHospital(DecompositionHospitalDto dto){
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return decompositionHospitalService.getDecompositionHospital(dto);
    }


    /**
     * 方法名:getDecompositionHospitalDetails
     * 方法功能描述:查询详细明细
     * @param:DecompositionHospitalDto
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月24日
     */
    public PageModel getDecompositionHospitalDetails(DecompositionHospitalDto dto){
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return decompositionHospitalService.getDecompositionHospitalDetails(dto);
    }

    /**
     * 方法名:getTotalNumberOfCasesAndTotalAmount
     * 方法功能描述:分解住院 计算总病例数和总金额方法
     * @param:@return
     * @return:List<Map<String, Object>>
     * @Author:王彤
     * @Create Date:2019年11月25日
     */
    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(DecompositionHospitalDto dto){
        return decompositionHospitalService.getTotalNumberOfCasesAndTotalAmount(dto);
    }

    /**
     * 方法名:getInsuredDataForm
     * 方法功能描述:参保人信息展示查询 BLH
     * @param:DecompositionHospitalDto
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年11月25日
     */
    public PageModel getInsuredDataForm(DecompositionHospitalDto dto){
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return decompositionHospitalService.getInsuredDataForm(dto);
    }




    /**
     * 方法名:getDetailsHospitalTable
     * 方法功能描述:病例信息详情 BLH
     * @param:DecompositionHospitalDto
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月24日
     */
    public PageModel getDetailsHospitalTable(DecompositionHospitalDto dto){
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return decompositionHospitalService.getDetailsHospitalTable(dto);
    }

    // 导出
    public DecompositionHospitalDto exportD(DecompositionHospitalDto dto) {
        String sid=dto.getSocial_card_id();
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("hisid", "结算单据号");
        map.put("zyh", "住院号");
        map.put("admissionDate", "入院日期");
        map.put("dischargeDate", "出院日期");
        map.put("zyts", "住院天数");
        map.put("billDate", "结算日期");
        map.put("dischargeDiseaseIdMain", "出院诊断编码");
        map.put("dischargeDiseaseNameMain", "出院诊断名称");
        map.put("totalAmount", "医疗总费用");
        map.put("bmiPayAmount", "基本统筹支付");
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
        String fileName =  "分解住院_" + toDate + "_";
        // 删除文件
        CSVUtils.deleteFiles(outPutPath);
        // 生成文件
        List<DecompositionHospital> list;
        if(sid != null && sid != ""){
            list=decompositionHospitalService.exportA(dto);
        }else {
            list = decompositionHospitalService.exportD(dto);
        }
        File csvFile = CSVUtils.createCSVFile(list, map, outPutPath, fileName);
        fileName = csvFile.getName();
        WebContextHolder.getContext().getRequest().getSession().setAttribute("csvFile", csvFile);
        dto.setFileName(fileName);
        dto.setOperateSuccess(true);
        return dto;
    }

}
