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

    <style>
    </style>
    <title>组织管理页面</title>
    <style>
        #unitId+.layui-form-selected dl{
            width: 250px;
            height:250px;
            overflow-x: auto;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <fieldset style="width:90%;margin:0 auto">
        <legend>组织信息</legend>


        <div class="layui-card-body">
            <div class="layui-form" lay-filter="layuiadmin-form-useradmin"
                 id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

                <input type="hidden" name="unit.id" id="id"
                       hide=true>

                <div class="layui-form-item">
                    <div class="layui-inline" style="padding-left:40px " id="btnChoose">
                        <input type="radio"  id="unit"    name="unit.isUnit" value="1" title="地区"  lay-filter="aaa" checked/>
                        <input type="radio" id="hospital" name="unit.isUnit" value="0" title="医院" lay-filter="aaa" />
                    </div>

                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">城市编码</label>
                        <div class="layui-input-inline" style="width: 250px">
                            <input type="text" id="cityCode"
                                   name="unit.cityCode" autocomplete="off"  lay-verify="cityCode"
                                   class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline" id="cityName">
                        <label class="layui-form-label">城市名称</label>
                        <div class="layui-input-inline" style="width: 250px">
                            <input type="text" id="unitName"
                                   name="unit.unitName" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>


                </div>

                <div id="unitHide">
                    <div class="layui-form-item">
                        <div class="layui-inline" style="width: 370px;position: relative;">
                            <label class="layui-form-label">机构名称</label>
                            <div class="layui-input-block" style="width: 250px">
                                <select id="unitId" name="unit.unitId" style="width: 250px;"lay-verify="" lay-search="">
                                    <option value="" disabled selected style='display:none; width: 250px'>请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline" >
                            <label class="layui-form-label">联系人</label>
                            <div class="layui-input-inline"style="width: 250px">
                                <input type="text" id="concat"
                                       name="unit.concat" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">

                        <div class="layui-inline" >
                            <label class="layui-form-label">联系方式</label>
                            <div class="layui-input-inline"style="width: 250px">
                                <input type="text" id="phone"
                                       name="unit.phone" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-inline"style="width: 250px">
                                <input type="text" id="email"
                                       name="unit.email" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>

                    </div>



                    <div class="layui-form-item" >

                        <div class="layui-inline" >
                            <label class="layui-form-label">网址</label>
                            <div class="layui-input-inline"style="width: 250px">
                                <input type="text" id="webUrl"
                                       name="unit.webUrl" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>

                    </div>

                    <div class="layui-form-item" >
                        <div class="layui-inline">
                            <label class="layui-form-label">地址</label>
                            <div class="layui-input-inline"style="width: 550px" >
                                <input type="text" id="unitAddress"
                                       name="unit.unitAddress" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                    </div>
                </div>



                <div class="layui-form-item layui-hide">
                    <input type="button" lay-submit
                           lay-filter="layuiadmin-btn-useradmin"
                           id="layuiadmin-btn-useradmin" value="确认">
                </div>
            </div>
        </div>
    </fieldset>
</div>


<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/unit/unitAddnew.js"></script>
</body>
</html>