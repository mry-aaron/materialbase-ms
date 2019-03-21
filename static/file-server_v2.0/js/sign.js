$(function () {
    /* 出现浮层不滚动 */
    $('body').css('overflow','hidden');
    /* 默认加载样式 */
    $(".sign_hearder span").addClass("tabstyle");
    $(".signup_content").css("display","none");
    /* Tab切换*/
    $(".login_hearder div").on("click", function () {
        /* 登录注册样式切换 */
        $(this).find("span").addClass("tabstyle");
        $(this).siblings().find("span").removeClass("tabstyle");
        /* 登录注册内容切换 */
        var index = $(this).index();
        if(index == 0){
            $(".sign_content").css("display","block").siblings().css("display","none");
        } else if (index == 1){
            $(".signup_content").css("display","block").siblings().css("display","none");
        }
    
    });
    /* 手机与账号登录切换 */
    $("#code-login").on("click", function () {
        $(".login_code").css("display","block");
        $(".login_phone").css("display","none");
    });
    $("#phone-login").on("click", function () {
        $(".login_code").css("display","none");
        $(".login_phone").css("display","block");
    });
    $(".tab_phone_reg").on("click", function () {
	$(".phone_reg").css("display","block");
	$(".code_reg").css("display","none");
    });
    $(".tab_code_reg").on("click", function () {
	$(".phone_reg").css("display","none");
	$(".code_reg").css("display","block");
    });
    
});

    /* 账号注册 */
    $(function () {
        var $username; // 账号
        var flag = false;
        /* 用户名验证 */
        $(".cr_username").on("blur", function () {
            $username = $(".cr_username").val();
            if($username != null && $username != ""){
                $.ajax({
                    type: "post",
                    url: "/finduser",
                    data: "name=" + $username,
                    success: function (data) {
                        if(data == "0") {
                            $(".signup_err").css("display","block");
                            $(".signup_err span").html("用户名已存在！");
                        } else {
                            $(".signup_err").css("display","none");
                            flag = true;
                        }
                    }
                });
            }
        });
        /* 绑定注册按钮单击事件 */
        $("#cr_reg").on("click", function () {
            // 获取账号/密码
            var $password = $(".cr_password").val();
            // 提交请求
            if( $username != null && $password != null && flag){
                $.ajax({
                    type: "post",
                    url: "/reg",
                    data: "name=" + $username + "&password=" + $password,
                    success: function () {
                        alert("用户注册成功！");
                        flag = false;
                        window.location.reload();
                    }
                });
            }
        });
    });
    /* 手机号注册 */
    $(function () {
        var $telephone;
        var $vlidatecode;
        var click_disabled = true;
        /* 手机号验证 */
        $(".cpn_val").on("blur", function () {
            $telephone = $(this).val();
            if($telephone != null && $telephone != ""){
                $.ajax({
                    type: "post",
                    url: "/finduser",
                    data: "telephone=" + $telephone,
                    success: function (data) {
                        if(data == "0") {
                            $(".signup_err").css("display","block");
                            $(".signup_err span").html("手机号已注册！");
                        } else {
                            $(".signup_err").css("display","none");
                            flag = true;
                        }
                    }
                });
            }
        });
        /* 发送手机验证码 */
        $("#send_code").on("click", function () {
            if($telephone != null && $telephone != "" && click_disabled){
                $.ajax({
                    type: "post",
                    url: "/sendverificode",
                    data: "telephone=" + $telephone,
                    success: function (data) {
                        if (data["reason"] == "操作成功") {
                            click_disabled = false;
                            var i = 60;
                            var l = setInterval(function () {
                                $("#send_code").html("重新发送 "+(--i)+"s");
                                if (i == 0){
                                    clearInterval(l);
                                    click_disabled = true;
                                    $("#send_code").html("重新发送");
                                }
                            }, 1000);
                        }
                    }
                });
            }
        });
        /* 给注册按钮绑定单击事件 */
        $("#cpn_reg").on("click", function () {
            // 获取验证码
            $vlidatecode = $(".vc_val").val();
            // 提交请求
            if( $telephone != null && $vlidatecode != null && flag){
                $.ajax({
                    type: "post",
                    url: "/verifi",
                    data: "code=" + $vlidatecode,
                    success: function (data) {
                        if(data == "0") {
                            $(".signup_err").css("display","block");
                            $(".signup_err span").html("验证码有误！");
                        } else if( data == "1") {
                            $.post("/reg","telephone="+$telephone, function () {
                                alert("用户注册成功！");
                                flag = false;
                                window.location.reload();
                            });
                        }
                    }
                });
            }
        });
    });
    /* 账号登录 */
    $(function () {
        $("#code_login").on("click", function () {
            // 获取用户名及密码
            var loginInfo = {};
            loginInfo["name"] = $(".cl_username").val();
            loginInfo["password"] = $(".cl_password").val();
            // 登录请求
            $.post("/login", "param="+ JSON.stringify(loginInfo), function (data) {
                var jsonObject = JSON.parse(data);
                if (jsonObject["code"] == 0) {
                    $(".sign_err").html("<i></i><span>用户名/密码有误！</span>");
                } else if (jsonObject["code"] == 1) { // 成功
                    $(".sign_err").html(null);
                    parent.location.href="/";
                }
            });
        });
    });
    /* 手机号登录 */
    $(function () {
        var $tele;
        var click_disabled1 = true;
        /* 验证手机号是否注册 */
        $(".cell_phone_number_icon").on("blur", function () {
            $tele = $(this).val();
            if($tele != null && $tele != ""){
                $.ajax({
                    type: "post",
                    url: "/finduser",
                    data: "telephone=" + $tele,
                    success: function (data) {
                        if(data == "1") {
                            $(".sign_err").html("<i></i><span>手机号未注册！</span>");
                        } else if (data == "0"){
                            $(".sign_err").html(null);
                        }
                    }
                });
            }
        });
        /* 发送验证码 */
        $("#send_code_login").on("click", function () {
            if($tele != null && $tele != "" && click_disabled1){
                $.ajax({
                    type: "post",
                    url: "/sendverificode",
                    data: "telephone=" + $tele,
                    success: function (data) {
                        if (data["reason"] == "操作成功") {
                            click_disabled1 = false;
                            var i = 60;
                            var l = setInterval(function () {
                                $("#send_code_login").html("重新发送 "+(--i)+"s");
                                if (i == 0){
                                    clearInterval(l);
                                    click_disabled1 = true;
                                    $("#send_code_login").html("重新发送");
                                }
                            }, 1000);
                        }
                    }
                });
            }
        });
        /* 登录按钮绑定单击事件 */
        $("#phone_login").on("click", function () {
            /* 获取用户输入的验证码 */
            var $vcode = $(".vli_code").val();
            /* 发送请求 */
            if($tele != null && $vcode != null) {
                $.ajax({
                    type: "post",
                    url: "/verifi",
                    data: "code=" + $vcode,
                    success: function (data) {
                        if(data == "0") {
                            $(".sign_err").html("<i></i><span>验证码有误！</span>");
                        } else if( data == "1") {
                            var tc = {};
                            tc["telephone"] = $tele;
                            tc["code"] = $vcode;
                            $.post("/logintele","param="+JSON.stringify(tc), function (data) {
                                var jsonObject = JSON.parse(data);
                                if (jsonObject["code"] == 0) {
                                    $(".sign_err").html("<i></i><span>登录失败！</span>");
                                } else if (jsonObject["code"] == 1) { // 成功
                                    $(".sign_err").html(null);
                                    parent.location.href="/";
                                }
                            });
                        }
                    }
                });
            }
        });

    });


/* 定义弹框函数 */
    function open() {
        layui.use(['laypage', 'layer'], function() {
            var laypage = layui.laypage, layer = layui.layer;
            layer.open({
                type: 2,
                title: false,
		scrollbar: false,
                /*maxmin: true,
                  shadeClose: true, //点击遮罩关闭层*/
                area: ['30rem', '32.5rem'],
                content: ['/sign.html', 'no']
            });
            $("#layui-layer-iframe").css("overflow", "hidden");
        });
    }

    /* 绑定单击事件弹出登录框 */
     $('#sign').on('click', function () {
        open();
     });

     /* 没登录状态访问其他页面时，弹出登录框 */
    $(function () {
        var $hidden_msg = $(".hidden_msg").html();
        if($hidden_msg != null && $hidden_msg != ""){
            open();
        }
    });


    /* 绑定回车事件 */
    function keyLogin(){
        if (event.keyCode==13) {  /*回车键的键值为13*/
            /* 调用登录按钮的登录事件 */
            var login_btn = document.getElementsByClassName("login_btn");
            login_btn[0].click();
            login_btn[1].click();
            /* 调用注册按钮的提交事件 */
            var reg_btn = document.getElementsByClassName("regi_btn");
            reg_btn[0].click();
            reg_btn[1].click();
        }
    }
/* 禁用其他登录 */
    $(".other_login,.other_sign").on("click", "a", function () {
        layui.use('layer', function(){
            /* 定义/使用弹框 */
            var active = {
                confirmTrans: function(){
                    layer.msg('请使用手机号/账号注册登录！' , {
                        offset:['10rem', '5rem'],
			shift: 6,
			time: 2000
                    });
                }
            };
            active['confirmTrans'] ? active['confirmTrans'].call(this) : '';
        });
    });
