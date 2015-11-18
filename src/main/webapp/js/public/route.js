//类别更换
$('.process').click(function (self) {
    var cls = $(this).attr('class').replace("process", "").trim();
    window.location.href = '/index/process/' + cls + '/all'
});

//类别更换
$('.entity').click(function (self) {
    var cls = $(this).attr('class').replace("entity", "").trim();
    window.location.href = '/index/entity/' + cls + '/all'
});
//点击链接
$('.hyperlink').click(function (self) {
    var cls = $(this).attr('class').replace("hyperlink", "").trim();
    window.location.href = '/index/' + cls;
});