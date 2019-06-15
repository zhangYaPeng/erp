package com.zyp.erp.interceptor;

import com.zyp.erp.utils.login.LoginUser;
import com.zyp.erp.utils.login.LoginUserUtil;
import com.zyp.erp.utils.login.UserContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCookieInterceptor extends AbstractHandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginUser user = LoginUserUtil.parseCookieLoginUser(request);
        if (user != null) {
            // 放到上下文
            UserContext.set(user);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        if ( modelAndView != null ) {
            LoginUser loginUser = UserContext.get();
            modelAndView.addObject("loginUser", loginUser);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
