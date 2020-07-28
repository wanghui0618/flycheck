package com.dhcc.piccbid.web.rest.procedure;

import javax.annotation.Resource;

import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.procedure.ProcedureBlh;
import com.dhcc.piccbid.dto.procedure.ProcedureDto;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-06 11:48:54
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/procedure/procedure")
public class ProcedureRest {

	@Resource
	private ProcedureBlh procedureBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) ProcedureDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(ProcedureDto dto) {
		procedureBlh.list(dto);
		return dto.getPageModel();
	}


	@PostMapping(value="listVo",consumes="application/json")
	public Page listVoRest(@RequestBody(required=false) ProcedureDto dto) {
		return this.listVo(dto);
	}
	@RequestMapping("listVo")
	public Page listVo(ProcedureDto dto) {
		procedureBlh.listVo(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public ProcedureDto saveRest(@RequestBody ProcedureDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public ProcedureDto save(ProcedureDto dto) {
		procedureBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public ProcedureDto deleteRest(@RequestBody ProcedureDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public ProcedureDto delete(ProcedureDto dto) {
		procedureBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public ProcedureDto findByIdRest(@RequestBody ProcedureDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public ProcedureDto findById(ProcedureDto dto) {
		procedureBlh.findById(dto);
		return dto;
	}


	@PostMapping(value="execute",consumes="application/json")
	public Page executeRest(@RequestBody(required=false) ProcedureDto dto) {
		return this.execute(dto);
	}
	@RequestMapping("execute")
	public Page execute(ProcedureDto dto) {
		boolean success=procedureBlh.execute(dto);
		Page page=new Page();
		page.setMsg(String.valueOf(success));
		return page;
	}

	@PostMapping(value="executeAll",consumes="application/json")
	public Page executeAllRest(@RequestBody(required=false) ProcedureDto dto) {
		return this.executeAll(dto);
	}
	@RequestMapping("executeAll")
	public Page executeAll(ProcedureDto dto) {
		procedureBlh.executeAll(dto);
		Page page=new Page();
//		page.setData(dto.getPageModel().getPageData());
//		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="flag",consumes="application/json")
	public Page flagRest(@RequestBody(required=false) ProcedureDto dto) {
		return this.flag(dto);
	}
	@RequestMapping("flag")
	public Page flag(ProcedureDto dto) {
		boolean flag=procedureBlh.flag();
		Page page=new Page();
		page.setMsg(String.valueOf(flag));
		return page;
	}
	@PostMapping(value="check",consumes="application/json")
	public Page checkRest(@RequestBody(required=false) ProcedureDto dto) {
		return this.check(dto);
	}
	@RequestMapping("check")
	public Page check(ProcedureDto dto) {
		boolean flag=procedureBlh.check(dto);
		Page page=new Page();
		page.setMsg(String.valueOf(flag));
		return page;
	}


	@PostMapping(value="stop",consumes="application/json")
	public Page stopRest(@RequestBody(required=false) ProcedureDto dto) {
		return this.stop(dto);
	}
	@RequestMapping("stop")
	public Page stop(ProcedureDto dto) {
		boolean flag=procedureBlh.stop();
		Page page=new Page();
		page.setMsg(String.valueOf(flag));
		return page;
	}
}
