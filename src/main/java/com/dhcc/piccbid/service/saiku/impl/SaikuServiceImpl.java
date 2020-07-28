package com.dhcc.piccbid.service.saiku.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

//import com.dhcc.piccbid.dao.ruletype.RuleTypeJdbcDao;
import com.dhcc.piccbid.dao.saiku.SaikuJdbcDao;
import com.dhcc.piccbid.service.saiku.SaikuService;

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
@Service("saikuService")
public class SaikuServiceImpl implements SaikuService {
	@Resource
	private SaikuJdbcDao saikuJdbcDao;
	/* (non-Javadoc)
	 * @see com.dhcc.piccbid.service.saiku.SaikuService#saveIp(java.lang.String)
	 */
	@Override
	public void saveIp(String ip) {
		saikuJdbcDao.saveIp(ip);
		
	}
	
	
}
