package net.qicp.aaron.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Aaron
 * @Description 登录拦截器
 * @Version 1.0
 * @Date 13:45 2019/3/13
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    // 创建日志对象（测试使用）
    private Logger log = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取session用户名
        Object userName = request.getSession().getAttribute("userName");
        Object telephone = request.getSession().getAttribute("telephone");

        log.debug(userName + " : " + telephone + "------LoginHandlerInterceptor中拦截到Session中用户名------");

        if (userName != null || telephone != null) {
            return true;
        } else {
            // 设置消息
            request.getSession().setAttribute("sign_msg", "没有权限，请登录！");
            // 重定向到登录页面
            response.sendRedirect("/");
            return false;
        }
    }
}
