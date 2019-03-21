layui.use("table");

// ����ļ���С����
$(function () {
    $("#upload_file").on("change", function () {
        // ��֤�ļ���С���ܳ���50MB
        var f = document.getElementById("upload_file").files;
        if(f[0].size / 1024 > 50 * 1024){
            layui.use('layer', function(){
                var active = {
                    confirmTrans: function(){
                        layer.msg('�ļ����ܳ���50MB' , {
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

/* ��Ⱦselect���� */
$(function () {
    // �����زķ��
    loadSelectItem("POST", "/getstyle", "select[name='smStyleId']", "sName");
    // �����ز�����
    loadSelectItem("POST", "/gettype", "select[name='smTypeId']", "tName");
    // ����ý��
    loadSelectItem("POST", "/getmedia", "select[name='mediaId']", "mName");
});

/* ����¼���� */
$(function () {
    function recordResult(_msg) {
        layui.use('layer', function(){
            var active = {
                confirmTrans: function(){
                    layer.msg(_msg , {
                        time: 2000, //2s���Զ��ر�
                        //btn: ['ȷ��']
                    });
                }
            };
            active['confirmTrans'] ? active['confirmTrans'].call(this) : '';
        });
    }
    var $result = $(".record_result").html();
    if($result == "success"){
        recordResult("�ز�¼��ɹ���<br/>��������Ĳ�����");
    } else if($result == "error"){
        recordResult('�ز�¼��ʧ�ܣ�<br/>������¼����Ϣ��');
    }
});