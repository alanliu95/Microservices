function displayForm() {
    var div = document.getElementById("form-container");
    div.style.display = "block";

}

function postNewDev() {
    // document.getElementById("devForm").submit();
    var siteSlt = document.getElementById("siteId");
    //获取列表当前选中值的索引
    var index = siteSlt.selectedIndex;
    var siteVal = siteSlt.options[index].value;
    $.post(
        '/deviceManagement/addDevice', {
            siteId: siteVal,
            name: $('#name').val(),
            token: $('#token').val(),
            info: $('#info').val()
        },
        function (data, textStatus) {

            refreshDevList();
        });

}

function refreshDevList() {
    var siteSlt = document.getElementById("site");
    //获取列表当前选中值的索引
    var index = siteSlt.selectedIndex;
    var siteVal = siteSlt.options[index].value;
    devManageUrlSuffix = '?siteId=' + siteVal;
    //console.debug('/deviceManagement' + devManageUrlSuffix);
    $('#home-container').load('/deviceManagement' + devManageUrlSuffix);
}

