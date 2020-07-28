package com.dhcc.piccbid.dao.saiku;


import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.jdbc.JdbcTemplateWrapper;

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
@Component
public class SaikuJdbcDao {
	 @Resource
	 private JdbcTemplate jdbcTemplate;
	 @Resource
	 private JdbcTemplateWrapper jdbcTemplateWrapper;
	/**
	 * @param ip
	 */
	public void saveIp(String ip) {
		String sql="update  KS_LOGINSAIKU set id ='admin"+ip+"' where teket='1' ";
		jdbcTemplate.update(sql);
		
		
	}
	 
	
	
}
