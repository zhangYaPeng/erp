var uedemo_list = {
    _service: common_service,

    init: function(options) {
        var _this = this;

        // 设置baseApiUrl
        if (options) {
            if (options.baseApiUrl) {
                _this._service.init({
                    baseApiUrl: options.baseApiUrl
                });
            }
        }

        // 初始化富文本插件
        if (UE) {
            var ue = UE.getEditor('content');
            var html = $('#objectContent').html();
            ue.ready(function () {
                ue.execCommand('inserthtml', html);
            });
        }

    }

};