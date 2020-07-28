package com.dhcc.piccbid.blh.abnormalanesthesia;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.abnormalanesthesia.AbnormalAnesthesiaDto;
import com.dhcc.piccbid.entity.abnormalanesthesia.AbnormalAnesthesia;
import com.dhcc.piccbid.service.abnormalanesthesia.AbnormalAnesthesiaService;
import com.dhcc.piccbid.utils.CSVUtils;


@Component
public class AbnormalAnesthesiaBlh {

	@Resource
	private AbnormalAnesthesiaService  service;
	
	public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalAnesthesiaDto dto){
		return  service.getTotalNumberOfCasesAndTotalAmount(dto);
	}
	
	public PageModel getAbnormalAnesthesia (AbnormalAnesthesiaDto dto) {;
	if(null == dto.getPageModel()) {
        dto.setPageModel(new PageModel());
    }
	 return service.getAbnormalAnesthesia(dto);
	}
	
	
	public PageModel getAbnormalAnesthesiabyhisidTable(AbnormalAnesthesiaDto dto) {
		if(null == dto.getPageModel()) {
            dto.setPageModel(new PageModel());       
	};
	return service.getAbnormalAnesthesiabyhisidTable(dto);
	}
	
    // 导出
    public AbnormalAnesthesiaDto exportD(AbnormalAnesthesiaDto dto) {
    	
        //String sid=dto.getSocial_card_id();
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
        String fileName =  "麻醉违规_" + toDate + "_";
        // 删除文件
        CSVUtils.deleteFiles(outPutPath);
        // 生成文件
        List<AbnormalAnesthesia> list;
  
            list = service.exportD(dto);
        
        File csvFile = CSVUtils.createCSVFile(list, map, outPutPath, fileName);
        fileName = csvFile.getName();
        WebContextHolder.getContext().getRequest().getSession().setAttribute("csvFile", csvFile);
        dto.setFileName(fileName);
        dto.setOperateSuccess(true);
        return dto;
    }
}
