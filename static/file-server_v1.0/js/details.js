/* 展示素材详情 */
    var filePath_url = "http://192.168.0.125/images/upload/materialbase/picture/";
    $(function () {
        /* 获取首页传递的id（用于查询素材详情）*/
        var reqUrl = decodeURIComponent(window.location.href);
        var id = reqUrl.substring(reqUrl.indexOf("=")+1);
        /* 素材详情 */
        $.ajax({
            type: 'post',
            url: '/getdetails/'+id,
            success: function (data) {
                var jsonObj = JSON.parse(data);
                var details = jsonObj[0];
                /* 大图展示 */
                $(".box_img").attr({"src":filePath_url + details["materialPath"], "title":details["productName"]});
                /* 小图展示 */
                $(".list_ul").html(null);
                var liStr = [];
                $.each(jsonObj, function (i, v) {
                    liStr.push('<li><a href="details.html?id='+ v["id"] +'"><img src="'+ (filePath_url+v["materialPath"]) +'" title="'+ v["productName"] +'"></a></li>');
                });
                $(".list_ul").html(liStr.join(''));
                /* 素材详情 */
                var pathSplit = details["materialPath"].split(".");
                $(".media_span").html(details["mediaBean"]["mName"]);
                $(".style_span").html(details["smStyleBean"]["sName"]);
                $(".platform_span").html(details["platform"]);
                $(".size_span").html(details["size"]);
                $(".format_span").html(pathSplit[1]);
                $(".upload_time").html(details["entryTime"]);
                $(".down_btn").attr("href", "/download?id="+pathSplit[0]+"&format="+pathSplit[1]+"&key="+details["id"]);
            }
        });
        /* 推荐素材 */
        $.ajax({
            type: 'post',
            url: encodeURI('/getrecommend'),
            success: function (data) {
                $(".ml_kin_list").html(null);
                var appendStr = [];
                $.each(JSON.parse(data), function (i, v) {
                    appendStr.push('<div class="ml_kin_card">');
                    appendStr.push('<img src="'+ (filePath_url+v["materialPath"]) +'" alt="">');
                    appendStr.push('<div><a href="details.html?id='+ v["id"] +'">'+ v["productName"] +'</a></div></div>');
                });
                $(".ml_kin_list").html(appendStr.join(''));
            }
        });
    });
    /* 鼠标悬浮动作 */
    $(function () {
        $(".list_ul").on("mouseover", "li", function () {
            var sv = $(this).find("a img").attr("src");
            $(".box_img").attr("src", sv);
        });
    });
    /* 翻页动作 */
    $(function () {
        var $_this = $(".box_list");
        var x = 0;
        var anm = true;
        $_this.scrollTop();
        //点击上
        $(".details_ul").on("click", ".box_up_c", function () {
            var imgW = $_this.find('.list_ul li').height();
            if (anm) {
                anm = false;
                x--;
                if (x < 0) {
                    $_this.scrollTop((imgW + 12) * ++x);
                }
                move(imgW);
            }
        });
        //点击下
        $(".details_ul").on("click", ".box_down_c", function () {
            var imgW = $_this.find('.list_ul li').height();
            if (anm) {
                anm = false;
                x++;
                if (x >= $_this.find('.list_ul li').length - 2) {
                    x--;
                }
                move(imgW);
            }
        });
        function move(_imgW) {
            $_this.stop().animate({scrollTop: (_imgW + 12) * x}, function () {
                anm = true;
            });
        }
    });