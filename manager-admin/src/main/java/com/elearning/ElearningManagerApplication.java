package com.elearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Spring 启动类
 * @author wudi
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@MapperScan(basePackages = "com.elearning.*.mapper")
public class ElearningManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElearningManagerApplication.class);
    }

}
