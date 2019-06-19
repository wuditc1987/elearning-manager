package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysRoleMenuExample;
import com.elearning.framework.domain.SysRoleMenuKey;
import java.util.List;

public interface SysRoleMenuMapper {
    int countByExample(SysRoleMenuExample example);

    int deleteByPrimaryKey(SysRoleMenuKey key);

    int insert(SysRoleMenuKey record);

    int insertSelective(SysRoleMenuKey record);

    List<SysRoleMenuKey> selectByExample(SysRoleMenuExample example);
}