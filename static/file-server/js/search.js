// ���ڽ����ݴ��ݵ��༭ҳ��
    var materialData;
    $(function () {
        // ������Ϣ����
        function msgbox(_msg){
            layui.use('layer', function(){
                // ����/ʹ�õ���
                var active = {
                    confirmTrans: function(){
                        layer.msg(_msg , {
                            time: 2000 //2s���Զ��ر�
                        });
                    }
                };
                active['confirmTrans'] ? active['confirmTrans'].call(this) : '';
            });
        }
        // ҳ�����ȫ���ز���Ϣ
        layui.use('table', function(){
            var table = layui.table;
            table.render({
                elem: '#materialList'
                ,url:'/findmaterial'
                ,cols: [
                    [
                        {field:'id',            width:50,        title: 'ID',     sort: true}
                        ,{field:'cName',        width:100,       title: '��˾��'}
                        ,{field:'productName',  width:100,       title: '��Ʒ��'}
                        ,{field:'productTheme', width:100,       title: '��Ʒ���'}
                        ,{field:'sName',        width:80,        title: '���'}
                        ,{field:'size',         width:100,       title: '�زĳߴ�',sort: true}
                        ,{field:'tName',        width:100,       title: '�ز�����'}
                        ,{field:'mName',        width:100,       title: 'ý��'}
                        ,{field:'platform',     width:100,       title: 'ƽ̨'}
                        ,{field:'starLevel',    width:80,        title: '�Ǽ�',   sort: true}
                        ,{field:'like',         width:80,        title: '����',   sort: true}
                        ,{field:'download',     width:80,        title: '����',   sort: true}
                        ,{field:'browse',       width:80,        title: '���',   sort: true}
                        ,{field:'entryTime',    width:180,       title: '¼��ʱ��', sort: true}
                        ,{fixed: 'right',       width:115,       title: '����',   toolbar: '#barOps'}
                    ]
                ]
                ,page: true
            });
            // �����й����¼�
            table.on('tool(test)', function(obj){
                var data = obj.data;
                materialData = data;
                // ɾ������
                if(obj.event === 'del'){
                    layer.confirm('���ɾ����ô��', function(index){
                        obj.del(); // ɾ����ǰ��
                        layer.close(index); // �ر���ʾ��
                        // ɾ�����ݿ�����
                        $.post("/delmaterial", "id="+data["id"], function (data) {
                            if (data){
                                msgbox("ɾ���ɹ���");
                            } else {
                                msgbox("�����ԣ�");
                            }
                        });

                    });
                } else if(obj.event === 'edit'){ // �༭����
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
        // form�첽�ύ
        $("#search_btn").on("click", function () {
            // ��ȡ���в���/ֵ
            var d = $("#search_form").serialize();
            // ��Ⱦ����
            layui.use('table', function(){
                var table = layui.table;
                table.render({
                    elem: '#materialList'
                    ,url: '/findmaterial?' + d
                    ,cols: [
                        [
                            {field:'id',            width:50,        title: 'ID',     sort: true}
                            ,{field:'cName',        width:100,       title: '��˾��'}
                            ,{field:'productName',  width:100,       title: '��Ʒ��'}
                            ,{field:'productTheme', width:100,       title: '��Ʒ���'}
                            ,{field:'sName',        width:80,        title: '���'}
                            ,{field:'size',         width:100,       title: '�زĳߴ�',sort: true}
                            ,{field:'tName',        width:100,       title: '�ز�����'}
                            ,{field:'mName',        width:100,       title: 'ý��'}
                            ,{field:'platform',     width:100,       title: 'ƽ̨'}
                            ,{field:'starLevel',    width:80,        title: '�Ǽ�',   sort: true}
                            ,{field:'like',         width:80,        title: '����',   sort: true}
                            ,{field:'download',     width:80,        title: '����',   sort: true}
                            ,{field:'browse',       width:80,        title: '���',   sort: true}
                            ,{field:'entryTime',    width:180,       title: '¼��ʱ��', sort: true}
                            ,{fixed: 'right',       width:115,       title: '����',   toolbar: '#barOps'}
                        ]
                    ]
                    ,page: true
                });
            });

            return false;
        });
    });
    /* ���������б����� */
    $(function () {
        // ���ع�˾����
        loadSelectItem("POST", "/getcompany", "select[name='companyId']", "cName");
        // �����زķ��
        loadSelectItem("POST", "/getstyle", "select[name='smStyleId']", "sName");
        // �����ز�����
        loadSelectItem("POST", "/gettype", "select[name='smTypeId']", "tName");
        // ����ý��
        loadSelectItem("POST", "/getmedia", "select[name='mediaId']", "mName");
    });