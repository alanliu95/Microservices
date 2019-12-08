var md;


$(document).ready(function () {

    var device_type = navigator.userAgent;//获取userAgent信息
    console.log('device_type is ' + device_type);
    md = new MobileDetect(device_type);//实例化mobile-detect

    // console.log( md.mobile() );          // 'Sony'
    // console.log( md.phone() );           // 'Sony'
    // console.log( md.tablet() );          // null
    // console.log( md.userAgent() );       // 'Safari'
    console.log(md.os());              // 'AndroidOS'
    console.log(md.is('iPhone'));      // false
    // console.log( md.is('bot') );         // false
    // console.log( md.version('Webkit') );         // 534.3
    // console.log( md.versionStr('Build') );       // '4.1.A.0.562'
    // console.log( md.match('playstation|xbox') ); // false

    var d = {};
    d.device_type = device_type;
    d.isIphone = md.is('iPhone');
    $.ajax({
        //接口地址
        url: "/surebet/check_device/",
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(d),
        async: false,
        cache: false,
        processData: false,
        success: function (response) {
            console.log('success');
        },
        error: function (returndata) {
            //请求异常的回调
            // modals.warn("网络访问失败，请稍后重试!");
        }
    });


    $('#main').empty();
    $('#main').load('dd_basketball');


    $('#test_btn1').on('click', function () {
        console.log('run on.....');
        $('#test_div').empty();
        $('#test_div').load('dd_soccer');
    });

    $('.nav-link').on('click', function () {
        $(".nav").find(".active").removeClass("active");
        $(this).addClass("active");
    });

});


function go_dd_soccer() {
    $('#main').empty();
    $('#main').load('dd_soccer');
}

function go_dd_basketball() {
    $('#main').empty();
    $('#main').load('dd_basketball');
}

function go_hedge() {
    $('#main').empty();
    $('#main').load('hedge');
}

function go_crawler() {
    $('#main').empty();
    $('#main').load('crawler');
}


function check_num_for_list(list) {
    for (var i = 0; i < list.length; i++) {
        if (!isNumber(list[i])) {
            console.log("check ..  " + list[i]);
            return false;
        }
    }
    return true;
}


// 判断是否为数字
function isNumber(val) {

    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if (regPos.test(val) || regNeg.test(val)) {
        return true;
    } else {
        return false;
    }

}