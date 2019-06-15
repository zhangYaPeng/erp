package com.zyp.erp.domain;

import javax.persistence.*;

@Table(name = "sys_role_user")
public class SysRoleUser {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 保留字段，用于程序判断
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * @return role_id - 角色名称
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色名称
     *
     * @param roleId 角色名称
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取保留字段，用于程序判断
     *
     * @return user_id - 保留字段，用于程序判断
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置保留字段，用于程序判断
     *
     * @param userId 保留字段，用于程序判断
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}