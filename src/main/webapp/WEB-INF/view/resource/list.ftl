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
            <div class="row cl">
                <label class="form-label col-sm-2 col-md-1">应用：</label>
                <div class="formControls col-sm-3 col-md-3">
                   <span class="select-box inline">
                    <select id="appType" name="appType" class="select chooseApp">
                        <option value="1">PC</option>
                        <option value="2">移动端</option>
                    </select>
                    </span>
                </div>
            </div>

            <div class="top-menu">
                <form action="#" id="search-form" method="post" >


                    <div class="row cl clearfix">

                        <div class="formControls col-md-8">
                        </div>

                        <div class="formControls col-sm-1 col-md-1">
                            <div class="text-c">
                                <a id="add" class="btn btn-primary" href="javascript:;" title="添加" >
                                    <i class="Hui-iconfont">&#xe610;</i> 添加
                                </a>
                            </div>
                        </div>

                        <div class="formControls col-sm-1 col-md-1" >
                            <div class="text-c">
                                <button name="" id="update" class="btn btn-warning" type="button"> 修改
                                </button>
                            </div>
                        </div>

                        <div class="formControls col-sm-1 col-md-1">
                            <div class="text-c">
                                <button name="" id="delete" class="btn btn-danger" type="button"> 删除
                                </button>
                            </div>
                        </div>

                    </div>
                </form>
            </div>


            <div class="left_menu" style="width: 45%;height: 800px;overflow:auto;float:left;">
                <div class="well">
                    <ul id="resourceNode" class="ztree"></ul>
                </div>
            </div>

            <div class="right_menu" style="width: 54%;height: 800px;overflow:auto;float: right;">

                <form class="form form-horizontal" id="form-add" method="post" action="">

                    <input type="hidden" name="id" id="resourceId">

                    <div class="row cl">

                        <label class="form-label col-md-3"><span class="span-required">*</span>资源名称：</label>
                        <div class="formControls col-md-6">
                            <input class=" input-text" type="text" name="name" value="" id="resourceName">
                        </div>

                    </div>

                    <div class="row cl">
                        <label class="form-label col-md-3">资源URL：</label>
                        <div class="formControls col-md-6">
                            <input class=" input-text" type="text" name="url" value="" id="resourceUrl">
                        </div>
                    </div>

                    <div class="row cl">
                        <label class="form-label col-md-3">资源字体图标：</label>
                        <div class="formControls col-md-6">
                            <input class=" input-text" type="text" name="icon" value="" id="resourceIcon">
                        </div>
                        <a class="btn btn-link" target="_blank" href="http://www.h-ui.net/Hui-3.7-Hui-iconfont.shtml">字体图标库</a>
                    </div>


                    <div class="row cl">
                        <label class="form-label col-md-3">状态：</label>
                        <div class="formControls col-md-3">
                        <span class="select-box inline">
                        <select name="state" class="select" id="resourceState" >
                                <option value="1">启用</option>
                                <option value="0">禁用</option>
                        </select>
                        </span>
                        </div>
                    </div>

                    <div class="row cl">
                        <label class="form-label col-md-3"><span class="span-required">*</span>自定义排序：</label>
                        <div class="formControls col-md-3">
                            <input class=" input-text" type="text" name="sort" value="" id="resourceSort">
                        </div>
                    </div>

                    <div class="row cl">
                        <label class="form-label col-md-3">父节点：</label>
                        <div class="formControls col-md-6" style="width:100%; height: 300px;overflow: auto;">
                            <ul id="parentResourceNode" class="ztree"></ul>
                            <input type="hidden" name="parentId" value="" id="resourceParentId">
                        </div>
                    </div>

                </form>
            </div>


        </article>
    </div>
</section>

<!--_footer 作为公共模版分离出去-->
    <#include "/common/footer-js.ftl">
<!--/_footer /作为公共模版分离出去-->

<script type="text/javascript" src="${base}/static/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${base}/static/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>

<script type="text/javascript" src="${base}/static/js/resource/list.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function() {
        resource_list.init();
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>