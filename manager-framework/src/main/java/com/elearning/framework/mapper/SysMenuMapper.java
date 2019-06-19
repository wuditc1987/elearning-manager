package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysMenu;
import com.elearning.framework.domain.SysMenuExample;
import java.util.List;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}