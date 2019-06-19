package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysDepartRole;
import com.elearning.framework.domain.SysDepartRoleExample;
import java.util.List;

public interface SysDepartRoleMapper {
    int countByExample(SysDepartRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysDepartRole record);

    int insertSelective(SysDepartRole record);

    List<SysDepartRole> selectByExample(SysDepartRoleExample example);

    SysDepartRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDepartRole record);

    int updateByPrimaryKey(SysDepartRole record);
}