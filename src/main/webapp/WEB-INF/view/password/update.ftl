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

            <form class="form form-horizontal" id="form-add" method="post" onsubmit="return false" action="">

                <div class="row cl">

                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>旧密码：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="password" name="oldPassword" value="" autocomplete="new-password">
                    </div>

                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>新密码：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="password" name="newPassword" value="" autocomplete="new-password">
                    </div>
                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>确认密码：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="password" name="confirmPassword" value="" autocomplete="new-password">
                    </div>
                </div>


                <div class="row cl">
                    <div class="col-sm-9 col-sm-offset-2">
                        <button id="submit" class="btn btn-primary mr-20" type="button">
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
<script type="text/javascript" src="${base}/static/js/password/update.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function () {
        password_update.init();
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>