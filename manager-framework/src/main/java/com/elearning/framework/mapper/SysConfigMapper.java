package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysConfig;
import com.elearning.framework.domain.SysConfigExample;
import java.util.List;

public interface SysConfigMapper {
    int countByExample(SysConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    List<SysConfig> selectByExample(SysConfigExample example);

    SysConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
}