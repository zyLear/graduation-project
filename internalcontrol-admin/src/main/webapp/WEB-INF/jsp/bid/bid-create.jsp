<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>创建标书</title>

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
                <h1 class="page-header">创建标书</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row custom-content">


            <div class="col-lg-offset-1 col-lg-10">

                <form id="form" class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">招标项目</label>
                        <div class="col-sm-7">
                            <select disabled="disabled" id="projectNumber" name="projectNumber" class="form-control">
                                <option value="${bidding.projectNumber}">${bidding.projectNumber}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${bidding.projectName}
                                </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">招标编号</label>
                        <div class="col-sm-7">
                            <select disabled="disabled" id="biddingNumber" name="biddingNumber" class="form-control">
                                <option value="${bidding.biddingNumber}">${bidding.biddingNumber}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${bidding.biddingName}
                                </option>
                            </select>
                        </div>
                    </div>

                    <%--<div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">标书编号</label>--%>
                    <%--<div class="col-sm-7">--%>
                    <%--<input type="text" class="form-control" id="bidNumber" name="bidNumber"--%>
                    <%--placeholder="标书编号">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">投标公司</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="bidCompany" name="bidCompany"
                                   placeholder="投标公司">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">标书内容</label>
                        <div class="col-sm-7">
                            <textarea cols="30" rows="10" class="form-control custom-textarea"
                                      id="bidContent" name="bidContent"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">招标价格</label>
                        <div class="col-sm-7">
                            <input readonly  type="text" class="form-control"
                                value="${bidding.prices}"   placeholder="招标价格">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">投标价格</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="bidPrices" name="bidPrices"
                                   placeholder="投标价格">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">标书文件上传</label>
                        <div class="col-sm-7">
                            <input id="file" name="file" data-show-upload="false" type="file" class="file">
                        </div>
                    </div>


                </form>

                <div class="text-center">
                    <button id="save" type="button" class="btn btn-info btn-lg">确定投标</button>
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
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-fileinput/js/fileinput.min.js"></script>
<script>

    <%--$('#projectNumber').initProjects('${pageContext.request.contextPath}/project/get-projects?projectStatus=' + ProjectStatusEnum.bidding);--%>

    $(document).ready(function () {
        $('#save').click(function () {


            if ($('#bidCompany').val() == '') {
                alert('投标公司不能为空');
                return;
            }

            if ($('#bidContent').val() == '') {
                alert('标书内容不能为空');
                return;
            }

            if ($('#bidPrices').val() == '') {
                alert('投标价格不能为空');
                return;
            }

            if (isNaN($('#bidPrices').val())) {
                alert('投标价格必须是数字');
                return;
            }

            if ($('#bidPrices').val() <= 0) {
                alert('投标价格必须大于0');
                return;
            }


            if ($('#file').val() == '') {
                alert('标书文件不能为空');
                return;
            }



            var param = new FormData($('#form')[0]);
            param.append('biddingNumber', '${bidding.biddingNumber}');
            $.ajax({
                    url: '${pageContext.request.contextPath}/bid/sure-bid-create',
                    type: 'POST',
                    data: param,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        if (data.errorCode==0) {
                            alert('投标成功');
                            window.location.href = '${pageContext.request.contextPath}/bid/bidding-list';
                        }else {
                            alert(data.errorMessage);
                        }
                    },
                    error: function (data) {
                        alert(data.errorMessage);
                    }
                }
            )
        });
    })
</script>


</body>

</html>
