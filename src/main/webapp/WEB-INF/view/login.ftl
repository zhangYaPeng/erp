<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <LINK rel="Bookmark" href="${base}/static/favicon.ico" >
    <LINK rel="Shortcut Icon" href="${base}/static/favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base}/static/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base}/static/lib/respond.min.js"></script>
    <![endif]-->
    <link href="${base}/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="${base}/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
    <link href="${base}/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${base}/static/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css"  />
    <link rel="stylesheet" type="text/css" href="${base}/static/lib/laypage/1.2/skin/laypage.css"/>

    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script><![endif]-->
    <title>ERP</title>
</head>
<body>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" action="" method="post" onsubmit="return false">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input name="username" type="text" placeholder="用户名" class="input-text size-L" autocomplete="new-password">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input name="password" type="password" placeholder="密码" class="input-text size-L" autocomplete="new-password">
                </div>
            </div>

            <div class="row cl">
                <div class="formControls col-xs-7 col-xs-offset-3">
                    <button id="loginBtn" class="btn btn-success radius size-L" style="width: 100%;" >&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="${base}/static/lib/jquery/1.12.4/jquery.js"></script>
<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base}/static/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="${base}/static/h-ui/js/H-ui.js"></script>
<script src="${base}/static/lib/js.cookie-2.2.0.min.js"></script>
<script type="text/javascript" src="${base}/static/js/common/service.js"></script>

<script type="text/javascript">
    $(function() {
        var baseApiUrl = "${base}";
        common_service.init({baseApiUrl: baseApiUrl});

        Cookies.remove('_i_u_cookie_');

        $('#loginBtn').on('click', function (event) {
            event.preventDefault();
            var username = $.trim($('input[name="username"]').val());
            if (username == '') {
                layer.msg("请输入用户名", {icon: 5, time: 700});
                return;
            }
            var password = $.trim($('input[name="password"]').val());
            if (password == '') {
                layer.msg("请输入密码", {icon: 5, time: 700});
                return;
            }
            common_service.ajaxAPI(
                    '/back/user/ajax/login',
                    {
                        username: username,
                        password: password
                    },
                    function(responseBody) {
                        window.location.href = '${base}/index';
                    }
            );
        });

    });
</script>
</body>
</html>