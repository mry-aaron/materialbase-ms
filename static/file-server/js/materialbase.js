$(function () {
    // 定义平台
    var pcOrMobile = function() {
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
    if(pcOrMobile.isAndroid || pcOrMobile.isPhone || pcOrMobile.isTablet){
        $(".layui-form-item .layui-inline").css("width", "35rem");
    }
    // 窗口监听
    var resize_flag = true;
    window.onresize = function () {
        // 获取窗口宽度
        var $width = $(window).width();
        if($width <= 950 && resize_flag){
            $(".layui-form-item .layui-inline").css("width", "30rem");
            resize_flag = false;
        } else if ($width >= 950 && !resize_flag){
            $(".layui-form-item .layui-inline").css("width", "45%");
            resize_flag = true;
        }
    }
});