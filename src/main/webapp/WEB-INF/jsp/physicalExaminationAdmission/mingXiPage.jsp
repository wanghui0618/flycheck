<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<%@include file="/WEB-INF/jsp/common/easyui.jsp"%>
<title>明细表</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">

			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline ">
						<label class="layui-form-label ">项目名称</label>
						<div class="layui-input-block">
							<input type="text" name="flyCheckMedicalDetailVo.itemName"
								placeholder="查询医保项目名称" autocomplete="off" class="layui-input">

						</div>
					</div>
					<div class="layui-inline ">
						<label class="layui-form-label">项目编码</label>
						<div class="layui-input-block">
							<input type="text" name="flyCheckMedicalDetailVo.itemId"
								placeholder="查询医保项目编码" autocomplete="off" class="layui-input">
						</div>
					</div>

					<!--  <div class="layui-inline ">
                    <label class="layui-form-label">费用类别</label>
                    <div class="layui-input-block">
                        <input type="text"
                               name="flyCheckMedicalDetailVo.pCategory" placeholder="查询费用类别"
                               autocomplete="off" class="layui-input">

                    </div>
                </div> -->
					<div class="layui-inline">
						<label class="layui-form-label">费用类别</label>
						<div class="layui-input-inline">
							<input id="fyType" name="flyCheckMedicalDetailVo.pCategory" />
						</div>
					</div>


					<div class="layui-inline ">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-detailedInformation-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
				</div>
			</div>
			<div class="layui-card-body">
				<table id="mingXiTable" class="layui-hide" lay-filter="mingXiTable">
				</table>

			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/physicalExaminationAdmission/mingXi.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/physicalExaminationAdmission/fyTypeDict.js"></script>
</body>
</html>
