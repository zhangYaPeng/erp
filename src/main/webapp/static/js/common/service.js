var common_service = {
    config : {
        baseUrl : ''
    },

    baseAPIUrl: function() {
        return this.config.baseUrl;
    },

    init: function(options) {
        if (options) {
            if (options.baseApiUrl) {
                this.config.baseUrl = options.baseApiUrl;
            }

            if (laydate) {
                laydate.skin('molv');
            }
        }
    },

    ajaxAPI : function (url, params, onSuccess, onFail, method, async, contentType) {
        url = this.baseAPIUrl()+url;
        if (layer) {
            layer.load();//加载层,防止多次点击
        }
        $.ajax({
            type: method || 'POST',
            url: url,
            //dataType : options.dataType||'json',
            // contentType : contentType || 'application/json; charset=utf-8',
            data: params,
            async : async || true,
            success: function (response) {
                if (layer) {
                    layer.closeAll('loading');
                }

                if (response.msg.success == true) {
                    if ( onSuccess ) {
                        onSuccess(response.data);
                    }

                } else {
                    if ( onFail ) {
                        onFail(response.msg);
                    } else {
                        console.log(response);
                        if (layer) {
                            layer.msg(response.msg.info, {icon: 5, time: 2000});
                        }
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (layer) {
                    layer.closeAll('loading');
                }

                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
                if (layer) {
                    layer.msg("服务器发生异常，请联系程序员小哥哥~~", {icon: 5, time: 2000});
                }
            }
        });
    },

    fileAjax : function (fileElementId, onSuccess, onFail) {
        this.fileAjaxWithUrl("/upload/ajaxUploadImg", {}, fileElementId, onSuccess, onFail);
    },

    fileAjaxWithUrl : function (url, data, fileElementId, onSuccess, onFail) {
        if (layer) {
            layer.load();//加载层,防止多次点击
        }

        $.ajaxFileUpload({
            url: this.baseAPIUrl() + url,
            data: data,
            secureurl: false,
            fileElementId: fileElementId,
            dataType: "json",
            success: function (response) {
                if (layer) {
                    layer.closeAll('loading');
                }
                if (response.msg.success == true) {
                    onSuccess(response.data);
                } else {
                    if (onFail) {
                        onFail(response.msg);
                    } else {
                        console.log(response);
                        if (layer) {
                            layer.msg(response.msg.info, {icon: 5, time: 2000});
                        }
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (layer) {
                    layer.closeAll('loading');
                }

                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
                if (layer) {
                    layer.msg("服务器发生异常，请联系程序员哥哥~~", {icon: 5, time: 2000});
                }
            }
        });
    },

    layerOpenHtml: function(content, title, width, height) {
        var title = title;
        width = width || '900px';
        height = height || '500px';
        layer.open({
            type: 1,
            skin: 'layui-layer-lan',
            title: title,
            fix: false,
            shadeClose: true,
            maxmin: true,
            area: [width, height],
            content: content,
            scrollbar: false
        });
    },

    layerOpen: function(url, title, width, height) {
        var url =  this.baseAPIUrl() + url;
        var title = title;
        width = width || '900px';
        height = height || '500px';
        layer.open({
            type: 2,
            skin: 'layui-layer-lan',
            title: title,
            fix: false,
            shadeClose: true,
            maxmin: true,
            area: [width, height],
            content: url,
            scrollbar: false
        });
    },
    layerFullOpen: function(url, title) {
        var url =  this.baseAPIUrl() + url;
        var title = title;

        var index = layer.open({
            type:2,
            skin: 'layui-layer-lan',
            title: title,
            fix: false,
            shadeClose: true,
            content: url,
            scrollbar: false
        });
        layer.full(index);
    },

    layerFullOpenHtml: function(content, title) {
        var title = title;
        var index = layer.open({
            type: 1,
            skin: 'layui-layer-lan',
            title: title,
            fix: false,
            shadeClose: true,
            maxmin: true,
            content: content,
            scrollbar: false
        });
        layer.full(index);
    },

    layerOpenImg: function(_this, divId, width, height) {
        var imageUrl = $(_this).attr("src");
        $("#"+ divId +"  img").attr("src", imageUrl);
        var area = null;
        if (width && height) {
            area = [width, height];
        } else if (width) {
            area = width;
        } else if (height) {
            area = height;
        }
        layer.open({
            type: 1,
            shadeClose: true,
            scrollbar: false,
            shade: 0.3,
            title: false, //不显示标题
            area: area, //宽高
            content: $('#' + divId), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(){
            }
        });
    },

    windowHref: function(url) {
        // if (layer) {
        //     layer.closeAll('loading');
        // }
        // if (layer) {
        //     layer.load();//加载层,防止多次点击
        // }
        window.location.href = this.baseAPIUrl() + url;
    }
};

function changePage(pageNum) {
    $("#pageNum").val(pageNum);
    $("#search-form").submit();
}

String.prototype.replaceAll = function(src, target){
    return this.replace(new RegExp(src,"gm"), target);
}