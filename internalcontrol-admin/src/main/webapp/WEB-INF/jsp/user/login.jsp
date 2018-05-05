<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>登录</title>

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
                    <h3 class="panel-title">登录</h3>
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

                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <img id="captchaImage"
                                             src="${pageContext.request.contextPath}/user/generateCaptcha"
                                             height="50px;" align="absmiddle"/>
                                        </br>
                                        <a id="changeCaptcha" href="javascrip:void(0);">换一张 </a>
                                    </div>

                                    <div class="col-sm-6">
                                        <input placeholder="输入验证码" class="form-control" id="captcha" type="text">
                                    </div>
                                </div>
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


<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="modalLabel">注册</h4>
            </div>
            <div class="modal-body">
                <div style="height: 50px;"></div>
                <div class="row">
                    <form class="form-horizontal">

                        <div class="form-group">
                            <label class="col-sm-4 control-label">账号</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="registerAccount"
                                       name="registerAccount"
                                       maxlength="16"
                                       placeholder="账号">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">密码</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="registerPassword"
                                       name="registerPassword"
                                       maxlength="16"
                                       placeholder="密码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">确认密码</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="registerSurePassword"
                                       name="registerSurePassword"
                                       maxlength="16"
                                       placeholder="确认密码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">角色</label>
                            <div class="col-sm-4">
                                <input readonly type="text" class="form-control" id="registerAuthority"
                                       value="投标者" name="registerAuthority"
                                       placeholder="投标者">
                            </div>
                        </div>

                    </form>

                    <div class="col-sm-offset-5 col-sm-2">
                        <button id="sureRegister"
                                class="btn btn-lg btn-success btn-block">注册
                        </button>
                    </div>
                </div>
                <div style="height: 50px;"></div>

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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

        $('#register').click(function () {
            register();
        });

        $('#sureRegister').click(function () {
            sureRegister();
        });

        $('#changeCaptcha').click(function () {
            changeCaptcha();
        });
    });

    login = function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/user/sure-login',
            type: 'POST',
            data: {
                "account": $('#account').val(),
                "password": $('#password').val(),
                "authority": $('[name="authority"]:checked').val(),
                "captcha": $('#captcha').val()
            },
            success: function (data) {
                if (data.errorCode == 0) {
                    if ($('[name="authority"]:checked').val() == 1) {
                        window.location.href = '${pageContext.request.contextPath}/project/project-list';
                    } else {
                        window.location.href = '${pageContext.request.contextPath}/bid/bidding-list';
                    }
                } else if (data.errorCode == 11) {
                    alert('验证码错误');
                    changeCaptcha();
                } else {
                    alert('账号或者密码错误');
                    changeCaptcha();
                }
            },
            error: function (data) {
                alert('网络错误');
            }
        });
    };

    changeCaptcha = function () {
        $("#captchaImage").attr('src', '${pageContext.request.contextPath}/user/generateCaptcha?' + Math.random());
    };

    sureRegister = function () {

        if ($('#registerPassword').val() != $('#registerSurePassword').val()) {
            alert('两次密码不一样');
            return;
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/user/sure-register',
            type: 'POST',
            data: {
                "account": $('#registerAccount').val(),
                "password": $('#registerPassword').val(),
                "authority": 0
            },
            success: function (data) {
                if (data.errorCode == 0) {
                    alert('注册成功');
                    window.location.href = '${pageContext.request.contextPath}/bid/bidding-list';
                } else {
                    alert('注册失败');
                }
            },
            error: function (data) {
                alert('网络错误');
            }
        });
    };

    register = function () {
        $('#myModal').modal('show');
    };

</script>


</body>

</html>
