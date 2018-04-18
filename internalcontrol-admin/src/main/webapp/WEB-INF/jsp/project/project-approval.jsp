<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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


            <div class="col-lg-offset-1 col-lg-10">

                <form id="form" class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目编号</label>
                        <div class="col-sm-7">
                            <select id="projectNumber" name="projectNumber" class="form-control">
                                <option value="none">未选择</option>
                                <option value="internal_group">1111</option>
                                <option value="outside_group">2222</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">审批结果</label>
                        <div class="col-sm-7">
                            <select id="projectStatus" name="projectStatus" class="form-control">
                                <option value="-1">未选择</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">审批评论</label>
                        <div class="col-sm-7">
                            <textarea cols="30" rows="7" class="form-control custom-textarea"
                                      id="approvalComment" name="approvalComment"></textarea>
                        </div>
                    </div>


                </form>

                <div class="text-center">
                    <button id="save" type="button" class="btn btn-info btn-lg"> 保 存</button>
                </div>

                <%--</div>--%>
                <%--&lt;%&ndash;pannel-body&ndash;%&gt;--%>
                <%--</div>--%>
                <%--&lt;%&ndash;pannel&ndash;%&gt;--%>
                <%--</div>--%>
                <%--&lt;%&ndash; col-lg-6 &ndash;%&gt;--%>


            </div>
            <%-- col-lg-6 --%>


        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


</div>
<!-- /#wrapper -->

<%@include file="../common/common_bottom_resource.jsp" %>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-fileinput/js/fileinput.min.js"></script>
<script>
    $(document).ready(function () {

        initApprovalResult();

        $('#projectNumber').initProjects('${pageContext.request.contextPath}/project/get-projects?projectStatus=' + ProjectStatusEnum.in_approval);

        $('#save').click(function () {
            $.ajax({
                    url: '${pageContext.request.contextPath}/project/sure-project-approval',
                    type: 'POST',
                    data: {
                        "projectNumber": $('#projectNumber').val(),
                        "projectStatus": $('#projectStatus').val(),
                        "approvalComment": $('#approvalComment').val()
                    },
//                    async: false,
//                    cache: false,
//                    contentType: false,
//                    processData: false,
                    success: function (data) {
                        alert(data.errorMessage);
                    },
                    error: function (data) {
                        alert('错误');
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


</body>

</html>
