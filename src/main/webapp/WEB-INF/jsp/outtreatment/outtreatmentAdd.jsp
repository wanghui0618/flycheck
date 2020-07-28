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
    <title>新增/修改超限定疗程</title>
</head>
<body>
<div class="layui-fluid">
    <fieldset style="width:90%;">
        <div class="layui-card-body">
            <div class="layui-form" lay-filter="layuiadmin-form-useradmin"
                 id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
                <input type="hidden" name="outtreatment.id" id="id" hide=true>

                <div class="layui-form-item">

                    <div class="layui-inline">
                        <label class="layui-form-label">城市编码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="cityCode"
                                   name="outtreatment.cityCode" autocomplete="off" lay-verify="cityCode"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">规则编码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="typeNo"
                                   name="outtreatment.typeNo" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>

                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">规则名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="typeName"
                                   name="outtreatment.typeName" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">目录编码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="itemCode"
                                   name="outtreatment.itemCode" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">目录名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="itemName"
                                   name="outtreatment.itemName" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>


                    <div class="layui-inline">
                        <label class="layui-form-label">判断逻辑类型</label>
                        <div class="layui-input-inline">
                            <input type="text" id="logicType"
                                   name="outtreatment.logicType" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>

                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">判断逻辑类型明细</label>
                        <div class="layui-input-inline">
                            <input type="text" id="logicTypeDetail"
                                   name="outtreatment.logicTypeDetail" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>


                    <div class="layui-inline">
                        <label class="layui-form-label">判断逻辑</label>
                        <div class="layui-input-inline">
                            <input type="text" id="logic"
                                   name="outtreatment.logic" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>

                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">逻辑算法</label>
                        <div class="layui-input-inline">
                            <input type="text" id="logicSql"
                                   name="outtreatment.logicSql1" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-inline">
                            <input type="text" id="comments"
                                   name="outtreatment.comments" autocomplete="off"
                                   class="layui-input">
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
<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/dictdiag/dictdiagAdd.js"></script>
</body>
</html>