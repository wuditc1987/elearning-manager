package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysRole;
import com.elearning.framework.domain.SysRoleExample;
import java.util.List;

public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}