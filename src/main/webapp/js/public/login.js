/**
 * Created by zheng on 2015/5/11.
 */
//监听 登录按钮
$('#loginBtn').click(function () {
    loginInfos();
});
//监听 密码框回车
$('#password').bind('keypress', function (e) {
    if ((e.which || e.keyCode) == 13) {
        loginInfos();
    }
});

/**
 * 用户登录
 * @param username,password
 */
function loginInfos() {
    $("#loginForm").attr('action', "login").attr('method','post').submit();
}
