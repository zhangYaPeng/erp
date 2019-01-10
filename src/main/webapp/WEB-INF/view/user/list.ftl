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

            <form action="${base}/back/user/view/list" id="search-form">
                <input type="hidden" value="1" id="pageNum" name="pageNum">

                <div class="row cl">
                    <div class="formControls col-sm-1">
                    </div>

                    <div class="formControls col-sm-2">
                        <input name="userName" value="${query.userName}" class="input-text" placeholder="用户名" >
                    </div>

                    <div class="formControls col-sm-2">
                        <input name="phone" value="${query.phone}" class="input-text" placeholder="手机号" >
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
                        <th style="width: 10%">用户名</th>
                        <th style="width: 10%">姓名</th>
                        <th style="width: 10%">手机号</th>
                        <th style="width: 15%">创建时间</th>
                        <th style="width: 5%">状态</th>
                        <th style="width: 10%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list page.list as obj>
                    <tr class="text-c">
                        <td>
                            ${obj.userName}
                        </td>
                        <td>
                            ${obj.realName}
                        </td>
                        <td>
                            ${obj.phone}
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

                            <#if obj.state == 0>
                            <a class="btn btn-link size-MINI updateState"
                               href="javascript:;"
                               data-id="${obj.id}"
                               data-state="1">
                                启用
                            </a>
                            </#if>

                            <#if obj.state == 1>
                            <a class="btn btn-link size-MINI updateState"
                               href="javascript:;"
                               data-id="${obj.id}"
                               data-state="0">
                                禁用
                            </a>
                            </#if>

                            <a class="btn btn-link size-MINI updatePassword"
                               href="javascript:;"
                               data-id="${obj.id}">
                                重置密码
                            </a>

                            <a class="btn btn-link size-MINI delete"
                               href="javascript:;"
                               data-id="${obj.id}"
                               data-realName="${obj.realName}"
                            >
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

<script type="text/javascript" src="${base}/static/js/user/list.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function () {
        user_list.init();
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>