// 用于将数据传递到编辑页面
    var materialData;
    $(function () {
        // 定义消息弹框
        function msgbox(_msg){
            layui.use('layer', function(){
                // 定义/使用弹框
                var active = {
                    confirmTrans: function(){
                        layer.msg(_msg , {
                            time: 2000 //2s后自动关闭
                        });
                    }
                };
                active['confirmTrans'] ? active['confirmTrans'].call(this) : '';
            });
        }
        // 页面加载全部素材信息
        layui.use('table', function(){
            var table = layui.table;
            table.render({
                elem: '#materialList'
                ,url:'/findmaterial'
                ,cols: [
                    [
                        {field:'id',            width:50,        title: 'ID',     sort: true}
                        ,{field:'cName',        width:100,       title: '公司名'}
                        ,{field:'productName',  width:100,       title: '产品名'}
                        ,{field:'productTheme', width:100,       title: '产品题材'}
                        ,{field:'sName',        width:80,        title: '风格'}
                        ,{field:'size',         width:100,       title: '素材尺寸',sort: true}
                        ,{field:'tName',        width:100,       title: '素材类型'}
                        ,{field:'mName',        width:100,       title: '媒体'}
                        ,{field:'platform',     width:100,       title: '平台'}
                        ,{field:'starLevel',    width:80,        title: '星级',   sort: true}
                        ,{field:'like',         width:80,        title: '点赞',   sort: true}
                        ,{field:'download',     width:80,        title: '下载',   sort: true}
                        ,{field:'browse',       width:80,        title: '浏览',   sort: true}
                        ,{field:'entryTime',    width:180,       title: '录入时间', sort: true}
                        ,{fixed: 'right',       width:115,       title: '操作',   toolbar: '#barOps'}
                    ]
                ]
                ,page: true
            });
            // 监听行工具事件
            table.on('tool(test)', function(obj){
                var data = obj.data;
                materialData = data;
                // 删除操作
                if(obj.event === 'del'){
                    layer.confirm('真的删除行么？', function(index){
                        obj.del(); // 删除当前行
                        layer.close(index); // 关闭提示框
                        // 删除数据库数据
                        $.post("/delmaterial", "id="+data["id"], function (data) {
                            if (data){
                                msgbox("删除成功！");
                            } else {
                                msgbox("请重试！");
                            }
                        });

                    });
                } else if(obj.event === 'edit'){ // 编辑操作
                    layui.use(['laypage', 'layer'], function() {
                        var laypage = layui.laypage, layer = layui.layer;
                        layer.open({
                            type: 2,
                            title: false,
                            area: ['60rem', '40rem'],
                            content: ['/edit.html', 'no']
                        });
                        $("#layui-layer-iframe").css("overflow", "hidden");
                    });
                }
            });
        });
        // form异步提交
        $("#search_btn").on("click", function () {
            // 获取所有参数/值
            var d = $("#search_form").serialize();
            // 渲染数据
            layui.use('table', function(){
                var table = layui.table;
                table.render({
                    elem: '#materialList'
                    ,url: '/findmaterial?' + d
                    ,cols: [
                        [
                            {field:'id',            width:50,        title: 'ID',     sort: true}
                            ,{field:'cName',        width:100,       title: '公司名'}
                            ,{field:'productName',  width:100,       title: '产品名'}
                            ,{field:'productTheme', width:100,       title: '产品题材'}
                            ,{field:'sName',        width:80,        title: '风格'}
                            ,{field:'size',         width:100,       title: '素材尺寸',sort: true}
                            ,{field:'tName',        width:100,       title: '素材类型'}
                            ,{field:'mName',        width:100,       title: '媒体'}
                            ,{field:'platform',     width:100,       title: '平台'}
                            ,{field:'starLevel',    width:80,        title: '星级',   sort: true}
                            ,{field:'like',         width:80,        title: '点赞',   sort: true}
                            ,{field:'download',     width:80,        title: '下载',   sort: true}
                            ,{field:'browse',       width:80,        title: '浏览',   sort: true}
                            ,{field:'entryTime',    width:180,       title: '录入时间', sort: true}
                            ,{fixed: 'right',       width:115,       title: '操作',   toolbar: '#barOps'}
                        ]
                    ]
                    ,page: true
                });
            });

            return false;
        });
    });
    /* 加载下拉列表数据 */
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