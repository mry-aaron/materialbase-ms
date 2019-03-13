package net.qicp.aaron;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "net.qicp.aaron.mapper")
@SpringBootApplication
public class MaterialbaseMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialbaseMsApplication.class, args);
    }

}
