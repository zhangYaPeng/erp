package com.zyp.erp.controller.back;

import com.github.pagehelper.PageInfo;
import com.zyp.erp.dao.SysRoleUserMapper;
import com.zyp.erp.domain.SysRole;
import com.zyp.erp.domain.SysRoleUser;
import com.zyp.erp.formbean.back.SysRoleFormBean;
import com.zyp.erp.service.SysRoleService;
import com.zyp.erp.utils.ZTreeUtil;
import com.zyp.erp.utils.response.ResponseObject;
import com.zyp.erp.utils.response.ResponseUtil;
import com.zyp.erp.vo.back.SysRoleVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/back/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;


    @GetMapping("/view/list")
    public String listView(String access, SysRoleFormBean query, HttpSession session, Model model) {
        String sessionKey = "SysRoleController_listView_query";
        if ( "reload".equals(access) ) {
            query = (SysRoleFormBean) session.getAttribute(sessionKey);
            if ( query == null ) {
                query = new SysRoleFormBean();
            }
        }

        PageInfo<SysRoleVO> page = sysRoleService.getPageInfo(query);
        model.addAttribute("page", page);
        model.addAttribute("query", query);

        session.setAttribute(sessionKey, query);
        return "role/list";
    }

    @GetMapping("/view/add")
    public String addView(Model model) {
        SysRoleVO sysRoleVO = new SysRoleVO();
        sysRoleVO.setId(-1);

        model.addAttribute("obj", sysRoleVO);
        return "role/add-update";
    }


    @GetMapping(value = "/view/update")
    public String findById(Integer id, Model model) {
        SysRoleVO sysRoleVO = sysRoleService.getById(id);
        model.addAttribute("obj", sysRoleVO);

        return "role/add-update";
    }


    @ResponseBody
    @PostMapping(value = "/ajax/saveOrUpdate")
    public ResponseObject save(SysRoleVO roleVO) {
        sysRoleService.saveOrUpdate(roleVO);
        return ResponseUtil.getOK();
    }

    @ResponseBody
    @PostMapping(value = "/ajax/delete")
    public ResponseObject delete(Integer roleId) {
        sysRoleService.deleteById(roleId);
        return ResponseUtil.getOK();
    }


    @ResponseBody
    @PostMapping(value = "/ajax/findAllAndGroup")
    public Object findAllAndGroup(Integer userId) {

        List<SysRole> roleList = sysRoleService.listAll();

        SysRoleUser roleUserQuery = new SysRoleUser();
        roleUserQuery.setUserId(userId);
        List<SysRoleUser> exitRoleUserList = sysRoleUserMapper.select(roleUserQuery);

        if ( CollectionUtils.isNotEmpty(roleList) ) {
            List list = ZTreeUtil.generateJSStr4Role(roleList, exitRoleUserList);
            return ResponseUtil.getOK(list);
        }

        return ResponseUtil.getOK(new ArrayList<>());
    }

}