<script type="text/javascript" src="${base}/static/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="${base}/static/lib/ueditor/1.4.3/ueditor.all.js"></script>
<script type="text/javascript" src="${base}/static/lib/lang/zh-cn/zh-cn.js" charset="utf-8" ></script>
<script>
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;//自定义方法 作用：保留原有getActionUrl方法功能
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadfile') {//action 名字 可以有图片 文件 视频上传 待扩展
            var weChatInfoId = $('input[name="weChatInfoId"]').val();
            return '${base}/weChatArticle/ajaxUploadImgUe/' + weChatInfoId; //自定义图片上传路径
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    };
</script>


