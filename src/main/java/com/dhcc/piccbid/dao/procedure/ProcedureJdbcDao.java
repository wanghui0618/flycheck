package com.dhcc.piccbid.dao.procedure;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.procedure.ProcedureDto;
import com.dhcc.piccbid.entity.procedure.Killer;
import com.dhcc.piccbid.entity.procedure.Procedure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class ProcedureJdbcDao {
    private static Boolean stopAll=false;
    private static Map<String,String> workingTable=new HashMap<String,String>();
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;
    public void listVo(ProcedureDto dto){
        PageModel page=dto.getPageModel();
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        StringBuilder sql=new StringBuilder();
        dto.getPageModel().setHqlParamMap(hqlParamMap);
//        sql.append("select pd_name,pd_module,pd_xm,pd_code,pd_desc,decode(type,'1','统计','0','机审') as type,decode(status,'1','可用','0','不可用') as status,pd_table,running_type from t_piccbid_Procedure where 1=1 and id != ('flag')");
        sql.append("select * from t_piccbid_Procedure where 1=1 and id != ('flag')");
        if(dto.getProcedure()!=null){
            if(dto.getProcedure().getPdName()!=null&&!dto.getProcedure().getPdName().equals("")){
                String pdName=dto.getProcedure().getPdName();
                sql.append("and pd_Name like '%"+pdName+"%'");
            }
            if(dto.getProcedure().getPdModule()!=null&&!dto.getProcedure().getPdModule().equals("")){
                String pdModule=dto.getProcedure().getPdModule();
                sql.append("and pd_Module='"+pdModule+"'");
            }
            if(dto.getProcedure().getType()!=null&&!dto.getProcedure().getType().equals("")){
                String type=dto.getProcedure().getType();
                sql.append("and type='"+type+"'");
            }
        }
        sql.append("order by to_number(sort) asc");
        List<Procedure> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),Procedure.class,hqlParamMap,page.getPageNo(),page.getPageSize(),"id");
        page.setPageData(list);
        page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(),"id",hqlParamMap));
    }

    public boolean executeOne(Procedure procedure,ProcedureDto dto){
        StringBuilder sql=new StringBuilder();
        String time1;
        String time2;
        if (dto.getProcedure()!=null&&dto.getProcedure().getTime()!=null&&procedure.getType().equals("0")) {
            String[] strarray=dto.getProcedure().getTime().split(" ");
            time1=strarray[0].replace("-","");
            time2=strarray[2].replace("-","");
            sql.append("begin\n" +
                    "  "+procedure.getPdName()+"("+time1+","+time2+");\n" +
                    "end;");
        }else{
            sql.append("begin\n" +
                    "  "+procedure.getPdName()+";\n" +
                    "end;");
        }
        workingTable.put(procedure.getPdName(),procedure.getPdTable());
        try {
            long startTime=System.currentTimeMillis();
            jdbcTemplate.execute(sql.toString());
            long endTime=System.currentTimeMillis();
            System.out.println(procedure.getPdName()+"存储过程执行时间："+(endTime-startTime)+"ms");
        }catch (Exception e){
            System.out.println("存储过程>>>>>>"+procedure.getPdModule()+"   模块执行失败");
            System.out.println("失败原因"+e.getMessage());
            return false;
        }
        workingTable.remove(procedure.getPdName());
//        System.out.println("存储过程>>>>>>"+procedure.getPdModule()+"   模块执行成功");
//        System.out.println("成功");
        return true;
    }

    public boolean flag(){
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        String sql="select pd_name from t_piccbid_Procedure where id='flag'";
        List<Procedure> flag=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),Procedure.class,hqlParamMap);
        if(flag.get(0).getPdName().equals("stop")){
            return false;
        }
        return true;
    }

    public boolean checkAndLock(){
        boolean run=flag();
        if(!run) {
            jdbcTemplate.execute("update t_piccbid_Procedure set pd_name='wroking' where id in('flag')");
        }
        return run;
    }


    public void executeAll(ProcedureDto dto){
        boolean run=flag();
        if(run==true) {
            System.out.println("开始全部执行");
            new Thread() {
                public void run() {
                    special(dto);
                }
            }.start();
            stopAll=false;
            StringBuilder sql = new StringBuilder();
            Map<String, Object> hqlParamMap = new HashMap<String, Object>();
            sql.append("select * from t_piccbid_Procedure where 1=1 and id not in('flag','1','2') and running_type !='1' ");
            if (dto.getProcedure() != null) {
                if(dto.getProcedure().getPdName()!=null&&!dto.getProcedure().getPdName().equals("")){
                    String pdName=dto.getProcedure().getPdName();
                    sql.append("and pd_Name like '%"+pdName+"%'");
                }
                if(dto.getProcedure().getPdModule()!=null&&!dto.getProcedure().getPdModule().equals("")){
                    String pdModule=dto.getProcedure().getPdModule();
                    sql.append("and pd_Module='"+pdModule+"'");
                }
                if (dto.getProcedure().getType() != null && !dto.getProcedure().getType().equals("")) {
                    String type = dto.getProcedure().getType();
                    sql.append("and type='" + type + "'");
                }
            }
//            sql.append("order by PD_XM,id,PD_NAME desc");
            sql.append("order by to_number(sort) asc");
            List<Procedure> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(), Procedure.class, hqlParamMap);
            for (Procedure p:list){
                if (p.getType()=="0"){
                    p.setTime(dto.getProcedure().getTime());
                }
            }
            List<Procedure> mistake = new ArrayList<Procedure>();
            List<String> flag = new ArrayList<String>();
            Double count = (double) list.size();
            int length;
            if (list.size() < 10) {
                length = list.size();
            } else {
                length = 10;
            }
            for (int p = 0; p < length; p++) {
                flag.add("false");
            }
            for (int j = 0; j < length; j++) {
                int k = j;
                new Thread() {
                    public void run() {
                        for (int i = 0; i < count; i++) {
                            if(stopAll){
                                System.out.println("第" + (k + 1) + "个进程执行" + (i) + "次，强行停止");
                                flag.set(k, "true");
                                break;
                            }
                            if ((i * 10 + k + 1) > list.size()) {
                                System.out.println("第" + (k + 1) + "个进程执行" + (i) + "次，执行完毕");
                                flag.set(k, "true");
                                break;
                            }
                            boolean success = executeOne(list.get(i * 10 + k),dto);
                            if (success == false) {
                                mistake.add(list.get(i * 10 + k));
                            }
//                        list.get(i * 10 + k).setSuccess(success);
                        }
                    }
                }.start();
            }
            new Thread() {
                public void run() {
                    boolean stop = true;
                    if(length==0){
                        stop=false;
                    }
                    while (stop) {
                        int y=0;
                        for (int x = 0; x < length; x++) {
                            if (flag.get(x) != "true") {
                                continue;
                            }
                            y++;
                            if (y == length) {
                                stop = false;
                            }
                        }
                    }
                    System.out.println("全部执行完毕");
                    if(stopAll==false) {
                        jdbcTemplate.execute("update t_piccbid_Procedure set pd_name='stop' where id in('flag')");
                    }
                }
            }.start();
        }
    }

    public boolean execute(ProcedureDto dto){
        if(dto.getProcedure().getPdName().equalsIgnoreCase("PRO_GET_HOSPTIMES")||dto.getProcedure().getPdName().equalsIgnoreCase("PRO_CHECK_DECOMPOSE")){
            try {
                sp(dto);
                jdbcTemplate.execute("update t_piccbid_Procedure set running_type = '0' where pd_name='" + dto.getProcedure().getPdName() + "'");
                System.out.println(dto.getProcedure().getPdName() + "存储过程执行完毕");
                return true;
            } catch (Exception e) {
                return false;
            }
        }else {
            try {
                executeOne(dto.getProcedure(), dto);
                jdbcTemplate.execute("update t_piccbid_Procedure set running_type = '0' where pd_name='" + dto.getProcedure().getPdName() + "'");
                System.out.println(dto.getProcedure().getPdName() + "存储过程执行完毕");
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean checkAndLockOne(Procedure procedure){
        StringBuilder sql=new StringBuilder();
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        sql.append("select * from t_piccbid_Procedure where pd_name='"+procedure.getPdName()+"'");
        List<Procedure> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),Procedure.class,hqlParamMap);
        if(list.get(0).getRunningType().equals("0")){
            jdbcTemplate.execute("update t_piccbid_Procedure set running_type = '1' where pd_name='"+procedure.getPdName()+"'");
            return false;
        }
        return true;
    }

    public boolean stop(){
        if(workingTable.size()>0) {
            stopAll = true;
            StringBuilder sql = new StringBuilder();
            Map<String, Object> hqlParamMap = new HashMap<String, Object>();
            sql.append("select b.username,b.sid,b.serial# as serial,logon_time \n" +
                    "from v$locked_object a,v$session b\n" +
                    "where a.session_id = b.sid and\n" +
                    " sid in(select a.session_id\n" +
                    "from v$locked_object a,dba_objects b\n" +
                    "where b.object_id = a.object_id and b.owner='PICCBID' and b.object_name in (");
            List<String> listKey=new ArrayList<String>();
            for (String key : workingTable.keySet()) {
                String value = (String) workingTable.get(key);
                sql.append("'" + value + "',");
                listKey.add(key);
            }
            for(int i=0;i<listKey.size();i++){
                workingTable.remove(listKey.get(i));
            }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append("))");
            List<Killer> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(), Killer.class, hqlParamMap);
            if (list.size() != 0) {
                try {
                    for (Killer k : list) {
                        jdbcTemplate.execute("alter system kill session '" + k.getSid() + "," + k.getSerial() + "'");
                    }
                } catch (Exception e) {
                    stop();
                }
            }
            jdbcTemplate.execute("update t_piccbid_Procedure set pd_name='stop' where id in('flag')");
            jdbcTemplate.execute("update t_piccbid_Procedure set RUNNING_TYPE='0' where id not in('flag')");

            System.out.println("全部停止");
            return true;
        }else {
            System.out.println("已经全部停止");
            jdbcTemplate.execute("update t_piccbid_Procedure set pd_name='stop' where id in('flag')");
            jdbcTemplate.execute("update t_piccbid_Procedure set RUNNING_TYPE='0' where id not in('flag')");
            return  true;
        }
    }

    public void special(ProcedureDto dto){
        StringBuilder sql = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        sql.append("select * from t_piccbid_Procedure where 1=1 and id  in('1','2') and running_type !='1' ");
        if (dto.getProcedure() != null) {
            if(dto.getProcedure().getPdName()!=null&&!dto.getProcedure().getPdName().equals("")){
                String pdName=dto.getProcedure().getPdName();
                sql.append("and pd_Name like '%"+pdName+"%'");
            }
            if(dto.getProcedure().getPdModule()!=null&&!dto.getProcedure().getPdModule().equals("")){
                String pdModule=dto.getProcedure().getPdModule();
                sql.append("and pd_Module='"+pdModule+"'");
            }
            if (dto.getProcedure().getType() != null && !dto.getProcedure().getType().equals("")) {
                String type = dto.getProcedure().getType();
                sql.append("and type='" + type + "'");
            }
        }
//            sql.append("order by PD_XM,id,PD_NAME desc");
        sql.append("order by to_number(sort) asc");
        List<Procedure> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(), Procedure.class, hqlParamMap);
        for(int i=0;i<list.size();i++){
            Procedure procedure=list.get(i);
            if (procedure.getId().equals("1")) {
                StringBuilder sql1 = new StringBuilder();
                if (dto.getProcedure() != null && dto.getProcedure() != null && dto.getProcedure().getYear() != null && !dto.getProcedure().getYear().equals("")) {
                    sql1.append("begin\n" +
                            "  " + procedure.getPdName() + "(" + dto.getProcedure().getYear() + ");\n" +
                            "end;");
                } else {
                    sql1.append("begin\n" +
                            "  " + procedure.getPdName() + "(2018);\n" +
                            "end;");
                }
                workingTable.put(procedure.getPdName(), procedure.getPdTable());
                try {
                    long startTime = System.currentTimeMillis();
                    jdbcTemplate.execute(sql1.toString());
                    long endTime = System.currentTimeMillis();
                    System.out.println(procedure.getPdName() + "存储过程执行时间：" + (endTime - startTime) + "ms");
                } catch (Exception e) {
                    System.out.println("存储过程>>>>>>" + procedure.getPdModule() + "   模块执行失败");
                    System.out.println("失败原因" + e.getMessage());
                }
                workingTable.remove(procedure.getPdName());
            } else if (procedure.getId().equals("2")) {
                StringBuilder sql1 = new StringBuilder();
                String time1;
                String time2;

                if (dto.getProcedure() != null && dto.getProcedure().getTime() != null && !dto.getProcedure().getTime().equals("")) {
                    String[] strarray = dto.getProcedure().getTime().split(" ");
                    time1 = strarray[0].replace("-", "");
                    time2 = strarray[2].replace("-", "");
                    sql1.append("begin\n" +
                            "  " + procedure.getPdName() + "(" + time1 + "," + time2);
                } else {
                    sql1.append("begin\n" +
                            "  " + procedure.getPdName() + "(2018");
                }
                if (dto.getProcedure() != null && dto.getProcedure().getHospNum() != null && !dto.getProcedure().getHospNum().equals("")) {
                    sql1.append("," + dto.getProcedure().getHospNum());
                }

                sql1.append(");\n" +
                        "end;");
                workingTable.put(procedure.getPdName(), procedure.getPdTable());
                try {
                    long startTime = System.currentTimeMillis();
                    jdbcTemplate.execute(sql1.toString());
                    long endTime = System.currentTimeMillis();
                    System.out.println(procedure.getPdName() + "存储过程执行时间：" + (endTime - startTime) + "ms");
                } catch (Exception e) {
                    System.out.println("存储过程>>>>>>" + procedure.getPdModule() + "   模块执行失败");
                    System.out.println("失败原因" + e.getMessage());
                }
                workingTable.remove(procedure.getPdName());
            }
        }
    }


    public void sp(ProcedureDto dto){
        Procedure procedure=dto.getProcedure();
        if (procedure.getPdName().equalsIgnoreCase("PRO_GET_HOSPTIMES")) {
            StringBuilder sql1 = new StringBuilder();
            if (dto.getProcedure() != null && dto.getProcedure() != null && dto.getProcedure().getYear() != null && !dto.getProcedure().getYear().equals("")) {
                sql1.append("begin\n" +
                        "  " + procedure.getPdName() + "(" + dto.getProcedure().getYear() + ");\n" +
                        "end;");
            } else {
                sql1.append("begin\n" +
                        "  " + procedure.getPdName() + "(2018);\n" +
                        "end;");
            }
            workingTable.put(procedure.getPdName(), procedure.getPdTable());
            try {
                long startTime = System.currentTimeMillis();
                jdbcTemplate.execute(sql1.toString());
                long endTime = System.currentTimeMillis();
                System.out.println(procedure.getPdName() + "存储过程执行时间：" + (endTime - startTime) + "ms");
            } catch (Exception e) {
                System.out.println("存储过程>>>>>>" + procedure.getPdModule() + "   模块执行失败");
                System.out.println("失败原因" + e.getMessage());
            }
            workingTable.remove(procedure.getPdName());
        } else if (procedure.getPdName().equalsIgnoreCase("PRO_CHECK_DECOMPOSE")) {
            StringBuilder sql1 = new StringBuilder();
            String time1;
            String time2;

            if (dto.getProcedure() != null && dto.getProcedure().getTime() != null && !dto.getProcedure().getTime().equals("")) {
                String[] strarray = dto.getProcedure().getTime().split(" ");
                time1 = strarray[0].replace("-", "");
                time2 = strarray[2].replace("-", "");
                sql1.append("begin\n" +
                        "  " + procedure.getPdName() + "(" + time1 + "," + time2);
            } else {
                sql1.append("begin\n" +
                        "  " + procedure.getPdName() + "(2018");
            }
            if (dto.getProcedure() != null && dto.getProcedure().getHospNum() != null && !dto.getProcedure().getHospNum().equals("")) {
                sql1.append("," + dto.getProcedure().getHospNum());
            }

            sql1.append(");\n" +
                    "end;");
            workingTable.put(procedure.getPdName(), procedure.getPdTable());
            try {
                long startTime = System.currentTimeMillis();
                jdbcTemplate.execute(sql1.toString());
                long endTime = System.currentTimeMillis();
                System.out.println(procedure.getPdName() + "存储过程执行时间：" + (endTime - startTime) + "ms");
            } catch (Exception e) {
                System.out.println("存储过程>>>>>>" + procedure.getPdModule() + "   模块执行失败");
                System.out.println("失败原因" + e.getMessage());
            }
            workingTable.remove(procedure.getPdName());
        }
    }
}

