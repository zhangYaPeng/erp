var role_list = {
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
            _this._service.windowHref('/back/role/view/update?id=' + id);

        });

        $("#add").click(function () {
            _this._service.windowHref('/back/role/view/add');
        });

        $(".delete").click(function () {

            var id = $(this).attr('data-id');

            layer.confirm('确认要删除吗？', function (index) {
                _this._service.ajaxAPI(
                    '/back/role/ajax/delete',
                    {
                        roleId: id
                    },
                    function (responseBody) {
                        layer.msg('成功!', {icon: 1, time: 800}, function () {
                            _this._service.windowHref('/back/role/view/list');
                        });
                    }
                );
            });

        });


    }

};