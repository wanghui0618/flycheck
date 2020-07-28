package com.dhcc.piccbid.web.rest.dict;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.dict.DictBlh;
import com.dhcc.piccbid.dto.dict.DictDto;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-15 09:58:13
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/dict/dict")
public class DictRest {
	

	@Resource
	private DictBlh dictBlh;

	@RequestMapping("findDict")
    public DictDto findDict(DictDto dto) { 
	    this.dictBlh.findDict(dto);
        return dto;
    }
	
	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) DictDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(DictDto dto) {
		dictBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public DictDto saveRest(@RequestBody DictDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public DictDto save(DictDto dto) {
		dictBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public DictDto deleteRest(@RequestBody DictDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public DictDto delete(DictDto dto) {
		dictBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public DictDto findByIdRest(@RequestBody DictDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public DictDto findById(DictDto dto) {
		dictBlh.findById(dto);
		return dto;
	}
}
