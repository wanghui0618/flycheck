package com.dhcc.piccbid.web.rest.nursingViolation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.nursingViolation.NursingViolationBlh;
import com.dhcc.piccbid.dto.nursingViolation.NursingViolationDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-29 16:10:27
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/nursingViolation/nursingViolation")
public class NursingViolationRest {

	@Resource
	private NursingViolationBlh nursingViolationBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) NursingViolationDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(NursingViolationDto dto) {
		nursingViolationBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public NursingViolationDto saveRest(@RequestBody NursingViolationDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public NursingViolationDto save(NursingViolationDto dto) {
		nursingViolationBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public NursingViolationDto deleteRest(@RequestBody NursingViolationDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public NursingViolationDto delete(NursingViolationDto dto) {
		nursingViolationBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public NursingViolationDto findByIdRest(@RequestBody NursingViolationDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public NursingViolationDto findById(NursingViolationDto dto) {
		nursingViolationBlh.findById(dto);
		return dto;
	}
	
	@RequestMapping("nursingViolation")
	public Page nursingViolation(NursingViolationDto dto) {
		nursingViolationBlh.nursingViolation(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
}
