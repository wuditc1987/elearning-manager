package com.elearning.admin.web;

import com.elearning.common.annotation.DataSource;
import com.elearning.common.config.datasource.constants.DataSourceType;
import com.elearning.common.utils.BootUtils;
import com.elearning.framework.domain.SysUser;
import com.elearning.framework.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户api")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "根据id查询用户",response = SysUser.class)
    @ResponseBody
    @RequestMapping(value = "/one")
    public SysUser findOne(@RequestParam Integer id){
        return sysUserService.findById(id);
    }

    @ApiOperation(value = "根据所有用户",response = List.class,httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<SysUser> findAll(){
        return sysUserService.findAll();
    }

}
