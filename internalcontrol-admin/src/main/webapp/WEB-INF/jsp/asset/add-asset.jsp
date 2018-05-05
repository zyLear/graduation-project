<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>添加资源</title>

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
                <h1 class="page-header">添加资产</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row custom-content">


            <div class="col-lg-offset-1 col-lg-10">

                <form id="form" class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">合同编号</label>
                        <div class="col-sm-7">
                            <select id="contractNumber" name="contractNumber" class="form-control">
                                <option value="none">无</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">资产编号</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="assetNumber" name="assetNumber"
                                   placeholder="资产编号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">资产名称</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="assetType" name="assetType"
                                   placeholder="资产名称">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-7">
                            <textarea cols="30" rows="10" class="form-control custom-textarea"
                                      id="remark" name="remark"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">单价</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="prices" name="prices"
                                   placeholder="单价">
                        </div>
                    </div>

                    <%--<div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">单价</label>--%>
                    <%--<div class="col-sm-7">--%>
                    <%--<input type="text" class="form-control" id="count" name="prices"--%>
                    <%--placeholder="数量">--%>
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
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-fileinput/js/fileinput.min.js"></script>
<script>
    $(document).ready(function () {

        $('#contractNumber').initContracts('${pageContext.request.contextPath}/contract/get-contracts?contractStatus=' + ContractStatusEnum.effective);

        $('#save').click(function () {

            if ($('#contractNumber').val() == 'none') {
                alert('未选择合同编号');
                return;
            }

            if ($('#assetNumber').val() == '') {
                alert('资产编号不能为空');
                return;
            }

            if ($('#assetType').val() == '') {
                alert('资产名称不能为空');
                return;
            }

            if ($('#remark').val() == '') {
                alert('备注不能为空');
                return;
            }

            if ($('#prices').val() == '') {
                alert('单价不能为空');
                return;
            }

            if (isNaN($('#prices').val())) {
                alert('单价必须是数字');
                return;
            }

            $.ajax({
                url: '${pageContext.request.contextPath}/asset/sure-add-asset',
                type: 'POST',
                data: $('#form').serialize(),
//                async: false,
//                cache: false,
//                contentType: false,
//                processData: false,
                success: function (data) {
                    alert(data.errorMessage);
                },
                error: function (data) {
                    alert(data.errorMessage);
                }
            })
        });
    })
</script>


</body>

</html>
