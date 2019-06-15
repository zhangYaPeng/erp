//package com.zyp.erp.controller.wechat;
//
//import com.zyp.back.wechat.biz.service.wechat.WeChatInfoService;
//import com.zyp.back.wechat.biz.service.wechat.WeChatService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.weixin4j.WeixinException;
//import org.weixin4j.spi.HandlerFactory;
//import org.weixin4j.spi.IMessageHandler;
//import org.weixin4j.util.TokenUtil;
//
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Controller
//@RequestMapping("/weChat")
//public class WeChatController {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private WeChatService weChatService;
//
//    @Autowired
//    private WeChatInfoService weChatInfoService;
//
//    //开发者接入验证
//    @GetMapping("/access/{weChatId}")
//    public void get(@PathVariable("weChatId") String weChatId, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        //消息来源可靠性验证
//        String signature = request.getParameter("signature");// 微信加密签名
//        String timestamp = request.getParameter("timestamp");// 时间戳
//        String nonce = request.getParameter("nonce");       // 随机数
//        String token = weChatInfoService.getToken(weChatId);
//        //1.验证消息真实性
//        //http://mp.weixin.qq.com/wiki/index.php?title=验证消息真实性
//        //成为开发者验证
//        String echostr = request.getParameter("echostr");
//        //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
//        if (TokenUtil.checkSignature(token, signature, timestamp, nonce)) {
//            response.getWriter().write(echostr);
//        }
//    }
//
//    //接收微信消息
//    @PostMapping("/access/{weChatId}")
//    public void post(@PathVariable("weChatId") String weChatId, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        //消息来源可靠性验证
//        String signature = request.getParameter("signature");// 微信加密签名
//        String timestamp = request.getParameter("timestamp");// 时间戳
//        String nonce = request.getParameter("nonce");       // 随机数
//
//        String token = weChatInfoService.getToken(weChatId);
//        //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
//        if (!TokenUtil.checkSignature(token, signature, timestamp, nonce)) {
//            //消息不可靠，直接返回
//            response.getWriter().write("");
//            return;
//        }
//        //用户每次向公众号发送消息、或者产生自定义菜单点击事件时，响应URL将得到推送
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/xml");
//            //获取POST流
//            ServletInputStream in = request.getInputStream();
//            //非注解方式，依然采用消息处理工厂模式调用
//            IMessageHandler messageHandler = HandlerFactory.getMessageHandler();
//            //处理输入消息，返回结果
//            String xml = messageHandler.invoke(in);
//            //返回结果
//            response.getWriter().write(xml);
//        } catch (IOException | WeixinException ex) {
//            response.getWriter().write("");
//        }
//    }
//
//}
