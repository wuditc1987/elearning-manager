package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysDict;
import com.elearning.framework.domain.SysDictExample;
import java.util.List;

public interface SysDictMapper {
    int countByExample(SysDictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    List<SysDict> selectByExample(SysDictExample example);

    SysDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);
}