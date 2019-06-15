var resource_add = {
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
                'name': {required: true},
                'sort': {required: true}
            },
            messages: {

            }
        });


        // 表单提交事件
        $('#submit').click(function() {
            var form = $("#form-add");
            if (form.valid() == false) {
                layer.msg('请填写完整信息!', {icon: 5, time: 2000});
            } else {
                _this._service.ajaxAPI(
                    '/back/resource/ajax/add',
                    form.serialize(),
                    function(responseBody) {
                        layer.msg('成功!', {icon: 1, time: 800}, function() {
                            window.location.href = _this._service.baseAPIUrl() + '/back/resource/view/list';
                        });
                    }
                );
            }
        }) ;



        $('.chooseApp').change(function() {
            chooseApp();
        });


        var parentNodes;

        chooseApp();


        // 选择应用
        function chooseApp() {
            var appType = $('select[name="appType"]').val();
            _this._service.ajaxAPI(
                '/back/resource/ajax/findByAppId',
                {appType: appType},
                function(responseBody) {
                    var resp = responseBody;
                    parentNodes = resp;
                    createParentTree();

                }
            );
        }

        function createParentTree() {
            $.fn.zTree.init($("#parentResourceNode"), parentSetting, parentNodes);
        }


        // 父节点
        var parentSetting = {
            check: {
                enable: true,
                chkStyle: "radio",
                radioType: "all"
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: checkParent
            }
        };

        function checkParent(event, treeId, treeNode, clickFlag) {
            $('input[name="parentId"]').val(treeNode.id);
        }


    }

};