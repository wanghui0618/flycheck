package com.dhcc.piccbid.dto.itemCodeAndNameSearch;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.itemCodeAndNameSearch.ItemInfoVO;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author Yangsx
 * @version V1.0
 * @date 2019-12-12 16:18:53
 */
public class ItemCodeAndNameSearchDto extends BaseAbstractDto {

    private static final long serialVersionUID = 1L;
    private String queryType;
    private String queryItemInfo;
    private String queryItemType;
    private List<ItemInfoVO> itemInfoVOs;
    private ItemInfoVO itemInfoVO;

	public String getQueryItemInfo() {
		return queryItemInfo;
	}

	public void setQueryItemInfo(String queryItemInfo) {
		this.queryItemInfo = queryItemInfo;
	}

	public String getQueryItemType() {
		return queryItemType;
	}

	public void setQueryItemType(String queryItemType) {
		this.queryItemType = queryItemType;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public List<ItemInfoVO> getItemInfoVOs() {
		return itemInfoVOs;
	}

	public void setItemInfoVOs(List<ItemInfoVO> itemInfoVOs) {
		this.itemInfoVOs = itemInfoVOs;
	}

	public ItemInfoVO getItemInfoVO() {
		return itemInfoVO;
	}

	public void setItemInfoVO(ItemInfoVO itemInfoVO) {
		this.itemInfoVO = itemInfoVO;
	}
}
