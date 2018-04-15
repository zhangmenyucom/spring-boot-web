if (typeof jQuery === 'undefined') {
    throw new Error('XUI\'s JavaScript requires jQuery')
}

//对象事件扩展
(function (win) {
    var customEventElement = function () {
        this.listeners = {} //如果写在proto里面会 list 会共享
    };
    customEventElement.prototype = {
        //如果listener 记录被绑定的对象，那么多想就无法释放
        addEventListener: function (type, callback) {
            if (!(type in this.listeners)) {
                this.listeners[type] = [];
            }
            this.listeners[type].push(callback);
        },
        removeEventListener: function (type, callback) {
            if (!(type in this.listeners)) {
                return;
            }
            var stack = this.listeners[type];
            for (var i = 0, l = stack.length; i < l; i++) {
                if (stack[i] === callback) {
                    stack.splice(i, 1);
                    return this.removeEventListener(type, callback);
                }
            }
        }
    };
    win.cusEventElement = customEventElement;
}(window));
+
    function ($) {
        'use strict';
        var version = $.fn.jquery.split(' ')[0].split('.')
        if (version[0] < 2 && version[1] < 9 || version[0] == 1 && version[1] == 9 && version[2] < 1) {
            throw new Error('XUI\'s JavaScript requires jQuery version 1.9.1 or higher')
        } else {
            window.Base = {
                siteUrl: location.protocol + '//' + location.hostname,
                loadingPageHtml: '<div class="loading-page"></div>'
            };
            //静态资源路径，针对html目录下一级目录
            Base.catchError = false;
            Base.catchErrorDebug = false;
            Base.assetsUrl = '';
            Base.localUrl = '/res/pc/assets/'
            Base.imgErrUrl = Base.assetsUrl + 'img/img-err.jpg';
        }
    }(jQuery);
+
    function (Base) {
        Base.imgLoad = function (url, done, err) {
            var oImg = new Image();

            function doneHandler() {
                oImg.removeEventListener('load', doneHandler);
                typeof done === 'function' && done(url);
            }

            oImg.addEventListener('load', doneHandler);

            function errHandler() {
                oImg.removeEventListener('error', errHandler);
                typeof err === 'function' && err(url);
            }

            oImg.addEventListener('error', errHandler);

            oImg.src = url;
        };
    }(Base);
//鼠标悬停显示图表
+
    function ($) {
        var codeReg = /\d{6}/;

        function showChart($this, e, prefix) {
            var code = $this.attr('data-code');
            if (!codeReg.test(code)) {
                return;
            }
            var url = prefix + code + '.gif';
            Base.imgLoad(url, function (curUrl) {
                if (url === curUrl) {
                    var $chart = $("#mouseover-showchart");
                    var $win = $(window);
                    $chart.html('<img src="' + curUrl + '"/>');
                    var left = e.clientX + 10;
                    var top = e.clientY + 10;
                    var boxWidth = $chart.offsetWidth;
                    var boxHeight = $chart.offsetHeight;
                    if (left > $win.width - boxWidth) {
                        left = e.clientX - boxWidth - 10;
                    }
                    if (top > $win.height - boxHeight - 20) {
                        top = e.clientY - boxHeight - 10;
                    }
                    $chart.css('transform', 'translate(' + left + 'px,' + top + 'px)').show();
                }
            });
        }

        $.fn.mouseoverShowChart = function () {
            return this.each(function () {
            });
        };
        $(function () {
            $(".show_time").on("mouseover", function (e) {
                showChart($(this), e, 'http://image.sinajs.cn/newchart/min/n/');
            });
            $(".show_time").on("mouseout", function (e) {
                $("#mouseover-showchart").hide();
            });
            $(".show_kline").on("mouseover", function (e) {
                showChart($(this), e, 'http://image.sinajs.cn/newchart/daily/n/');
            });
            $(".show_kline").on("mouseout", function (e) {
                $("#mouseover-showchart").hide();
            });
            $($).on("click", function (e) {
                $("#mouseover-showchart").hide();
            });
        });
    }(jQuery);