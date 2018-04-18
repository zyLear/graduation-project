<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>预算申请</title>

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

                <form id="form" class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目编号</label>
                        <div class="col-sm-7">
                            <select id="projectNumber" name="projectNumber" class="form-control">
                                <option value="none">未选择</option>
                                <option value="1111">1111</option>
                                <option value="2222">2222</option>
                            </select>
                        </div>
                    </div>


                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">预算面板</h3>
                        </div>
                        <div class="panel-body" id="itemsPanel">

                            <div name="item">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">预算项</label>
                                    <div class="col-sm-4">
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
                                    <div class="col-sm-offset-2 col-sm-7">
                                        <textarea cols="30" rows="5" class="form-control custom-textarea"
                                                  placeholder="预算描述"></textarea>
                                    </div>
                                </div>
                            </div>


                        </div>
                        <%--panel body --%>
                    </div>
                    <%--panel --%>

                    <%--<div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">金额</label>--%>
                    <%--<div class="col-sm-7">--%>
                    <%--<input type="text" class="form-control" id="budgetMoney" name="budgetMoney"--%>
                    <%--placeholder="金额">--%>
                    <%--</div>--%>
                    <%--</div>--%>

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
<script>
    $(document).ready(function () {

        $('#projectNumber').initProjects('${pageContext.request.contextPath}/project/get-projects?projectStatus=' + ProjectStatusEnum.budgeting);

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
//                    dataType: 'json',
//                    headers: {
//                        'Accept': 'application/json',
//                        'Content-Type': 'application/json'
//                    },
                    success: function (data) {
                        alert(data.errorMessage);
                    },
                    error: function (data) {
                        alert('错误');
                    }
                }
            );
        });

    });


    addItem = function () {
        var html = '  <div name="item">' +
            '  <div class="form-group">' +
            ' <label class="col-sm-2 control-label">预算项</label>' +
            ' <div class="col-sm-4">' +
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
            '   <div class="col-sm-offset-2 col-sm-7">' +
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


</body>

</html>
