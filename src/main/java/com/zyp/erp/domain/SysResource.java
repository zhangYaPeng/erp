package com.zyp.erp.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_resource")
public class SysResource {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父节点
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源url
     */
    private String url;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 资源类型，1：链接；2：按钮
     */
    @Column(name = "resource_type")
    private Integer resourceType;

    /**
     * 资源所属应用，1：pc，2：移动端
     */
    @Column(name = "app_type")
    private Integer appType;

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
    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

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
     * 获取父节点
     *
     * @return parent_id - 父节点
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父节点
     *
     * @param parentId 父节点
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取资源url
     *
     * @return url - 资源url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置资源url
     *
     * @param url 资源url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取资源图标
     *
     * @return icon - 资源图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置资源图标
     *
     * @param icon 资源图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取排序号
     *
     * @return sort - 排序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序号
     *
     * @param sort 排序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取资源类型，1：链接；2：按钮
     *
     * @return resource_type - 资源类型，1：链接；2：按钮
     */
    public Integer getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类型，1：链接；2：按钮
     *
     * @param resourceType 资源类型，1：链接；2：按钮
     */
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取资源所属应用，1：pc，2：移动端
     *
     * @return app_type - 资源所属应用，1：pc，2：移动端
     */
    public Integer getAppType() {
        return appType;
    }

    /**
     * 设置资源所属应用，1：pc，2：移动端
     *
     * @param appType 资源所属应用，1：pc，2：移动端
     */
    public void setAppType(Integer appType) {
        this.appType = appType;
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
}