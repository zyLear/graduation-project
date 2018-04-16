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
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap-fileinput/css/fileinput.min.css"
          rel="stylesheet">

</head>

<body>

<div id="wrapper">

    <%@include file="../common/common_navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">立项申请</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row custom-content">
            <div class="col-lg-offset-1 col-lg-10">

                <form id="form" class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目编号</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="projectNumber" name="projectNumber"
                                   placeholder="项目编号">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目名字</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="projectName" name="projectName"
                                   placeholder="项目名字">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">申请人</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="applicant" name="applicant" placeholder="申请人">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">申请部门</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="applicationDepartment"
                                   name="applicationDepartment" placeholder="申请部门">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目描述</label>
                        <div class="col-sm-7">
                            <textarea cols="60" rows="20" class="form-control custom-textarea"
                                      id="projectContent" name="projectContent"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目预算</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="projectBudget" name="projectBudget"
                                   placeholder="项目预算">
                        </div>
                    </div>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">项目预算</label>--%>
                        <%--<div class="col-sm-7">--%>
                            <%--<input type="text" class="form-control" name="a" placeholder="a">--%>
                        <%--</div>--%>
                    <%--</div>--%>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">申请表上传</label>
                        <div class="col-sm-7">
                            <input id="file" name="file" data-show-upload="false" type="file" class="file">
                        </div>
                    </div>

                </form>
                <div style="text-align: center">
                    <button id="save" type="button" class="btn btn-info btn-lg"> 保 存</button>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


    <%@include file="../common/common_bottom_resource.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-fileinput/js/fileinput.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#save').click(function () {

//                var test = new Array();
//                var object = new Object();
//
//                object.itemMoney = 100;
//                object.itemContent = 'content';
//                test.push(object);
//                test.push(object);
//                test.push(object);
//
                var param = new FormData($('#form')[0]);
//                param.append('items', JSON.stringify(test));
                $.ajax({
                        url: '${pageContext.request.contextPath}/project/sure-project-application',
                        type: 'POST',
                        data: param,
                        async: false,
                        cache: false,
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            alert(data.errorMessage);
                        },
                        error: function (data) {
                            alert(data.errorMessage);
                        }
                    }
                )
            });
        })
    </script>

</div>
<!-- /#wrapper -->


</body>

</html>
