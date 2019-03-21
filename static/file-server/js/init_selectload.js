    /*
     * ��ʼ��ҳ������
     * @param _jsonObject   ��ʼ������
     */
    function initData(_jsonObject) {
        layui.use(['form'], function() {
            var form = layui.form;
            form.val('example', _jsonObject);
        });
    }
    /*
     * ��װ����select�����б�
     * @param _method   ����ʽ
     * @param _url      �����ַ
     * @param _select   selectѡ����
     * @param _val      �������ݵ�չʾ����
     */
    function loadSelectItem(_method, _url, _select, _val) {
        $.ajax({
            type: _method,
            url: _url,
            success:function(data){
                $(_select).empty();
                var jsonObj = JSON.parse(data);
                // ƴ������ѡ��
                var opts = "";
                opts += "<option value='0'>��ѡ��</option>";
                for (var i = 0; i < jsonObj.length; i++){
                    opts += "<option value='"+jsonObj[i]["id"]+"'>"+jsonObj[i][_val]+"</option>";
                }
                $(_select).append(opts);
            }
        })
    }

