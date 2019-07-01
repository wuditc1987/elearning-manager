package com.elearning.framework.service.impl;

import com.elearning.common.annotation.DataSource;
import com.elearning.common.config.datasource.constants.DataSourceType;
import com.elearning.framework.domain.SysUser;
import com.elearning.framework.mapper.SysUserMapper;
import com.elearning.framework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wudi
 * @version 1.0
 * @Description 用户操作Service实现类
 * @date 2019/6/17 1:40 PM
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Cacheable(value = {"syler"})
    @Override
    public SysUser findById(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    @DataSource(type = DataSourceType.CLUSTER)
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }
}
