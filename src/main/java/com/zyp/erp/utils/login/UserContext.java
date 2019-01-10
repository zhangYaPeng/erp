package com.zyp.erp.utils.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserContext {

    private static final Logger logger = LoggerFactory.getLogger(UserContext.class);

    private static final ThreadLocal<LoginUser> current = new ThreadLocal<>();

    public UserContext(LoginUser user) {
        current.set(user);
    }

    public static void set(LoginUser user) {
        current.set(user);
    }

    public static LoginUser get() {
        return current.get();
    }

    public static void remove() {
        if (logger.isDebugEnabled()) {
            if (get() != null) {
                logger.info("remove user from ThreadLocal : {}", get().getId());
            }
        }
        current.remove();
    }

}
