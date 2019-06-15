package com.zyp.erp.utils.login;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;


public class LoginUserUtil {

    private static final Logger logger = LoggerFactory.getLogger(LoginUserUtil.class);

    private LoginUserUtil() {
        throw new Error("can't invoke constructor");
    }

    private static final String COOKIE_NAME = "_i_u_cookie_";
    private static final String COOKIE_PATH = "/";
    private static final Integer COOKIE_EXPIRE_SECOND = 24 * 60 * 60;// 1天之后失效

    private static final String SALT = "jfowfnojfndfoej,<<<))))))";

    /**
     * 从cookie解析loginUser
     *
     * @param request
     * @return
     */
    public static LoginUser parseCookieLoginUser(HttpServletRequest request) {
        String originalCookieValue = CookieUtil.getCookieValue(request, COOKIE_NAME);
        if ( StringUtils.isBlank(originalCookieValue) ) {
            return null;
        }
        String cookieValue = new String(EncodeUtil.decodeBase64(originalCookieValue));
        if (!StringUtils.isBlank(cookieValue)) {
            String[] arr = cookieValue.split(":");
            // userId:expireTime:sign
            // 过期时间 毫秒
            if (arr.length == 5) {
                Long id = Long.parseLong(arr[0]);
                String username = arr[1];
                String realName = username;
                try {
                    realName = URLDecoder.decode(arr[2], "utf-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("parseCookieLoginUser error", e);
                }
                Long expireTime = Long.parseLong(arr[3]);
                String sign = arr[4];
                // 登录未超时
                if (new Date().getTime() < expireTime) {
                    // 验证sign
                    if (sign.equals(buildSign(id, expireTime))) {
                        LoginUser user = new LoginUser();
                        user.setId(id);
                        user.setUsername(username);
                        user.setRealName(realName);
                        StringBuilder userCookie = new StringBuilder();
                        userCookie.append(COOKIE_NAME).append("=\"").append(originalCookieValue).append("\"");
                        user.setUserCookie(userCookie.toString());
                        return user;
                    }
                }

            }

        }
        return null;
    }

    public static void setCookieLoginUser(HttpServletResponse response, LoginUser user) {
        setCookieLoginUser(response, user, null);
    }

    public static void setCookieLoginUser(HttpServletResponse response, LoginUser user, String cookieDomain) {
        // 根据规则拼接 cookieValue
        // userId:用户名:expireTime:sign
        // 过期时间 毫秒
        long time = new Date().getTime() + COOKIE_EXPIRE_SECOND * 1000;
        Long id = user.getId();
        String realName = "";
        try {
            realName = URLEncoder.encode(user.getRealName(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            realName = user.getUsername();
        }
        String sign = buildSign(id, time);
        StringBuilder sb = new StringBuilder();
        sb.append(user.getId()).append(":")
                .append(user.getUsername()).append(":")
                .append(realName).append(":")
                .append(time).append(":")
                .append(sign);
        CookieUtil.setCookie(response, COOKIE_NAME, EncodeUtil.encodeBase64(sb.toString().getBytes()), cookieDomain, COOKIE_PATH, COOKIE_EXPIRE_SECOND);
    }

    /**
     *
     * @param response
     * @param user
     * @param cookieDomain 域名
     * @param expireTime 过期时间，单位秒
     */
    public static void setCookieLoginUser(HttpServletResponse response, LoginUser user, String cookieDomain, Integer expireTime) {

        if ( expireTime == null ) {
            expireTime = COOKIE_EXPIRE_SECOND;
        }
        // 根据规则拼接 cookieValue
        // userId:用户名:expireTime:sign
        // 过期时间 毫秒
        long time = new Date().getTime() + expireTime * 1000;
        Long id = user.getId();
        String realName = "";
        try {
            realName = URLEncoder.encode(user.getRealName(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            realName = user.getUsername();
        }
        String sign = buildSign(id, time);
        StringBuilder sb = new StringBuilder();
        sb.append(user.getId()).append(":")
                .append(user.getUsername()).append(":")
                .append(realName).append(":")
                .append(time).append(":")
                .append(sign);
        CookieUtil.setCookie(response, COOKIE_NAME, EncodeUtil.encodeBase64(sb.toString().getBytes()), cookieDomain, COOKIE_PATH, expireTime);
    }



    private static String buildSign(Long id, long time) {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(":").append(time).append(":").append(SALT);
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
    }

}
