package com.zyp.erp.exceptions;


import com.zyp.erp.utils.response.enums.MSGEnum;

import java.io.Serializable;

/**
 * dubbo服务，如果消费者想正确地捕获到自定义的异常，那么，这个自定义的异常，必须和接口类在同一个jar包中。
 */
public class CommonRuntimeException extends RuntimeException implements Serializable {

    private int code;

    public CommonRuntimeException() {
        super();
    }

    public CommonRuntimeException(MSGEnum responseHeadEnum ) {
        super(responseHeadEnum.getInfo());
        this.code = responseHeadEnum.getCode();
    }

    public CommonRuntimeException(String message) {
        super(message);
        code = -1;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
