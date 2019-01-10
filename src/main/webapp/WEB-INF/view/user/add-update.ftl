<!DOCTYPE HTML>
<html>
<head>
    <!--_meta 作为公共模版分离出去-->
    <#include "/common/meta.ftl">
    <!--/meta 作为公共模版分离出去-->
    <link rel="stylesheet" href="${base}/static/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
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

            <form class="form form-horizontal" id="form-add" method="post" onsubmit="return false" action="">

                <input type="hidden" name="id" value="${(obj.id)!}">

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>用户名：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="text" name="userName" value="${(obj.userName)!}"  <#if obj.id != -1>readonly</#if> autocomplete="new-password" >
                    </div>
                </div>

                <#if obj.id == -1>
                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>密码：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="password" name="password" autocomplete="new-password" >
                    </div>
                </div>
                </#if>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>姓名：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="text" name="realName" value="${(obj.realName)!}">
                    </div>
                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>手机号：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="tel" name="phone" value="${(obj.phone)!}">
                    </div>
                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2">选择角色：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <td>
                            <ul id="roleNode" class="ztree"></ul>
                            <input type="hidden" name="roleIds" value="" id="roleIds">
                        </td>
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
<script type="text/javascript" src="${base}/static/js/user/add-update.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function() {
        user_add_update.init();
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>