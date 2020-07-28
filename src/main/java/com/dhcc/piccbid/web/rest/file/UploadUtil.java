package com.dhcc.piccbid.web.rest.file;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;

/**
 * <p>
 * 标题: Uploadutil.java
 * 业务描述:文件上传
 * 公司:东华软件股份公司
 * @author 李研
 * @date 2017年8月1日
 * @version V1.0
 */
//@Controller
@RestController
@RequestMapping("/dhccApi/uploadApi")
public class UploadUtil {

	@SuppressWarnings("rawtypes")
	@RequestMapping("/uploadAudit")
	public String springUpload(HttpServletRequest request) throws Exception {

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		String filePath = request.getParameter("filePath");
		StringBuffer path = new StringBuffer();
		path.append(request.getServletContext().getRealPath(""));
		path.append(filePath);
		File document = new File(path.toString());
		 if(!document.exists()){
			 document.mkdirs();
	      }

		if (multipartResolver.isMultipart(request)) {

			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			String  name ="";
			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());

				if (file != null) {
					name = file.getOriginalFilename().replaceAll(" ", ""); //去除空格
					file.transferTo(new File(path.toString()+name));
					
					//只对头像压缩
					if(path.toString().contains("headimg")) {
						Thumbnails.of(path.toString()+name).size(200, 200).toFile(path.toString()+name);
					}
				}

			}

		}
		//return "/success";
        return "1";
	}
}
