package com.dhcc.piccbid.dao.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dhcc.framework.common.SpringContextHolder;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.entity.dict.Dict;
import com.dhcc.piccbid.entity.dict.DictRequestVo;
import com.dhcc.piccbid.entity.dict.DictResponseVo;
import com.dhcc.piccbid.service.unit.UnitService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 * 
 * @author CodeGenUtils
 * @version V1.0
 */
@Repository
public class DictJdbcDao extends HibernatePersistentObjectDAO<Dict> {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	@Autowired
	private UnitService unitService;

	
	@SuppressWarnings("unchecked")
	public List<DictResponseVo> findDict(DictRequestVo dictRequestVo) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		List<DictResponseVo> dictVos = new ArrayList<DictResponseVo>();
		String table = dictRequestVo.getTableName();
		//数据授权管控
		List<String> listCityCode=unitService.getUserDataAhthority();
		String cityCode="city_code";

		if (!StringUtils.isNullOrEmpty(table)) {
			
			 if("t_piccbid_dict_drug".equalsIgnoreCase(table)) {
					sqlStr.setLength(0);
					sqlStr.append("select distinct city_name as value,city_name as text  from T_PICCBID_DRUG where 1=1  and city_name is not null   ");
					sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);
					sqlStr.append("order by text");
				     }
			if ("t_piccbid_dict_city".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select distinct city_code as value,city_name as text from t_piccbid_dict_city where 1=1  order by text ");
			}else if("t_piccbid_dict_city_org".equalsIgnoreCase(table)){
				sqlStr.setLength(0);
				sqlStr.append("select id,org_code as value,org_name as text from t_piccbid_dict_city_org where 1=1 order by text ");
			}else if ("t_piccbid_dict".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select id,type as value,type as text from t_piccbid_dict where 1=1 order by type ");
			} else if ("t_piccbid_dictd".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select id,type as value,type as text from t_piccbid_dict where  state='1' order by type  ");
			} else if("t_piccbid_dict_city_xingzheng".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select distinct  city_adminarea as value,city_adminarea as text  from t_piccbid_dict_city where 1=1 and city_code <> 'DHCC' and city_adminarea is not null ");
				sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);
				sqlStr.append("order by text");
			}else if("t_piccbid_city_relation".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select distinct  city_name_left as value,city_name_left as text  from t_piccbid_city_relation where 1=1 ");
			}else if ("t_piccbid_dictCity".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				//sqlStr.append("select id,city_code as value,city_name as text from t_piccbid_dict_city where 1=1 order by city_name   ");
				sqlStr.append("select id,handding_ins_code as value,handding_ins_name as text from T_PICCBID_HANDDING_ORG_CODE where 1=1    ");
				String cityCode1="city_code,handding_ins_code";
				//新授权
				sqlStr=unitService.appendDataAuhoritySql(cityCode1,sqlStr,listCityCode);
				sqlStr.append(" order by handding_ins_name ");
			}
			else if("T_PICCBID_DICT_ICD".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select distinct type_name as value,type_name as text  from T_PICCBID_DICT_ICD where 1=1 order by type_name ");
			}
			else if("t_piccbid_dict_citydrug".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select id,city_name as value,city_name as text from t_piccbid_dict_city where 1=1 order by text ");
				
			}
			else if("T_PICCBID_MEDICAL".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select max(t.create_time) as value,min(t.create_time) as text from T_PICCBID_MEDICAL t where 1=1 order by text ");			
			}
			else if("T_PICCBID_MEDICAL_ORGNAME".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select distinct t.org_code as value,c.org_name as text from T_PICCBID_MEDICAL t left join t_piccbid_dict_city_org c on t.org_code = c.org_code  where 1=1 order by text ");			
			}
			else if("T_PICCBID_KNOWLEDGE".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append(" select distinct type_small as value  from T_PICCBID_KNOWLEDGE");			
			}
			else if("T_PICCBID_DICT_RULE".equalsIgnoreCase(table)) {
				sqlStr.setLength(0);
				sqlStr.append("select distinct rule_no as value,rule_name as text from T_PICCBID_DICT_RULE  where 1=1 and valid_flag='1'  order by rule_no ");			
			}else if("dataQuality".equalsIgnoreCase(table)) {
				if(dictRequestVo.getTtName()!=null) {
					sqlStr.setLength(0);
					String T_PICCBID_MEDICAL[] = {"ID","STATUS","SYS_STATUS","USER_STATUS","FINA_STATUS","AUDIT_STATUS",
							"DOCTOR_NAME","VISITING_CARD_NUMBER","SYS_AUDITING_DATE","ADUIT_STATUS","BALANCE_DATE",
							"MEDICAL_CLOSE_NUMBER","INSURE_PERSON_CODE","INSURE_PERSON_TYPE","SEE_DOC_TYPE","SEE_DOC_DETAIL",
							"DEPART_CODE","INSURANCE_CLOSE_DATE","HANDING_TIME","OUT_HOS_DEPART","OUT_HOS_DEPART_CODE","ATTENDING_DOC_CODE",
							"ATTENDING_DOC_TITLE","RESIDENT_DOC","HREAD_DEPART","DIRECTOR_DOC","CHARGE_NURSE","OUT_HOS_METHOD","OUT_HOS_METHOD_CODE",
							"IN_HOS_METHOD","IN_HOS_METHOD_CODE","OUT_HOS_SUMMARY","CHANGE_HOS","INSURE_COST","NOT_INSURE_COST","CLAIM_COST","RANGE_YEAR_COST",
							"HANDDING_PERSON","UPDATE_DATE","RESULT_APPEAL_STATU","OPERATION_TIME"};
					String T_PICCBID_MEDICAL_REGISTER[] = {"ID","CITY_CODE","HANDDING_INS_CODE","SEE_DOC_ID","UNIT_ID","INHOS_DATE","OUTHOS_DATE","BALANCE_DATE","BALANCE_ID","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_MEDICAL_DETAIL[] = {"ID","CREATE_DATE","UPDATE_DATE","BALANCE_DATE","RECIPEL_ID","ADVICE_ID","SEE_DOC_CODE"};
					String T_PICCBID_MEDICAL_ADVICE[] = {"ID","CITY_CODE","PERSON_ID","ID_ADVICE_INFO_DETAIL","ID_ADVICE_GROUP","ADVICE_GROUP_CODE","ORG_CODE","PROJECT_TYPE","OPEN_EXECUTE_TIME","STOP_EXECUTE_TIME","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_MEDICAL_DIAG[] = {"ID","CITY_CODE","BILL_NO","SEE_DOC_ID","HANDDING_INS_CODE","SEE_DOC_TIME","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_MEDICAL_OPERATION[] = {"ID","OPERATION_ID","HADDING_INS_CODE","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_SPECSICK_REG[] = {"ID","EVENT_ID","PERSON_ID","BEGIN_TIME","END_TIME","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_ZY_MEDICALRECORD[] = {"ID","BR_ID","RYSJ","CYSJ","ZKRQ","HXJSYSJ","PK_ID","SSJCZRQ7","SSJCZRQ6","SSJCZRQ5","SSJCZRQ4","SSJCZRQ3","SSJCZRQ2","SSJCZRQ1"};
					String T_PICCBID_DELETE[] = {"ID","WORK_ID","DELETE_DATE","CREATE_DATE","UPDATE_DATE"};
					if("T_PICCBID_MEDICAL".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL' ) b  where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL[i]+"'");
							if (i<T_PICCBID_MEDICAL.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL_REGISTER".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_REGISTER' ) b  where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_REGISTER.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_REGISTER[i]+"'");
							if (i<T_PICCBID_MEDICAL_REGISTER.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL_DETAIL".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_DETAIL' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_DETAIL.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_DETAIL[i]+"'");
							if (i<T_PICCBID_MEDICAL_DETAIL.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL_ADVICE".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_ADVICE' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_ADVICE.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_ADVICE[i]+"'");
							if (i<T_PICCBID_MEDICAL_ADVICE.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL_DIAG".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_DIAG' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_DIAG.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_DIAG[i]+"'");
							if (i<T_PICCBID_MEDICAL_DIAG.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL_OPERATION".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_OPERATION' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_OPERATION.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_OPERATION[i]+"'");
							if (i<T_PICCBID_MEDICAL_OPERATION.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_SPECSICK_REG".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_SPECSICK_REG' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_SPECSICK_REG.length; i++) {
							sqlStr.append("'"+T_PICCBID_SPECSICK_REG[i]+"'");
							if (i<T_PICCBID_SPECSICK_REG.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_ZY_MEDICALRECORD".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_ZY_MEDICALRECORD' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_ZY_MEDICALRECORD.length; i++) {
							sqlStr.append("'"+T_PICCBID_ZY_MEDICALRECORD[i]+"'");
							if (i<T_PICCBID_ZY_MEDICALRECORD.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_DELETE".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_DELETE' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_DELETE.length; i++) {
							sqlStr.append("'"+T_PICCBID_DELETE[i]+"'");
							if (i<T_PICCBID_DELETE.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_INSURED_PERSON".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_INSURED_PERSON' ) b where 1=1 order by b.comments");		
					}
				}
			}
			else if("logicRule".equalsIgnoreCase(table)) {
				if (dictRequestVo.getTtName()!=null) {
					sqlStr.setLength(0);
					String T_PICCBID_MEDICAL_REGISTER[] = {"ID","CITY_CODE","HANDDING_INS_CODE","SEE_DOC_ID","UNIT_ID","INHOS_DATE","OUTHOS_DATE","BALANCE_DATE","BALANCE_ID","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_MEDICAL[] = {"ID","STATUS","SYS_STATUS","USER_STATUS","FINA_STATUS","AUDIT_STATUS","PAYMENT_DATE","INHOS_DATE","OUTHOS_DATE","CREATE_TIME","SYS_AUDITING_DATE","ADUIT_STATUS","INSURANCE_CLOSE_DATE","HANDING_TIME","CREATE_DATE","UPDATE_DATE","OPERATION_TIME"};
					String T_PICCBID_MEDICAL_DETAIL[] = {"ID","FEE_CREATE_DATE","CREATE_DATE","UPDATE_DATE","BALANCE_DATE","MEDICAL_ID","RECIPEL_ID","ADVICE_ID","SEE_DOC_CODE"};
					String T_PICCBID_MEDICAL_ADVICE[] = {"ID","CITY_CODE","PERSON_ID","MEDICAL_ID","ID_ADVICE_INFO_DETAIL","ID_ADVICE_GROUP","ADVICE_GROUP_CODE","ORG_CODE","PROJECT_TYPE","OPEN_TIME","OPEN_EXECUTE_TIME","STOP_EXECUTE_TIME","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_MEDICAL_DIAG[] = {"ID","CITY_CODE","MEDICAL_ID","BILL_NO","SEE_DOC_ID","HANDDING_INS_CODE","SEE_DOC_TIME","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_MEDICAL_OPERATION[] = {"ID","OPERATION_TIME","OPER_BEGIN_TIME","OPER_END_TIME","OPERATION_ID","HADDING_INS_CODE","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_SPECSICK_REG[] = {"ID","EVENT_ID","PERSON_ID","BEGIN_TIME","END_TIME","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_ZY_MEDICALRECORD[] = {"ID","BR_ID","RYSJ","CYSJ","ZKRQ","HXJSYSJ","PK_ID","SSJCZRQ7","SSJCZRQ6","SSJCZRQ5","SSJCZRQ4","SSJCZRQ3","SSJCZRQ2","SSJCZRQ1"};
					String T_PICCBID_DELETE[] = {"ID","WORK_ID","DELETE_DATE","CREATE_DATE","UPDATE_DATE"};
					String T_PICCBID_DELETE_CODE[] = {"ID","BUSINESS_ID"};
					if ("T_PICCBID_MEDICAL_REGISTER".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_REGISTER' ) b  where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_REGISTER.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_REGISTER[i]+"'");
							if (i<T_PICCBID_MEDICAL_REGISTER.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL' ) b  where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL[i]+"'");
							if (i<T_PICCBID_MEDICAL.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL_DETAIL".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_DETAIL' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_DETAIL.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_DETAIL[i]+"'");
							if (i<T_PICCBID_MEDICAL_DETAIL.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL_ADVICE".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_ADVICE' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_ADVICE.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_ADVICE[i]+"'");
							if (i<T_PICCBID_MEDICAL_ADVICE.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL_DIAG".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_DIAG' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_DIAG.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_DIAG[i]+"'");
							if (i<T_PICCBID_MEDICAL_DIAG.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_MEDICAL_OPERATION".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_MEDICAL_OPERATION' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_MEDICAL_OPERATION.length; i++) {
							sqlStr.append("'"+T_PICCBID_MEDICAL_OPERATION[i]+"'");
							if (i<T_PICCBID_MEDICAL_OPERATION.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_SPECSICK_REG".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_SPECSICK_REG' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_SPECSICK_REG.length; i++) {
							sqlStr.append("'"+T_PICCBID_SPECSICK_REG[i]+"'");
							if (i<T_PICCBID_SPECSICK_REG.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_ZY_MEDICALRECORD".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_ZY_MEDICALRECORD' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_ZY_MEDICALRECORD.length; i++) {
							sqlStr.append("'"+T_PICCBID_ZY_MEDICALRECORD[i]+"'");
							if (i<T_PICCBID_ZY_MEDICALRECORD.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_DELETE".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_DELETE' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_DELETE.length; i++) {
							sqlStr.append("'"+T_PICCBID_DELETE[i]+"'");
							if (i<T_PICCBID_DELETE.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}else if ("T_PICCBID_DELETE_CODE".equals(dictRequestVo.getTtName())) {
						sqlStr.append("select distinct b.column_name as value,b.comments as text from (select * from   user_col_comments where Table_Name='T_PICCBID_DELETE_CODE' ) b where 1=1 and column_name not in(");		
						for (int i = 0; i < T_PICCBID_DELETE_CODE.length; i++) {
							sqlStr.append("'"+T_PICCBID_DELETE_CODE[i]+"'");
							if (i<T_PICCBID_DELETE_CODE.length-1) {
								sqlStr.append(",");
							}
						}
						sqlStr.append(") order by b.comments ");
					}
					
				}
			}
			if (!StringUtils.isNullOrEmpty(sqlStr.toString())) {
				JdbcTemplateWrapper umJdbcTemplateWrapper = new JdbcTemplateWrapper(
						(JdbcTemplate) SpringContextHolder
								.getBean("jdbcTemplate"),
						(DataSourceProperties) SpringContextHolder
								.getBean("dataSourceProperties"),
						(JpaProperties) SpringContextHolder
								.getBean("spring.jpa-org.springframework.boot.autoconfigure.orm.jpa.JpaProperties"));

				dictVos = umJdbcTemplateWrapper.queryAllMatchListWithParaMap(
						sqlStr.toString(), DictResponseVo.class, map);
			}
		}
		return dictVos;
	}
}
