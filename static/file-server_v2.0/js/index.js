// 素材路径
    var filePath = "http://materialbase-ms.com/images/upload/materialbase/picture/";
    // 图片轮播
    $(function () {
        $.post("/homebanner", "", function (data) {
            var jsonObj = JSON.parse(data);
            $(".banner_t .boxgallery").html(null);
            for(var v in jsonObj){
                $(".banner_t .boxgallery").append("<div class='panel'>" +
                    "<img src='" + (filePath + jsonObj[v]["materialPath"]) + "'/></div>");
            }
            new BoxesFx( document.getElementById( 'boxgallery' ) );
        });
    });
    // 展示所有素材
    var $this_points_download;
    $(function () {
        var curr = 1;
        /* 获取数据 */
        function getPageData(){
            $.ajax({
                type: 'POST',
                url: '/getallpicsm',
                data: {page:curr,limit: 8},
                dataType: 'json',
                success: function (data) {
                    paged(data);
                }
            });
        };
        /* 实现分页 */
        function paged(_data){
            var jsonData = _data["data"];
            layui.use(['laypage', 'layer'], function() {
                var laypage = layui.laypage, layer = layui.layer;
                laypage.render({
                    elem: 'pages'
                    , count: _data["total"]
                    , layout: ['prev', 'page', 'next', 'count', 'skip']
                    , limit: 8
                    , curr: curr
                    , jump: function (obj, first) {
                        curr = obj.curr;
                        if(!first){ // 排除初始化加载
                            getPageData();
                        }
                        $(".ml_list").html(null);
                        var appendStr = ['<div class="ml_row">'];
                        for(var i = 0; i < jsonData.length; i++){
                            appendStr.push('<div class="ml_card">');
                            appendStr.push('<div class="card_img">');
                            var detailsPath = "details.html?id="+ jsonData[i]["id"];
                            appendStr.push('<a href="'+ detailsPath +'"><img src="'+ (filePath+jsonData[i]["materialPath"]) +'"></a>');
                            appendStr.push('<div class="card_tag">');
                            appendStr.push('<a href="Javascript:void(0)" class="points_a"><i rel="'+ jsonData[i]["id"] +'" class="'+ (jsonData[i]["points"] == 0 ? "icon-follow" : "icon-followed") +'"></i></a>');
                            var namesuffix = jsonData[i]["materialPath"].split(".");
                            var urlPath = "/download?id="+ namesuffix[0] +"&format="+ namesuffix[1] +"&key="+ jsonData[i]["id"] + "&"+new Date().getTime();
                            appendStr.push('<a href="Javascript:void(0)" rel="'+ urlPath +'" class="download_a"><i rel="'+ jsonData[i]["id"] +'" class="icon-download"></i></a>');
                            appendStr.push('</div>');
                            appendStr.push('</div>');
                            appendStr.push('<div class="card_title">');
                            appendStr.push('<p>'+ jsonData[i]["productName"] +'</p>');
                            appendStr.push('<p>');
                            appendStr.push('<i class="follow_icon"></i><span class="follow_count">'+ jsonData[i]["like"] +'</span>');
                            appendStr.push('<i class="download_icon"></i><span class="download_count">'+ jsonData[i]["download"] +'</span>');
                            appendStr.push('</p>');
                            appendStr.push('</div>');
                            appendStr.push('</div>');
                            if((i+1) % 4 == 0){
                                appendStr.push('</div><div class="ml_row">');
                            }
                        }
                        appendStr.push('</div>');
                        $(".ml_list").html(appendStr.join(''));
                    }
                });
            });
        }
        getPageData();
        /* 点赞绑定单击事件 */
        $(".ml_list").on("click", ".ml_row .ml_card .card_img .card_tag .points_a", function () {
            if($(".hidden_userId").text() != "" && $(".hidden_userId").text() != null){
                var no = 0;
                /* 样式 */
                if($(this).find("i").attr("class") == "icon-follow"){
                    $(this).find("i").removeClass("icon-follow");
                    $(this).find("i").addClass("icon-followed");
                    no = 1;
                } else {
                    $(this).find("i").removeClass("icon-followed");
                    $(this).find("i").addClass("icon-follow");
                    no = 0;
                }
                /* 数据 */
                $this_points_download = $(this).parent().parent().siblings(".card_title").find("p").find(".follow_count");
                $.ajax({
                    type: 'POST',
                    url:'/points',
                    data: "id="+$(this).find("i").attr("rel")+"&no="+no,
                    success: function (data) {
                        $this_points_download.html(data);
                    }
                });
            } else {
                location.href="/umanager.html";
            }
        });
        /* 下载绑定单击事件 */
        $(".ml_list").on("click", ".ml_row .ml_card .card_img .card_tag .download_a", function () {
            $this_points_download = $(this).parent().parent().siblings(".card_title").find("p").find(".download_count");
            $.ajax({
                type: 'POST',
                url:'/getdownloadcount',
                data: "id="+$(this).find("i").attr("rel"),
                success: function (data) {
                    $this_points_download.html(parseInt(data)+1);
                }
            });
            location.href=$(this).attr("rel");
        });
    });
