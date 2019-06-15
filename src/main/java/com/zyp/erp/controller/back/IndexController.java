package com.zyp.erp.controller.back;

import com.zyp.erp.utils.MenuContext;
import com.zyp.erp.utils.login.LoginUser;
import com.zyp.erp.utils.login.LoginUserUtil;
import com.zyp.erp.vo.back.MenuVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private MenuContext menuContext;


    @RequestMapping(value = {"", "/", "/index", "index.html"})
    public String index(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginUser loginUser = LoginUserUtil.parseCookieLoginUser(request);
        if (loginUser != null) {

            // 跳转到对应的第一个
            List<MenuVO> menuList = menuContext.listMenu(loginUser.getId().intValue());

            String url = null;
            try {
                url = menuList.get(0).getSub().get(0).getUrl();
            } catch (Exception e) {
                logger.error("找不到默认链接，跳转至404", e);
            }

            if ( StringUtils.isBlank(url) ) {
                logger.info("找不到默认链接，跳转至404");
                request.getRequestDispatcher("/404").forward(request,response);
                return null;
            }

            return "redirect:" + url;
        }

        return "redirect:/back/user/view/login";
    }

    @RequestMapping(value = {"/404"})
    public String index404() {
        return "404";
    }

}
