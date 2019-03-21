layui.use("table");

// 浏览文件大小限制
$(function () {
    $("#upload_file").on("change", function () {
        // 验证文件大小不能超过50MB
        var f = document.getElementById("upload_file").files;
        if(f[0].size / 1024 > 50 * 1024){
            layui.use('layer', function(){
                var active = {
                    confirmTrans: function(){
                        layer.msg('文件不能超过50MB' , {
                            time: 2000
                        });
                    }
                };
                active['confirmTrans'] ? active['confirmTrans'].call(this) : '';
            });
            return false;
        }
        $(".fileName").val($(this).val());
    });
});

/* 渲染select数据 */
$(function () {
    // 加载素材风格
    loadSelectItem("POST", "/getstyle", "select[name='smStyleId']", "sName");
    // 加载素材类型
    loadSelectItem("POST", "/gettype", "select[name='smTypeId']", "tName");
    // 加载媒体
    loadSelectItem("POST", "/getmedia", "select[name='mediaId']", "mName");
});

/* 弹出录入结果 */
$(function () {
    function recordResult(_msg) {
        layui.use('layer', function(){
            var active = {
                confirmTrans: function(){
                    layer.msg(_msg , {
                        time: 2000, //2s后自动关闭
                        //btn: ['确定']
                    });
                }
            };
            active['confirmTrans'] ? active['confirmTrans'].call(this) : '';
        });
    }
    var $result = $(".record_result").html();
    if($result == "success"){
        recordResult("素材录入成功！<br/>请继续您的操作！");
    } else if($result == "error"){
        recordResult('素材录入失败！<br/>请重新录入信息！');
    }
});