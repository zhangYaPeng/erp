//package com.zyp.erp.controller.wechat;
//
//import com.zyp.back.wechat.biz.service.wechat.WeChatUserService;
//import com.zyp.back.wechat.client.formbean.wechat.WeChatUserListFormBean;
//import com.zyp.back.wechat.client.vo.PageVO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.weixin4j.model.user.User;
//
//@Controller
//@RequestMapping("/weChatUser")
//public class WeChatUserController {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private WeChatUserService weChatUserService;
//
//
//    @GetMapping("/list")
//    public String listWithPage(WeChatUserListFormBean formBean, Model model) {
//
//        PageVO<User> page = weChatUserService.listWithPage(formBean);
//
//        model.addAttribute("page", page);
//        model.addAttribute("formBean", formBean);
//
//        return "wechat/weChatUserList";
//    }
//
//
//}
