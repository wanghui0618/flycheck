package com.dhcc.piccbid.service.admin.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.admin.AdminDao;
import com.dhcc.piccbid.dao.admin.BigDataJdbcDao;
import com.dhcc.piccbid.dto.admin.AdminDto;
import com.dhcc.piccbid.entity.admin.Admin;
import com.dhcc.piccbid.entity.admin.superviseRuleIndex;
import com.dhcc.piccbid.service.admin.BigdataService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-08-02 10:27:08
 * @version V1.0
 */
@Service("bigdataService")
public class BigdataServiceImpl extends AbstractBaseService<Admin, String> implements BigdataService {
	@Resource
	private AdminDao adminDao;
	@Resource
	private CommonService commonService;
	@Resource
	private BigDataJdbcDao bigDataJdbcDao;

	public BigdataServiceImpl(AdminDao adminDao) {
		super(adminDao);
		this.adminDao = adminDao;
	}
	
	@Override
	public PageModel AverageNumber(AdminDto dto) {
		bigDataJdbcDao.AverageNumber(dto);
		return dto.getPageModel();
	}
	
	@Override
	public List<superviseRuleIndex> OutDate(AdminDto dto) {
		// TODO Auto-generated method stub
		return bigDataJdbcDao.OutDate( dto);
	}
	

	@Override
	public superviseRuleIndex getName() {
		// TODO Auto-generated method stub
		return bigDataJdbcDao.getName();
	}


	

}
