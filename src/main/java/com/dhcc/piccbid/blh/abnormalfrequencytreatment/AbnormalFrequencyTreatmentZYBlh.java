package com.dhcc.piccbid.blh.abnormalfrequencytreatment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Column;

import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.abnormalFrequencyTreatment.AbnormalFrequencyTreatmentZYDto;
import com.dhcc.piccbid.entity.abnormalfrequencytreatment.AbnormalFrequencyTreatmentZY;
import com.dhcc.piccbid.service.abnormalfrequencytreatment.AbnormalFrequencyTreatmentZYService;
import com.dhcc.piccbid.utils.CSVUtils;

/**
 * <p>标题: AbnormalFrequencyTreatmentZYBlh.java</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 贺和平
 * @date 2020年1月6日
 * @version V1.0
 */
@Component
public class AbnormalFrequencyTreatmentZYBlh {

	@Resource
	private  AbnormalFrequencyTreatmentZYService  service;
	
	/**
     * 方法名:getFrequencyTreatment
     * 方法功能描述:就诊频次 方法
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2019年11月23日
     */
    public PageModel getFrequencyTreatment(AbnormalFrequencyTreatmentZYDto dto){
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return service.getFrequencyTreatment(dto);
    }


    /**
     * 方法名:getFrequencyTreatmentDetails
     * 方法功能描述:就诊次数右边 方法
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2019年12月24日
     */
    public PageModel getFrequencyTreatmentDetails(AbnormalFrequencyTreatmentZYDto dto){
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return service.getFrequencyTreatmentDetails(dto);
    }

    /**
     * 方法名:getTotalNumberOfCasesAndTotalAmount
     * 方法功能描述:就诊次数异常 计算总病例数和总金额方法
     * @param:@return
     * @return:List<Map<String, Object>>
     * @Author:贺和平
     * @Create Date:2019年11月25日
     */
    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalFrequencyTreatmentZYDto dto){
        return service.getTotalNumberOfCasesAndTotalAmount(dto);
    }


    /**
     * 方法名:getFrequencyTreatmentmxbyhisidTable
     * 方法功能描述:病例明细详情查询,根据病人的hisid查询住院明细结算表
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2019年12月24日
     */
    public PageModel getFrequencyTreatmentmxbyhisidTable(AbnormalFrequencyTreatmentZYDto dto) {
        //判断dto 的PageModel 是否为空，如果为空，则创建一个新的PageModel
        if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());
        }
        return service.getFrequencyTreatmentmxbyhisidTable(dto);
    }

    // 导出
    public AbnormalFrequencyTreatmentZYDto exportD(AbnormalFrequencyTreatmentZYDto dto) {
        String sid=dto.getSocial_card_id();
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("hisid", "结算单据号");
        map.put("zyh", "住院号");
        map.put("admissionDate", "入院日期");
        map.put("dischargeDate", "出院日期");
        map.put("zyts", "住院天数");
        map.put("billDate", "结算日期");
        map.put("dischargeDiseaseIdMain", "诊断编码");
        map.put("dischargeDiseaseNameMain", "诊断名称");
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
        String fileName =  "住院就诊次数异常_" + toDate + "_";
        // 删除文件
        CSVUtils.deleteFiles(outPutPath);
        // 生成文件
        List<AbnormalFrequencyTreatmentZY> list;
        if(sid != null && sid != ""){
            list=service.exportA(dto);
        }else {
            list = service.exportD(dto);
        }
        File csvFile = CSVUtils.createCSVFile(list, map, outPutPath, fileName);
        fileName = csvFile.getName();
        WebContextHolder.getContext().getRequest().getSession().setAttribute("csvFile", csvFile);
        dto.setFileName(fileName);
        dto.setOperateSuccess(true);
        return dto;
    }
    /**
     * 方法名:getBenefitType
     * 方法功能描述:医保类型
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2020年1月3日
     */
    public List<Map<String, Object>> getBenefitType(){
    	return service.getBenefitType();
    }
}
