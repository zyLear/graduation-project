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
                                <label class="col-sm-2 control-label">申请表上传</label>
                                <div class="col-sm-7">
                                    <input readonly type="text" class="form-control" id="filePath" name="filePath"
                                           value="${project.filePath}" placeholder="申请书">
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
                            <h3 class="panel-title">预算面板</h3>
                        </div>
                        <div class="panel-body" id="itemsPanel">

                            <div name="item">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label"
                                           style="padding-left:0; padding-right: 0">预算项</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="budgetAspect" name="budgetAspect"
                                               placeholder="预算模块">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="budgetMoney" name="budgetMoney"
                                               placeholder="金额">
                                    </div>
                                    <div class="col-sm-1">
                                        <button type="button" class="btn btn-info " onclick="addItem()"> 增加新项</button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-1 col-sm-10">
                                        <textarea cols="30" rows="5" class="form-control custom-textarea"
                                                  placeholder="预算描述"></textarea>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <%--panel body --%>
                    </div>
                    <%--panel --%>

                    <div class="text-center">
                        <button id="save" type="button" class="btn btn-info btn-lg"> 保 存</button>
                    </div>

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
            $('#save').click(function () {

                var params = new Object();
                var items = new Array();
                params.projectNumber = $('#projectNumber').val();
                $('[name="item"]').each(function () {
                    var object = new Object();
                    object.budgetAspect = $(this).find('[name="budgetAspect"]').val();
                    object.budgetMoney = $(this).find('[name="budgetMoney"]').val();
                    object.budgetContent = $(this).find('textarea').val();
                    items.push(object);
                });
                params.items = JSON.stringify(items);

                $.ajax({
                        url: '${pageContext.request.contextPath}/budget/sure-budget-application',
                        type: 'POST',
                        data: params,
//                    },
                        success: function (data) {
                            alert(data.errorMessage);
                        },
                        error: function (data) {
                            alert('网络错误');
                        }
                    }
                );
            });

        });


        addItem = function () {
            var html = '  <div name="item">' +
                '  <div class="form-group">' +
                ' <label class="col-sm-1 control-label"' +
                ' style="padding-left:0; padding-right: 0">预算项</label>' +
                ' <div class="col-sm-5">' +
                '<input type="text" class="form-control" name="budgetAspect"' +
                ' placeholder="预算模块">' +
                '   </div>' +
                '   <div class="col-sm-3">' +
                '  <input type="text" class="form-control" name="budgetMoney"' +
                '  placeholder="金额">' +
                '    </div>' +
                '    <div class="col-sm-1">' +
                '   <button type="button" class="btn btn-info " onclick="deleteItem(this)"> 删除项</button>' +
                '   </div>' +
                '   </div>' +
                '    <div class="form-group">' +
                '   <div class="col-sm-offset-1 col-sm-10">' +
                '   <textarea cols="30" rows="5" class="form-control custom-textarea"' +
                ' placeholder="预算描述"></textarea>' +
                '  </div>' +
                '  </div>' +
                '  </div>';
            $('#itemsPanel').append(html);
        };

        deleteItem = function ($this) {
            $($this).parent().parent().parent().remove();
        };

    </script>

</div>
<!-- /#wrapper -->


</body>

</html>
