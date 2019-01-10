package com.zyp.erp.interceptor;

import com.zyp.erp.utils.login.CookieUtil;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginSkinInterceptor extends AbstractHandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        if ( modelAndView != null ) {
            String huiskin = CookieUtil.getCookieValue(request, "Huiskin");
            huiskin = huiskin == null ? "default" : huiskin;
            modelAndView.addObject("Huiskin", huiskin);
        }

    }
}
