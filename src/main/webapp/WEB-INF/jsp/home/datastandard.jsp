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
<title>数据规范</title>
<style>
h2{
    margin-left:50px;
}
h3{
    line-height:40px;
}
h5{
	margin: 0 20px;
	line-height:25px;
	text-indent:25px
}
span{
    margin-left:70px;
}
.tb td{
	border:1px solid #eee;
	padding-left:10px;
}
</style>
</head>
<body style="overflow-x:hidden;">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-body layui-text layadmin-text">
        <div class="layui-card">
          <div class="layui-card-header">数据上传规范及说明</div>
          <div class="layui-card-body layui-text">
            <table class="layui-table" style="border:1px solid;">
              <colgroup>
                <col width="300">
                <col>
                <col width="300">
              </colgroup>
              <tbody>
                <tr>
                  <td>1、接口说明文档</td>
                  <td>
					<a href="<%=request.getContextPath() %>/sysfile/智能审核接口文档.docx">智能审核接口文档.docx</a>
                  </td>
                  <td>1.0版本</td>
                </tr>
                <tr>
                  <td>2、数据对接接口文档</td>
                  <td>
                  	<a href="<%=request.getContextPath() %>/sysfile/智能审核数据接口文档V2.0.docx"> 智能审核数据接口文档V2.0.docx</a>
                 </td>
                 <td>2.0版本</td>
                </tr>
                <tr>
                  <td>3、住院病人收费明细记录实时对接数据接口规范</td>
                  <td><a href="<%=request.getContextPath() %>/sysfile/住院病人收费明细记录webservice对接数据接口规范.doc">住院病人收费明细记录webservice对接数据接口规范.doc</a></td>
                  <td>1.0版本</td>
                </tr>
                <tr>
                  <td>4、系统功能清单</td>
                  <td><a href="<%=request.getContextPath() %>/sysfile/智能审核系统功能清单.docx">智能审核系统功能清单.docx</a></td>
                  <td>2019-06-20</td>
                </tr>
                
                <tr>
                  <td>5、单点登录子系统改造方法</td>
                  <td><a href="<%=request.getContextPath() %>/sysfile/sso子系统修改.docx">sso子系统修改.docx</a></td>
                  <td>1.0版本</td>
                </tr>
              </tbody>
            </table>
            <h3>1、数据采集目标</h3>
            <h5>基于标准的数据采集，是数据交换与共享的基本任务。完整准确的数据是数据共享的基础，传输过程的安全高效是数据共享的保障。采集到的标准数据为1）基础资源管理（如医疗机构、科室、重要设备管理等）；2）医保三大目录、诊断编码等辅助性基础数据；3）就诊病例等应用提供数据支撑。
            </h5>
            <h3>2、数据采集范围及优先级</h3>
            <h5>采集标准分为基础资源、辅助资源、就诊病历三部分，基础资源与辅助资源数据采用结构化方式进行数据的上传。电子病历数据可以通过结构化方式上传业务数据内容，也可以通过word、excel等非结构化的形式上传。当医疗机构的信息系统以结构化的形式存储业务数据时，按照结构化数据标准上传数据内容即可不必上传非结构化的报告数据；当机构的信息系统以非结构化的形式存储业务数据时，则只需上传能够关联该报告的基本就诊信息和相关病例等文件即可。
            </h5>
            <h3>3、数据采集方式比较</h3>
            <h5>通过下表的比价，数据采集建议按照中间表/视图的方式传输，实时交互通过webservice的方式进行
            <table class="tb" style="width: 600px;text-align: center;">
            	<tr>
            		<td></td>
            		<td>中间表方法</td>
            		<td>Web Services方法</td>
            		<td>共享文档方法</td>
            	</tr>
            	<tr>
            		<td>传输效率</td>
            		<td>高</td>
            		<td>较高</td>
            		<td>高</td>
            	</tr>
            	<tr>
            		<td>安全性</td>
            		<td>高</td>
            		<td>高</td>
            		<td>高</td>
            	</tr>
            	<tr>
            		<td>XML支持</td>
            		<td>高</td>
            		<td>高</td>
            		<td>高</td>
            	</tr>
            	<tr>
            		<td>HL7支持</td>
            		<td>高</td>
            		<td>高</td>
            		<td>高</td>
            	</tr>
            </table>
            </h5>
            <h3>4、实时数据采集及与交互</h3>
            <h5>通过Web Service等方法可实时采集医疗机构业务数据，该方式实时地将医疗服务医疗机构产生的业务数据上传至平台，反应数据的实时变化情况，便于医疗监管机构及时、迅速、全面掌握医疗数据，快速准确制定决策，是信息平台实现数据采集与共享的主要方式。
                                               该方式主要应用于有实时监管要求的数据采集，如突发疾病管理信息、双向转诊信息、医疗保险信息等。
				本方法主要部署在通过前置机端的Web Service采集接口，再利用数据传输协议将数据上传到数据中心。
				采用webservice进行数据的传输，分类单条业务数据的传输和批量附件形式数据传输两种方式。
				医院以往的历史业务数据采用批量附件形式上传，当前医院产生的实时数据采用单条形式上传。
			</h5>
<h3>5、数据上传接口规范</h3>
<h5>
数据上传接口：<br>
<img style="margin:0 auto;" src="<%=request.getContextPath() %>/sysfile/webservice_1.png"  /><br>
单条数据的上传是以”uploadDataToIshare”为根标签，业务表名作为子标签，在子标签每一条记录对应一个表记录的XML内容。例如单条提交从业人员记录<br>
<img style="margin:0 auto;" src="<%=request.getContextPath() %>/sysfile/webservice_2.png"  />
</h5>
<h3>6、采集标准控制字段说明</h3>
<h5>数据采集控制字段：<br><img style="margin:0 auto;" src="<%=request.getContextPath() %>/sysfile/webservice_3.png"  /><br>
</h5>
<h3>7、数据采集流程</h3>
            <h5>首先由医院/医保局平台上传/导出本机构正在使用的基础术语字典、icd字典、三大目录字典，平台在获得机构的字典标准后，进行映射处理，然后由医院/医保局确认和纠正映射关系的正确性。同时医院根据Webservice的WSDL规范进行客户端的开发，然后通过webservice方式首先上传医院的的基础数据（机构信息、科室信息、从业人员信息）到平台这些信息卫生局审核通过之后，再上传医院的其他业务数据。数据上传的顺序与“3.1数据采集范围及提交优先级”中的要求是一致。
医院在上传患者信息，以及业务数据时，首先要判断该患者是否是干部健康管理平台需要的患者，通过干部健康管理平台公医号查询接口可以获取当前平台管理的人员信息（主要是正在使用的公医号、身份证号、公医号使用状态、旧的公医号信息）。如果公医号/身份证号是新增状态的时候，需要上传该患者所有的历史以往数据。如果是正在使用状态时，只需要上传该患者当前的数据信息。如果当该被平台注销时，不再上传该患者的数据。流程如下图
</h5>
<h3>8、数据采集评价流程</h3>
            <h5>数据采集通过实时接口的方式获取平台或机构的数据，在获取业务数据之后调用校验功能，根据交换标准对获取的数据进行校验。然后调用数据评价接口完成对数据的评价，评价通过的数据直接通过上载模块接口将数据上传至区域信息平台然后存入正确库，评价不通过的数据则直接存入错误库中。
医生或信息科人员，可以直接登录广东省干部健康管理平台的填报系统，将填报数据上传给区域平台。
在数据上传过程中，通过数据采集标准中控制字段业务编号（business_no）和数据上传标识，综合判断数据数据是否重复上传。对于判断是重复的数据将校验不通过，存入错误库。
在数据采集过程中所有数据上传、校验、评价的结果信息可以登录到前置机管理界面直接查看。
</h5>
          </div>
        </div>
      </div>
    </div>
</div>
</body>
</html>