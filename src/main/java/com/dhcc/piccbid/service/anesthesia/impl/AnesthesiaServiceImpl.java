package com.dhcc.piccbid.service.anesthesia.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.anesthesia.AnesthesiaJdbcDao;
import com.dhcc.piccbid.dto.anesthesia.AnesthesiaDto;
import com.dhcc.piccbid.entity.anesthesia.AnesthesiaVo;
import com.dhcc.piccbid.service.anesthesia.AnesthesiaService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ll
 * @date 2019-04-11 15:28:39
 * @version V1.0
 */
@Service("anesthesiaService")
public class AnesthesiaServiceImpl implements AnesthesiaService {
	@Resource
	private AnesthesiaJdbcDao anesthesiaJdbcDao;
	@Resource
	private CommonService commonService;

	/* (non-Javadoc)
	 * @see com.dhcc.piccbid.service.anesthesia.AnesthesiaService#listVo(com.dhcc.piccbid.dto.anesthesia.AnesthesiaDto)
	 */
	@Override
	public PageModel listVo(AnesthesiaDto dto) {
		anesthesiaJdbcDao.listVo(dto);
		commonService.fillSqlPageModelData(dto.getPageModel(),AnesthesiaVo.class,"b.HISID");
		return dto.getPageModel();
	}
	
	
	
	
}
