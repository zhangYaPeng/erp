//package com.zyp.erp.controller.wechat;
//
//import com.alibaba.fastjson.JSON;
//import com.zyp.back.wechat.biz.service.wechat.WeChatButtonService;
//import com.zyp.back.wechat.biz.service.wechat.WeChatService;
//import com.zyp.back.wechat.client.exceptions.CommonRuntimeException;
//import com.zyp.back.wechat.client.formbean.wechat.WeChatButtonAddFormBean;
//import com.zyp.back.wechat.client.mongodb.wechat.WeChatButton;
//import com.zyp.back.wechat.client.util.BeanCopierUtil;
//import com.zyp.back.wechat.client.util.response.ResponseObject;
//import com.zyp.back.wechat.client.util.response.ResponseUtil;
//import com.zyp.back.wechat.client.vo.WeChatButtonTreeVO;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.weixin4j.WeixinException;
//import org.weixin4j.component.MenuComponent;
//import org.weixin4j.model.menu.ButtonType;
//import org.weixin4j.model.menu.Menu;
//import org.weixin4j.model.menu.SingleButton;
//import org.weixin4j.model.menu.ViewButton;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/weChatButton")
//public class WeChatButtonController {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private WeChatService weChatService;
//
//    @Autowired
//    private WeChatButtonService weChatButtonService;
//
//
//    @GetMapping("/show")
//    public String show(String weChatInfoId, String weChatInfoName, Model model) {
//
//        model.addAttribute("weChatInfoId", weChatInfoId);
//        model.addAttribute("weChatInfoName", weChatInfoName);
//
//        return "wechat/weChatButton";
//    }
//
//
//    @GetMapping("/add")
//    public String add(String weChatInfoId, String weChatInfoName,
//                      Integer level, String parentButtonId, String parentButtonName,  Model model) {
//
//        model.addAttribute("weChatInfoId", weChatInfoId);
//        model.addAttribute("weChatInfoName", weChatInfoName);
//        model.addAttribute("level", level);
//        model.addAttribute("parentButtonId", parentButtonId);
//        model.addAttribute("parentButtonName", parentButtonName);
//
//        return "wechat/weChatButtonAdd";
//    }
//
//    @PostMapping("/addOrUpdate")
//    @ResponseBody
//    public ResponseObject addOrUpdate(WeChatButtonAddFormBean weChatButtonAddFormBean) {
//
//        WeChatButton weChatButton = new WeChatButton();
//        BeanCopierUtil.copy(weChatButtonAddFormBean, weChatButton);
//
//        String type = weChatButtonAddFormBean.getType();
//        Integer level = weChatButtonAddFormBean.getLevel();
//
//        if ( level == 1 ) {
//
//            if (!type.equals("single") &&
//                    !type.equals( ButtonType.View.toString() ) ) {
//                throw new CommonRuntimeException("一级菜单暂不支持的菜单类型【"+ type +"】");
//            }
//
//        } else if ( level == 2 ) {
//
//            if ( !type.equals( ButtonType.View.toString() ) ) {
//                throw new CommonRuntimeException("二级菜单暂不支持的菜单类型【"+ type +"】");
//            }
//
//        }
//
//        weChatButtonService.addOrUpdate(weChatButton);
//
//
//        return ResponseUtil.getOK();
//    }
//
//
//    @PostMapping("/listWithTree")
//    @ResponseBody
//    public ResponseObject<List<WeChatButtonTreeVO>> listWithTree(String weChatInfoId) {
//
//        List<WeChatButtonTreeVO> list = weChatButtonService.listWithTree(weChatInfoId);
//
//        return ResponseUtil.getOK(list);
//    }
//
//    @PostMapping("/publish")
//    @ResponseBody
//    public ResponseObject publish(String weChatInfoId) {
//
//        List<WeChatButtonTreeVO> list = weChatButtonService.listWithTree(weChatInfoId);
//
//        if (CollectionUtils.isEmpty(list)) {
//            throw new CommonRuntimeException("菜单不能为空");
//        }
//
//        List<SingleButton> buttons = list.stream()
//                .map(item -> {
//                    SingleButton button = createWeixinButton(item);
//                    List<WeChatButtonTreeVO> children = item.getChildren();
//                    if ( children != null ) {
//                        List<SingleButton> subButtons =
//                                children.stream()
//                                        .map(child -> child == null ? null : createWeixinButton(child))
//                                        .collect(Collectors.toList());
//                        button.setSubButton(subButtons);
//                    }
//
//                    return button;
//                })
//                .collect(Collectors.toList());
//
//        Menu menu = new Menu();
//        menu.setButton(buttons);
//        logger.info("menu->{}", JSON.toJSONString(menu));
//
//        MenuComponent menuComponent = weChatService.getWeixin(weChatInfoId).menu();
//        try {
//            menuComponent.create(menu);
//        } catch (WeixinException e) {
//            logger.error("发布菜单失败，weChatInfoId:{}", weChatInfoId, e);
//            throw new CommonRuntimeException("发布菜单失败，原因：" + e.getMessage());
//        }
//
//        return ResponseUtil.getOK();
//    }
//
//    private SingleButton createWeixinButton(WeChatButtonTreeVO weChatButton) {
//        if ( weChatButton == null ) {
//            return null;
//        }
//
//        String type = weChatButton.getType();
//
//        if ( type.equals("single") ) {
//            SingleButton singleButton = new SingleButton(weChatButton.getName());
//            return singleButton;
//        } else if ( type.equals( ButtonType.View.toString() ) ) {
//            ViewButton viewButton = new ViewButton(weChatButton.getName(), weChatButton.getUrl());
//            return viewButton;
//        } else {
//            throw new CommonRuntimeException("发布菜单失败 不支持的菜单类型：" + type);
//        }
//    }
//
//    @PostMapping("/delete")
//    @ResponseBody
//    public ResponseObject delete(String weChatButtonId) {
//
//        if ( StringUtils.isBlank(weChatButtonId) ) {
//            return ResponseUtil.getOK();
//        }
//
//        weChatButtonService.delete(weChatButtonId);
//
//        return ResponseUtil.getOK();
//    }
//}
