/**
 * 
 */
package com.dhcc.piccbid.entity.admin;

import java.io.Serializable;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @version V1.0
 * @date 2019-08-14 05:06:10
 */
public class AdminVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String value;
	private String text;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	

}
