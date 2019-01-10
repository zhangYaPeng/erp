package com.zyp.erp.utils.login;


import org.apache.commons.lang3.StringUtils;

public class ExcludeRequestPath {

    private ExcludeRequestPath() {
        throw new Error("can't invoke constructor");
    }

    /**
     * 判断请求的uri是否包含在排除路径之中
     *
     * @param excludePaths 排除路径
     * @param requestUri   请求uri
     * @return 包含 true，否则 false
     */
    public static boolean isExclude(String excludePaths, String requestUri) {
        if (StringUtils.isBlank(excludePaths) || StringUtils.isBlank(requestUri)) {
            return false;
        }
        String[] excludes = excludePaths.split(",");
        for (String exclude : excludes) {
            if (requestUri.contains(exclude)) {
                return true;
            }
        }
        return false;
    }
}
