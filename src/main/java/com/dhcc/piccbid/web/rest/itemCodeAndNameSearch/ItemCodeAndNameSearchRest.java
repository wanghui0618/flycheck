package com.dhcc.piccbid.web.rest.itemCodeAndNameSearch;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.dhcc.piccbid.entity.page.Page;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.piccbid.blh.itemCodeAndNameSearch.ItemCodeAndNameSearchBlh;
import com.dhcc.piccbid.dto.itemCodeAndNameSearch.ItemCodeAndNameSearchDto;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author Yangsx
 * @date 2019-12-12 16:18:53
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/itemCodeAndNameSearch/itemCodeAndNameSearch")
public class ItemCodeAndNameSearchRest {

	@Resource
	private ItemCodeAndNameSearchBlh itemCodeAndNameSearchBlh;

	/**
	 * 根据项目编码或名称  查询项目信息 （名称模糊查询，编码精确查询）
	 */
	@RequestMapping("getItemInfoByCodeOrName")
	public Page getItemInfoByCodeOrName(ItemCodeAndNameSearchDto dto) {
		itemCodeAndNameSearchBlh.getItemInfoByCodeOrName(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value = "getItemInfoByCodeOrName", consumes = "application/json")
	public Page getItemInfoByCodeOrNameRest(@RequestBody(required = false) ItemCodeAndNameSearchDto dto) {
		return this.getItemInfoByCodeOrName(dto);
	}

	 //项目查询系统导出到excel
    @RequestMapping("exportExcel")
    public XSSFWorkbook exportExcel(HttpServletResponse response) throws IOException {
        XSSFWorkbook wb =itemCodeAndNameSearchBlh.exportExcel();
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=项目编码和名称互查.xls");
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
