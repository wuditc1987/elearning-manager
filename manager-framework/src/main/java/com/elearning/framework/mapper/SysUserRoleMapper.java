package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysUserRole;
import com.elearning.framework.domain.SysUserRoleExample;
import java.util.List;

public interface SysUserRoleMapper {
    int countByExample(SysUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}