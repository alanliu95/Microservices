var inited = 0;
var myChart;
var devs;

function lineChart(recJson) {
    // 指定图表的配置项和数据
    var option = {
        xAxis: {
            type: 'category',
            data: recJson["ts"],
        },
        yAxis: {
            type: 'value',
            max: 100,
            min: 0
        },
        legend: {
            data: ['cpu利用率', 'mem利用率']
        },
        series: [{
            name: 'cpu利用率',
            data: recJson["cpu"],
            type: 'line'
        }, {
            name: 'mem利用率',
            data: recJson["mem"],
            type: 'line'
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function getRecords() {
    var id = document.getElementById("device").value;
    var httpReq = new XMLHttpRequest();
    httpReq.onreadystatechange =
        function () {
            if (httpReq.readyState == 4 && httpReq.status == 200) {
                var text = httpReq.responseText;
                var recsJson = JSON.parse(text);
                lineChart(recsJson);
            }
        };
    httpReq.open("get", "/data/records?currDevName=" + id, true);
    httpReq.send();
}

$(document).ready(function () {
    //console.debug("init:::::::dom加载完成，开始获取dev");
    initSiteSlt();
    myChart = echarts.init(document.getElementById('main'));
});

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