package com.dhcc.piccbid.web.rest.notExists;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.piccbid.blh.notExists.NotExistsBlh;
import com.dhcc.piccbid.dto.notExists.NotExistsDto;
import com.dhcc.piccbid.entity.page.Page;

@RestController
@RequestMapping("/dhccApi/notExists/notExists")
public class NotExistsRest {

	@Resource
	private NotExistsBlh notExistsBlh;

	/*@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) NotExistsDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(NotExistsDto dto) {
		notExistsBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public NotExistsDto saveRest(@RequestBody NotExistsDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public NotExistsDto save(NotExistsDto dto) {
		notExistsBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public NotExistsDto deleteRest(@RequestBody NotExistsDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public NotExistsDto delete(NotExistsDto dto) {
		notExistsBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public NotExistsDto findByIdRest(@RequestBody NotExistsDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public NotExistsDto findById(NotExistsDto dto) {
		notExistsBlh.findById(dto);
		return dto;
	}
	
	
	}
	@PostMapping(value = "countnotExists",consumes = "application/json")
	public Page countabnormalHospitalStay(@RequestBody(required = false) UnreasonableAdmissionDto dto){
		return this.countabnormalHospitalStay(dto);
	}
	//病例数
	@RequestMapping("countnotExists")
	public Page countabnormalHospitalStay(NotExistsDto dto) {
		notExistsBlh.countabnormalHospitalStay(dto);
		Page page=new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}
	//明细情况
	@RequestMapping("countnotExists1")
	public Page countabnormalHospitalStay1(NotExistsDto dto) {
		notExistsBlh.countabnormalHospitalStay1(dto);
		Page page=new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}
	//弹出明细
	 @RequestMapping("searchDetail")
	    public Page searchDetail(NotExistsDto dto) {
	        Page page=new Page();
	        notExistsBlh.searchDetail(dto);
	        page.setData(dto.getPageModel().getPageData());
	        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
	        return page;
	    }
	 */
	//项目查询
	@RequestMapping("notExists")
	public Page notExists(NotExistsDto dto) {
		notExistsBlh.notExists(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	//项目查询
		@RequestMapping("notExists1")
		public Page notExists1(NotExistsDto dto) {
			notExistsBlh.notExists1(dto);
			Page page=new Page();
			page.setData(dto.getPageModel().getPageData());
			page.setCount(String.valueOf(dto.getPageModel().getTotals()));
			return page;
		}
	
		//明细情况
		@RequestMapping("countnotExists1")
		public Page countabnormalHospitalStay1(NotExistsDto dto) {
			notExistsBlh.countabnormalHospitalStay1(dto);
			Page page=new Page();
			page.setCount(String.valueOf(dto.getPageModel().getTotals()));
			page.setData(dto.getPageModel().getPageData());
			return page;
		}
	
		//互斥项目查询
	 @RequestMapping("searchMutex")
	    public Page searchMutex(NotExistsDto dto) {
	        Page page=new Page();
	        notExistsBlh.searchMutex(dto);
	        page.setData(dto.getPageModel().getPageData());
	        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
	        return page;
	    }
	 
		//互斥项目查询
	 @RequestMapping("searchMutex1")
	    public Page searchMutex1(NotExistsDto dto) {
		 Page page=new Page();
	        notExistsBlh.searchMutex1(dto);
	        page.setData(dto.getPageModel().getPageData());
	        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
	        return page;
	       
	    }
	 
		// 导出到excel
		@RequestMapping("exportMutex")
		public SXSSFWorkbook exportExcelToSelf(HttpServletResponse response,HttpServletRequest request,NotExistsDto dto) throws IOException {
			SXSSFWorkbook wb = notExistsBlh.exportExcelToSelf(request,dto);
			OutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=互斥项目查询.xlsx");
			response.setContentType("application/msexcel");
			try {
				wb.write(output);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		
		 //项目查询系统导出到excel
	    @RequestMapping("exportExcel")
	    public XSSFWorkbook exportExcel(HttpServletResponse response) throws IOException {
	        XSSFWorkbook wb =notExistsBlh.exportExcel();
	        OutputStream output = response.getOutputStream();
	        response.reset();
	        response.setHeader("Content-disposition", "attachment; filename=项目查询.xls");
	        response.setContentType("application/msexcel");
	        try {
	            wb.write(output);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                output.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return  null;

	    }
}
