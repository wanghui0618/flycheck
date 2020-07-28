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
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<title>药品说明书</title>
 <style>
.layui-form-item .layui-inline {
    margin-bottom: 0px;
    margin-right: 0px;
}

</style>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					
					<div class="layui-inline pt">
						<label class="layui-form-label">药品名</label>
						<div class="layui-input-block">
							<input type="text"
								name="drugLnstmction.name" placeholder="请输入名称"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline pt">
						<label class="layui-form-label" style="width:55px">禁忌症</label>
						<div class="layui-input-block" style="margin-left:65px">
							<input type="text"
								name="drugLnstmction.contraindication" placeholder="请输入禁忌症"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline pt">
						<label class="layui-form-label"  style="width:55px">适应症</label>
						<div class="layui-input-block" style="margin-left:65px">
							<input type="text"
								name="drugLnstmction.indication" placeholder="请输入适应症"
								autocomplete="off" class="layui-input">
						</div>
					</div>
                    
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
					<div class="layui-inline" style="margin-left: 5px">
						<button id='druglnstmction-add' data-type="add"
							class="layui-btn  layui-icon-add layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-add"><i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>添加</button>
					</div>
					<div class="layui-inline" style="margin-left: 5px">
						<button class="layui-btn layui-icon-upload-main" id="medicalUplpad-medical-importExcel1">
							<i class="layui-icon layui-icon-upload-drag  layuiadmin-button-btn"></i>导入
						</button>
					</div>
				</div>
			</div>
		<div class="layui-card-body">
         <table id="druglnstmctionTable" class="layui-hide" lay-filter="druglnstmctionTable"></table>
        <script type="text/html" id="table-orgadmin-webuser">
		{{#if (!existsButton('druglnstmction-search')) { }}
		 <a class="layui-btn layui-btn-xs" lay-event="view"><i class="layui-icon layui-icon-search"></i>预览</a>
        {{# } }}
        {{#if (!existsButton('druglnstmction-edit')) { }}
		 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        {{# } }}	
        {{#if (!existsButton('druglnstmction-delete')) { }}
         <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
         {{# } }}
        </script>
		</div>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/druglnstmction/druglnstmctioninfo.js"></script>
	<script type="text/javascript">

        layui.use(['upload','table'], function(){
            var upload = layui.upload;
            var table = layui.table;
           	var fileName="";
            //执行实例
            upload.render({
                elem: '#medicalUplpad-medical-importExcel1' //绑定元素
                ,multiple:false
                ,exts:'xls|xlsx|xlsm|xlt|xltx|xltm'
				,data:{filePath:'_uploadFile/drugLnstmction/'}
                ,url: $WEB_ROOT_PATH+'/dhccApi/uploadApi/uploadAudit'
                ,choose: function(obj) {   //选择文件后的回调函数
                    obj.preview(function (index, file) {
                        fileName = file.name.replace(/\s*/g,""); //得到文件名称并去除空格
                    });
                }
                //上传完毕回调
                ,done: function(res,index){
                    $.ajax({url : $WEB_ROOT_PATH+'/dhccApi/druglnstmction/drugLnstmction/insertData',
                        data :{"fileName":fileName,"path":'_uploadFile/drugLnstmction/'},
                        success : function(result) {
                            if(result.operateSuccess){
                                //上传完毕回调
								layer.msg(result.msg);
                                fileName="";//初始化变量
                                table.reload('druglnstmctionTable');
                                layer.close(index); //关闭弹层
                            }else{
								layer.msg(result.msg);
                                fileName="";//初始化变量
                                table.reload('druglnstmctionTable');
                                layer.close(index); //关闭弹层
                            }
                        },
                    });
                }
                //请求异常回调
                ,error: function(index){
                    //请求异常回调
                    layer.msg("导入失败");
                    layer.close(index); //关闭弹层
                }

       		 });
        });
	</script>
</body>
</html>