package com.zyp.erp.utils.response.enums;

/**
 * Created by 张亚鹏 on 2018/5/2.
 */
public enum MSGEnum {


    PARAMS_ERROR(1, "应用参数缺失"),


    UNKNOWN(1, "UNKNOWN");

    private int code;
    private String info;

    MSGEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
