package com.zyp.erp.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public void initUEditor(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");

        String rootPath = UEController.class.getClassLoader().getResource("").getPath();

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write( new ActionEnter( request, rootPath ).exec() );
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
