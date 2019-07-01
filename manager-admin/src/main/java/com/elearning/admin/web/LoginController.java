package com.elearning.admin.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wudi
 * @version 1.0
 * @Description TODO
 * @date 2019/6/19 2:07 PM
 */
@RestController
public class LoginController {

    /**
     * 登录方法
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "success";
    }


}
