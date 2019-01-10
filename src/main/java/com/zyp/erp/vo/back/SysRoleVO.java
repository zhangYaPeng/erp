package com.zyp.erp.vo.back;

import java.util.Date;

public class SysRoleVO {
    /**
     * id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 保留字段，用于程序判断
     */
    private String role;

    /**
     * 状态：0：禁用；1：启用
     */
    private Integer state;

    /**
     * 是否删除：0：否；1：是
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date addTime;

    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    private String resourceIds;

    private Integer appType;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取保留字段，用于程序判断
     *
     * @return role - 保留字段，用于程序判断
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置保留字段，用于程序判断
     *
     * @param role 保留字段，用于程序判断
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取状态：0：禁用；1：启用
     *
     * @return state - 状态：0：禁用；1：启用
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态：0：禁用；1：启用
     *
     * @param state 状态：0：禁用；1：启用
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取是否删除：0：否；1：是
     *
     * @return deleted - 是否删除：0：否；1：是
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除：0：否；1：是
     *
     * @param deleted 是否删除：0：否；1：是
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取创建时间
     *
     * @return add_time - 创建时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置创建时间
     *
     * @param addTime 创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}