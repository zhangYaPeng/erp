var role_add = {
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
                'name': {required: true}

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
                    '/back/role/ajax/saveOrUpdate',
                    form.serialize(),
                    function(responseBody) {
                        layer.msg('成功!', {icon: 1, time: 800}, function() {
                            var id = $.trim($('input[name="id"]').val());
                            if ( id != -1 && id != '' ) {
                                _this._service.windowHref('/back/role/view/list?access=reload');
                            } else {
                                _this._service.windowHref('/back/role/view/list');
                            }

                        });
                    }
                );
            }
        }) ;

        chooseApp();

        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: onCheck
            }
        };

        var zNodes;

        function onCheck(e, treeId, treeNode) {
            count();
        }

        function count() {
            var zTree = $.fn.zTree.getZTreeObj("permissionNode"),
                checkedNodes = zTree.getCheckedNodes(true);
            //nocheckCount = zTree.getCheckedNodes(false).length;

            var resourceIds = "";
            for (var i = 0; i < checkedNodes.length; i++) {
                resourceIds += checkedNodes[i].id + ",";
            }
            //alert(resourceIds);

            $("#resourceIds").val(resourceIds);

        }

        function setCheck() {
            var zTree = $.fn.zTree.getZTreeObj("permissionNode");
            zTree.setting.check.chkboxType = {"Y": "s", "N": "ps"};
        }

        function createTree() {
            $.fn.zTree.init($("#permissionNode"), setting, zNodes);
            setCheck();
            onCheck();
        }

        // 选择应用
        function chooseApp() {
            var appType = $('select[name="appType"]').val();
            var roleId = $('input[name="id"]').val();

            _this._service.ajaxAPI(
                '/back/resource/ajax/findByAppTypeAndRoleId',
                {appType: appType, roleId: roleId},
                function(responseBody) {
                    var resp = responseBody;
                    zNodes = resp;
                    createTree();
                }
            );
        }


        $('.chooseApp').change(function() {
            chooseApp();
        });




    }

};