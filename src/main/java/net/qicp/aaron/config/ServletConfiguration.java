package net.qicp.aaron.config;

import net.qicp.aaron.filter.EncodingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @Author Aaron
 * @Description 注册Servlet组件
 * @Version 1.0
 * @Date 19:43 2019/3/15
 */
@Configuration
public class ServletConfiguration implements WebMvcConfigurer {

    @Autowired
    private EncodingFilter encodingFilter;
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(encodingFilter);
        registrationBean.setUrlPatterns(Arrays.asList("/edituser"));
        return registrationBean;
    }


}
