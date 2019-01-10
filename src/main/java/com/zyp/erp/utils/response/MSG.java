package com.zyp.erp.utils.response;

import java.io.Serializable;


public class MSG implements Serializable {
    private static final long serialVersionUID = -4098114674766030014L;
    private static final int SUCCESS_CODE = 0;
    private static final int FAILURE_CODE = 1;
    private static final int NOT_LOGIN_FAILURE_CODE = 4;
    public static final String DEFAULT_MSG = "done";

    private String info;
    private int code;
    private boolean success;

    public MSG() {
        this.info = DEFAULT_MSG;
        this.code = SUCCESS_CODE;
        this.success = true;
    }

    public void setErrorCode() {
        this.code = FAILURE_CODE;
    }

    public void setNotLoginErrorCode() {
        this.code = NOT_LOGIN_FAILURE_CODE;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
