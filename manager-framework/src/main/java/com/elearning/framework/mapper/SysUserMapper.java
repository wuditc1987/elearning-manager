package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysUser;
import com.elearning.framework.domain.SysUserExample;
import java.util.List;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> findAll();
}