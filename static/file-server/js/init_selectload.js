    /*
     * 初始化页面数据
     * @param _jsonObject   初始化数据
     */
    function initData(_jsonObject) {
        layui.use(['form'], function() {
            var form = layui.form;
            form.val('example', _jsonObject);
        });
    }
    /*
     * 封装加载select下拉列表
     * @param _method   请求方式
     * @param _url      请求地址
     * @param _select   select选择器
     * @param _val      返回数据的展示属性
     */
    function loadSelectItem(_method, _url, _select, _val) {
        $.ajax({
            type: _method,
            url: _url,
            success:function(data){
                $(_select).empty();
                var jsonObj = JSON.parse(data);
                // 拼接下拉选项
                var opts = "";
                opts += "<option value='0'>请选择</option>";
                for (var i = 0; i < jsonObj.length; i++){
                    opts += "<option value='"+jsonObj[i]["id"]+"'>"+jsonObj[i][_val]+"</option>";
                }
                $(_select).append(opts);
            }
        })
    }

