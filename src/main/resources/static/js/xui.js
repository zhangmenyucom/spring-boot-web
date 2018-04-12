/*!
 * XUI v1.0.0
 * Copyright 2016 WWW.YUNCAIJING.COM, Inc.
 */

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

(function () {
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
}());

+
    function ($) {
        'use strict';
        var version = $.fn.jquery.split(' ')[0].split('.')
        if ((version[0] < 2 && version[1] < 9) || (version[0] == 1 && version[1] == 9 && version[2] < 1)) {
            throw new Error('XUI\'s JavaScript requires jQuery version 1.9.1 or higher')
        } else {

            window.XUI = {
                version: '1.0.0',
                reg: {
                    //匹配所有空白字符
                    trimAll: /\s/g,
                    qq: /^[1-9]\d{4,11}$/,
                    //email: /^[a-z\d]+[\w.-]*@[a-z\d]+(\-*[a-z\d])*(\.[a-z]{2,6})+$/i,
                    email: /^[a-z\d]([\w-.]+(?:[a-z\d]))?@[a-z\d]+(\-*[a-z\d])*(\.[a-z]{2,6})+$/i,
                    //匹配手机号
                    tel: /^1[34578]\d{9}$/,
                    //匹配固号
                    ftel: /^[1-9]\d{0,3}-\d{3}-\d{8}$/,
                    //匹配身份证
                    id: /^[1-8]{2}\d{4}[12][089]\d{2}[01]\d[0-3]\d{4}[\dX]$/i,
                    formatPassword: /[^\w\s\\\[\]`~!@#$%^&*()-+={};:'"|,.<>/?]/g
                }
            };
            window.Base = {
                siteUrl: location.protocol + '//' + location.hostname,
                loadingPageHtml: '<div class="loading-page"></div>',
                str: {
                    trimAll: function (string) {
                        return string.replace(XUI.reg.trimAll, '');
                    }
                },
                //静态模板内路径(图片)赋值
                formatTemplate: function (msg) {
                    return msg.replace(/\{\s*assetsUrl\s*\}/g, Base.assetsUrl);
                }
            };
            //静态资源路径，针对html目录下一级目录
            Base.catchError = false;
            Base.catchErrorDebug = false;
            Base.errorPusher = function (errorMessage, scriptURI, lineNumber, columnNumber, errorObj) {

                var error_msg = '',
                    stack,
                    hosts,
                    script_name;
                !!scriptURI && (script_name = scriptURI.match(/\w*\.js/g));
                !!script_name && (script_name = script_name[0]);

                if (errorObj) {
                    stack = errorObj.stack
                } else {
                    stack = "没有堆栈信息"
                }
                hosts = location.host;
                //error_msg += "错误信息：" +errorMessage + ',' +"出错文件：" + script_name + ','+ 'userAgent：'+ navigator.userAgent+','+'出错连接：'+ location.pathname;//stack host
                error_msg = {
                    host: location.host,
                    path: location.pathname,
                    msg: errorMessage,
                    file: script_name,
                    stack: stack,
                    userAgent: navigator.userAgent
                }
                if (Base.catchErrorDebug) {
                    // console.log(error_msg)
                    // console.log(errorObj)
                }
                if (!!Base.catchError) {
                    if (window.r) {
                        // window.r.ycj_api.makeApi({
                        //     name: 'error',
                        //     url: 'http://fe.yuncaijing.com:3080/post_log',
                        //     param: error_msg,
                        //     full_res: true
                        // })

                    } else {
                        // $.post('http://fe.yuncaijing.com:3080/post_log',error_msg,function(data){
                        //console.log('has sent this error to server');
                        // })
                    }
                }
            };
            window.onerror = Base.errorPusher;
            Base.tryCatch = function (error_msg) {
                try {
                    throw new Error(error_msg);
                } catch (e) {
                    console.error(e.message);
                    this.errorPusher(e.message, 'trycatch.js', 0, 0, e);
                }
            };

            Base.assetsUrl = '';
            Base.localUrl = '/res/pc/assets/';
            //本地调试，针对html目录下二级目录下的*.html
            if (location.host == 'www.yuncaijing.com' || location.host == 'www.online.yuncaijing.com') {
                Base.catchError = true;
                Base.assetsUrl = 'http://aliyun.yuncaijing.com/res/pc/assets/';
                Base.localUrl = 'http://aliyun.yuncaijing.com/res/pc/assets/';
                //线上路径
                if (location.protocol === 'https:') { // 增加https协议
                    Base.assetsUrl = 'https://aliyun.yuncaijing.com/res/pc/assets/';
                    Base.localUrl = 'https://aliyun.yuncaijing.com/res/pc/assets/';
                }
            } else if (/test/.test(location.host.match(/www\.(\S*)\.yuncaijing\.com/))) {
                Base.catchErrorDebug = true;
                Base.assetsUrl = 'http://cdn.test.yuncaijing.com/res/pc/assets/';
                Base.localUrl = 'http://cdn.test.yuncaijing.com/res/pc/assets/';
                if (location.protocol === 'https:') { // 增加https协议
                    Base.assetsUrl = 'https://cdn.test.yuncaijing.com/res/pc/assets/';
                    Base.localUrl = 'https://cdn.test.yuncaijing.com/res/pc/assets/';
                }
            } else {
                //Base.catchError = true;
                Base.assetsUrl = '/res/pc/src/';
                Base.localUrl = '/res/pc/assets/'

            }
            Base.imgErrUrl = Base.assetsUrl + 'img/img-err.jpg';
            Base.zdf = function (zdf) {
                zdf = '' + zdf;
                //将涨跌幅统一成数字类型
                if (zdf.slice(-1) === '%') {
                    zdf = parseFloat(zdf.slice(0, -1)).toFixed(2);
                } else {
                    zdf = parseFloat(zdf).toFixed(2);
                }

                //判断涨跌幅
                var className = 'stock-gray';
                if (zdf > 0) {
                    zdf = '+' + zdf;
                    className = 'stock-red';
                } else if (zdf < 0) {
                    className = 'stock-green';
                } else {
                    zdf = '0.00';
                }
                return {
                    className: className,
                    pureZdf: zdf,
                    zdf: zdf + '%'
                }
            };
            //登录更新
            Base.updatesOfLoginFnSet = [];
            Base.updatesOfLogin = function (fn) {
                if (typeof fn === 'function') {
                    Base.updatesOfLoginFnSet.push(fn);
                }
            };
            Base.dataHandler = {
                toDECTimestamp: function (HEX) {
                    return parseInt(HEX, 16) * 1000
                },
                scientific: function (num) {
                    var unit,
                        complete,
                        part,
                        num = num === null ? '-' : num.toString();
                    try {
                        if (num.match(/-$/)) {
                            complete = null;
                            part = null
                            unit = '';
                        } else if (num.match('E')) {
                            complete = new Number(num);
                            part = parseFloat(num.match(/-*\d*\.?\d*/)[0]);
                            unit = num.match(/E+(\d*)/g)[0].replace(/E+(\d*)/, '$1');
                        } else {
                            complete = parseFloat(num);
                            part = parseFloat(num);
                            unit = '';
                        }
                    } catch (e) {
                        complete = null;
                        part = null
                        unit = '';
                    }


                    switch (unit) {
                        case '5':
                            unit = '万'
                            break;
                        case '6':
                            unit = '十万'
                            break;
                        case '7':
                            unit = '百万'
                            break;
                        case '8':
                            unit = '千万'
                            break;
                        case '9':
                            unit = '亿'
                            break;
                        default:
                            unit = ''
                            break;
                    }
                    return {
                        unit: unit,
                        complete_number: complete,
                        number: part
                    }
                },
                ycjToHighChart: function (HEXkey, line, hasX) {
                    var xAis_key = HEXkey,
                        line = line,
                        line_x,
                        line_y,

                        point_attr_set = {
                            o: 'open',
                            c: 'close',
                            h: 'high',
                            l: 'low',
                            v: 'vol',
                            r: 'ratio'
                        },
                        line_open,
                        line_close,
                        line_low,
                        line_high,
                        data_x,
                        data_unit,
                        data_origin,
                        point_set = {},
                        has_x = (hasX == false) ? false : true;

                    try {
                        for (var line_key in line) {
                            data_x = this.toDECTimestamp(xAis_key);
                            var line_point = line[line_key],
                                scientific;
                            if (typeof line_point == 'string' || typeof line_point == 'number') {
                                scientific = this.scientific(line[line_key])
                                line_x = xAis_key;
                                line_y = scientific.number;
                                data_unit = scientific.unit;
                                data_origin = scientific.complete_number;

                                point_set[line_key] = {
                                    y: line_y,
                                    data: {
                                        timestamp: data_x,
                                        unit: data_unit,
                                        origin_number: data_origin
                                    }
                                }
                            } else if (typeof line_point === 'object') {
                                point_set[line_key] = {
                                    data: {
                                        timestamp: data_x,
                                    }
                                }
                                for (var LP_key in line_point) {
                                    var point_attr = point_attr_set[LP_key], // 属性名字转换
                                        point_attr_value = parseFloat(line_point[LP_key]); //属性值
                                    if (point_attr) {
                                        if (LP_key == 'r' || LP_key == 'v') {
                                            point_set[line_key]['data'][point_attr] = point_attr_value
                                        } else {
                                            point_set[line_key][point_attr] = point_attr_value
                                        }
                                    } else {
                                        point_set[line_key]['data'][LP_key] = line_point[LP_key];
                                    }

                                }
                            } else {
                                point_set[line_key] = {
                                    y: null,
                                    data: {
                                        timestamp: data_x,
                                        unit: ''
                                    }
                                };
                            }

                            !!has_x && (point_set[line_key].x = data_x)

                        }
                    } catch (e) {
                        throw e
                    }
                    return point_set;
                },
                dataCache: function (xAxis, codes, line_key, point_has_x) {
                    var data_cache = {},
                        status = {}, //状态集合
                        type_set = {}, //类型集合
                        trade_date = [], //交易日期
                        trade_date_cache = {}, //交易日 hash检测对象
                        line_key_set = line_key, //线集合
                        xAxis = xAxis.sort().map(function (v) {
                            return parseInt(v, 16) * 1000
                        }),
                        has_x = (point_has_x == false) ? false : true, //has_x false 的话输出的点会没有x 这个属性
                        hasInit = false;
                    if ((line_key.sort().join('') === ['close', 'date', 'kline', 'ratio', 'vol'].sort().join('') && line_key.indexOf('adjfactor') === -1) || (line_key.sort().join('') === ['c', 'd', 'k', 'r', 'v'].sort().join('') && line_key.indexOf('adjfactor') === -1)) { //暂时兼容未复权前的接口,自动加上adjfactor key
                        line_key.push('adjfactor');
                    }

                    function initLine(code) { //初始化线对象
                        var date;
                        line_key.forEach(function (line) { // 线的缩写
                            data_cache[code][line] = {};
                            type_set[code][line] = 'line';
                            xAxis.forEach(function (axis) { //x轴
                                data_cache[code][line][axis] = null
                                date = new Date(axis).Format('yyyy-MM-dd');


                                if (!trade_date_cache[date]) { //日期
                                    trade_date.push(date)
                                    trade_date_cache[date] = true
                                }

                            })
                        })
                    }

                    (function () {

                        if (!$.isArray(codes) && (typeof codes === 'string' || typeof codes === 'number')) {
                            codes = [codes];
                        } else if (!codes) {
                            codes = []
                        }


                        codes.forEach(function (code) {
                            data_cache[code] = {};
                            status[code] = {
                                hasUpdate: false
                            };
                            type_set[code] = {};

                            initLine(code);
                        });
                        //初始化 缓存对象

                        hasInit = true;
                    }());

                    this.getXAxis = function () {
                        return xAxis.map(function (v) {
                            return v
                        })
                    };
                    this.getTradeDate = function () {
                        return trade_date.sort();
                    };
                    this.getDataCache = function () {
                        return $.extend({}, data_cache) //clone a obj
                    };
                    this.getTptySet = function () {
                        return type_set
                    };
                    this.getHasInit = function () {
                        return hasInit
                    };
                    this.getHasUpdate = function (code) {
                        return status[code].hasUpdate
                    };
                    this.updateSinglePoint = function (code, key, decTimestamp, point) { //

                        if (typeof data_cache[code][key][decTimestamp] != 'undefined') {
                            data_cache[code][key][decTimestamp] = $.extend(data_cache[code][key][decTimestamp], point);
                        } else {
                            Base.tryCatch('线上面没有这个点; 十进制：' + decTimestamp + ' 时间：' + new Date(decTimestamp).Format('yyyy-MM-dd hh:mm:ss'))
                        }

                    };
                    this.updatePoint = function (code, ajax_data) {
                        var data = ajax_data,
                            cache_key = code,
                            point_set = {},
                            point,
                            point_key,
                            index = 0,
                            timestamp; //point.data.timestamp

                        if (typeof data_cache[cache_key] == 'undefined') {
                            Base.tryCatch('没用这个对象集合，请用newPointSet来插入新的对象')
                        }

                        for (timestamp in data) {
                            point_set = Base.dataHandler.ycjToHighChart(timestamp, data[timestamp], has_x);

                            for (point_key in point_set) {
                                if (typeof data_cache[cache_key][point_key] == 'undefined' && point_key !== 'adjfactor') {
                                    Base.tryCatch('对象中没有' + point_key + '这个键')
                                } else {

                                    if (typeof data_cache[cache_key][point_key][Base.dataHandler.toDECTimestamp(timestamp)] != 'undefined') { //缓存没有改定点不更新
                                        data_cache[cache_key][point_key][Base.dataHandler.toDECTimestamp(timestamp)] = point_set[point_key]

                                        if (index == 5 || index == 15 || index == 39 || index == 150) { //遍历点，搜索是否有 linekey 是对象
                                            if (typeof data[timestamp][point_key] == 'object') {

                                                type_set[code][point_key] = 'k'
                                            }
                                        }

                                    } else {
                                        Base.tryCatch('x轴上没有这个点 时间戳' + timestamp)

                                    }

                                }

                            }
                            index++
                        }
                        status[cache_key].hasUpdate = true
                    };
                    this.newPointSet = function (code, ajax_data) {
                        if (!data_cache[code]) {
                            data_cache[code] = {}
                            status[code] = {hasUpdate: false};
                            type_set[code] = {}
                            initLine(code);
                        }
                        this.updatePoint(code, ajax_data);
                    };
                    this.getData = function (code, type) {
                        var data = data_cache[code],
                            result = {};
                        if (!data) {
                            return result
                        }
                        $.each(data, function (k, v) { // 遍历缓存 k是线
                            var line = [];
                            $.each(v, function (v_k, v_v) { //遍历缓存中的线对象
                                var point;
                                if (v_v === null) {
                                    if (type_set[code][k] == 'k') {
                                        point = {
                                            open: null,
                                            close: null,
                                            high: null,
                                            low: null,
                                            data: {
                                                timestamp: parseInt(v_k),
                                                unit: ''
                                            }
                                        };
                                    } else {
                                        point = {
                                            y: v_v,
                                            data: {
                                                timestamp: parseInt(v_k),
                                                unit: ''
                                            }
                                        };
                                    }

                                    !!has_x && (point.x = parseInt(v_k))
                                    line.push(point)

                                } else {
                                    line.push($.extend({}, v_v)) //bug 修复  clone一个对象，不要返回缓存中的对象
                                }

                            });
                            result[k] = line;
                        });
                        return result;
                    }
                }
            };
            Base.timeLimit = function () {
                var time, msg_time = new Date().Format('hh:mm:ss');
                time = (msg_time > '09:29:59' && msg_time < '11:30:01') || (msg_time > '13:00:00' && msg_time < '15:00:01');
                var week = new Date().getDay();
                if ((week != 0 && week != 6) && time) {
                    return true
                } else {
                    return false
                }
            }
        }
    }(jQuery);

//imgLoad
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
+
    function ($) {
        var temp = '<div id="mouseover-showchart"></div>';
        var codeReg = /\d{6}/;
        var $win = $(window);
        var $chart = $();
        function showChart($this, e) {
            var code = $this.attr('data-showchart-code');
            if (!codeReg.test(code)) {
                return;
            }
            code = 'n' + code;
            var url = 'http://image.sinajs.cn/newchart/small/' + code + '.gif';
            Base.imgLoad(url, function (curUrl) {
                if (url === curUrl) {
                    $chart.html('<img src="' + curUrl + '"/>');
                    var left = e.clientX + 10;
                    var top = e.clientY + 10;
                    var boxWidth = $chart.outerWidth();
                    var boxHeight = $chart.outerHeight();
                    if (left > $win.width() - boxWidth) {
                        left = e.clientX - boxWidth - 10;
                    }
                    if (top > $win.height() - boxHeight - 20) {
                        top = e.clientY - boxHeight - 10;
                    }
                    $chart.css('transform', 'translate(' + left + 'px,' + top + 'px)').show();
                }
            });
        }
        $(function() {
            $chart = $(temp).appendTo('#container-div').hide();
            $(document).on('mouseover.showChart', '[data-showchart-code]', function(e) {
                    showChart($(this), e);
                }).on('mouseout.showChart', function() {
                    $chart.hide();
                });
        });
    }(jQuery);