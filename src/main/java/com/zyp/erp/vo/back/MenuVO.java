package com.zyp.erp.vo.back;




import com.zyp.erp.domain.SysResource;

import java.util.ArrayList;
import java.util.List;


public class MenuVO {
    private Integer id;
    private String name;
    private String iconFont;
    private String url;

    private List<MenuVO> sub;


    public static MenuVO DO2VO(SysResource sysResource) {
        return new MenuVO(sysResource.getId(), sysResource.getName(), sysResource.getIcon(), sysResource.getUrl());
    }


    public MenuVO() {
    }

    public MenuVO(Integer id, String name, String iconFont, String url) {
        this.id = id;
        this.name = name;
        this.iconFont = iconFont;
        this.url = url;
        this.sub = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconFont() {
        return iconFont;
    }

    public void setIconFont(String iconFont) {
        this.iconFont = iconFont;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuVO> getSub() {
        return sub;
    }

    public void setSub(List<MenuVO> sub) {
        this.sub = sub;
    }
}
