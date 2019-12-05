var devs;
var myChart;
var myChart2;
var currDevName;
//var timerId;
option = {
    tooltip: {
        formatter: "{a} <br/>{b} : {c}%"
    },
    toolbox: {
        feature: {
            restore: {},
            saveAsImage: {}
        }
    },
    series: [
        {
            name: 'cpu',
            type: 'gauge',
            detail: {formatter: '{value}%'},
            data: [{value: 0, name: 'cpu'}]
        }
    ]
};
option2 = {
    tooltip: {
        formatter: "{a} <br/>{b} : {c}%"
    },
    toolbox: {
        feature: {
            restore: {},
            saveAsImage: {}
        }
    },
    series: [
        {
            name: 'mem',
            type: 'gauge',
            detail: {formatter: '{value}%'},
            data: [{value: 0, name: 'mem'}]
        }
    ]
};

$(document).ready(function () {
    //console.debug("init:::::::dom加载完成，开始获取dev");
    initSiteSlt();
    myChart = echarts.init(document.getElementById('cpuChart'));
    myChart.setOption(option);

    myChart2 = echarts.init(document.getElementById('memChart'));
    myChart2.setOption(option2);
    //timerId = setInterval(function () {}, 4000);
    console.debug(hello);
});

function recordHandler() {
    var httpReq = new XMLHttpRequest();
    httpReq.onreadystatechange =
        function () {
            if (httpReq.readyState == 4 && httpReq.status == 200) {
                var text = httpReq.responseText;
                //console.debug(text);
                if (text == "no") return;
                var recJson = JSON.parse(text);
                option.series[0].data[0].value = recJson.cpu.toFixed(2);
                option2.series[0].data[0].value = recJson.mem.toFixed(2);
                myChart.setOption(option, true);
                myChart2.setOption(option2, true);
            }
        };
    httpReq.open("get", "http://localhost:9090/devices/" + currDevName, true);
    httpReq.send();

}

function startDisplay() {
    //旧事件清除
    clearInterval(timerId);
    //设置当前设备名
    currDevName = document.getElementById("device").value;

    timerId = setInterval(recordHandler, 2000);
}

function initSiteSlt() {
    var httpReq = new XMLHttpRequest();
    httpReq.onreadystatechange =
        function () {
            if (httpReq.readyState == 4 && httpReq.status == 200) {
                var slt = document.getElementById("site");
                //document.getElementById("debug").innerHTML=httpReq.responseText;
                devs = JSON.parse(httpReq.responseText);
                for (var k in devs) {
                    var objOption = document.createElement("OPTION");
                    objOption.text = k;
                    objOption.value = k;
                    slt.add(objOption);
                }
                refreshDevSlt();
            }
        };
    httpReq.open("get", "/data/devices", true);
    httpReq.send();
}

// 下拉列表“场地”被修改时，触发 设置下拉列表“设备”的选项
function refreshDevSlt() {
    var siteSlt = document.getElementById("site");
    //获取列表当前选中值的索引
    var index = siteSlt.selectedIndex;
    //if(index==0) return;
    var siteVal = siteSlt.options[index].text;
    var devSlt = document.getElementById("device");
    //清空devSlt值
    devSlt.length = 0;
    //全局变量 devs
    var arrDev = devs[siteVal];
    for (var i = 0; i < arrDev.length; i++) {
        devSlt[i] = new Option(arrDev[i], arrDev[i]);
    }
}