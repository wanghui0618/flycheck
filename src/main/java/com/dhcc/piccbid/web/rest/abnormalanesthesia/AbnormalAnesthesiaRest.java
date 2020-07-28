package com.dhcc.piccbid.web.rest.abnormalanesthesia;

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

import com.dhcc.piccbid.blh.abnormalanesthesia.AbnormalAnesthesiaBlh;
import com.dhcc.piccbid.dto.abnormalanesthesia.AbnormalAnesthesiaDto;
import com.dhcc.piccbid.entity.page.Page;

@RestController
@RequestMapping("/dhccApi/abnormalanesthesiarest")
public class AbnormalAnesthesiaRest {
	
	@Resource
	private  AbnormalAnesthesiaBlh blh;
	
	
	@RequestMapping(value="/getAbnormalAnesthesia")
    public Page getAbnormalAnesthesia(AbnormalAnesthesiaDto dto) {
    	blh.getAbnormalAnesthesia(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	
	@RequestMapping(value="/getTotalNumberOfCasesAndTotalAmount")
    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalAnesthesiaDto dto) {
        return blh.getTotalNumberOfCasesAndTotalAmount(dto);
    }
	
	
	@RequestMapping(value="/getAbnormalAnesthesiabyhisidTable")
    public Page getAbnormalAnesthesiabyhisidTable(AbnormalAnesthesiaDto dto) {
    	blh.getAbnormalAnesthesiabyhisidTable(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	
	//批量导出方法
    @RequestMapping(value="/expDate")
    public AbnormalAnesthesiaDto expDate(AbnormalAnesthesiaDto dto) {
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

}
