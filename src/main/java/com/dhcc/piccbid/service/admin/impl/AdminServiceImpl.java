package com.dhcc.piccbid.service.admin.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.admin.AdminDao;
import com.dhcc.piccbid.dao.admin.AdminDictJdbcDao;
import com.dhcc.piccbid.dao.admin.AdminJdbcDao;
import com.dhcc.piccbid.dao.admin.SuperviseRuleIndexJdbcDao;
import com.dhcc.piccbid.dto.admin.AdminDto;
import com.dhcc.piccbid.entity.admin.Admin;
import com.dhcc.piccbid.entity.admin.AdminDict;
import com.dhcc.piccbid.entity.admin.AdminVo;
import com.dhcc.piccbid.entity.admin.superviseRuleIndex;
import com.dhcc.piccbid.service.admin.AdminService;

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
@Service("adminService")
public class AdminServiceImpl extends AbstractBaseService<Admin, String> implements AdminService {
	@Resource
	private AdminDao adminDao;
	@Resource
	private CommonService commonService;
	@Resource
	private AdminJdbcDao adminJdbcDao;
	
	@Resource
	private SuperviseRuleIndexJdbcDao superviseRuleIndexJdbcDao;
	
	@Resource
	private AdminDictJdbcDao adminDictJdbcDao;

	public AdminServiceImpl(AdminDao adminDao) {
		super(adminDao);
		this.adminDao = adminDao;
	}
	
	@Override
	public PageModel listNumber(AdminDto dto) {
		adminJdbcDao.listNumber(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel IntegrityToday(AdminDto dto) {
		adminJdbcDao.IntegrityToday(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel TodayNumber(AdminDto dto) {
		adminJdbcDao.TodayNumber(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel InhosNumber(AdminDto dto) {
		adminJdbcDao.InhosNumber(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel AreaNumber(AdminDto dto) {
		adminJdbcDao.AreaNumber(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel YearData(AdminDto dto) {
		adminJdbcDao.YearData(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel QualityToday(AdminDto dto) {
		adminJdbcDao.QualityToday(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel RuleNumber(AdminDto dto) {
		superviseRuleIndexJdbcDao.RuleNumber(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel MonitorNumber(AdminDto dto) {
		superviseRuleIndexJdbcDao.MonitorNumber(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel ProvinceNumber(AdminDto dto) {
		superviseRuleIndexJdbcDao.ProvinceNumber(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel TcNumber(AdminDto dto) {
		superviseRuleIndexJdbcDao.TcNumber(dto);
		return dto.getPageModel();
	}
	
	@Override
	public PageModel AverageNumber(AdminDto dto) {
		superviseRuleIndexJdbcDao.AverageNumber(dto);
		return dto.getPageModel();
	}

	@Override
	public List<superviseRuleIndex> TcNumberDate() {
		// TODO Auto-generated method stub
		return superviseRuleIndexJdbcDao.TcNumberDate();
	}
	
	@Override
	public List<superviseRuleIndex> OutDate(AdminDto dto) {
		// TODO Auto-generated method stub
		return superviseRuleIndexJdbcDao.OutDate( dto);
	}
	
	@Override
	public List<AdminVo> findCityDict(AdminDto dto) {
		return adminDictJdbcDao.findCityDict(dto);
	}
	
	@Override
	public List<AdminVo> findCityOrgDict(AdminDto dto) {
		return adminDictJdbcDao.findCityOrgDict(dto);
	}
	
	@Override
	public List<AdminVo> findCityNameDict(AdminDto dto) {
		return adminDictJdbcDao.findCityNameDict(dto);
	}
	
	@Override
	public List<AdminVo> findOrgNameDict(AdminDto dto) {
		return adminDictJdbcDao.findOrgNameDict(dto);
	}
	
	@Override
	public List<AdminVo> medicalNameDict(AdminDto dto) {
		return adminDictJdbcDao.medicalNameDict(dto);
	}
	
	@Override
	public PageModel findDict(AdminDto dto) {
		adminDictJdbcDao.findDict(dto);
		//commonService.fillSqlPageModelData(dto.getPageModel(),AdminDict.class,"id");
		return dto.getPageModel();
	}

	@Override
	public superviseRuleIndex getName() {
		// TODO Auto-generated method stub
		return superviseRuleIndexJdbcDao.getName();
	}


	

}
