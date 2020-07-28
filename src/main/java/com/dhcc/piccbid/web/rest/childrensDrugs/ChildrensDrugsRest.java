package com.dhcc.piccbid.web.rest.childrensDrugs;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.dhcc.piccbid.entity.page.Page;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.childrensDrugs.ChildrensDrugsBlh;
import com.dhcc.piccbid.dto.childrensDrugs.ChildrensDrugsDto;

import java.io.IOException;
import java.io.OutputStream;


/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2019-11-23 13:32:56
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/childrensDrugs/childrensDrugs")
public class ChildrensDrugsRest {

	@Resource
	private ChildrensDrugsBlh childrensDrugsBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) ChildrensDrugsDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(ChildrensDrugsDto dto) {
		childrensDrugsBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public ChildrensDrugsDto saveRest(@RequestBody ChildrensDrugsDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public ChildrensDrugsDto save(ChildrensDrugsDto dto) {
		childrensDrugsBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public ChildrensDrugsDto deleteRest(@RequestBody ChildrensDrugsDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public ChildrensDrugsDto delete(ChildrensDrugsDto dto) {
		childrensDrugsBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public ChildrensDrugsDto findByIdRest(@RequestBody ChildrensDrugsDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public ChildrensDrugsDto findById(ChildrensDrugsDto dto) {
		childrensDrugsBlh.findById(dto);
		return dto;
	}

	@RequestMapping("childrensDrugs")
	public Page childrensDrugs(ChildrensDrugsDto dto) {
		childrensDrugsBlh.childrensDrugs(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@RequestMapping("gather")
	public Page gather(ChildrensDrugsDto dto) {
		childrensDrugsBlh.gather(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	//导出到excel
	@RequestMapping("exportExcel")
	public XSSFWorkbook exportExcel(HttpServletResponse response,ChildrensDrugsDto dto) throws IOException {
		XSSFWorkbook wb =childrensDrugsBlh.exportExcel(dto);
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=childrensDrugs.xls");
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
	public Page detailRest(@RequestBody(required = false) ChildrensDrugsDto dto) {
		return this.detaileTable(dto);
	}

	@RequestMapping("detail")
	public Page detaileTable(ChildrensDrugsDto dto) {
		childrensDrugsBlh.detail(dto);
		Page page = new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}

}
