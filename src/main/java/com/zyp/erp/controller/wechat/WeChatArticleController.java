//package com.zyp.erp.controller.wechat;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.base.Splitter;
//import com.zyp.back.wechat.biz.service.common.UploadFileService;
//import com.zyp.back.wechat.biz.service.wechat.WeChatArticleService;
//import com.zyp.back.wechat.biz.service.wechat.WeChatService;
//import com.zyp.back.wechat.client.exceptions.CommonRuntimeException;
//import com.zyp.back.wechat.client.formbean.wechat.WeChatArticleListFormBean;
//import com.zyp.back.wechat.client.mongodb.wechat.WeChatArticle;
//import com.zyp.back.wechat.client.util.BeanCopierUtil;
//import com.zyp.back.wechat.client.util.response.ResponseObject;
//import com.zyp.back.wechat.client.util.response.ResponseUtil;
//import com.zyp.back.wechat.client.vo.PageVO;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.weixin4j.Weixin;
//import org.weixin4j.WeixinException;
//import org.weixin4j.model.media.Article;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/weChatArticle")
//public class WeChatArticleController {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private WeChatService weChatService;
//
//    @Autowired
//    private WeChatArticleService weChatArticleService;
//
//    @Autowired
//    private UploadFileService uploadFileService;
//
//    @GetMapping("/list")
//    public String listWithPage(String access, WeChatArticleListFormBean formBean, Model model, HttpSession session) {
//
//        if ( "reload" .equals(access)) {
//            formBean = (WeChatArticleListFormBean) session.getAttribute("weChatArticle_listWithPage_formBean");
//        }
//
//        PageVO<WeChatArticle> page = weChatArticleService.listWithPage(formBean);
//
//        model.addAttribute("page", page);
//        model.addAttribute("baseImageUrl", uploadFileService.getDomain());
//        model.addAttribute("formBean", formBean);
//
//        session.setAttribute("weChatArticle_listWithPage_formBean", formBean);
//
//        return "wechat/weChatArticleList";
//    }
//
//    @GetMapping("/addOrUpdate/{weChatInfoId}/{weChatArticleId}")
//    public String addOrUpdate(@PathVariable String weChatInfoId, @PathVariable String weChatArticleId, Model model) {
//
//        if ( StringUtils.isBlank(weChatArticleId) || "-1".equals(weChatArticleId) ) {
//            WeChatArticle weChatArticle = new WeChatArticle();
//            weChatArticle.setWeChatInfoId(weChatInfoId);
//            model.addAttribute("object", weChatArticle);
//            model.addAttribute("baseImageUrl", uploadFileService.getDomain());
//        } else {
//            model.addAttribute("object", weChatArticleService.findById(weChatArticleId));
//            model.addAttribute("baseImageUrl", uploadFileService.getDomain());
//        }
//
//        return "wechat/weChatArticleAddOrUpdate";
//    }
//
//    @PostMapping("/addOrUpdate")
//    @ResponseBody
//    public ResponseObject addOrUpdate(WeChatArticle weChatArticle) {
//
//        weChatArticleService.addOrUpdate(weChatArticle);
//
//        return ResponseUtil.getOK();
//    }
//
//    @PostMapping("/delete")
//    @ResponseBody
//    public ResponseObject delete(String weChatArticleId) {
//
//        weChatArticleService.delete(weChatArticleId);
//
//        return ResponseUtil.getOK();
//    }
//
//    @PostMapping("/preview")
//    @ResponseBody
//    public ResponseObject preview(String weChatArticleIds, String openId, String weChatInfoId) {
//
//        List<String> weChatArticleIdList = Splitter.on(",")
//                .omitEmptyStrings()
//                .trimResults()
//                .splitToList(weChatArticleIds);
//
//        if (CollectionUtils.isEmpty(weChatArticleIdList)) {
//            return ResponseUtil.getError("请选择文章");
//        }
//        if ( weChatArticleIdList.size() > 8 ) {
//            return ResponseUtil.getError("一次推送的信息不能超过8篇文章");
//        }
//
//        List<Article> list = weChatArticleIdList.stream()
//                .map(weChatArticleId -> {
//                    WeChatArticle weChatArticle = weChatArticleService.findById(weChatArticleId);
//                    if (weChatArticle != null) {
//                        Article article = new Article();
//                        BeanCopierUtil.copy(weChatArticle, article);
//                        article.setContent(exchangeImageSrcAndAlt(article.getContent()));
//                        return article;
//                    }
//
//                    return null;
//                })
//                .filter(article -> article != null)
//                .collect(Collectors.toList());
//
//        try {
//            Weixin weixin = weChatService.getWeixin(weChatInfoId);
//            String mediaId = weixin.media().uploadnews(list);
//            String accessToken = weixin.getToken().getAccess_token();
//
//            weChatArticleService.preview(accessToken, openId, mediaId);
//
//            return ResponseUtil.getOK("预览成功，请查看手机吧");
//        } catch (CommonRuntimeException e1) {
//            throw e1;
//        } catch (Exception e) {
//            logger.error("预览失败，weChatArticleIds:{}, openId:{}, weChatInfoId:{}",
//                    weChatArticleIds, openId, weChatInfoId, e);
//            return ResponseUtil.getError("预览失败");
//        }
//    }
//
//
//    @PostMapping("/sendAll")
//    @ResponseBody
//    public ResponseObject sendAll(String weChatArticleIds, String weChatInfoId) {
//
//        List<String> weChatArticleIdList = Splitter.on(",")
//                .omitEmptyStrings()
//                .trimResults()
//                .splitToList(weChatArticleIds);
//
//        if (CollectionUtils.isEmpty(weChatArticleIdList)) {
//            return ResponseUtil.getError("请选择文章");
//        }
//        if ( weChatArticleIdList.size() > 8 ) {
//            return ResponseUtil.getError("一次推送的信息不能超过8篇文章");
//        }
//
//        List<Article> list = weChatArticleIdList.stream()
//                .map(weChatArticleId -> {
//                    WeChatArticle weChatArticle = weChatArticleService.findById(weChatArticleId);
//                    if (weChatArticle != null) {
//                        Article article = new Article();
//                        BeanCopierUtil.copy(weChatArticle, article);
//                        article.setContent(exchangeImageSrcAndAlt(article.getContent()));
//                        return article;
//                    }
//
//                    return null;
//                })
//                .filter(article -> article != null)
//                .collect(Collectors.toList());
//
//        try {
//            Weixin weixin = weChatService.getWeixin(weChatInfoId);
//            String mediaId = weixin.media().uploadnews(list);
//
//            List<String> openids = weixin.user().getAll().getData().getOpenid();
//            String messageGoupSendId = weixin.message().massSendNews(openids.toArray(new String[openids.size()]), mediaId);
//            logger.error("群发消息成功，weChatArticleIds:{}, weChatInfoId:{}",
//                    weChatArticleIds, weChatInfoId);
//            logger.info("群发消息成功，返回的群发消息id为:{}", messageGoupSendId);
//
//            return ResponseUtil.getOK("预览成功，请查看手机吧");
//        } catch (CommonRuntimeException e1) {
//            throw e1;
//        } catch (Exception e) {
//            logger.error("群发消息失败，weChatArticleIds:{}, weChatInfoId:{}",
//                    weChatArticleIds, weChatInfoId, e);
//            return ResponseUtil.getError("群发消息失败");
//        }
//    }
//
//
//    private String exchangeImageSrcAndAlt(String content) {
//        if ( StringUtils.isNotBlank(content) ) {
//            Document doc = Jsoup.parseBodyFragment(content);
//            Element body = doc.body();
//            Elements imgElements = body.getElementsByTag("img");
//
//            for (Element imgElement : imgElements) {
//                String src = imgElement.attr("src");
//                String alt = imgElement.attr("title");
//
//                if ( StringUtils.isNotBlank(src) && StringUtils.isNotBlank(alt) ) {
//                    content = content.replace(src, alt);
//                }
//            }
//        }
//
//        return content;
//    }
//
//    /**
//     * 上传缩略图
//     *
//     */
//    @RequestMapping("/ajaxUploadImg")
//    public void ajaxUploadImg(HttpServletResponse response,
//                              @RequestParam("imgFile") MultipartFile imgFile, String weChatInfoId) {
//
//        // 判断文件大小
//        long fileSize = imgFile.getSize();
//        logger.info("上传缩略图图片，size：" + fileSize + "，大小"+ (fileSize > 61440 ? "超过了" : "没有超过") +"60KB");
//
//        if ( fileSize >= 61440 ) {
//            ResponseUtil.writeOut(response, JSON.toJSONString(ResponseUtil.getError("图片太大，不能超过60k!!")));
//            return;
//        }
//
//        // 获得后缀
//        String originalFilename = imgFile.getOriginalFilename();
//        String suffix = "jpg";
//        try {
//            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//        } catch (Exception e) {
//        }
//
//
//        File tempFile = null;
//        byte[] bytes = null;
//
//        try {
//            bytes = imgFile.getBytes();
//
//            // 创建临时文件
//            if ( !suffix.startsWith(".") ) {
//                suffix = "." + suffix;
//            }
//            tempFile = new File("./" + System.currentTimeMillis() + suffix);
//            if ( tempFile.exists() ) {
//                tempFile.delete();
//            }
//            tempFile.createNewFile();
//            FileUtils.copyInputStreamToFile(imgFile.getInputStream(), tempFile);
//
//            // 微信验证该缩略图，获取thumb_media_id
//            Weixin weixin = weChatService.getWeixin(weChatInfoId);
//            String mediaId =  weChatArticleService.uploadThumb(tempFile, weixin);
//
//            logger.info("微信上传缩略图，thumbMediaId: " + mediaId);
//
//            // 七牛上传
//            String imgUrl = uploadFileService.upload(bytes, suffix);
//
//            Map<String, Object> data = new HashMap<>();
//            data.put("imgUrl", imgUrl);
//            data.put("baseImageUrl", uploadFileService.getDomain());
//            data.put("thumbMediaId", mediaId);
//
//            ResponseUtil.writeOut(response, JSON.toJSONString(ResponseUtil.getOK(data)));
//            return;
//        } catch (IOException e) {
//            logger.error("创建临时文件失败", e);
//        } catch (WeixinException e) {
//            logger.error("微信验证缩略图失败", e);
//        } finally {
//            if ( tempFile != null ) {
//                tempFile.delete();
//            }
//        }
//
//        ResponseUtil.writeOut(response, JSON.toJSONString(ResponseUtil.getError("图片上传失败，请重试")));
//    }
//
//    /**
//     * ueditor 微信文章中图片上传 单图和多图上传接口
//     *
//     */
//    @RequestMapping("/ajaxUploadImgUe/{weChatInfoId}")
//    public void ajaxUploadImgUe(HttpServletResponse response,
//                                @RequestParam("imgFile") MultipartFile imgFile,
//                                @PathVariable("weChatInfoId") String weChatInfoId) {
//
//        // 判断图片大小
//        long size = imgFile.getSize();
//        if ( size >= ( 1024 * 1024 ) ) {
//            Map<String, Object> returnMap = new HashMap<>();
//            returnMap.put("state", "图片不能超过1MB");// UEDITOR的规则:不为SUCCESS则显示state的内容
//            ResponseUtil.writeOut(response, JSON.toJSONString(returnMap));
//            return;
//        }
//
//        // 微信文章中的图片，按照微信的要求是要上传到微信的服务上的，所以，不再使用七牛来保存了
//        // 获得后缀
//        String originalFilename = imgFile.getOriginalFilename();
//        String suffix = "jpg";
//        try {
//            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//        } catch (Exception e) {
//        }
//
//        // 创建临时文件
//        File tempFile = null;
//        byte[] bytes = null;
//        try {
//            bytes = imgFile.getBytes();
//            tempFile = new File("./" + System.currentTimeMillis() + "." + suffix);
//            if ( tempFile.exists() ) {
//                tempFile.delete();
//            }
//            tempFile.createNewFile();
//            FileUtils.copyInputStreamToFile(imgFile.getInputStream(), tempFile);
//            Weixin weixin = weChatService.getWeixin(weChatInfoId);
//            String weixinImgUrl = weixin.media().uploadimg(tempFile);
//
//            String original = uploadFileService.upload(bytes, suffix);
//
//            Map<String, Object> returnMap = new HashMap<>();
//            returnMap.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
//            returnMap.put("url", uploadFileService.getDomain() + original);
//            returnMap.put("title", weixinImgUrl);
//            returnMap.put("original", weixinImgUrl);
//
//            ResponseUtil.writeOut(response, JSON.toJSONString(returnMap));
//            return;
//        } catch (IOException e) {
//            logger.error("上传微信文章内图片失败", e);
//        } catch (WeixinException e) {
//            logger.error("上传微信文章内图片失败", e);
//        } finally {
//            if ( tempFile != null ) {
//                tempFile.delete();
//            }
//        }
//
//        Map<String, Object> returnMap = new HashMap<>();
//        returnMap.put("state", "图片上传失败！！！");// UEDITOR的规则:不为SUCCESS则显示state的内容
//        ResponseUtil.writeOut(response, JSON.toJSONString(returnMap));
//
//    }
//
//}
