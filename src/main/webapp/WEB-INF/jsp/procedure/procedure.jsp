<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <style>
        .loading{
            pointer-events:none;
            background-color: gray;
            cursor: wait;
        }
    </style>
    <title>存储过程管理</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item" >
                <div class="layui-inline" >
                    <label class="layui-form-label"style="width:90px">存储过程名称</label>
                    <div class="layui-input-inline">
                        <input type="text" style=""id="pdName"name="procedure.pdName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline" >
                    <label class="layui-form-label" style="width:90px">模块名称</label>
                    <div class="layui-input-inline">
                        <input type="text" style=""id="pdModule"name="procedure.pdModule" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <%--<div class="layui-inline">--%>
                    <%--<label class="layui-form-label" style="width:45px;">类型</label>--%>
                    <%--<div class="layui-input-inline">--%>
	                    <%--<select id="type" name="procedure.type"  lay-filter="brickType">--%>
	                        <%--<option value="1">统计</option>--%>
	                        <%--<option value="0" >机审</option>--%>
	                    <%--</select>--%>
                    <%--</div>--%>
                    <%----%>
                <%--</div>--%>
                <div class="layui-inline" style="width:270px">
                    <label class="layui-form-label" >入参日期</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="time" name="procedure.time" style="width:180px" placeholder=" - "readonly="true">
                    </div>
                </div>
                <div class="layui-inline cxtjtop" >
                    <label class="layui-form-label" style="width:90px">入参年份</label>
                    <div class="layui-input-inline" >
                        <input type="text" class="layui-input" id="time2" name="procedure.time" placeholder=" - "readonly="true">
                    </div>
                </div>
                <div class="layui-inline cxtjtop" >
                    <label class="layui-form-label" style="width:90px">分解住院天数</label>
                    <div class="layui-input-inline"style="text-align:center; width: 150px">
                        <input type="text" style=""id="hospNum"name="procedure.hospNum" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div id="xiala" onclick="showSearch()" style="color:#2284FF;cursor:pointer;z-index:9999;display: inline-block;">更多<img style="margin: 0 auto; height: 6px;width: 8px;" src="<%=request.getContextPath()%>/images/main/xiala.png" /></div>
                <div id="shangla" onclick="hideSearch()" style="color:#2284FF;cursor:pointer;z-index:9999;display: inline-block;">收起<img style="margin: 0 auto; height: 6px;width: 8px;" src="<%=request.getContextPath()%>/images/main/shangla.png" /></div>

                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-avgDays-front-search">
                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询</button>
                </div>
                <%--<div class="layui-inline">--%>

                <%--<a class="layui-btn layuiadmin-btn-useradmin" style="width: 130px" lay-event="startAll"><i class="layui-icon layui-icon-play"></i>全部执行</a>--%>

                <%--</div>--%>
                <div class="layui-inline">
                    <button id='startAll' data-type="startAll"
                            class="layui-btn layui-icon-add layuiadmin-btn-useradmin" lay-submit
                            lay-filter="LAY-user-front-add"><i class="layui-icon layui-icon-add-circle layuiadmin-button-btn"></i><span id="exc">执行全部</span></button>
                </div>
                <div class="layui-inline">
                    <button id='stopAll' data-type="stopAll"
                            class="layui-btn layui-icon-add layuiadmin-btn-useradmin" lay-submit
                            lay-filter="LAY-user-front-stop"><i class="layui-icon layui-icon-add-circle layuiadmin-button-btn"></i><span id="stop">停止全部</span></button>
                </div>

            </div>

        </div>

        <div class="layui-card-body">

            <table id="procedureTable" class="layui-hide" lay-filter="procedureTable"></table>
            <script type="text/html" id="table-useradmin-webuser">
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="start"><i class="layui-icon layui-icon-play"></i>启动</a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>

            </script>
        </div>

    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/procedure/procedure.js"></script>
<script>
    layui.use('laydate', function() {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#time'
            ,range: true
            ,value:thisYear()
        });
        laydate.render({
            elem: '#time2'
            ,type: 'year'
            ,value:nowYear()
        });
       	document.getElementById("timeSelect").style.display="none";
    });
    function thisYear() {
        var day1 = new Date();
        day1.setTime(day1.getTime()-24*60*60*1000);
        var s0=day1.getFullYear()+'-01-01'
        var s1 = day1.getFullYear()+"-" + (day1.getMonth()+1) + "-" + day1.getDate();
        return s0+' - '+s1;
    }
    function nowYear(){
        var day1 = new Date();
        day1.setTime(day1.getTime()-24*60*60*1000);
        return day1.getFullYear()
    }

    layui.use(['form'], function() {
        var form=layui.form;
        form.on('select(brickType)', function(data){
            var val=data.value;
            var sl=document.getElementById("timeSelect")
            if(val=='1'){
                sl.style.display="none";
            }else{
                sl.style.display="inline-block";
            }
            var field = {"procedure.type":val};
            //执行重载
            layui.table.reload('procedureTable', {
                where: field
                ,page: { curr: 1}
            });
        });
    });
    $(".cxtjtop").hide();
    $("#shangla").hide();
    function showSearch(){
        $("#shangla").show();
        $("#xiala").hide();
        $(".cxtjtop").show();
    }
    function hideSearch(){
        $(".cxtjtop").hide();
        $("#shangla").hide();
        $("#xiala").show();
    }

</script>
</body>
</html>