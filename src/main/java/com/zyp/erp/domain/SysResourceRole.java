package com.zyp.erp.domain;

import javax.persistence.*;

@Table(name = "sys_resource_role")
public class SysResourceRole {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父节点
     */
    @Column(name = "resource_id")
    private Integer resourceId;

    /**
     * 资源名称
     */
    @Column(name = "role_id")
    private Integer roleId;

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
     * @return resource_id - 父节点
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * 设置父节点
     *
     * @param resourceId 父节点
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取资源名称
     *
     * @return role_id - 资源名称
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置资源名称
     *
     * @param roleId 资源名称
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}