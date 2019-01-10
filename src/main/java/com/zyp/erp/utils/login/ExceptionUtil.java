package com.zyp.erp.utils.login;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * created on 2016-07-10 15:00
 *
 * @author nextyu
 */
public class ExceptionUtil {
    private ExceptionUtil() {
        throw new Error("can't invoke constructor");
    }

    /**
     * 将CheckedException转换为UncheckedException
     *
     * @param e
     * @return
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转化为String
     *
     * @param e
     * @return
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}

