package com.zyp.erp.interceptor;

import com.zyp.erp.utils.MenuContext;
import com.zyp.erp.utils.login.LoginUser;
import com.zyp.erp.utils.login.UserContext;
import com.zyp.erp.vo.back.MenuVO;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class LoginMenuInterceptor extends AbstractHandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuContext menuContext;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        if ( modelAndView != null ) {
            LoginUser loginUser = UserContext.get();
            Long userId = loginUser.getId();
            List<MenuVO> menuList = menuContext.listMenu(userId.intValue());
            modelAndView.addObject("menuList", menuList);

            int currentMenuId = 0;
            int currentMenuSubId = 0;

            // url统一是【/back/菜单名/view/行为名】
            String currentUrl = request.getServletPath();
            String currentUrlPrefix = currentUrl.substring(0, currentUrl.indexOf("/view/") + 1 );

            for ( MenuVO menu : menuList ) {
                List<MenuVO> subList = menu.getSub();
                for ( MenuVO sub : subList ) {
                    String subMenuUrl = sub.getUrl();
                    if ( subMenuUrl.startsWith(currentUrlPrefix) ) {
                        currentMenuId = menu.getId();
                        currentMenuSubId = sub.getId();
                        break;
                    }
                }
            }

            if ( (currentMenuId == 0 || currentMenuSubId == 0) ) {
                request.getRequestDispatcher("/index").forward(request,response);
                return;
            }

            modelAndView.addObject("currentMenuId", currentMenuId);
            modelAndView.addObject("currentMenuSubId", currentMenuSubId);
        }

    }
}
