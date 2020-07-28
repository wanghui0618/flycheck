package com.dhcc.piccbid.web.rest.findDict;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.findDict.FindDictBlh;
import com.dhcc.piccbid.dto.findDict.FindDictDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-08-29 10:26:53
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/findDict/findDict")
public class FindDictRest {

	@Resource
	private FindDictBlh findDictBlh;

	@PostMapping(value = "list", consumes = "application/json")
	public PageModel listRest(@RequestBody(required = false) FindDictDto dto) {
		return this.list(dto);
	}

	@RequestMapping("list")
	public PageModel list(FindDictDto dto) {
		findDictBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value = "save", consumes = "application/json")
	public FindDictDto saveRest(@RequestBody FindDictDto dto) {
		return this.save(dto);
	}

	@PostMapping("save")
	public FindDictDto save(FindDictDto dto) {
		findDictBlh.save(dto);
		return dto;
	}

	@PostMapping(value = "delete", consumes = "application/json")
	public FindDictDto deleteRest(@RequestBody FindDictDto dto) {
		return this.delete(dto);
	}

	@PostMapping("delete")
	public FindDictDto delete(FindDictDto dto) {
		findDictBlh.delete(dto);
		return dto;
	}

	@PostMapping(value = "findById", consumes = "application/json")
	public FindDictDto findByIdRest(@RequestBody FindDictDto dto) {
		return this.findById(dto);
	}

	@PostMapping("findById")
	public FindDictDto findById(FindDictDto dto) {
		findDictBlh.findById(dto);
		return dto;
	}

	// 分页查询字典
	@RequestMapping("findDict")
	public Map<String, Object> findDict(FindDictDto dto) {
		return findDictBlh.findDict(dto);
	}

	@RequestMapping("fyType")
	public Map<String, Object> fyType(FindDictDto dto) {
		return findDictBlh.fyType(dto);
	}
	
	
	@RequestMapping("findpCategory")
	public Map<String, Object> findpCategory(FindDictDto dto) {
		return findDictBlh.findpCategory(dto);
	}
}
