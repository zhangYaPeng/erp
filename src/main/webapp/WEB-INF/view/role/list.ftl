<!DOCTYPE HTML>
<html>
<head>
    <!--_meta 作为公共模版分离出去-->
    <#include "/common/meta.ftl">
    <!--/meta 作为公共模版分离出去-->
</head>
<body>
<!--_header 作为公共模版分离出去-->
    <#include "/common/header.ftl">
<!--/_header 作为公共模版分离出去-->

<!--_menu 作为公共模版分离出去-->
    <#include "/common/menu.ftl">
<!--/_menu 作为公共模版分离出去-->

<section class="Hui-article-box">
    <div class="Hui-article">
        <article class="cl pd-20">

            <form action="${base}/back/role/view/list" id="search-form">
                <input type="hidden" value="1" id="pageNum" name="pageNum">

                <div class="row cl">
                    <div class="formControls col-sm-4">
                    </div>

                    <div class="formControls col-sm-2">
                        <input name="name" value="${query.name}" class="input-text" placeholder="角色名称" >
                    </div>

                    <div class="formControls col-sm-1">
                        <div class="text-c">
                            <button class="btn btn-success" type="button" onclick="changePage(1)">
                                <i class="Hui-iconfont">&#xe665;</i> 查询
                            </button>
                        </div>
                    </div>
                </div>
            </form>

            <div class="cl pd-5 bg-1 bk-gray mt-20">
                <a id="add" class="btn btn-primary" href="javascript:;" title="添加" >
                    <i class="Hui-iconfont">&#xe610;</i> 添加
                </a>
                <span class="r">共有数据：<strong>${page.total}</strong> 条</span>
            </div>

            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                    <thead>
                    <tr class="text-c">
                        <th style="width: 10%">角色名称</th>
                        <th style="width: 10%">角色</th>
                        <th style="width: 15%">创建时间</th>
                        <th style="width: 5%">状态</th>
                        <th style="width: 10%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list page.list as obj>
                    <tr class="text-c">
                        <td>
                            ${obj.name}
                        </td>
                        <td>
                            ${obj.role}
                        </td>
                        <td>
                            ${(obj.addTime)?string("yyyy-MM-dd HH:mm")}
                        </td>
                        <td>
                            <#if obj.state == 0><span class="label label-warning">禁用</span></#if>
                            <#if obj.state == 1><span class="label label-success">启用</span></#if>
                        </td>

                        <td class="f-14 td-manage">
                            <a class="btn btn-link size-MINI update"
                               href="javascript:;"
                               data-id="${obj.id}">
                                修改
                            </a>

                            <a class="btn btn-link size-MINI delete"
                               href="javascript:;"
                               data-id="${obj.id}">
                                删除
                            </a>
                        </td>
                    </tr>
                    </#list>

                    </tbody>
                </table>
            </div>

            <#include "/common/page.ftl">

        </article>
    </div>
</section>

<!--_footer 作为公共模版分离出去-->
    <#include "/common/footer-js.ftl">
<!--/_footer /作为公共模版分离出去-->
<script type="text/javascript" src="${base}/static/js/role/list.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function() {
        role_list.init();
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>