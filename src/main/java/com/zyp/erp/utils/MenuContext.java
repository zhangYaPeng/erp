package com.zyp.erp.utils;

import com.zyp.erp.dao.SysResourceMapper;
import com.zyp.erp.domain.SysResource;
import com.zyp.erp.vo.back.MenuVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 张亚鹏 on 2019/1/3.
 */
public class MenuContext {

    private static ConcurrentHashMap<String, List<MenuVO>> menuMap = new ConcurrentHashMap<>();

    private Object lock = new Object();

    @Autowired
    private SysResourceMapper resourceMapper;

    public List<MenuVO> listMenu(int userId) {
        List<MenuVO> menuVOS = menuMap.get(userId + "");

        if ( menuVOS != null ) {
            return menuVOS;
        }

        synchronized (lock) {

            menuVOS = menuMap.get(userId + "");

            if ( menuVOS != null ) {
                return menuVOS;
            }

            menuVOS = getTreeMenuList(userId);
            menuMap.put(userId + "", menuVOS);
            return menuVOS;
        }
    }

    private List<MenuVO> getTreeMenuList(int userId) {
        // 查询出的资源信息
        List<SysResource> list = resourceMapper.listResourceByUserId4PC(userId);

        if ( CollectionUtils.isEmpty(list) ) {
            return new ArrayList<>();
        }

        List<MenuVO> menuList = new ArrayList<>();
        for ( SysResource item : list ) {
            if ( 0 == item.getParentId() ) {
                MenuVO vo = MenuVO.DO2VO(item);
                menuList.add(vo);
            }
        }

        if ( CollectionUtils.isEmpty(menuList) ) {
            return new ArrayList<>();
        }

        for ( MenuVO level1Menu : menuList ) {
            Integer parentId = level1Menu.getId();
            for ( SysResource item : list ) {
                if ( item.getParentId().intValue() == parentId.intValue() ) {
                    MenuVO vo = MenuVO.DO2VO(item);
                    level1Menu.getSub().add(vo);
                }
            }
        }

        return menuList;
    }


    public List<MenuVO> refreshMenuList(int userId) {
        List<MenuVO> menuVOS = menuMap.get(userId + "");

        if ( menuVOS != null ) {
            menuMap.remove(userId + "");
        }

        synchronized (lock) {

            menuVOS = menuMap.get(userId + "");

            if ( menuVOS != null ) {
                menuMap.remove(userId + "");
            }

            menuVOS = getTreeMenuList(userId);
            menuMap.put(userId + "", menuVOS);
            return menuVOS;
        }
    }

    public void clearAll() {
        menuMap.clear();
    }

}
