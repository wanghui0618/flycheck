/**
 * 
 */
package com.dhcc.piccbid.dao.findDict;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.findDict.FindDictDto;
import com.dhcc.piccbid.service.unit.UnitService;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @version V1.0
 * @date 2019-08-29 10:35:50
 */
@Component
public class FindDictJdbcDao {

	private static Log logger = LogFactory.getLog(FindDictJdbcDao.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	@Autowired
	private UnitService unitService;

	public void findDict(FindDictDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		String dictName = dto.getDictName();
		// List<String> listCityCode=unitService.getUserDataAhthority();
		String cityCode = null;
		if (dictName.equals("org")) {
			// cityCode="city_code,org_code";
			sqlStr.append("select org_name as name,org_code as code from t_piccbid_org where 1=1 ");
		} else if (dictName.equals("handding")) {
			// cityCode="city_code,handding_ins_code";
			sqlStr.append(
					"select handding_ins_name as name,handding_ins_code as code from t_piccbid_handding_org_code where 1=1 ");
		} else if (dictName.equals("diag")) {
			sqlStr.append("select diag_name as name,diag_code as code from t_piccbid_diag where 1=1 ");
		} else if (dictName.equals("itemid")) {
			sqlStr.append("select item_name as name,item_id as code from T_PICCBID_ITEMINFO where 1=1 ");
		} else if (dictName.equals("itemname")) {
			sqlStr.append("select item_name as name,item_id as code from T_PICCBID_ITEMINFO where 1=1 ");
		}
		// 调用数据授权
		// sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);
		String keyword = dto.getKeyword();
		if (dictName.equals("org")) {
			if (keyword != null && !"".equals(keyword)) {
				sqlStr.append(" and org_name like '%" + keyword + "%'");
			}
			sqlStr.append(" order by org_code ");
		} else if (dictName.equals("handding")) {
			if (keyword != null && !"".equals(keyword)) {
				sqlStr.append(" and handding_ins_name like '%" + keyword + "%'");
			}
		} else if (dictName.equals("diag")) {
			if (keyword != null && !"".equals(keyword)) {
				sqlStr.append(" and diag_name like '%" + keyword + "%'");
			}
		} else if (dictName.equals("itemid")) {
			if (keyword != null && !"".equals(keyword)) {
				sqlStr.append(" and item_id like '%" + keyword + "%'");
			}
		} else if (dictName.equals("itemname")) {
			if (keyword != null && !"".equals(keyword)) {
				sqlStr.append(" and item_name like '%" + keyword + "%'");
			}
		}
		
		dto.getPageModel().setQueryHql(sqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
	}

	// 费用类型下拉框
	public void fyType(FindDictDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		String keyword = dto.getKeyword();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append(
				"select * from T_FLYCHECK_P_CATEGORY t");
//						"select * from (select P_CATEGORY as name from T_FLYCHECK_MEDICAL_DETAIL where P_CATEGORY is not null group by P_CATEGORY)");
		if (keyword != null && !"".equals(keyword)) {
			sqlStr.append(" where name like '%" + keyword + "%'");
		}
		dto.getPageModel().setQueryHql(sqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
	}

	public void findpCategory(FindDictDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		String keyword = dto.getKeyword();
		
			sqlStr.append(
					"select name from T_FLYCHECK_P_CATEGORY where 1=1 ");
		if(keyword!=null && !keyword.equals("")) {
			sqlStr.append(" and name like '%"+keyword+"%'");
		}
		dto.getPageModel().setQueryHql(sqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		
	}
}
