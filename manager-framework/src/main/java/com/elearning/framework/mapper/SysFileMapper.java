package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysFile;
import com.elearning.framework.domain.SysFileExample;
import java.util.List;

public interface SysFileMapper {
    int countByExample(SysFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysFile record);

    int insertSelective(SysFile record);

    List<SysFile> selectByExample(SysFileExample example);

    SysFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysFile record);

    int updateByPrimaryKey(SysFile record);
}