package com.dhcc.piccbid.entity.medicalAnalysis;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
* <p>标题: MedicalAnalysis.java</p>
* <p>业务描述:基层医疗卫生开发平台</p>
* <p>公司:东华软件股份公司</p>
* <p>版权:dhcc2013</p>
* @author 姚凯
* @date 2019年8月8日
* @version V1.0 
*/

@Entity
public class MedicalAnalysis {
	@Id
	private int id;

	/**  
	* @return id 
	*/
	public int getId() {
		return id;
	}

	/**  
	* @param id id 
	*/
	public void setId(int id) {
		this.id = id;
	}
	
	
}
