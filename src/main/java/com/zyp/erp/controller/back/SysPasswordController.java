package com.zyp.erp.controller.back;

import com.zyp.erp.dao.SysUserMapper;
import com.zyp.erp.domain.SysUser;
import com.zyp.erp.utils.PasswordUtil;
import com.zyp.erp.utils.login.LoginUser;
import com.zyp.erp.utils.login.UserContext;
import com.zyp.erp.utils.response.ResponseObject;
import com.zyp.erp.utils.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/back/password")
public class SysPasswordController {

    @Autowired
    private SysUserMapper sysUserMapper;


    @RequestMapping("/view/update")
    public String updateView(Model model) {
        return "password/update";
    }

    @ResponseBody
    @PostMapping("/ajax/update")
    public ResponseObject updateAjax(@RequestParam String oldPassword, @RequestParam String newPassword,
                                     HttpServletResponse response) {
        LoginUser loginUser = UserContext.get();

        SysUser user = sysUserMapper.selectByPrimaryKey(loginUser.getId().intValue());
        String userName = user.getUserName();
        String dbPassword = user.getPassword();
        if ( PasswordUtil.verifyPassword(oldPassword, userName, dbPassword) ) {
            String pwd = PasswordUtil.encryptPassword(newPassword, userName);
            SysUser updateObject = new SysUser();
            updateObject.setId(loginUser.getId().intValue());
            updateObject.setPassword(pwd);
            sysUserMapper.updateByPrimaryKeySelective(updateObject);

            return ResponseUtil.getOK();
        }

        return ResponseUtil.getError("旧密码不正确");
    }


}
