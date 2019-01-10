package com.zyp.erp.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public abstract class AbstractHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
    /**
     * URL拦截器的排除路径，多个以逗号隔开
     */
    private String excludePaths;

    public String getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(String excludePaths) {
        this.excludePaths = excludePaths;
    }
}
