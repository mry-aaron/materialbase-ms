layui.use('table');
/* 渲染select数据 */
$(function () {
    // 加载公司名称
    loadSelectItem("POST", "/getcompany", "select[name='companyId']", "cName");
    // 加载素材风格
    loadSelectItem("POST", "/getstyle", "select[name='smStyleId']", "sName");
    // 加载素材类型
    loadSelectItem("POST", "/gettype", "select[name='smTypeId']", "tName");
    // 加载媒体
    loadSelectItem("POST", "/getmedia", "select[name='mediaId']", "mName");
});
$(function () {
    /* 初始化页面数据 */
    initData(JSON.parse(JSON.stringify(parent.materialData)));
    /* 异步提交 */
    $("#edit_btn").on("click", function () {
        var d = $("#edit_form").serialize();
        $.ajax({
            type: 'post',
            url: '/editmaterial',
            data: d,
            success: function (data) {
                if(data){
                    layer.alert('素材编辑完成！', {
                        skin: 'layui-layer-molv'    //样式类名? 自定义样式
                        ,closeBtn: 1                    //是否显示关闭按钮
                        ,anim: 1                         //动画类型
                        ,btn: ['确定']                   //按钮
                        ,icon: 6
                        ,yes:function(){
                            // 关闭open页面
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.location.reload(index);
                        }
                    });
                } else {
                    layer.alert('请重试！', {
                        skin: 'layui-layer-molv'    //样式类名? 自定义样式
                        ,closeBtn: 1		//是否显示关闭按钮
                        ,anim: 1                    //动画类型
                        ,btn: ['确定']              //按钮
                        ,icon: 6
                    });
                }
            }
        });

        return false;
    });
});