/**
 * 
 */
package com.dhcc.piccbid.dao.dictdiag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.dhcc.framework.common.PageModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
//import com.dhcc.piccbid.dto.consumablerule.ConsumableRuleDto;
import com.dhcc.piccbid.dto.dictdiag.DictdiagDto;
//import com.dhcc.piccbid.entity.cityrelation.CityRelation;
//import com.dhcc.piccbid.entity.consumablerule.ConsumableInfo;
//import com.dhcc.piccbid.entity.consumablerule.ConsumableRule;
//import com.dhcc.piccbid.entity.consumablerule.ConsumableVol;
import com.dhcc.piccbid.entity.dictdiag.Dictdiag;



/**
 * @author Arch
 *
 */
@Repository
public class DictdiagJdbcDao extends HibernatePersistentObjectDAO<Dictdiag> {
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	@Resource
	private JdbcTemplate jdbcTemplate;

	private static Log logger = LogFactory.getLog(DictdiagDto.class);


	/**
	 * @param dto
	 */
	public void list(DictdiagDto dto) {
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("SELECT * FROM T_PICCBID_DICT_DIAG where PARENT_LEAF='1' ");
		Dictdiag Dictdiag = dto.getDictdiag();
		if(Dictdiag!=null){
			if(Dictdiag.getDiagName()!=null && !"".equals(Dictdiag.getDiagName())) {
				sqlStr.append(" and diag_name='"+Dictdiag.getDiagName()+"'");
			}else if (Dictdiag.getDiagCode()!=null && !"".equals(Dictdiag.getDiagCode())) {
				sqlStr.append(" and diag_code='"+Dictdiag.getDiagCode()+"'");
			}
		}
		sqlStr.append(" order by id asc");
		dto.getPageModel().setQueryHql(sqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		
	}
	
	public void listSecondVo(DictdiagDto dto) {
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("SELECT * FROM T_PICCBID_DICT_DIAG where PARENT_LEAF='2' and PARENT_Id='"+dto.getDictdiag().getParentId()+"'");
		Dictdiag Dictdiag = dto.getDictdiag();
		if(Dictdiag!=null){
			if(Dictdiag.getDiagName()!=null && !"".equals(Dictdiag.getDiagName())) {
				sqlStr.append(" and diag_name='"+Dictdiag.getDiagName()+"'");
			}
			if(Dictdiag.getDiagCode()!=null && !"".equals(Dictdiag.getDiagCode())) {
				sqlStr.append(" and diag_code='"+Dictdiag.getDiagCode()+"'");
			}
		}
		sqlStr.append(" order by diag_code asc");
		dto.getPageModel().setQueryHql(sqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		
	}

	public void ztreeDiag(DictdiagDto dictdiagDto){
		StringBuilder sqlStr=new StringBuilder();
		String index="";
		if(dictdiagDto.getDictdiag()!=null&&!"".equals(dictdiagDto.getDictdiag())){
			index =dictdiagDto.getDictdiag().getParentIndex();
		}
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		PageModel pageModel=new PageModel();
		dictdiagDto.setPageModel(pageModel);
		dictdiagDto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select ID,DIAG_CODE,DIAG_NAME,PARENT_ID，PARENT_LEAF,DIAG_DESC,PARENT_INDEX from t_piccbid_dict_diag");
		if(!"".equals(index)||null==index) {
			sqlStr.append(" where parent_index='" + index + "'");
		}
		sqlStr.append("  order by id asc");
		System.out.println(sqlStr);
		dictdiagDto.getPageModel().setQueryHql(sqlStr.toString());
		dictdiagDto.getPageModel().setHqlParamMap(hqlParamMap);
	}
	



}
