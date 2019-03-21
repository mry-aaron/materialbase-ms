
// 使用表格（下拉列表属于表格）
layui.use('table');
// 使用选项卡
layui.use('element');

// 登录注册
$(function () {
        $('#sign').on('click', function () {
            layui.use(['laypage', 'layer'], function() {
                var laypage = layui.laypage, layer = layui.layer;
                layer.open({
                    type: 2,
                    title: false,
                    /*maxmin: true,
                    shadeClose: true, //点击遮罩关闭层*/
                    area: ['30rem', '32.5rem'],
                    content: ['/sign.html', 'no']
                });
                $("#layui-layer-iframe").css("overflow", "hidden");
            });
        });
    });

// 上传头像
$(function () {
        $("#upload_file").on("change", function () {
            var $file = $(this);
            var objUrl = $file[0].files[0];
            // 获得一个http格式的url路径
            var windowURL = window.URL || window.webkitURL;
            // 创建一个指向该图片的URL
            var dataURL = windowURL.createObjectURL(objUrl);
            // 显示图片
            $(".upload_img").attr("src", dataURL);
        });
    }); 
// 初始化User
    function initUser(form) {
        $.post("/toeditpage", "", function (data) {
            var user = JSON.parse(data);
            if(user != null && user != ""){
                // 密码不做修改
                if(user["password"] != null && user["password"] != ""){
                    $(".pwd_display").css("display","none");
                } else {
                    $(".pwd_display").css("display","block");
                }
                // 表单初始赋值
                $(".upload_img").attr("src","http://materialbase-ms.com/images/upload/heads/"+user["headImg"]);
                form.val('example', {
                    "id": user["id"]
                    ,"name": user["name"]
                    ,"deptId": user["deptId"]
                    ,"telephone": user["telephone"]
                });
            }
        });
    }
    // 加载select
    function getDataList(form) {
        $.ajax({
            type: 'POST',
            url: '/findall',
            success:function(data){
                $('.dept_choose').empty();
                var dept = JSON.parse(data);
                // 拼接下拉选项
                var opts = "";
                opts += "<option value='0'>请选择部门</option>";
                for (var i = 0; i < dept.length; i++){
                    opts += "<option value='"+dept[i]["id"]+"'>"+dept[i]["deptName"]+"</option>";
                }
                $('.dept_choose').append(opts);
                initUser(form);
            }
        })
    }
    $(function () {
        // 当前登录用户名
        var user_name;
        layui.use(['form'], function(){
            var form = layui.form;
            getDataList(form);
        });
        $("input[name='name']").on("blur", function () {
            var $name = $("input[name='name']").val();
            if($name != null && $name != user_name){
                $.post("/finduser", "name="+$name, function (data) {
                    if(data == "0") {
                        $("#btn_submit").attr('disabled',true);
                        $(".submit_msg").html('<i style="color: #FF0000; margin-left: 2rem;"> x 用户名已存在！</i>');
                    } else {
                        $("#btn_submit").attr('disabled',false);
                        $(".submit_msg").html('<i style="color: #008000; margin-left: 2rem;"> √ 用户名可用！</i>');
                    }
                });
            } else {
                $("#btn_submit").attr('disabled',false);
                $(".submit_msg").html('<i style="color: #008000; margin-left: 2rem;"> √ 用户名可用！</i>');
            }
        });
    });
