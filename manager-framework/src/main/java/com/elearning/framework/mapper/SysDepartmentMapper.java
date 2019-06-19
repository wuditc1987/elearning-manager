package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysDepartment;
import com.elearning.framework.domain.SysDepartmentExample;
import java.util.List;

public interface SysDepartmentMapper {
    int countByExample(SysDepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    List<SysDepartment> selectByExample(SysDepartmentExample example);

    SysDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);
}