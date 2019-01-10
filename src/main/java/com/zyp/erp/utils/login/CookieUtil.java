package com.zyp.erp.utils.login;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CookieUtil() {
        throw new Error("can't invoke constructor");
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param domain
     * @param path
     * @param maxAge      秒
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain, String path, int maxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        if ( StringUtils.isNotBlank(domain) ) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        if ( maxAge > 0 ) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 根据cookieName 获取 Cookie
     *
     * @param request
     * @param cookieName
     * @return Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (cookieName.equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 根据cookieName 获取 cookieValue
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie cookie = getCookie(request, cookieName);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * 设置Base64 编码之后 cookie
     *
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param domain
     * @param path
     * @param maxAge
     */
    public static void setCookieBase64(HttpServletResponse response, String cookieName, String cookieValue, String domain, String path, int maxAge) {
        Cookie cookie = new Cookie(cookieName, EncodeUtil.encodeBase64(cookieValue.getBytes()));
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 根据cookieName 获取 Base64 解码之后 cookieValue
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValueBase64(HttpServletRequest request, String cookieName) {
        Cookie cookie = getCookie(request, cookieName);
        if (cookie != null) {
            return new String(EncodeUtil.decodeBase64(cookie.getValue()));
        } else {
            return null;
        }
    }

    /**
     * 设置cookie失效
     *
     * @param response
     * @param cookieName
     * @param domain
     * @param path
     */
    public static void invalidateCookie(HttpServletResponse response, String cookieName, String domain, String path) {
        Cookie cookie = new Cookie(cookieName, "");
        if ( StringUtils.isNotBlank(domain) ) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}
