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

                <input type="hidden" name="resourceType" value="1">

                <div class="row cl">

                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>资源名称：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="text" name="name" value="">
                    </div>

                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2">资源URL：</label>
                    <div class="formControls col-sm-3 col-md-8">
                        <input class=" input-text" type="text" name="url" value="">
                    </div>
                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2">资源字体图标：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="text" name="icon" value="">
                    </div>
                    <a class="btn btn-link" target="_blank" href="http://www.h-ui.net/Hui-3.7-Hui-iconfont.shtml">字体图标库</a>
                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2">状态：</label>
                    <div class="formControls col-sm-3 col-md-3">
                    <span class="select-box inline">
                    <select name="state" class="select">
                            <option value="1">启用</option>
                            <option value="0">禁用</option>
                    </select>
                    </span>
                    </div>
                </div>

                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>自定义排序：</label>
                    <div class="formControls col-sm-3 col-md-3">
                        <input class=" input-text" type="text" name="sort" value="">
                    </div>
                </div>


                <div class="row cl">
                    <label class="form-label col-sm-2 col-md-2">所属应用：</label>
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
                    <label class="form-label col-sm-2 col-md-2">父节点：</label>
                    <div class="formControls col-sm-3 col-md-3" style="width: 100%;height: 300px; overflow: auto;">
                        <ul id="parentResourceNode" class="ztree"></ul>
                        <input type="hidden" name="parentId" value="0">
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

<script type="text/javascript" src="${base}/static/js/resource/add.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function () {
        resource_add.init();
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>