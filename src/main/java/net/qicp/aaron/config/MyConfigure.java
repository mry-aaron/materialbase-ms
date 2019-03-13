package net.qicp.aaron.config;

import net.qicp.aaron.component.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;

@Configuration
public class MyConfigure implements WebMvcConfigurer {

    /**
     * 配置视图解析
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/sign.html").setViewName("pages/iframe/sign.html");
    }

    /**
     * 配置拦截器拦截请求
     *
     * @param registry
     */
    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration interceptor = registry.addInterceptor(loginHandlerInterceptor);
        // 设置拦截排除
        interceptor.excludePathPatterns(Arrays.asList("/","/index.html","/sign.html"));
        interceptor.excludePathPatterns("/error");
        interceptor.excludePathPatterns("/reg","/verifi","/finduser","/sendverificode");
        // 设置拦截(拦截全部)
        interceptor.addPathPatterns("/**");

    }
}
