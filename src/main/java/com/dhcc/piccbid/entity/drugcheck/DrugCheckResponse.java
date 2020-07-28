package com.dhcc.piccbid.entity.drugcheck;

/**
* <p>标题: DrugCheckResponse.java</p>
* <p>业务描述:国家drg平台</p>
* <p>公司:东华软件股份公司</p>
* <p>版权:dhcc2018</p>
* @author gzw
* @date 2019年4月11日
* @version V1.0 
*/
public class DrugCheckResponse {
	
	private String status;
	private Data data;
	/**  
	* @return status 
	*/
	public String getStatus() {
		return status;
	}
	/**  
	* @param status status 
	*/
	public void setStatus(String status) {
		this.status = status;
	}
	/**  
	* @return data 
	*/
	public Data getData() {
		return data;
	}
	/**  
	* @param data data 
	*/
	public void setData(Data data) {
		this.data = data;
	}

	
}
