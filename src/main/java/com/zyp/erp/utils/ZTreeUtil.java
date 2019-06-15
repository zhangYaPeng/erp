package com.zyp.erp.utils;

import com.zyp.erp.domain.SysResourceRole;
import com.zyp.erp.domain.SysRole;
import com.zyp.erp.domain.SysRoleUser;
import com.zyp.erp.vo.back.SysResourceVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ZTreeUtil {

    public static List<Map> generateJSStr(List<SysResourceVO> list) {
        /**
         { id:1, pId:0, name:"随意勾选 1", open:true},
         { id:11, pId:1, name:"随意勾选 1-1", open:true},
         { id:111, pId:11, name:"随意勾选 1-1-1"},
         { id:112, pId:11, name:"随意勾选 1-1-2"},
         { id:12, pId:1, name:"随意勾选 1-2", open:true},
         { id:121, pId:12, name:"随意勾选 1-2-1"},
         { id:122, pId:12, name:"随意勾选 1-2-2"},
         */
        List<Map> mapList = new ArrayList<>();
        for (SysResourceVO sysResource : list) {
            Map<String, Object> map = new HashMap<>();
            Integer resourceId = sysResource.getId();
            Integer parentId = sysResource.getParentId();
            String resourceName = sysResource.getName();
            map.put("id", resourceId);
            map.put("pId", parentId);
            map.put("name", resourceName);
            map.put("open", true);

            // 一级资源不允许勾选
            if (0 == parentId.intValue()) {
                //map.put("chkDisabled", true);
            }

            mapList.add(map);
        }
        return mapList;
    }

    public static List<Map<String, Object>> generateJSStr4Resource(List<SysResourceVO> list, List<SysResourceRole> existList) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (SysResourceVO sysResource : list) {
            Map<String, Object> map = new HashMap<>();
            Integer resourceId = sysResource.getId();
            Integer parentId = sysResource.getParentId();
            String resourceName = sysResource.getName();
            map.put("id", resourceId);
            map.put("pId", parentId);
            map.put("name", resourceName);
            map.put("open", true);

            // 一级资源不允许勾选
            if (0 == parentId.longValue()) {
                //map.put("chkDisabled", true);
            }

            for (SysResourceRole item : existList) {
                if ( resourceId.intValue() == item.getResourceId().intValue() ) {
                    map.put("checked", true);
                    break;
                }
            }

            mapList.add(map);
        }
        return mapList;
    }

    public static List<Map<String, Object>> generateJSStr4Role(List<SysRole> roleList, List<SysRoleUser> exitRoleUserList) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        //创建SysRole节点
        for (SysRole sysRole : roleList) {
            Map<String, Object> m2 = new HashMap<>();
            Integer roleId = sysRole.getId();
            String roleName = sysRole.getName();
            m2.put("id", "" + roleId);
            m2.put("pId", "0");
            m2.put("name", roleName);
            for (SysRoleUser roleUser : exitRoleUserList) {
                if ( roleId.intValue() == roleUser.getRoleId().intValue() ) {
                    m2.put("checked", true);
                    break;
                }
            }
            mapList.add(m2);
        }

        return mapList;
    }
}
