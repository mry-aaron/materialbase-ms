package net.qicp.aaron.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author Aaron
 * @Description 编码格式过滤器
 * @Version 1.0
 * @Date 19:28 2019/3/15
 */
@Component
public class EncodingFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(EncodingFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("======执行过滤========");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
