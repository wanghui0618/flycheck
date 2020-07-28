package com.dhcc.piccbid.web.rest.hospitalviolation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.hospitalviolation.HospitalViolationBlh;
import com.dhcc.piccbid.dto.hospitalviolation.HospitalViolationDto;
import com.dhcc.piccbid.entity.page.Page;
import com.dhcc.piccbid.entity.violationdetails.SysVerifyVo;
import com.dhcc.piccbid.entity.violationdetails.ViolationDetail;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-05-27 17:22:38
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/hospitalviolation/hospitalViolation")
public class HospitalViolationRest {

	@Resource
	private HospitalViolationBlh hospitalViolationBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) HospitalViolationDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(HospitalViolationDto dto) {
		hospitalViolationBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public HospitalViolationDto saveRest(@RequestBody HospitalViolationDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public HospitalViolationDto save(HospitalViolationDto dto) {
		hospitalViolationBlh.save(dto);
		return dto;
	}

	@PostMapping(value="delete",consumes="application/json")
	public HospitalViolationDto deleteRest(@RequestBody HospitalViolationDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public HospitalViolationDto delete(HospitalViolationDto dto) {
		hospitalViolationBlh.delete(dto);
		return dto;
	}

	@PostMapping(value="findById",consumes="application/json")
	public HospitalViolationDto findByIdRest(@RequestBody HospitalViolationDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public HospitalViolationDto findById(HospitalViolationDto dto) {
		hospitalViolationBlh.findById(dto);
		return dto;
	}
	//listVo()加载页面table
	@RequestMapping("listVo")
	public Page listVo(HospitalViolationDto dto) throws UnsupportedEncodingException {
		//转码
		if(dto.getHanddingInsName()!=null&&!"".equals(dto.getHanddingInsName())) {
			System.out.println(dto.getHanddingInsName());
			String handdingInsName = URLDecoder.decode(dto.getHanddingInsName(),"UTF-8");
			dto.setHanddingInsName(handdingInsName);
			System.out.println(handdingInsName);
		}
		hospitalViolationBlh.listVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	//医院违规，各类型分布
	@RequestMapping("violationSpread")
	public List<SysVerifyVo> violationSpread(HospitalViolationDto dto) throws UnsupportedEncodingException{
		if(dto.getHanddingInsName()!=null&&!"".equals(dto.getHanddingInsName())) {
			String handdingInsName = URLDecoder.decode(dto.getHanddingInsName(),"UTF-8");
			dto.setHanddingInsName(handdingInsName);
		}
		return hospitalViolationBlh.violationSpread(dto);
	}
	//导出到excel
		@RequestMapping("exportExcelToSelf")
		public XSSFWorkbook  exportExcelToSelf(HttpServletResponse response,String status,String handdingInsName) throws IOException {
			if(handdingInsName!=null&&!"".equals(handdingInsName)) {
				handdingInsName = URLDecoder.decode(handdingInsName,"UTF-8");
			}
			XSSFWorkbook wb =hospitalViolationBlh.exportExcelToSelf(status,handdingInsName);
			OutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=hospitalViolation.xls");
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
		
		//规则违规金额统计
		@RequestMapping("hopVio")
		public Page hopVio(HospitalViolationDto dto) throws UnsupportedEncodingException {
			hospitalViolationBlh.hopVio(dto);
			Page page = new Page();
			page.setData(dto.getPageModel().getPageData());
			//page.setCount(String.valueOf(dto.getPageModel().getTotals()));
			return page;
		}
		
		//医保项目违规金额统计
		@RequestMapping("medVio")
		public Page medVio(HospitalViolationDto dto) throws UnsupportedEncodingException {
			hospitalViolationBlh.medVio(dto);
			Page page = new Page();
			@SuppressWarnings("unchecked")
			List<ViolationDetail> list=(List<ViolationDetail>) dto.getPageModel().getPageData();
			for (ViolationDetail violationDetail : list) {
				if(violationDetail.getItemName()==null) {
					violationDetail.setItemName("其它");
				}else {
					violationDetail.setItemName(violationDetail.getItemName().trim());
				}
				
			}
			page.setData(list);
			//page.setCount(String.valueOf(dto.getPageModel().getTotals()));
			return page;
		}

}
