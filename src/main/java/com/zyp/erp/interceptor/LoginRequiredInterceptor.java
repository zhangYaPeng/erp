package com.zyp.erp.interceptor;

import com.zyp.erp.utils.login.LoginUser;
import com.zyp.erp.utils.login.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器<br/>
 * 拦截用户请求， 判断UserContext中是否有登录用户信息；<br/>
 * 如果没有则跳转到登录界面。
 *
 */
public class LoginRequiredInterceptor extends AbstractHandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginUser user = UserContext.get();
        if (user == null) {
            // 未登录,定向到登录页面
            request.getRequestDispatcher("/back/user/view/login").forward(request,response);
            return false;
        }
        return true;
    }



}
