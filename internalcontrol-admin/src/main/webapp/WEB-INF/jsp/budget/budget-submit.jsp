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
                <h1 class="page-header">预算申请</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row custom-content">


            <div class="col-lg-offset-1 col-lg-10">

                <div class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目编号</label>
                        <div class="col-sm-7">
                            <select id="project-number" name="type" class="form-control">
                                <option value="none">未选择</option>
                                <option value="internal_group">1111</option>
                                <option value="outside_group">2222</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">预算模块</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="budget-aspect" placeholder="预算模块">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">预算描述</label>
                        <div class="col-sm-7">
                            <textarea cols="30" rows="10" class="form-control custom-textarea"
                                      id="budget-content"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">金额</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="budget-money"
                                   placeholder="金额">
                        </div>
                    </div>

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


    <%--<div class="modal fade bs-example-modal-lg" id="add-budget-modal" tabindex="-1" role="dialog"--%>
    <%--aria-labelledby="myModalLabel">--%>
    <%--<div class="modal-dialog modal-lg" role="document">--%>
    <%--<div class="modal-content">--%>
    <%--<div class="modal-header">--%>
    <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
    <%--<span aria-hidden="true">&times;</span>--%>
    <%--</button>--%>
    <%--<h4 class="modal-title" id="modalLabel">添加预算</h4>--%>
    <%--</div>--%>
    <%--<div class="modal-body">--%>

    <%--<div class="form-horizontal">--%>
    <%--<div class="form-group">--%>
    <%--<label class="col-sm-2 control-label">预算模块</label>--%>
    <%--<div class="col-sm-7">--%>
    <%--<input type="text" class="form-control" id="budget-aspect" placeholder="预算模块">--%>
    <%--</div>--%>
    <%--</div>--%>


    <%--<div class="form-group">--%>
    <%--<label class="col-sm-2 control-label">预算描述</label>--%>
    <%--<div class="col-sm-7">--%>
    <%--<textarea cols="30" rows="10" class="form-control custom-textarea"--%>
    <%--id="budget-content"></textarea>--%>
    <%--</div>--%>
    <%--</div>--%>

    <%--<div class="form-group">--%>
    <%--<label class="col-sm-2 control-label">金额</label>--%>
    <%--<div class="col-sm-7">--%>
    <%--<input type="text" class="form-control" id="budget-money"--%>
    <%--placeholder="金额">--%>
    <%--</div>--%>
    <%--</div>--%>

    <%--</div>--%>
    <%--<div class="modal-footer">--%>
    <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>

    <%--<button type="button" id="sure-assign" class="btn btn-primary">确定</button>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
</div>
<!-- /#wrapper -->

<%@include file="../common/common_bottom_resource.jsp" %>
<script>
    $(document).ready(function () {


    });


</script>


</body>

</html>
