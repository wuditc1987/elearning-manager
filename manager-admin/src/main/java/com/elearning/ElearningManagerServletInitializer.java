package com.elearning;

import com.elearning.common.utils.BootUtils;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author wudi
 * @version 1.0
 * @Description 在WEB容器中进行部署
 * @date 2019/6/20 1:40 PM
 */
public class ElearningManagerServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        BootUtils.setApplicationContext(builder.context());
        return builder.sources(ElearningManagerApplication.class);
    }
}
