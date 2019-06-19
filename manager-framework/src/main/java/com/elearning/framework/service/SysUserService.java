package com.elearning.framework.service;

import com.elearning.framework.domain.SysUser;

import java.util.List;

public interface SysUserService {

    SysUser findById(Integer id);

    List<SysUser> findAll();
}
