package com.elearning.framework.mapper;

import com.elearning.framework.domain.SysMessage;
import com.elearning.framework.domain.SysMessageExample;
import java.util.List;

public interface SysMessageMapper {
    int countByExample(SysMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    List<SysMessage> selectByExample(SysMessageExample example);

    SysMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKey(SysMessage record);
}