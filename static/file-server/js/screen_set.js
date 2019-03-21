var screen_flag = true;
    $(function () {
        // 定义平台
        var os = function() {
            var ua = navigator.userAgent,
                isWindowsPhone = /(?:Windows Phone)/.test(ua),
                isSymbian = /(?:SymbianOS)/.test(ua) || isWindowsPhone,
                isAndroid = /(?:Android)/.test(ua),
                isFireFox = /(?:Firefox)/.test(ua),
                isChrome = /(?:Chrome|CriOS)/.test(ua),
                isTablet = /(?:iPad|PlayBook)/.test(ua) || (isAndroid && !/(?:Mobile)/.test(ua)) || (isFireFox && /(?:Tablet)/.test(ua)),
                isPhone = /(?:iPhone)/.test(ua) && !isTablet,
                isPc = !isPhone && !isAndroid && !isSymbian;
            return {
                isTablet: isTablet,
                isPhone: isPhone,
                isAndroid : isAndroid,
                isPc : isPc
            };
        }();
        // 若是手机
        if(os.isAndroid || os.isPhone || os.isTablet){
            var fl = true;
            $("#show_menu").bind("click", function () {
                if(fl){
                    $(".sidebar").addClass("show_menu");
                    fl = false;
                } else {
                    $(".sidebar").removeClass("show_menu");
                    fl = true;
                }
            });
        }
        // 窗口监听
        window.onresize = function () {
            // 获取窗口宽度
            var $width = $(window).width();
            // 添加单击事件
            if($width <= 990 && screen_flag){
                var fl = true;
                $("#show_menu").bind("click", function () {
                    if(fl){
                        $(".sidebar").addClass("show_menu");
                        fl = false;
                    } else {
                        $(".sidebar").removeClass("show_menu");
                        fl = true;
                    }
                });
                screen_flag = false;
            } else if ($width >= 990 && !screen_flag){
                $(".sidebar").removeClass("show_menu");
                $("#show_menu").unbind('click');
                screen_flag = true;
            }
        }
           /* PC端窗口小于990宽度时菜单添加事件 */
            var $screen_width = $(window).width();
            if($screen_width <= 990){
                var sfl = true;
                $("#show_menu").bind("click", function () {
                    if(sfl){
                        $(".sidebar").addClass("show_menu");
	        sfl = false;
                    } else {
                        $(".sidebar").removeClass("show_menu");
	        sfl = true;
                    }
                });
            } 

    });