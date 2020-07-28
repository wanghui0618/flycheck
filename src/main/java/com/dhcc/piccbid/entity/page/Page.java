package com.dhcc.piccbid.entity.page;

import java.util.List;

/**
* <p>标题: Page.java</p>
* <p>业务描述:国家drg平台</p>
* <p>公司:东华软件股份公司</p>
* <p>版权:dhcc2018</p>
* @author gzw
* @date 2019年1月9日
* @version V1.0 
*/
public class Page{
	  private Integer code = 0;
	  
	  private String msg = "";
	  
	  private String count;
	  
	  private List data;

	  
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**  
	* @return code 
	*/
	public Integer getCode() {
		return code;
	}

	/**  
	* @param code code 
	*/
	public void setCode(Integer code) {
		this.code = code;
	}

	/**  
	* @return msg 
	*/
	public String getMsg() {
		return msg;
	}

	/**  
	* @param msg msg 
	*/
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**  
	* @return count 
	*/
	public String getCount() {
		return count;
	}

	/**  
	* @param count count 
	*/
	public void setCount(String count) {
		this.count = count;
	}

	/**  
	* @return data 
	*/
	public List getData() {
		return data;
	}

	/**  
	* @param data data 
	*/
	public void setData(List data) {
		this.data = data;
	}

	
	
	  
}
