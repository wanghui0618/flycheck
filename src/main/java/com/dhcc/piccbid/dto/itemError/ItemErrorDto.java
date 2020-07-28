package com.dhcc.piccbid.dto.itemError;

import java.util.List;
import com.dhcc.piccbid.entity.itemError.ItemError;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-28 09:13:41
 * @version V1.0
 */
public class ItemErrorDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private ItemError itemError;
	private List<ItemError> itemErrors;

	public ItemError getItemError() {
		return itemError;
	}

	public void setItemError(ItemError itemError) {
		this.itemError = itemError;
	}

	public List<ItemError> getItemErrors() {
		return itemErrors;
	}

	public void setItemErrors(List<ItemError> itemErrors) {
		this.itemErrors = itemErrors;
	}
}
