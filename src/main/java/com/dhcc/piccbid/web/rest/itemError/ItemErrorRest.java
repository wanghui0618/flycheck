package com.dhcc.piccbid.web.rest.itemError;

import javax.annotation.Resource;

import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.*;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.itemError.ItemErrorBlh;
import com.dhcc.piccbid.dto.itemError.ItemErrorDto;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-28 09:13:42
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/itemError/itemError")
public class ItemErrorRest {

	@Resource
	private ItemErrorBlh itemErrorBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) ItemErrorDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(ItemErrorDto dto) {
		itemErrorBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public ItemErrorDto saveRest(@RequestBody ItemErrorDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public ItemErrorDto save(ItemErrorDto dto) {
		itemErrorBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public ItemErrorDto deleteRest(@RequestBody ItemErrorDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public ItemErrorDto delete(ItemErrorDto dto) {
		itemErrorBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public ItemErrorDto findByIdRest(@RequestBody ItemErrorDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public ItemErrorDto findById(ItemErrorDto dto) {
		itemErrorBlh.findById(dto);
		return dto;
	}


	@RequestMapping  (value = "listHop111", consumes = "application/json")
	public Page listHopRest(@RequestBody(required = false) ItemErrorDto dto) {
		return this.listHop(dto);
	}

	@RequestMapping("listHop111")
	public Page listHop(ItemErrorDto dto) {
		itemErrorBlh.listHop(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}


//	@GetMapping(value="listHop",consumes="application/json")
//	public Page listHopRest(@RequestBody(required=false) ItemErrorDto dto) {
//		return this.listHop(dto);
//	}
//	@PostMapping("listHop")
//	public Page listHop(ItemErrorDto dto) {
//		itemErrorBlh.listHop(dto);
//		Page page=new Page();
//		page.setData(dto.getPageModel().getPageData());
//		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		return page;
//	}
	@RequestMapping(value="listItem111",consumes="application/json")
	public PageModel listItemRest(@RequestBody(required=false) ItemErrorDto dto) {
		return this.listItem(dto);
	}
	@RequestMapping("listItem111")
	public PageModel listItem(ItemErrorDto dto) {
		itemErrorBlh.listItem(dto);
		return dto.getPageModel();
	}
}
