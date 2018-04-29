<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>创建账号</title>

    <%@include file="../common/common_head_resource.jsp" %>
</head>

<body>

<div id="wrapper">

    <%@include file="../common/common_navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">创建账号</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row custom-content">


            <div class="col-lg-offset-1 col-lg-10">

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
                                <select id="registerAuthority" name="registerAuthority" class="form-control">
                                    <option value="1">管理员</option>
                                    <option value="0">投标者</option>
                                </select>
                            </div>
                        </div>

                    </form>

                    <div class="col-sm-offset-5 col-sm-2">
                        <button id="sureRegister"
                                class="btn btn-lg btn-success btn-block">注册
                        </button>
                    </div>
                </div>


            </div>
            <%-- col-lg-6 --%>


        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


</div>
<!-- /#wrapper -->

<%@include file="../common/common_bottom_resource.jsp" %>
<script>

    $(document).ready(function () {
        $('#sureRegister').click(function () {
            sureRegister();
        });
    });



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
                "authority": $('#registerAuthority').val()
            },
            success: function (data) {
                if (data.errorCode == 0) {
                    alert('注册成功');
                    <%--window.location.href = '${pageContext.request.contextPath}/bid/bidding-list';--%>
                } else {
                    alert('注册失败');
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
