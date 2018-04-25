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

    <title>项目预算</title>

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
                <h1 class="page-header">项目预算</h1>
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
                                    <input type="text" class="form-control" id="projectNumber" name="projectNumber"
                                         value="${project.projectNumber}"   placeholder="项目编号">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目名字</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="projectName" name="projectName"
                                           value="${projectName.projectNumber}"   placeholder="项目名字">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">申请人</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control"
                                           value="${project.applicant}" id="applicant" name="applicant" placeholder="申请人">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">申请部门</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="applicationDepartment"
                                           value="${project.applicationDepartment}"  name="applicationDepartment" placeholder="申请部门">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目描述</label>
                                <div class="col-sm-7">
                            <textarea cols="60" rows="20" class="form-control custom-textarea"
                                      id="projectContent" name="projectContent"> ${project.projectContent}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目预算</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="projectBudget" name="projectBudget"
                                           value="${project.projectBudget}"  placeholder="项目预算">
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
                                <input type="text" class="form-control" id="filePath" name="filePath"
                                       value="${project.filePath}"  placeholder="申请书">
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
                            <h3 class="panel-title">预算内容</h3>
                        </div>
                        <div class="panel-body" id="itemsPanel">
                            <c:forEach items="${project.items}" var="item">

                                <div name="item" class="form-group">
                                    <label class="col-sm-2 control-label">预算项</label>
                                    <div class="col-sm-7">
                                    <textarea readonly cols="60" rows="3" class="form-control custom-textarea"
                                              placeholder="预算描述"> ${item.itemContent}</textarea>
                                    </div>
                                    <div class="col-sm-2">
                                        <input readonly value="${item.itemMoney}" type="text" class="form-control"
                                               placeholder="金额">

                                    </div>
                                </div>

                            </c:forEach>
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


            $('#save').click(function () {

                var items = new Array();

                $('[name="item"]').each(function () {
                    var object = new Object();
                    object.itemMoney = $(this).find('input').val();
                    object.itemContent = $(this).find('textarea').val();
                    items.push(object);
                });
//                var object = new Object();
//                object.itemMoney = 100;
//                object.itemContent = 'content';
//
//                items.push(object);
//                items.push(object);
//                items.push(object);

                var param = new FormData($('#form')[0]);
                param.append('items', JSON.stringify(items));


                $.ajax({
                        url: '${pageContext.request.contextPath}/contract/sure-contract-create',
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
        });


        sureFinish = function (id) {
//            var html = '  <div name="item" class="form-group">' +
//                '<label class="col-sm-2 control-label">合同项</label>' +
//                '<div class="col-sm-7">' +
//                '<textarea cols="60" rows="3" class="form-control custom-textarea"></textarea>' +
//                '</div>' +
//                '<div class="col-sm-2">' +
//                '<input readonly type="text" class="form-control" placeholder="金额">' +
//                '<button type="button" class="btn btn-info custom-button" onclick="deleteItem(this)"> 删除项</button>' +
//                '</div>' +
//                '</div>';
//            $('#itemsPanel').append(html);

            if (confirm('确定完成此合同项吗？')) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/contract/sure-finish-item',
                    type: 'POST',
                    data: {
                        "itemId": id
                    },
                    success: function (data) {
                        alert(data.errorMessage);
                        window.location.reload();
                    },
                    error: function (data) {
                        alert('网络错误');
                    }
                });
            }
        };

        //        deleteItem = function ($this) {
        //            $($this).parent().parent().remove();
        //        };


    </script>

</div>
<!-- /#wrapper -->


</body>

</html>
