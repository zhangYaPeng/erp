package com.zyp.erp.controller.back;

import com.zyp.erp.formbean.back.SysUserFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/back/uedemo")
public class UEDemoController {

    @GetMapping(value = "/view/list")
    public String listView(String access, SysUserFormBean query, Model model, HttpSession session) {
        return "uedemo/list";
    }

}
