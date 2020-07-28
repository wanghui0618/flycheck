package com.dhcc.piccbid.web.rest.genderLimit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.dhcc.piccbid.dto.childrensDrugs.ChildrensDrugsDto;
import com.dhcc.piccbid.entity.page.Page;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.genderLimit.GenderLimitBlh;
import com.dhcc.piccbid.dto.genderLimit.GenderLimitDto;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2020-01-03 10:32:21
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/genderLimit/genderLimit")
public class GenderLimitRest {

	@Resource
	private GenderLimitBlh genderLimitBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) GenderLimitDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(GenderLimitDto dto) {
		genderLimitBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public GenderLimitDto saveRest(@RequestBody GenderLimitDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public GenderLimitDto save(GenderLimitDto dto) {
		genderLimitBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public GenderLimitDto deleteRest(@RequestBody GenderLimitDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public GenderLimitDto delete(GenderLimitDto dto) {
		genderLimitBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public GenderLimitDto findByIdRest(@RequestBody GenderLimitDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public GenderLimitDto findById(GenderLimitDto dto) {
		genderLimitBlh.findById(dto);
		return dto;
	}

	@RequestMapping("genderLimit")
	public Page genderLimit(GenderLimitDto dto) {
		genderLimitBlh.genderLimit(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("gather")
	public Page gather(GenderLimitDto dto) {
		genderLimitBlh.gather(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	//导出到excel
	@RequestMapping("exportExcel")
	public XSSFWorkbook exportExcel(HttpServletResponse response, GenderLimitDto dto) throws IOException {
		XSSFWorkbook wb =genderLimitBlh.exportExcel(dto);
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=genderLimit.xls");
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

	@PostMapping(value = "detail", consumes = "application/json")
	public Page detailRest(@RequestBody(required = false) GenderLimitDto dto) {
		return this.detaileTable(dto);
	}

	@RequestMapping("detail")
	public Page detaileTable(GenderLimitDto dto) {
		genderLimitBlh.detail(dto);
		Page page = new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}
}
