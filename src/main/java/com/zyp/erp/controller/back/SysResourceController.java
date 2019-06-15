package com.zyp.erp.controller.back;


import com.zyp.erp.dao.SysResourceRoleMapper;
import com.zyp.erp.domain.SysResourceRole;
import com.zyp.erp.service.SysResourceService;
import com.zyp.erp.utils.MenuContext;
import com.zyp.erp.utils.ZTreeUtil;
import com.zyp.erp.utils.response.ResponseObject;
import com.zyp.erp.utils.response.ResponseUtil;
import com.zyp.erp.vo.back.SysResourceVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/back/resource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private SysResourceRoleMapper resourceRoleMapper;

    @Autowired
    private MenuContext menuContext;

    @RequestMapping("/view/list")
    public String list(Model model) {
        return "resource/list";
    }

    @ResponseBody
    @PostMapping("/ajax/findByAppId")
    public ResponseObject findByAppId(Integer appType) {
        List<SysResourceVO> sysResourceVOS = sysResourceService.findByAppType(appType);
        if (CollectionUtils.isNotEmpty(sysResourceVOS)) {
            List list = ZTreeUtil.generateJSStr(sysResourceVOS);
            return ResponseUtil.getOK(list);
        }

        return ResponseUtil.getOK(new ArrayList<>());
    }

    @ResponseBody
    @RequestMapping("/ajax/findByAppTypeAndRoleId")
    public Object findByAppTypeAndRoleId(Integer appType, Integer roleId) {
        List<SysResourceVO> resourceList = sysResourceService.findByAppType(appType);

        SysResourceRole resourceRoleQuery = new SysResourceRole();
        resourceRoleQuery.setRoleId(roleId);
        List<SysResourceRole> existList = resourceRoleMapper.select(resourceRoleQuery);

        if (CollectionUtils.isNotEmpty(resourceList)) {
            List<Map<String, Object>> list = ZTreeUtil.generateJSStr4Resource(resourceList, existList);
            return ResponseUtil.getOK(list);
        }

        return ResponseUtil.getOK(new ArrayList());
    }

    @ResponseBody
    @PostMapping("/ajax/findById")
    public ResponseObject findById(Integer resourceId) {
        SysResourceVO sysResourceVO = sysResourceService.getById(resourceId);
        return ResponseUtil.getOK(sysResourceVO);
    }



    @ResponseBody
    @PostMapping("/ajax/delete")
    public ResponseObject delete(Integer resourceId) {
        sysResourceService.deleteByIdAndSons(resourceId);
        menuContext.clearAll();
        return ResponseUtil.getOK();
    }

    @ResponseBody
    @PostMapping("/ajax/update")
    public ResponseObject update(SysResourceVO resource) {
        sysResourceService.update(resource);
        menuContext.clearAll();
        return ResponseUtil.getOK();
    }

    @RequestMapping("/view/add")
    public String addView(Model model) {
        return "resource/add";
    }

    @ResponseBody
    @PostMapping("/ajax/add")
    public ResponseObject addAjax(SysResourceVO resource) {
        sysResourceService.save(resource);
        return ResponseUtil.getOK();
    }

}