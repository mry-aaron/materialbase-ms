package net.qicp.aaron;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@MapperScan(value = "net.qicp.aaron.mapper")
@SpringBootApplication
public class MaterialbaseMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialbaseMsApplication.class, args);
    }

}
