package com.dhcc.piccbid.web.rest.abnormalfrequencytreatment;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.piccbid.blh.abnormalfrequencytreatment.AbnormalFrequencyTreatmentBlh;
import com.dhcc.piccbid.dto.abnormalFrequencyTreatment.AbnormalFrequencyTreatmentDto;
import com.dhcc.piccbid.entity.page.Page;


/**
 * <p>标题: AbnormalFrequencyTreatmentRest.java</p>
 * <p>获取前台js 的链接，并且返回json类型的数据</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 贺和平
 * @date 2019年11月23日
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/abnormalfrequencytreatmentrest")
public class AbnormalFrequencyTreatmentRest {
	
	    @Resource
	    private AbnormalFrequencyTreatmentBlh blh;

	    @RequestMapping(value="/getFrequencyTreatment")
	    public Page getFrequencyTreatment(AbnormalFrequencyTreatmentDto dto) {
	    	blh.getFrequencyTreatment(dto);
	        Page page = new Page();
	        page.setData(dto.getPageModel().getPageData());
	        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
	        return page;
	    }

	    @RequestMapping(value="/getFrequencyTreatmentDetails")
	    public Page getFrequencyTreatmentDetails(AbnormalFrequencyTreatmentDto dto) {
	        blh.getFrequencyTreatmentDetails(dto);
	        Page page = new Page();
	        page.setData(dto.getPageModel().getPageData());
	        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
	        return page;
	    }

	    @RequestMapping(value="/getInsuredDataForm")
	    public Page getInsuredDataForm(AbnormalFrequencyTreatmentDto dto) {
	        System.out.println("Rest测试"+dto.getSettlementTime());
	        blh.getInsuredDataForm(dto);
	        Page page = new Page();
	        page.setData(dto.getPageModel().getPageData());
	        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
	        return page;
	    }


	    @RequestMapping(value="/getFrequencyTreatmentmxbyhisidTable")
	    public Page getFrequencyTreatmentmxbyhisidTable(AbnormalFrequencyTreatmentDto dto) {
	        blh.getFrequencyTreatmentmxbyhisidTable(dto);
	        Page page = new Page();
	        page.setData(dto.getPageModel().getPageData());
	        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
	        return page;
	    }



	    @RequestMapping(value="/getTotalNumberOfCasesAndTotalAmount")
	    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalFrequencyTreatmentDto dto) {
	        return blh.getTotalNumberOfCasesAndTotalAmount(dto);
	    }
	    //批量导出方法
	    @RequestMapping(value="/expDate")
	    public AbnormalFrequencyTreatmentDto expDate(AbnormalFrequencyTreatmentDto dto) {
	        blh.exportD(dto);
	        return dto;
	    }
	    //下载
	    @PostMapping("downLoadFile")
	    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        String fileName = (String) request.getParameter("fileName");

	        String savePath = request.getServletContext().getRealPath("");
	        String root = new File(savePath).getParentFile().getAbsolutePath();
	        String basicPath = "/upload/fileExport/wordExport/";
	        String filePath = root + basicPath;
	        try {
	            response.setContentType("Application/Octet-stream;charset=utf-8");
	            // 通过response响应返回给客户端
	            response.setHeader("Content-Disposition", "attachment;filename=\""
	                    + new String(fileName.getBytes("GBK"), "ISO8859-1") + "\"");// 文件名称
	            //获取文件名
	            File document = new File(filePath.toString());
	            if(!document.exists()){
	                document.mkdirs();
	            }
	        } catch (Exception e) {
	            response.setContentType("text/html; charset=utf-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>");
	            out.println("alert('您要下载的资源已被删除！');");
	            out.println("history.back();");
	            out.println("</script>");
	            out.close();
	            out.flush();
	        }finally{
	            File file = new File(filePath+fileName);
	            FileInputStream fos = new FileInputStream(filePath+fileName);// 注意正则表达式的冲突，所以使用//
	            byte b[] = new byte[1024];
	            int length;
	            while ((length = fos.read(b)) > 0) {
	                response.getOutputStream().write(b, 0, length);
	            }
	            fos.close();
	            if(file.exists()) {
	                file.delete();
	            }
	            response.getOutputStream().flush();
	        }
	    }
	    
	    @RequestMapping(value="/getBenefitTypemz")
	    public List<Map<String, Object>> getBenefitType(){
	    	return blh.getBenefitType();
	    }
}
