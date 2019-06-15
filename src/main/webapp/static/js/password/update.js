var password_update = {
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



        // 添加表单校验
        $("#form-add").validate({
            errorElement: "em",
            errorClass: "c-red",
            //确定校验提示信息在表单标签文字后面显示
            errorPlacement: function (error, element) {
                if (element.is(":radio"))
                    error.appendTo(element.parent());
                else if (element.is("select"))
                    error.appendTo(element.parent().parent());
                else if (element.is(":text"))
                    error.appendTo(element.parent());
                else if (element.is(":checkbox"))
                    error.appendTo(element.parent().parent().parent().parent());
                else
                    error.insertAfter(element);
            },
            rules: {
                'oldPassword': {required: true},
                'newPassword': {required: true},
                'confirmPassword': {required: true}
            },
            messages: {

            }
        });


        // 表单提交事件
        $('#submit').click(function() {
            var form = $("#form-add");
            if (form.valid() == false) {
                layer.msg('请填写完整信息，不允许有空格!', {icon: 5, time: 2000});
            } else {

                var oldPassword = $.trim($('input[name="oldPassword"]').val());
                var newPassword = $.trim($('input[name="newPassword"]').val());
                var confirmPassword = $.trim($('input[name="confirmPassword"]').val());

                if ( oldPassword == '' || newPassword == '' || confirmPassword == '' ) {
                    layer.msg('请填写完整信息，不允许有空格!', {icon: 5, time: 2000});
                    return;
                }

                if ( confirmPassword != newPassword ) {
                    layer.msg('新密码和确认密码不一致!', {icon: 5, time: 2000});
                    return;
                }

                _this._service.ajaxAPI(
                    '/back/password/ajax/update',
                    form.serialize(),
                    function(responseBody) {
                        layer.msg('成功!', {icon: 1, time: 800}, function() {
                            _this._service.windowHref('/back/user/view/login');
                        });
                    }
                );
            }
        }) ;






    }

};