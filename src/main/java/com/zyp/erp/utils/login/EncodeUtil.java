package com.zyp.erp.utils.login;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class EncodeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncodeUtil.class);
    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    private EncodeUtil() {
        throw new Error("can't invoke constructor");
    }

    /**
     * Hex编码
     *
     * @param data
     * @return
     */
    public static String encodeHex(byte[] data) {
        return Hex.encodeHexString(data);
    }

    /**
     * Hex编码
     *
     * @param data
     * @return
     */
    public static byte[] decodeHex(String data) {
        try {
            return Hex.decodeHex(data.toCharArray());
        } catch (DecoderException e) {
            LOGGER.error("decodeHex", e);
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    /**
     * Base64编码
     *
     * @param data
     * @return
     */
    public static String encodeBase64(byte[] data) {
        return Base64.encodeBase64String(data);
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
     *
     * @param data
     * @return
     */
    public static String encodeUrlSafeBase64(byte[] data) {
        return Base64.encodeBase64URLSafeString(data);
    }

    /**
     * Base64解码
     *
     * @param data
     * @return
     */
    public static byte[] decodeBase64(String data) {
        return Base64.decodeBase64(data);
    }

    /**
     * html转码
     * <p>
     * For example:
     * </p>
     * <p><code>"bread" &amp; "butter"</code></p>
     * becomes:
     * <p>
     * <code>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</code>.
     * </p>
     *
     * @param html
     * @return
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * html解码，和escapeHtml相反
     *
     * @param htmlEscaped
     * @return
     */
    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * xml转码
     *
     * @param xml
     * @return
     */
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml11(xml);

    }

    /**
     * xml转码
     *
     * @param xmlEscaped
     * @return
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    /**
     * URL编码,Encode默认为UTF-8
     *
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encodeUrl", e);
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * URL解码,Encode默认为UTF-8
     *
     * @param urlEncoded
     * @return
     */
    public static String decodeUrl(String urlEncoded) {
        try {
            return URLDecoder.decode(urlEncoded, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("decodeUrl", e);
            throw ExceptionUtil.unchecked(e);
        }
    }

}
