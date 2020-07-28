package com.dhcc.piccbid.dto.findDict;

import java.util.List;
import com.dhcc.piccbid.entity.findDict.FindDict;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-08-29 10:26:51
 * @version V1.0
 */
public class FindDictDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FindDict findDict;
	private List<FindDict> findDicts;
	private FindDictVo findDictVo;
	private List<FindDictVo> findDictVos;
	private int page;
	private int rows;
	private String keyword;
	private String dictName;

	public FindDict getFindDict() {
		return findDict;
	}

	public void setFindDict(FindDict findDict) {
		this.findDict = findDict;
	}

	public List<FindDict> getFindDicts() {
		return findDicts;
	}

	public void setFindDicts(List<FindDict> findDicts) {
		this.findDicts = findDicts;
	}

	public FindDictVo getFindDictVo() {
		return findDictVo;
	}

	public void setFindDictVo(FindDictVo findDictVo) {
		this.findDictVo = findDictVo;
	}

	public List<FindDictVo> getFindDictVos() {
		return findDictVos;
	}

	public void setFindDictVos(List<FindDictVo> findDictVos) {
		this.findDictVos = findDictVos;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
	
}
