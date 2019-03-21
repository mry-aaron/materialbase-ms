package net.qicp.aaron.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Aaron
 * @Description Redis配置类
 * @Version 1.0
 * @Date 1:31 2019/3/21
 */
@Configuration
public class RedisConfiguration implements WebMvcConfigurer {

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setDefaultSerializer(jsonRedisSerializer);
        return template;
    }

}
