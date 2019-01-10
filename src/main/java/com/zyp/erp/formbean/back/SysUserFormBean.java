package com.zyp.erp.formbean.back;

import com.zyp.erp.formbean.BaseFormBean;


public class SysUserFormBean extends BaseFormBean {

    private String userName;

    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
