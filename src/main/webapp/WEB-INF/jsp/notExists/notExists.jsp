<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
          media="all">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/login.css"
          media="all">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/tree/css/zTreeStyle.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
    <title>项目查询</title>
</head>
<style>
    .layui-form-label {
        width: 110px;
    }
    .limittime input{
    width:72px;
    }
    .xxvalue input{
    width:72px;
    }
</style>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <%-- 医疗机构 需要做成下拉框 --%>
           <div class="layui-inline ">
                <label class="layui-form-label ">就诊途径</label>
                <div class="layui-input-inline">
                    <select id="path" name="path" lay-filter="path">
                        <option value="住院" selected="selected">住院</option>
                        <option value="门诊">门诊</option>
                    </select>
                </div>
            </div>
            <!-- <div class="layui-inline ">
                <label class="layui-form-label ">险种类型</label>
                <div class="layui-input-inline">
                    <select id="benefitType" name="benefitType">
                        <option id="initbenefitType" value="" selected></option>
                        <option value="市属职工">市属职工</option>
                        <option value="市属居民">市属居民</option>
                        <option value="省直（职工）">省直（职工）</option>
                        <option value="省内异地">省内异地</option>
                        <option value="异地省外">异地省外</option>
                        <option value="自费">自费</option>
                    </select>
                </div>
            </div> -->
            <div class="layui-inline">
                <label class="layui-form-label">医疗机构</label>
                <div class="layui-input-inline">
                    <input id="getOrgName" name="hospitalName"/>
                    <input type="text" id="orgCode" name="hospitalId" style="display: none;"/>
                </div>
            </div>

            <!-- 下拉按钮 -->
            <div id="xiala" onclick="showSearch()"
                 style="color:#2284FF;cursor:pointer;z-index:9999; margin-left: 10px;display: inline-block;">更多
                <img style="margin: 0 auto;height:6px;width:8px;"
                     src="<%=request.getContextPath()%>/images/main/xiala.png"/>
            </div>


            <div class="layui-inline cxtjtop">
                <label class="layui-form-label">结算日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="test6"
                           placeholder="结算日期" name="billDate">
                </div>
            </div>
            <!-- <div class="layui-inline cxtjtop zysj">
                <label class="layui-form-label">入院时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="test7"
                           placeholder="入院时间" name="admissionDate">
                </div>
            </div> -->
            <!-- <div class="layui-inline cxtjtop zysj">
                <label class="layui-form-label">出院时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="test8"
                           placeholder="出院时间" name="dischargeDate">
                </div>
            </div> -->

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label">科室名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="dischargeDeptName"
                           name="dischargeDeptName" placeholder="科室名称"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label">结算号</label>
                <div class="layui-input-inline">
                    <input type="text" id="hisid" class="layui-input" autocomplete="off"
                           name="hisid" placeholder="结算号">
                </div>
            </div>
        
             <!-- <div class="layui-inline cxtjtop">
                <label class="layui-form-label">总金额</label>
                <div class="layui-input-inline">
                    <input type="text" id="totalAmount"
                           name="totalAmount" placeholder="请输入总金额"
                           autocomplete="off" class="layui-input">
                </div>
            </div> -->
			 <div class="layui-inline cxtjtop">
                <label class="layui-form-label">医院项目编码</label>
                <div class="layui-input-inline">
                    <input type="text" id="itemId"
                           name="itemId" placeholder="医院项目编码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label">医院项目名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="itemName"
                           name="itemName" placeholder="医院项目名称"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
			
            <!-- <div class="layui-inline cxtjtop">
                <label class="layui-form-label">条件</label>
                <div class="layui-input-inline" id="xzxztj" style="width:85px">
                   <select id="xzzt" name="sxtj" lay-filter="xzzt" >
                        <option value="dey" selected>等于</option>
                        <option value="dy">大于</option>
                        <option value="xy">小于</option>
                        <option value="tscx">同时出现</option>
                        <option value="btscx">不同时出现</option>
                    </select>
                </div>
                <div class="layui-input-inline xxvalue" >
                   <input type="text" id="xzvalue" name="xzvalue" placeholder="请输入" autocomplete="off" class="layui-input" style="width:72px">
                </div>
                <div class="layui-input-inline xxvalue" style='margin-left:-5px'>
                  <select id="costOrnum" name="costOrnum" lay-filter="costOrnum" >
                        <option value="cost" selected>价格</option>
                        <option value="num">数量</option>
                   </select>
                </div>
                <div class="layui-input-inline limittime" style="width:72px">
                    <select id="limittime" name="limittime" lay-filter="limittime" >
                        <option value="tc" selected>同次</option>
                        <option value="tt">同天</option>
                    </select>
                </div>
            </div> -->
            
            <div class="layui-inline cxtjtop" >
                <label class="layui-form-label">医保项目编码</label>
                <div class="layui-input-inline">
                    <input type="text" id="itemId1"
                           name="itemId1" placeholder="医保项目编码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label">医保项目名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="itemName1"
                           name="itemName1" placeholder="医保项目名称"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            
            <!-- 上收按钮 -->
            <div id="shangla" onclick="hideSearch()"
                 style="color:#2284FF;cursor:pointer; margin-left: 10px;z-index:9999;display:inline-block;">收起
                <img style="margin: 0 auto;height:6px;width:8px;"
                     src="<%=request.getContextPath()%>/images/main/shangla.png"/>
            </div>

            
            <div class="layui-inline">
					<button id="search" class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="search" stylename="search">
						<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
					</button>
					 <button class="layui-btn layui-btn-primary" lay-submit
                         id="resets" stylename="allUpdate" lay-filter="LAY-user-front-reset"  onclick="restart()">
                    	<i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
                	</button>
					<button id="exportInfo" stylename="export"
							class="layui-btn layuiadmin-btn-useradmin layui-icon-down-main"
							lay-submit lay-filter="LAY-user-front-export">
						<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
					</button>
				</div>
            
            
            
        </div>
        <div class="layui-card-body">
            <!--<script type="text/html" id="searchDemo">
                <a class="layui-btn layui-btn-xs" lay-event="searcDetail">查看明细</a>
            </script>-->
            <table id="notExists" class="layui-hide"
                   lay-filter="notExists">
            </table>
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title">
                    <li class="layui-this">汇总：</li>
                   <!--  <li>总病例数：<span class="layui-badge" id="rowsum"></span></li>
                    <li>涉及病例总金额：<span class="layui-badge" id="sumAmount"></span></li> -->
                    <li>涉及明细数量：<span class="layui-badge" id="rowsum1"></span></li>
                    <li>涉及明细总金额：<span class="layui-badge" id="sumAmount1"></span></li>
                </ul>
                <div class="layui-tab-content"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/app/js/notExists/notExists.js"></script>
<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<script type="text/javascript">
//全局变量
var goal_path='';
    $(".cxtjtop").hide();
    $("#shangla").hide();
    $(".limittime").hide();

    function xzzthide(){
    	$(".limittime").hide();
    	$(".xxvalue").show();
    }
    function xzztshow(){
    	$(".limittime").show();
    	$(".xxvalue").hide();
    }
 
</script>
</body>

</html>