/**
 * 
 */
package com.dhcc.piccbid.entity.findDict;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @version V1.0
 * @date 2019-08-29 10:28:31
 */
@Entity
public class FindDict implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
