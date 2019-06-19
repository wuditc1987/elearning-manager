package com.elearning.admin.web;

import com.elearning.common.annotation.DataSource;
import com.elearning.common.config.datasource.constants.DataSourceType;
import com.elearning.framework.domain.SysUser;
import com.elearning.framework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping(value = "/{name}",method = RequestMethod.POST)
    public String getName(@PathVariable String name){
        return name;
    }

    @ResponseBody
    @RequestMapping(value = "/one")
    public SysUser findOne(@RequestParam Integer id){
        return sysUserService.findById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<SysUser> findAll(){
        return sysUserService.findAll();
    }
}
