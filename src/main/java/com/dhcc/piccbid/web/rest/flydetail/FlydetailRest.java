package com.dhcc.piccbid.web.rest.flydetail;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.flydetail.FlydetailBlh;
import com.dhcc.piccbid.dto.flydetail.FlydetailDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 14:19:36
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/flydetail/flydetail")
public class FlydetailRest {

	@Resource
	private FlydetailBlh flydetailBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) FlydetailDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(FlydetailDto dto) {
		flydetailBlh.list(dto);
		return dto.getPageModel();
	}
	
	@RequestMapping("listVo")
	public Page listVo(FlydetailDto dto) {
		flydetailBlh.listVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public FlydetailDto saveRest(@RequestBody FlydetailDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlydetailDto save(FlydetailDto dto) {
		flydetailBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlydetailDto deleteRest(@RequestBody FlydetailDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlydetailDto delete(FlydetailDto dto) {
		flydetailBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlydetailDto findByIdRest(@RequestBody FlydetailDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlydetailDto findById(FlydetailDto dto) {
		flydetailBlh.findById(dto);
		return dto;
	}
}
