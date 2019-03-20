package net.qicp.aaron.config;

import net.qicp.aaron.component.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.List;

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
        registry.addViewController("/details.html").setViewName("pages/materialbase/details.html");
        // 录入素材
        registry.addViewController("/record.html").setViewName("pages/materialbase/record.html");
        // 搜索素材
        registry.addViewController("/search.html").setViewName("pages/materialbase/search.html");
        // 编辑素材
        registry.addViewController("/edit.html").setViewName("pages/iframe/edit.html");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
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
        // 页面/静态资源
        List<String> pages = Arrays.asList("/", "/sign.html", "/static/**");
        interceptor.excludePathPatterns(pages);
        // 登录注册请求
        List<String> sign = Arrays.asList("/reg", "/verifi", "/finduser", "/sendverificode", "/login", "/logintele");
        interceptor.excludePathPatterns(sign);
        // 首页素材加载请求
        List<String> home = Arrays.asList("/homebanner", "/getallpicsm");
        interceptor.excludePathPatterns(home);
        // 请求错误地址
        interceptor.excludePathPatterns("/error");
        // 设置拦截(拦截全部)
        interceptor.addPathPatterns("/**");

    }

}
