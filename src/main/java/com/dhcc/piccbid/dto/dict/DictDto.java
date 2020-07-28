package com.dhcc.piccbid.dto.dict;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.dict.Dict;
import com.dhcc.piccbid.entity.dict.DictRequestVo;
import com.dhcc.piccbid.entity.dict.DictResponseVo;


public class DictDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;
	
	private Dict dict;
	private List<Dict> dicts;
	private DictRequestVo dictRequestVo;
	private List<DictResponseVo> dictList;
	
	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}
	public List<Dict> getDicts() {
		return dicts;
	}
	public void setDicts(List<Dict> dicts) {
		this.dicts = dicts;
	}
	
	public DictRequestVo getDictRequestVo() {
		return dictRequestVo;
	}
	public void setDictRequestVo(DictRequestVo dictRequestVo) {
		this.dictRequestVo = dictRequestVo;
	}
	public List<DictResponseVo> getDictList() {
		return dictList;
	}
	public void setDictList(List<DictResponseVo> dictList) {
		this.dictList = dictList;
	}

	

}
