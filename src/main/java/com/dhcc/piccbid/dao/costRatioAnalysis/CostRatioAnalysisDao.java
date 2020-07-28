package com.dhcc.piccbid.dao.costRatioAnalysis;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.costRatioAnalysis.CostRatioAnalysisDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * <p>标题: CostRatioAnalysisDao.java</p>
 * <p>业务描述:CostRatioAnalysisDao.java</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年10月18日
 * @version V1.0
 */
@Component
public class CostRatioAnalysisDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;

    /**
     * 方法名:getCostRatioAnalysis
     * 方法功能描述:查询住院明细表，计算占比
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年10月28日
     */
    public PageModel getCostRatioAnalysis(CostRatioAnalysisDto dto){
        StringBuilder sql = new StringBuilder();
        //旧版sql，注释掉，防止回退版本
//        sql.append(" select t6.T_BILLING_NO,t6.ITEM_COST_DRUG_ZB,t6.ITEM_COST_Medical_ZB,t6.ITEM_COST_AN_ZB from ( ");
//        sql.append(" select * from ( ");
//        sql.append(" select t3.T_BILLING_NO,t3.BILLING_NO,t3.ITEM_COST_DRUG_ZB,t2.ITEM_COST_Medical_ZB from (select T_BILLING_NO,t1.BILLING_NO,t1.ITEM_COST_DRUG_ZB ");
//        sql.append(" from (select BILLING_NO T_BILLING_NO from t_fly_medical_detail group by BILLING_NO) T ");
//        sql.append(" left join (select t_DRUG.BILLING_NO, round((t_DRUG.ITEM_COST / DRUG_zb.zb) * 100, 2) ITEM_COST_DRUG_ZB ");
//        sql.append(" from (select BILLING_NO, sum(decode(SIGN(ITEM_COST),-1,0,1,ITEM_COST)) ITEM_COST ");
//        sql.append(" from t_fly_medical_detail  where CHARGE_TYPE in ('西成药', '中草药') group by BILLING_NO) t_DRUG, ");
//        sql.append(" (select sum(decode(SIGN(ITEM_COST),-1,0,1,ITEM_COST)) zb from t_fly_medical_detail ");
//        sql.append(" where CHARGE_TYPE in ('西成药', '中草药')) DRUG_zb) t1 on t1.BILLING_NO = T.T_BILLING_NO) t3 ");
//        sql.append(" left join (select t_Medical.BILLING_NO,round((t_Medical.ITEM_COST / Medical_zb.zb) * 100, 2) ITEM_COST_Medical_ZB ");
//        sql.append(" from (select BILLING_NO, sum(decode(SIGN(ITEM_COST),-1,0,1,ITEM_COST)) ITEM_COST from t_fly_medical_detail ");
//        sql.append(" where CHARGE_TYPE in ('检查', '输血','检验', '麻醉','治疗','手术','护理') ");
//        sql.append(" group by BILLING_NO) t_Medical,(select sum(decode(SIGN(ITEM_COST),-1,0,1,ITEM_COST)) zb ");
//        sql.append(" from t_fly_medical_detail  where CHARGE_TYPE in ('检查', '输血','检验', '麻醉','治疗','手术','护理')) Medical_zb) t2 ");
//        sql.append(" on t2.BILLING_NO = t3.BILLING_NO ) t4   ");
//        sql.append(" left join (select t_an.BILLING_NO, ");
//        sql.append(" round((t_an.ITEM_COST / an_zb.zb) * 100, 2) ITEM_COST_AN_ZB ");
//        sql.append(" from (select BILLING_NO, sum(decode(SIGN(ITEM_COST),-1,0,1,ITEM_COST)) ITEM_COST ");
//        sql.append(" from t_fly_medical_detail ");
//        sql.append("  where CHARGE_TYPE in ('检查') group by BILLING_NO) t_an, ");
//        sql.append(" (select sum(decode(SIGN(ITEM_COST),-1,0,1,ITEM _COST)) zb ");
//        sql.append(" from t_fly_medical_detail ");
//        sql.append(" where CHARGE_TYPE in ('检查')) an_zb) t5 ");
//        sql.append(" on t5.BILLING_NO = t4.BILLING_NO ) t6 ");
        //拼接sql
        sql.append(" select t6.T_HISID,t6.ITEM_COST_DRUG_ZB,t6.ITEM_COST_Medical_ZB,t6.ITEM_COST_AN_ZB from ( ");
        sql.append(" select * from (select t3.T_HISID,t3.HISID,t3.ITEM_COST_DRUG_ZB,t2.ITEM_COST_Medical_ZB from  ");
        sql.append(" (select T_HISID,t1.HISID,t1.ITEM_COST_DRUG_ZB ");
        sql.append(" from (select HISID T_HISID ");
        sql.append(" from t_Fly_Detail_Inhos group by HISID) T ");
        sql.append(" left join (select t_DRUG.HISID, ");
        sql.append(" round((t_DRUG.ITEM_COST / DRUG_zb.zb) * 100, 2) ITEM_COST_DRUG_ZB ");
        sql.append(" from (select HISID, sum(decode(SIGN(COST),-1,0,1,COST)) ITEM_COST ");
        sql.append(" from t_Fly_Detail_Inhos ");
        sql.append(" where P_CATEGORY in ('西成药', '中草药') ");
        sql.append(" group by HISID) t_DRUG, ");
        sql.append(" (select sum(decode(SIGN(COST),-1,0,1,COST)) zb ");
        sql.append(" from t_Fly_Detail_Inhos where P_CATEGORY in ('西成药', '中草药')) DRUG_zb) t1 ");
        sql.append(" on t1.HISID = T.T_HISID   ) t3 ");
        sql.append(" left join (select t_Medical.HISID, ");
        sql.append(" round((t_Medical.ITEM_COST / Medical_zb.zb) * 100, 2) ITEM_COST_Medical_ZB ");
        sql.append(" from (select HISID, sum(decode(SIGN(COST),-1,0,1,COST)) ITEM_COST ");
        sql.append(" from t_Fly_Detail_Inhos where P_CATEGORY in ('检查', '输血','检验', '麻醉','治疗','手术','护理') ");
        sql.append(" group by HISID) t_Medical,(select sum(decode(SIGN(COST),-1,0,1,COST)) zb ");
        sql.append(" from t_Fly_Detail_Inhos where P_CATEGORY in ('检查', '输血','检验', '麻醉','治疗','手术','护理')) Medical_zb) t2 ");
        sql.append(" on t2.HISID = t3.HISID ) t4 ");
        sql.append(" left join (select t_an.HISID, ");
        sql.append(" round((t_an.ITEM_COST / an_zb.zb) * 100, 2) ITEM_COST_AN_ZB ");
        sql.append(" from (select HISID, sum(decode(SIGN(COST),-1,0,1,COST)) ITEM_COST ");
        sql.append(" from t_Fly_Detail_Inhos where P_CATEGORY in ('检查') ");
        sql.append(" group by HISID) t_an,(select sum(decode(SIGN(COST),-1,0,1,COST)) zb ");
        sql.append(" from t_Fly_Detail_Inhos where P_CATEGORY in ('检查')) an_zb) t5 ");
        sql.append(" on t5.HISID = t4.HISID ) t6 ");
        //根据t6.T_HISID 去计算总数 为了分页做准备
        int n = jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(),"t6.T_HISID", null);
        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
        //将list添加到dto的 PageData
        dto.getPageModel().setPageData(list);
        //将总数据量加入到dto的Totals
        dto.getPageModel().setTotals(n);
        //打印sql，方便出错调试
        System.out.println("住院费用明细占比计算:"+sql);
        //返回dto的PageModel
        return dto.getPageModel();
    }

    /**
     * 方法名:getCostRatioAnalysisMenz
     * 方法功能描述:查询门诊明细表，计算占比
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年10月28日
     */
    public PageModel getCostRatioAnalysisMenz(CostRatioAnalysisDto dto){
        StringBuilder sql = new StringBuilder();
        //拼接sql
        sql.append(" select t6.T_HISID,t6.ITEM_COST_DRUG_ZB,t6.ITEM_COST_Medical_ZB,t6.ITEM_COST_AN_ZB from ( ");
        sql.append(" select * from (select t3.T_HISID,t3.HISID,t3.ITEM_COST_DRUG_ZB,t2.ITEM_COST_Medical_ZB from  ");
        sql.append(" (select T_HISID,t1.HISID,t1.ITEM_COST_DRUG_ZB ");
        sql.append(" from (select HISID T_HISID ");
        sql.append(" from T_FLY_DETAIL_MENZ group by HISID) T ");
        sql.append(" left join (select t_DRUG.HISID, ");
        sql.append(" round((t_DRUG.ITEM_COST / DRUG_zb.zb) * 100, 2) ITEM_COST_DRUG_ZB ");
        sql.append(" from (select HISID, sum(decode(SIGN(COST),-1,0,1,COST)) ITEM_COST ");
        sql.append(" from T_FLY_DETAIL_MENZ ");
        sql.append(" where P_CATEGORY in ('西成药', '中草药') ");
        sql.append(" group by HISID) t_DRUG, ");
        sql.append(" (select sum(decode(SIGN(COST),-1,0,1,COST)) zb ");
        sql.append(" from T_FLY_DETAIL_MENZ where P_CATEGORY in ('西成药', '中草药')) DRUG_zb) t1 ");
        sql.append(" on t1.HISID = T.T_HISID   ) t3 ");
        sql.append(" left join (select t_Medical.HISID, ");
        sql.append(" round((t_Medical.ITEM_COST / Medical_zb.zb) * 100, 2) ITEM_COST_Medical_ZB ");
        sql.append(" from (select HISID, sum(decode(SIGN(COST),-1,0,1,COST)) ITEM_COST ");
        sql.append(" from T_FLY_DETAIL_MENZ where P_CATEGORY in ('检查', '输血','检验', '麻醉','治疗','手术','护理') ");
        sql.append(" group by HISID) t_Medical,(select sum(decode(SIGN(COST),-1,0,1,COST)) zb ");
        sql.append(" from T_FLY_DETAIL_MENZ where P_CATEGORY in ('检查', '输血','检验', '麻醉','治疗','手术','护理')) Medical_zb) t2 ");
        sql.append(" on t2.HISID = t3.HISID ) t4 ");
        sql.append(" left join (select t_an.HISID, ");
        sql.append(" round((t_an.ITEM_COST / an_zb.zb) * 100, 2) ITEM_COST_AN_ZB ");
        sql.append(" from (select HISID, sum(decode(SIGN(COST),-1,0,1,COST)) ITEM_COST ");
        sql.append(" from T_FLY_DETAIL_MENZ where P_CATEGORY in ('检查') ");
        sql.append(" group by HISID) t_an,(select sum(decode(SIGN(COST),-1,0,1,COST)) zb ");
        sql.append(" from T_FLY_DETAIL_MENZ where P_CATEGORY in ('检查')) an_zb) t5 ");
        sql.append(" on t5.HISID = t4.HISID ) t6 ");
        //根据t6.T_HISID 去计算总数 为了分页做准备
        int n = jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(),"t6.T_HISID", null);
        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
        //将list添加到dto的 PageData
        dto.getPageModel().setPageData(list);
        //将总数据量加入到dto的Totals
        dto.getPageModel().setTotals(n);
        System.out.println("门诊费用明细占比计算:"+sql);
        return dto.getPageModel();
    }

    /**
     * 方法名：toPageModelSql
     *方法功能描述：将sql转换为分页sql
     *@param:@sql 初始sql，pageNo  当前页数，pageSize  当前页面个数
     *@return:String
     *@Atuhor：王彤
     *@Create Date : 2019-10-18 10:48
     */
    public static String toPageModelSql(String sql,int pageNo,int pageSize){
        sql = " select * from (select a.*,rownum as num1 from ( "+sql+") a where rownum <= "+pageNo*pageSize+")"
                + " where num1 > "+(pageNo-1)*pageSize;
        return sql;
    }

}