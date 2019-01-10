package com.zyp.erp.controller.back;

import com.github.pagehelper.PageInfo;
import com.zyp.erp.dao.SysUserMapper;
import com.zyp.erp.domain.SysUser;
import com.zyp.erp.formbean.back.SysUserFormBean;
import com.zyp.erp.service.SysUserService;
import com.zyp.erp.utils.PasswordUtil;
import com.zyp.erp.utils.login.LoginUser;
import com.zyp.erp.utils.login.LoginUserUtil;
import com.zyp.erp.utils.response.ResponseObject;
import com.zyp.erp.utils.response.ResponseUtil;
import com.zyp.erp.vo.back.SysUserVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/back/user")
public class SysUserController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/view/login")
    public String login(HttpSession session, Model model) {
        return "login";
    }

    @PostMapping("/ajax/login")
    @ResponseBody
    public ResponseObject login(String username, String password,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ResponseUtil.getError("参数不完整，请输入用户名/密码");
        }

        SysUser userQuery = new SysUser();
        userQuery.setUserName(username);
        userQuery.setState(1);
        userQuery.setDeleted(0);

        List<SysUser> list = sysUserMapper.select(userQuery);
        if ( CollectionUtils.isEmpty(list) ) {
            return ResponseUtil.getError("用户名或密码错误");
        }

        SysUser sysUser = list.get(0);
        Boolean verifyPassword = PasswordUtil.verifyPassword(password, sysUser.getUserName(), sysUser.getPassword());
        if ( verifyPassword ) {
            LoginUser loginUser = new LoginUser();
            loginUser.setId((long) sysUser.getId());
            loginUser.setUsername(sysUser.getUserName());
            loginUser.setRealName(sysUser.getRealName());
            LoginUserUtil.setCookieLoginUser(response, loginUser, null);

            return ResponseUtil.getOK();
        }

        return ResponseUtil.getError("用户名或密码错误");
    }


    @GetMapping(value = "/view/list")
    public String listView(String access, SysUserFormBean query, Model model, HttpSession session) {

        String sessionKey = "SysUserController_listView_query";
        if ( "reload".equals(access) ) {
            query = (SysUserFormBean) session.getAttribute(sessionKey);
            if ( query == null ) {
                query = new SysUserFormBean();
            }
        }

        PageInfo<SysUserVO> page = sysUserService.getPageInfo(query);
        model.addAttribute("page", page);
        model.addAttribute("query", query);

        session.setAttribute(sessionKey, query);
        return "user/list";
    }

    @GetMapping(value = "/view/add")
    public String addView(Model model) {
        SysUserVO obj = new SysUserVO();
        obj.setId(-1);

        model.addAttribute("obj", obj);
        return "user/add-update";
    }

    @GetMapping(value = "/view/update")
    public String updateView(Integer id, Model model) {
        SysUserVO obj = sysUserService.getById(id);
        model.addAttribute("obj", obj);
        return "user/add-update";
    }


    @ResponseBody
    @PostMapping(value = "/ajax/saveOrUpdate")
    public ResponseObject saveOrUpdate(SysUserVO userVO) {
        sysUserService.saveOrUpdate(userVO);
        return ResponseUtil.getOK();
    }

    @ResponseBody
    @PostMapping(value = "/ajax/updateState")
    public ResponseObject updateState(Integer id, Integer state) {
        sysUserService.updateState(id, state);
        return ResponseUtil.getOK();
    }

    @ResponseBody
    @PostMapping(value = "/ajax/updatePassword")
    public ResponseObject updatePassword(Integer id, String password) {
        SysUser user = sysUserMapper.selectByPrimaryKey(id);

        SysUser updateUser = new SysUser();
        updateUser.setId(id);
        updateUser.setPassword(PasswordUtil.encryptPassword(password, user.getUserName()));

        sysUserMapper.updateByPrimaryKeySelective(updateUser);

        return ResponseUtil.getOK();
    }


    @ResponseBody
    @PostMapping(value = "/ajax/delete")
    public ResponseObject delete(Integer id) {
        sysUserService.delete(id);
        return ResponseUtil.getOK();
    }
}
