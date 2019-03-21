package net.qicp.aaron;

import net.qicp.aaron.component.TaskThreadPoolProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan(value = "net.qicp.aaron.mapper")
@EnableConfigurationProperties({TaskThreadPoolProperties.class})
@EnableAsync
@EnableCaching
@SpringBootApplication
public class MaterialbaseMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialbaseMsApplication.class, args);
    }

}
