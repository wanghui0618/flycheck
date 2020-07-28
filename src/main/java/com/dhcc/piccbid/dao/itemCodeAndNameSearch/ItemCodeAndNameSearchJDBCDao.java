package com.dhcc.piccbid.dao.itemCodeAndNameSearch;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.itemCodeAndNameSearch.ItemCodeAndNameSearchDto;
import com.dhcc.piccbid.entity.itemCodeAndNameSearch.ItemInfoVO;
import com.dhcc.piccbid.entity.notExists.NotExistsDetail;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author yangsx
 * @version V1.0
 * @ClassName ItemCodeAndNameSearchJDBCDao
 * @date 2019/12/12 16:39
 */
@Component
public class ItemCodeAndNameSearchJDBCDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;
    
    private static String exportsql;
    /***
     * 根据项目编码或名称  查询项目信息 （名称模糊查询，编码精确查询）
     * @param dto
     */
    public void getItemInfoByCodeOrName(ItemCodeAndNameSearchDto dto) {
        PageModel pageModel = dto.getPageModel();
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();

        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }

        String queryType = dto.getQueryType();          //医保还是医院
        String queryItemType = dto.getQueryItemType();  // 编码还是名称
        String queryItemInfo = dto.getQueryItemInfo();  //查询信息

        String queryTypeInfo = "";

        if (queryItemInfo != null & !"".equals(queryItemInfo)) {
            if ("cent".equals(queryType)) {
                if ("code".equals(queryItemType)) {
                    queryTypeInfo = " and ITEM_ID = '" + queryItemInfo + "' ";
                }
                if ("name".equals(queryItemType)) {
//                    queryTypeInfo = " and item_name like '%" + queryItemInfo + "%' ";
                    queryTypeInfo=" and  instr(item_name,'"+ queryItemInfo +"')>0 ";
                }
            }
            if ("hosp".equals(queryType)) {
                if ("code".equals(queryItemType)) {
                    queryTypeInfo = " and item_id_hosp = '" + queryItemInfo + "' ";
                }
                if ("name".equals(queryItemType)) {
//                    queryTypeInfo = " and item_name_hosp like '%" + queryItemInfo + "%' ";
                    queryTypeInfo=" and  instr(item_name_hosp,'"+ queryItemInfo +"')>0 ";
                }
            }
        }
        sqlStr.append("select item_id_hosp, item_name_hosp, item_id, item_name\n" +
                "          from t_piccbid_iteminfo\n" +
                "         where 1=1 \n" + queryTypeInfo + "\n" +
                "         group by item_id_hosp, item_name_hosp, item_id, item_name\n" +
                "        ");

        dto.getPageModel().setHqlParamMap(hqlParamMap);
        exportsql=sqlStr.toString();
        List<ItemInfoVO> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), ItemInfoVO.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "item_Id");
        String sqlString = "select count(1) from (" + sqlStr.toString() + ")";
        int count = jdbcTemplateWrapper.queryForInt(sqlString, null);
        dto.getPageModel().setTotals(count);
        dto.getPageModel().setPageData(list);
    }
    
  //导出到excel
    public XSSFWorkbook exportExcel() {
        //获取数据
        StringBuilder sqlStr = new StringBuilder();
        XSSFWorkbook wb = new XSSFWorkbook();
        sqlStr.append(exportsql);
        // List<PersonalInformationInquiryVo> personalInformationInquiryVoList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), PersonalInformationInquiryVo.class, null);
        List<ItemInfoVO> overclockingItemVoDetailList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),ItemInfoVO.class,null);
        XSSFSheet sheet2= wb.createSheet("查询结果");
        // 在sheet里创建第一行
        XSSFRow DetailRow1 = sheet2.createRow(0);
        // 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)
        DetailRow1.createCell(0).setCellValue("医院项目编码");
        DetailRow1.createCell(1).setCellValue("医院项目名称");
        DetailRow1.createCell(2).setCellValue("医保项目编码");
        DetailRow1.createCell(3).setCellValue("医保项目名称");

        for (int i = 0; i < overclockingItemVoDetailList.size(); i++) {
            XSSFRow rowNum = sheet2.createRow(i + 1);
            rowNum.createCell(0).setCellValue(overclockingItemVoDetailList.get(i).getItemIdHosp());
            rowNum.createCell(1).setCellValue(overclockingItemVoDetailList.get(i).getItemIdHosp());
            rowNum.createCell(2).setCellValue(overclockingItemVoDetailList.get(i).getItemId());
            rowNum.createCell(3).setCellValue(overclockingItemVoDetailList.get(i).getItemName());
        }

        return wb;
    }

}
