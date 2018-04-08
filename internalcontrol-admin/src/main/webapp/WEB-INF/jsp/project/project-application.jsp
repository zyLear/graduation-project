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
            <div class="col-lg-offset-2 col-lg-6">

                <div class="form-horizontal">


                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目编号</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="project-number" placeholder="项目编号">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目名字</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="project-name" placeholder="项目名字">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">申请人</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="applicant" placeholder="申请人">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">申请部门</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="application-department" placeholder="申请部门">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目描述</label>
                        <div class="col-sm-7">
                            <textarea cols="60" rows="20" class="form-control custom-textarea"
                                      id="project-content"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目预算</label>
                        <div class="col-sm-7">
                            <input disabled type="text" class="form-control" id="project-budget" placeholder="项目预算">
                        </div>
                    </div>

                </div>
                <div style="text-align: center">
                    <button id="save" type="button" class="btn btn-info btn-lg"> 保存</button>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


    <%@include file="../common/common_bottom_resource.jsp" %>

    <script>
        $(document).ready(function () {
            $('#save').click(function () {
                $('#project-budget').removeAttr('disabled');
                $('#project-name').attr('disabled', "disabled");
            });
        })
    </script>

</div>
<!-- /#wrapper -->


</body>

</html>
