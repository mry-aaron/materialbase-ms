
// ʹ�ñ�������б����ڱ��
layui.use('table');
// ʹ��ѡ�
layui.use('element');

// ��¼ע��
$(function () {
        $('#sign').on('click', function () {
            layui.use(['laypage', 'layer'], function() {
                var laypage = layui.laypage, layer = layui.layer;
                layer.open({
                    type: 2,
                    title: false,
                    /*maxmin: true,
                    shadeClose: true, //������ֹرղ�*/
                    area: ['30rem', '32.5rem'],
                    content: ['/sign.html', 'no']
                });
                $("#layui-layer-iframe").css("overflow", "hidden");
            });
        });
    });

// �ϴ�ͷ��
$(function () {
        $("#upload_file").on("change", function () {
            var $file = $(this);
            var objUrl = $file[0].files[0];
            // ���һ��http��ʽ��url·��
            var windowURL = window.URL || window.webkitURL;
            // ����һ��ָ���ͼƬ��URL
            var dataURL = windowURL.createObjectURL(objUrl);
            // ��ʾͼƬ
            $(".upload_img").attr("src", dataURL);
        });
    }); 
// ��ʼ��User
    function initUser(form) {
        $.post("/toeditpage", "", function (data) {
            var user = JSON.parse(data);
            if(user != null && user != ""){
                // ���벻���޸�
                if(user["password"] != null && user["password"] != ""){
                    $(".pwd_display").css("display","none");
                } else {
                    $(".pwd_display").css("display","block");
                }
                // ����ʼ��ֵ
                $(".upload_img").attr("src","http://192.168.0.125/images/upload/heads/"+user["headImg"]);
                form.val('example', {
                    "id": user["id"]
                    ,"name": user["name"]
                    ,"deptId": user["deptId"]
                    ,"telephone": user["telephone"]
                });
            }
        });
    }
    // ����select
    function getDataList(form) {
        $.ajax({
            type: 'POST',
            url: '/findall',
            success:function(data){
                $('.dept_choose').empty();
                var dept = JSON.parse(data);
                // ƴ������ѡ��
                var opts = "";
                opts += "<option value='0'>��ѡ����</option>";
                for (var i = 0; i < dept.length; i++){
                    opts += "<option value='"+dept[i]["id"]+"'>"+dept[i]["deptName"]+"</option>";
                }
                $('.dept_choose').append(opts);
                initUser(form);
            }
        })
    }
    $(function () {
        // ��ǰ��¼�û���
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
                        $(".submit_msg").html('<i style="color: #FF0000; margin-left: 2rem;"> x �û����Ѵ��ڣ�</i>');
                    } else {
                        $("#btn_submit").attr('disabled',false);
                        $(".submit_msg").html('<i style="color: #008000; margin-left: 2rem;"> �� �û������ã�</i>');
                    }
                });
            } else {
                $("#btn_submit").attr('disabled',false);
                $(".submit_msg").html('<i style="color: #008000; margin-left: 2rem;"> �� �û������ã�</i>');
            }
        });
    });