layui.use('table');
/* ��Ⱦselect���� */
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
$(function () {
    /* ��ʼ��ҳ������ */
    initData(JSON.parse(JSON.stringify(parent.materialData)));
    /* �첽�ύ */
    $("#edit_btn").on("click", function () {
        var d = $("#edit_form").serialize();
        $.ajax({
            type: 'post',
            url: '/editmaterial',
            data: d,
            success: function (data) {
                if(data){
                    layer.alert('�زı༭��ɣ�', {
                        skin: 'layui-layer-molv'    //��ʽ����? �Զ�����ʽ
                        ,closeBtn: 1                    //�Ƿ���ʾ�رհ�ť
                        ,anim: 1                         //��������
                        ,btn: ['ȷ��']                   //��ť
                        ,icon: 6
                        ,yes:function(){
                            // �ر�openҳ��
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.location.reload(index);
                        }
                    });
                } else {
                    layer.alert('�����ԣ�', {
                        skin: 'layui-layer-molv'    //��ʽ����? �Զ�����ʽ
                        ,closeBtn: 1		//�Ƿ���ʾ�رհ�ť
                        ,anim: 1                    //��������
                        ,btn: ['ȷ��']              //��ť
                        ,icon: 6
                    });
                }
            }
        });

        return false;
    });
});