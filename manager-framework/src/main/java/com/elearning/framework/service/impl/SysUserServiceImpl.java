package com.elearning.framework.service.impl;

import com.elearning.common.annotation.DataSource;
import com.elearning.common.config.datasource.constants.DataSourceType;
import com.elearning.framework.domain.SysUser;
import com.elearning.framework.mapper.SysUserMapper;
import com.elearning.framework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findById(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @DataSource(type = DataSourceType.CLUSTER)
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }
}
