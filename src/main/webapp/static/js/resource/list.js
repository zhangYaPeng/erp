var resource_list = {
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
                'sort': {required: true},
                'parentId': {required: true},
            },
            messages: {

            }
        });

        $("#add").click(function () {

            _this._service.windowHref('/back/resource/view/add');

        });


        $("#update").click(function() {

            var resourceId = $.trim($("#resourceId").val());

            if(resourceId == '') {
                layer.msg('请选择需要修改的菜单!', {icon:5, time: 2000});
                return;
            }


            var form = $("#form-add");

            if (form.valid() == false) {
                layer.msg('请填写完整信息!', {icon: 5, time: 2000});
            } else {
                layer.confirm('确认要修改吗？', function (index) {
                    _this._service.ajaxAPI(
                        '/back/resource/ajax/update',
                        form.serialize(),
                        function(responseBody) {
                            layer.msg('成功!', {icon: 1, time: 800}, function() {
                                _this._service.windowHref('/back/resource/view/list');
                            });
                        }
                    );
                });
            }

        });


        $("#delete").click(function() {

            var resourceId = $.trim($("#resourceId").val());

            if(resourceId == '') {
                layer.msg('请选择需要删除的菜单!', {icon:5, time: 2000});
                return;
            }

            layer.confirm('确认要删除吗？', function (index) {
                _this._service.ajaxAPI(
                    '/back/resource/ajax/delete',
                    {
                        resourceId: resourceId
                    },
                    function(responseBody) {
                        layer.msg('成功!', {icon: 1, time: 800}, function() {
                            _this._service.windowHref('/back/resource/view/list');
                        });
                    }
                );
            });

        });


        $('.chooseApp').change(function() {
            chooseApp();
        });


        var zNodes;
        var parentNodes;


        chooseApp();



        // 选择应用
        function chooseApp() {
            var appType = $("#appType").val();
            _this._service.ajaxAPI(
                '/back/resource/ajax/findByAppId',
                {appType: appType},
                function(responseBody) {
                    var resp = responseBody;
                    zNodes = resp;
                    parentNodes = resp;
                    createTree();
                    createParentTree();

                }
            );
        }


        function createTree() {
            $.fn.zTree.init($("#resourceNode"), setting, zNodes);
            setCheck();
        }

        function setCheck() {
            var zTree = $.fn.zTree.getZTreeObj("resourceNode");
            zTree.setting.check.chkboxType = {"Y": "s", "N": "ps"};
        }

        function onClick(event, treeId, treeNode, clickFlag) {
            findById(treeNode.id);
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


        var setting = {
            /*check: {
             enable: true
             },*/
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onClick
            },
//            check:{
//                enable:true
//            }
        };

        function checkParent(event, treeId, treeNode) {
            if ( treeNode.checked ) {
                var resourceId = $("#resourceId").val();
                if ( treeNode.id == resourceId ) {
                    $("#resourceParentId").val(0);
                } else {
                    $("#resourceParentId").val(treeNode.id);
                }
            } else {
                $("#resourceParentId").val(0);
            }
        }

        // 根据资源id查找单个资源.
        function findById(resourceId) {
            _this._service.ajaxAPI(
                '/back/resource/ajax/findById',
                {resourceId: resourceId},
                function(responseBody) {
                    var data = responseBody;

                    $("#resourceId").val(data.id);
                    $("#resourceName").val(data.name);
                    $("#resourceUrl").val(data.url);
                    $("#resourceIcon").val(data.icon);

                    $("#resourceState").val(data.state);
                    $("#resourceSort").val(data.sort);

                    $("#resourceParentId").val(data.parentId);
                    if ( data.parentId != 0 ) {
                        var parentTree = $.fn.zTree.getZTreeObj("parentResourceNode");
                        var parentNode = parentTree.getNodeByParam("id", data.parentId);
                        parentTree.checkNode(parentNode,true,true);
                    }
                }
            );
        }

    }

};