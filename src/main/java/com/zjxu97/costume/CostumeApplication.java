package com.zjxu97.costume;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author thisxzj
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.zjxu97.course.mapper")
public class CostumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CostumeApplication.class, args);
    }

}
