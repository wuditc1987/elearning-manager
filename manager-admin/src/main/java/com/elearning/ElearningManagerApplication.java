package com.elearning;

import com.elearning.common.utils.BootUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring 启动类
 * @author wudi
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ElearningManagerApplication {

    private static final Logger log = LoggerFactory.getLogger(ElearningManagerApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ElearningManagerApplication.class);
        if(log.isDebugEnabled()){
            log.info(String.format("context = %s" , context));
        }
        BootUtils.setApplicationContext(context);
    }

}
