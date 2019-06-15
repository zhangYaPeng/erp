<script type="text/javascript" src="${base}/static/lib/jquery/1.12.4/jquery.js"></script>
<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${base}/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="${base}/static/h-ui.admin/js/H-ui.admin.page.js"></script>
<script type="text/javascript" src="${base}/static/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${base}/static/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="${base}/static/js/common/service.js"></script>
<script type="text/javascript">
    $(function() {
        if (layer) {
            layer.closeAll('loading');
        }
        var baseApiUrl = "${base}";
        common_service.init({baseApiUrl: baseApiUrl});
    });
</script>

