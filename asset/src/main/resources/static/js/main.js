var hello = "hello from main.js"
var timerId;
timerId = setInterval(function () {
}, 4000);

function currentPage() {
    clearInterval(timerId);
    // console.debug("currentPage");
    $('#home-container').empty();
    $('#home-container').load('/current');
}

function historyPage() {
    clearInterval(timerId);
    // console.debug("historyPage");
    $('#home-container').empty();
    $('#home-container').load('/history');
}

var devManageUrlSuffix = '';

function deviceManagement() {
    clearInterval(timerId);
    // console.debug("currentPage");
    $('#home-container').empty();
    //目标div区域用新文本填充
    $('#home-container').load('/deviceManagement' + devManageUrlSuffix);
}

function deviceStatus() {
    clearInterval(timerId);
    // console.debug("historyPage");
    $('#home-container').empty();
    $('#home-container').load('/deviceStatus');
}