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
<link rel="stylesheet" href="${base}/static/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

<section class="Hui-article-box">
    <div class="Hui-article">
        <article class="cl pd-20">
            <form class="form form-horizontal" id="form-add" method="post" onsubmit="return false" action="">

                <input type="hidden" name="id" value="${(obj.id)!}">

                <div class="row cl">

                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>角色名称：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="text" name="name" value="${(obj.name)!}">
                    </div>

                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2">角色(用于程序判断)：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="text" name="role" value="${(obj.role)!}">
                    </div>
                </div>


                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2">状态：</label>
                    <div class="formControls col-sm-3 col-md-3">
                    <span class="select-box inline">
                    <select id="isAvailable" name="isAvailable" class="select">
                        <option value="1" <#if obj.state = 1>selected</#if>>启用</option>
                        <option value="0" <#if obj.state = 0>selected</#if>>禁用</option>
                    </select>
                    </span>
                    </div>
                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2">应用：</label>
                    <div class="formControls col-sm-3 col-md-3">
                    <span class="select-box inline">
                    <select name="appType" class="select chooseApp">
                        <option value="1">PC</option>
                        <option value="2">移动端</option>
                    </select>
                    </span>
                    </div>
                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2">选择权限：</label>
                    <div class="formControls col-sm-3 col-md-3" style="width: 100%;height: 300px; overflow: auto;">
                        <ul id="permissionNode" class="ztree"></ul>
                        <input type="hidden" name="resourceIds" value="" id="resourceIds">
                    </div>
                </div>

                <div class="row cl">
                    <div class="col-sm-9 col-sm-offset-2">
                        <button id="submit" class="btn btn-primary radius mr-20" type="button">
                            <i class="Hui-iconfont">&#xe632;</i> 提交
                        </button>
                    </div>
                </div>

            </form>
        </article>
    </div>
</section>

<!--_footer 作为公共模版分离出去-->
    <#include "/common/footer-js.ftl">
<!--/_footer /作为公共模版分离出去-->

<script type="text/javascript" src="${base}/static/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${base}/static/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${base}/static/js/role/add-update.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function() {
        role_add.init();
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>