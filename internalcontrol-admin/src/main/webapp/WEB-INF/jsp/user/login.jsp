<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <%@include file="../common/common_head_resource.jsp" %>

</head>

<body>

<div class="container">

    <div class="row">
        <div style="text-align: center; margin-top: 100px;">
            <h1>
                内部控制系统
            </h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">请登录</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="输入账号" id="account" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="输入密码" id="password" type="password"
                                       value="">
                            </div>
                            <div class="custom-radio-group">
                                <input type="radio" name="authority" value="1" checked="checked">
                                <label>管理员&nbsp;</label>
                                <input type="radio" name="authority" value="0"> <label>投标者&nbsp;</label>
                            </div>
                            <%--<div class="checkbox">--%>
                            <%--<label>--%>
                            <%--<input name="remember" type="checkbox" value="Remember Me">Remember Me--%>
                            <%--</label>--%>
                            <%--</div>--%>
                            <!-- Change this to a button or input when using this as a form -->
                            <a href="javascrip:void(0);" id="login" class="btn btn-lg btn-success btn-block">登录</a>
                            <a href="javascrip:void(0);" id="register" class="btn btn-lg btn-success btn-block">注册</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../common/common_bottom_resource.jsp" %>
<script>

    $(document).ready(function () {
        $('#login').click(function () {
            login();
        });
    });

    login = function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/user/sure-login',
            type: 'POST',
            data: {
                "account": $('#account').val(),
                "password": $('#password').val(),
                "authority": $('[name="authority"]:checked').val()
            },
            success: function (data) {
                if (data.errorCode == 0) {
                    if ($('[name="authority"]:checked').val()==1) {
                        window.location.href = '${pageContext.request.contextPath}/project/project-list';
                    }else {
                        window.location.href = '${pageContext.request.contextPath}/bid/bidding-list';
                    }

                } else {
                    alert('账号或者密码错误');
                }
            },
            error: function (data) {
                alert('网络错误');
            }
        });
    };

</script>


</body>

</html>
