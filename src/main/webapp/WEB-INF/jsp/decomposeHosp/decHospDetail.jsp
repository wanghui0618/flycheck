<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
 	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<title>分解住院（限7天）/住院天数异常</title>
<style type="text/css">
.layui-table-cell>span{
text-align: center;
display: block;
}
</style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-body">
        <table id="dg" class="layui-hide" lay-filter="dg"></table>
      </div>
    </div>
  </div>
  <input id="orgCode" type="hidden" value="${medicalRecord.orgCode}">
  <input id="idNo" type="hidden" value="${medicalRecord.idNo}">
  <input id="patientName" type="hidden" value="${medicalRecord.patientName}">
  <input id="condition" type="hidden" value="${medicalRecord.condition}">
  <input id="lastDischargeDate" type="hidden" value="<fmt:formatDate value="${medicalRecord.lastDischargeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
  <input id="admissionDate" type="hidden" value="<fmt:formatDate value="${medicalRecord.admissionDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/decomposeHosp/decHospDetail.js"></script>
</body>
</html>