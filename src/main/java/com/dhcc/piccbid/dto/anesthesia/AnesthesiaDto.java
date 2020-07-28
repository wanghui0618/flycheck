/**
 * 
 */
package com.dhcc.piccbid.dto.anesthesia;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.anesthesia.AnesthesiaVo;

/**
 * @author zwy15
 *
 */
public class AnesthesiaDto extends BaseAbstractDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AnesthesiaVo anesthesiaVo;
	private List<AnesthesiaVo> anesthesias;
	/**
	 * @return the anesthesiaVo
	 */
	public AnesthesiaVo getAnesthesiaVo() {
		return anesthesiaVo;
	}
	/**
	 * @param anesthesiaVo the anesthesiaVo to set
	 */
	public void setAnesthesiaVo(AnesthesiaVo anesthesiaVo) {
		this.anesthesiaVo = anesthesiaVo;
	}
	/**
	 * @return the anesthesias
	 */
	public List<AnesthesiaVo> getAnesthesias() {
		return anesthesias;
	}
	/**
	 * @param anesthesias the anesthesias to set
	 */
	public void setAnesthesias(List<AnesthesiaVo> anesthesias) {
		this.anesthesias = anesthesias;
	}
	
	

}
