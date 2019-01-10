//package com.zyp.erp.controller.wechat;
//
//import com.zyp.back.wechat.biz.service.wechat.WeChatInfoService;
//import com.zyp.back.wechat.biz.service.wechat.WeChatService;
//import com.zyp.back.wechat.client.mongodb.wechat.WeChatInfo;
//import com.zyp.back.wechat.client.util.response.ResponseObject;
//import com.zyp.back.wechat.client.util.response.ResponseUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.weixin4j.WeixinConfig;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/weChatInfo")
//public class WeChatInfoController {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private WeChatInfoService weChatInfoService;
//
//    @Autowired
//    private WeChatService weChatService;
//
//
//    @GetMapping("/list")
//    public String list(Model model) {
//        List<WeChatInfo> list = weChatInfoService.listAll();
//        model.addAttribute("list", list);
//
//        return "wechat/weChatInfoList";
//    }
//
//    @GetMapping("/addOrUpdate/{id}")
//    public String addOrUpdate(@PathVariable("id") String id, Model model) {
//        if ( "-1".equals(id) ) {
//            WeChatInfo weChatInfo = new WeChatInfo();
//            model.addAttribute("object", weChatInfo);
//        } else {
//            WeChatInfo weChatInfo = weChatInfoService.getById(id);
//            model.addAttribute("object", weChatInfo);
//        }
//
//        return "wechat/weChatInfoAddOrUpdate";
//    }
//
//    @PostMapping("/addOrUpdate")
//    @ResponseBody
//    public ResponseObject<Object> addOrUpdate(String id, String name, WeixinConfig weixinConfig) {
//        if (StringUtils.isBlank(id)) {
//            id = null;
//        }
//
//        WeChatInfo weChatInfo = new WeChatInfo();
//        weChatInfo.setId(id);
//        weChatInfo.setName(name);
//        weChatInfo.setWeixinConfig(weixinConfig);
//
//        weChatInfoService.addOrUpdate(weChatInfo);
//
//        return ResponseUtil.getOK();
//    }
//
//    @PostMapping("/modifyState")
//    @ResponseBody
//    public ResponseObject<Object> modifyState(String id, Integer state) {
//        WeChatInfo weChatInfo = new WeChatInfo();
//        weChatInfo.setId(id);
//        weChatInfo.setWeixinConfig(null);
//        weChatInfo.setState(state);
//
//        weChatInfoService.addOrUpdate(weChatInfo);
//
//        return ResponseUtil.getOK();
//    }
//
//    @PostMapping("/delete")
//    @ResponseBody
//    public ResponseObject<Object> delete(String id) {
//        if (StringUtils.isBlank(id)) {
//            return ResponseUtil.getOK();
//        }
//
//        weChatInfoService.delete(id);
//
//        return ResponseUtil.getOK();
//    }
//
//
//}
