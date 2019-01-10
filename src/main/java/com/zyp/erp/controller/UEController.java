package com.zyp.erp.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UEController {


    /**
     * UEditor初始化
     */
    @RequestMapping("/initUEditor")
    public void ajaxUploadImgUe(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");

        ServletContext application= request.getServletContext();

        String configPath = System.getProperty("spring.config.location");
        configPath = configPath.substring(0, configPath.lastIndexOf("/"));

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write( new ActionEnter( request, configPath ).exec() );
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.close();
            }
        }

    }


}
