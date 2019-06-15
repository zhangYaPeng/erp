package com.zyp.erp.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_user")
public class SysUser {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 登录名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实名字
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 头像路径
     */
    private String pic;

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
     * 获取登录名
     *
     * @return user_name - 登录名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置登录名
     *
     * @param userName 登录名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取真实名字
     *
     * @return real_name - 真实名字
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实名字
     *
     * @param realName 真实名字
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取头像路径
     *
     * @return pic - 头像路径
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置头像路径
     *
     * @param pic 头像路径
     */
    public void setPic(String pic) {
        this.pic = pic;
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