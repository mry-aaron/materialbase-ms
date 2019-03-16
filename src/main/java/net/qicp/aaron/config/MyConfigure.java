package net.qicp.aaron.config;

import net.qicp.aaron.component.LoginHandlerInterceptor;
import net.qicp.aaron.filter.EncodingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
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
        // 访问首页
        registry.addViewController("/").setViewName("index.html");
        // 访问登录注册页面
        registry.addViewController("/sign.html").setViewName("pages/iframe/sign.html");
        // 访问用户管理页面
        registry.addViewController("/umanager.html").setViewName("pages/usermanager.html");
        // 访问详情页
        registry.addViewController("/details.html").setViewName("pages/details.html");
        // 录入素材
        registry.addViewController("/input.html").setViewName("pages/materialbaseinput.html");
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
        interceptor.excludePathPatterns(Arrays.asList("/","/sign.html"));
        interceptor.excludePathPatterns("/error");
        interceptor.excludePathPatterns("/reg","/verifi","/finduser","/sendverificode","/login","/logintele");
        // 设置拦截(拦截全部)
        interceptor.addPathPatterns("/**");

    }

}
