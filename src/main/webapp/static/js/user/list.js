var user_list = {
    _service: common_service,

    init: function (options) {
        var _this = this;
        if (options) {
            if (options.baseApiUrl) {
                _this._service.init({
                    baseApiUrl: options.baseApiUrl
                });
            }
        }


        $(".update").click(function () {
            var id = $(this).attr('data-id');
            _this._service.windowHref('/back/user/view/update?id=' + id);

        });

        $("#add").click(function () {
            _this._service.windowHref('/back/user/view/add');
        });

        $(".updatePassword").click(function () {
            var id = $(this).attr('data-id');

            var html = '' +
                '    <form class="form form-horizontal" id="form-updatePasswordDiv" method="post" onsubmit="return false" action="">'+
                '        <input type="hidden" name="id" value="'+ id +'">'+
                '        <div class="row cl">'+
                '            <label class="form-label col-sm-2 col-md-2"><span class="span-required">*</span>新密码：</label>'+
                '            <div class="formControls col-sm-5 col-md-5">'+
                '                <input class=" input-text" type="password" name="password" >'+
                '            </div>'+
                '        </div>'+
                '        <div class="row cl">'+
                '            <div class="col-sm-9 col-sm-offset-2">'+
                '                <button id="updatePasswordDivSubmit" class="btn btn-primary radius mr-20" type="button">'+
                '                    <i class="Hui-iconfont">&#xe632;</i> 提交'+
                '                </button>'+
                '            </div>'+
                '        </div>'+
                '    </form>';

            _this._service.layerOpenHtml(html, '重置密码', '550px', '200px');

        });

        $('body').on('click', '#updatePasswordDivSubmit', function () {
            var id = $('#form-updatePasswordDiv').find('input[name="id"]').val();
            var password = $.trim($('#form-updatePasswordDiv').find('input[name="password"]').val());
            if ( password == '' ) {
                layer.msg('请填写完整信息!', {icon: 5, time: 1000});
                return;
            }

            _this._service.ajaxAPI(
                '/back/user/ajax/updatePassword',
                {
                    id: id,
                    password: password
                },
                function (responseBody) {
                    layer.msg('密码重置成功!', {icon: 1, time: 800}, function () {
                        _this._service.windowHref('/back/user/view/list?access=reload');
                    });
                }
            );

        });

        $(".updateState").click(function () {
            var id = $(this).attr('data-id');
            var state = $(this).attr('data-state');
            var msg = "启用";
            if (state == 0) {
                msg = "禁用"
            }

            layer.confirm('确认要'+msg+'吗？', function (index) {
                _this._service.ajaxAPI(
                    '/back/user/ajax/updateState',
                    {
                        id: id,
                        state: state
                    },
                    function (responseBody) {
                        layer.msg('成功!', {icon: 1, time: 800}, function () {
                            _this._service.windowHref('/back/user/view/list?access=reload');
                        });
                    }
                );
            });

        });

        $(".delete").click(function () {
            var id = $(this).attr('data-id');
            var realName = $(this).attr('data-realName');

            layer.confirm('确认要删除【'+ realName +'】吗？', function (index) {
                _this._service.ajaxAPI(
                    '/back/user/ajax/delete',
                    {
                        id: id
                    },
                    function (responseBody) {
                        layer.msg('成功!', {icon: 1, time: 800}, function () {
                            _this._service.windowHref('/back/user/view/list?access=reload');
                        });
                    }
                );
            });

        });

    }

};