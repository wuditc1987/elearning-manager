package com.elearning.common.base.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wudi
 * @version 1.0
 * @Description 实体基类
 * @date 2019/6/19 1:35 PM
 */
public class BaseEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建用户",name="createUser",example = "syler")
    private int createUserId;

    @ApiModelProperty(value = "更新用户",name="updateUser",example = "syler")
    private int updateUserId;

    @ApiModelProperty(value = "创建时间",name = "createTime",example = "2018-12-15 18:03:58",dataType = "java.util.Date")
    private Date createTime;

    @ApiModelProperty(value = "更新时间",name = "updateTime",example = "2018-12-15 18:03:58",dataType = "java.util.Date")
    private Date updateTime;

    /**
     * 用于乐观锁的实现
     */
    @ApiModelProperty(value = "版本号",name = "versionNo",example = "1",dataType = "java.lang.Integer")
    private int versionNo;

    @ApiModelProperty(value = "搜索文本",name="searchText")
    private String searchText;

    public Object getId(){
        return null;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
