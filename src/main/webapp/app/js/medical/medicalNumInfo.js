var medicalName;
var year;
var flag= true;
function child(obj){
    var data = JSON.parse(obj);
    medicalName = data.condition.medicalName;
    year = data.year;
    if(layui.table != undefined && flag){
        layui.table.render({
            elem: '#userTable'
            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listCondition'
            ,cellMinWidth: 80
            ,height: 425
            ,where: {  'medical.condition':medicalName,'medical.status':year}
            ,cols: [[
                {type: 'numbers', title: '序号' }
                ,{field:'aduitStatus', title: '就诊时间'}
                ,{field:'orgName', title: '医疗机构'}
                ,{field:'name', title: '姓名',width:100}
                ,{field:'sex', title: '性别',width:100,templet:function(data){
                        if(data.sex=='1'){
                            return '男'
                        }
                        if(data.sex=='2'){
                            return  '女'
                        }
                        if(data.sex==null){
                            return ' '
                        }
                        return data.sex
                    }}
                // ,{field:'age', title: '年龄',width:100}
                ,{field:'idcard', title: '身份证号'}
                ,{field:'condition', title: '疾病名称'}
                // ,{field:'medicalType', title: '险种类型',width:100}


            ]]

            ,page: true
        });
    }
}

//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;
         if(medicalName != undefined){
             flag=false;
            table.render({
                elem: '#userTable'
                ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listCondition'
                ,cellMinWidth: 80
                ,height: 425
                ,where: {  'medical.condition':medicalName,'medical.status':year}
                ,cols: [[
                    {type: 'numbers', title: '序号' }
                    ,{field:'aduitStatus', title: '就诊时间'}
                    ,{field:'orgName', title: '医疗机构'}
                    ,{field:'name', title: '姓名',width:100}
                    ,{field:'sex', title: '性别',width:100,templet:function(data){
                            if(data.sex=='1'){
                                return '男'
                            }
                            else if(data.sex=='2'){
                                return  '女'
                            }
                            else if(data.sec=='男'){
                                return data.sex
                            }
                            else if(data.sec=='女'){
                                return data.sex
                            }
                            else{
                                return '/'
                            }
                        }}
                    // ,{field:'age', title: '年龄',width:100}
                    ,{field:'idcard', title: '身份证号'}
                    ,{field:'condition', title: '疾病名称'}
                    // ,{field:'medicalType', title: '险种类型',width:100}


                ]]

                ,page: true
            });
        }
    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});