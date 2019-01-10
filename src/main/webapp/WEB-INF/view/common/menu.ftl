<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <#list menuList as menu>
        <dl id="menu-${menu.id}">
            <dt <#if menu.id==currentMenuId>class="selected" </#if> ><i class="Hui-iconfont">${menu.iconFont}</i> ${menu.name}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd <#if menu.id==currentMenuId>style="display: block;"</#if> >
                <ul>
                    <#list menu.sub as sub>
                    <li <#if sub.id==currentMenuSubId>class="current"</#if> >
                        <a href="${base}${sub.url}"
                           title="${sub.name}">
                            ${sub.name}
                        </a>
                    </li>
                    </#list>
                </ul>
            </dd>
        </dl>
        </#list>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>