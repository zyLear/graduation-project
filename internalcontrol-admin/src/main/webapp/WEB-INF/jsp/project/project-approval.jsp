<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>项目审批</title>

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
                <h1 class="page-header">项目审批</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row custom-content">

            <form id="form">
                <%-- class="form-horizontal"--%>
                <div class="col-lg-6 form-horizontal">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">项目信息</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目编号</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control" id="projectNumber"
                                           name="projectNumber"
                                           value="${project.projectNumber}" placeholder="项目编号">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目名字</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control" id="projectName" name="projectName"
                                           value="${project.projectNumber}" placeholder="项目名字">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">申请人</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control"
                                           value="${project.applicant}" id="applicant" name="applicant"
                                           placeholder="申请人">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">申请部门</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control" id="applicationDepartment"
                                           value="${project.applicationDepartment}" name="applicationDepartment"
                                           placeholder="申请部门">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目描述</label>
                                <div class="col-sm-7">
                            <textarea readonly cols="30" rows="10" class="form-control custom-textarea"
                                      id="projectContent" name="projectContent"> ${project.projectContent}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目预算</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control" id="projectBudget"
                                           name="projectBudget"
                                           value="${project.projectBudget}" placeholder="项目预算">
                                </div>
                            </div>

                            <%--<div class="form-group">--%>
                            <%--<label class="col-sm-2 control-label">项目预算</label>--%>
                            <%--<div class="col-sm-7">--%>
                            <%--<input readonly type="text" class="form-control" name="a" placeholder="a">--%>
                            <%--</div>--%>
                            <%--</div>--%>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">申请表</label>
                                <div class="col-sm-7 custom-link">
                                    <a href="${pageContext.request.contextPath}/downloader/download?filePath=${project.filePath}">${project.fileName}</a>
                                </div>
                            </div>

                        </div>
                        <%--panel body --%>
                    </div>
                    <%--panel --%>
                </div>


                <div class="col-lg-6 form-horizontal">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">审批</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">审批结果</label>
                                <div class="col-sm-9">
                                    <select id="projectStatus" name="projectStatus" class="form-control">
                                        <option value="-1">未选择</option>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">审批评论</label>
                                <div class="col-sm-9">
                            <textarea cols="30" rows="7" class="form-control custom-textarea"
                                      id="approvalComment" name="approvalComment"></textarea>
                                </div>
                            </div>

                            <div class="text-center">
                                <button id="save" type="button" class="btn btn-info btn-lg custom-button-inline"> 保 存
                                </button>
                                <button onclick="back()" type="button" class="btn btn-info btn-lg custom-button-inline">
                                    返回
                                </button>
                            </div>

                        </div>
                        <%--panel body --%>
                    </div>
                    <%--panel --%>
                </div>
                <%--<div class="col-lg-6 form-horizontal">--%>

            </form>
            <%--<div style="text-align: center">--%>
            <%--<button id="save" type="button" class="btn btn-info btn-lg"> 保 存</button>--%>
            <%--</div>--%>

        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


    <%@include file="../common/common_bottom_resource.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-fileinput/js/fileinput.min.js"></script>
    <script>

        $(document).ready(function () {

            initApprovalResult();

            $('#save').click(function () {
                $.ajax({
                        url: '${pageContext.request.contextPath}/project/sure-project-approval',
                        type: 'POST',
                        data: {
                            "projectNumber": $('#projectNumber').val(),
                            "projectStatus": $('#projectStatus').val(),
                            "approvalComment": $('#approvalComment').val()
                        },
                        success: function (data) {
                            if (data.errorCode == 0) {
                                alert('审批成功');
                                window.location.href = '${pageContext.request.contextPath}/project/project-list';
                            } else {
                                alert(data.errorMessage);
                            }
                        },
                        error: function (data) {
                            alert('网络错误');
                        }

                    }
                )
            });
        });


        function initApprovalResult() {
            var html = '<option value="-1">未选择</option>' +
                '<option value="' + ProjectStatusEnum.budgeting + '">同意立项</option>' +
                '<option value="' + ProjectStatusEnum.pending + '">待定</option>' +
                '<option value="' + ProjectStatusEnum.cancel + '">不同意立项</option>';
            $('#projectStatus').html(html);
        }

    </script>

</div>
<!-- /#wrapper -->


</body>

</html>
