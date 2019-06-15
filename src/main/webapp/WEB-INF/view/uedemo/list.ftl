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

            <form class="form form-horizontal" id="form-add">

                <div class="row cl">
                    <label class="form-label col-sm-1"><span class="c-red">*</span>内容：</label>
                    <div class="formControls col-sm-2">
                        <script id="content" name="content" type="text/plain" style="width:800px;height:500px;" ></script>
                        <!-- 实例化编辑器 -->
                    </div>
                    <div style="display: none" id="objectContent"></div>
                </div>

                <div class="row cl">
                    <div class="col-sm-9 col-sm-offset-1 mb-40">
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
    <#include "/common/ueditor.ftl">

<script type="text/javascript" src="${base}/static/js/uedemo/list.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function() {
        uedemo_list.init();
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>