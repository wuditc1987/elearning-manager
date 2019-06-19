package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysI18nMessgae;
import com.elearning.framework.domain.SysI18nMessgaeExample;
import java.util.List;

public interface SysI18nMessgaeMapper {
    int countByExample(SysI18nMessgaeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysI18nMessgae record);

    int insertSelective(SysI18nMessgae record);

    List<SysI18nMessgae> selectByExample(SysI18nMessgaeExample example);

    SysI18nMessgae selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysI18nMessgae record);

    int updateByPrimaryKey(SysI18nMessgae record);
}