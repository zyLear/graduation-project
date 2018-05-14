<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>添加招标公告</title>

    <%@include file="../common/common_head_resource.jsp" %>
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap-fileinput/css/fileinput.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet">
</head>

<body>

<div id="wrapper">

    <%@include file="../common/common_navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">添加招标公告</h1>
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
                            <select readonly id="projectNumber" name="projectNumber" class="form-control">
                                <option value="${project.projectNumber}">${project.projectNumber}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${project.projectName}</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">招标编号</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="biddingNumber" name="biddingNumber"
                                   placeholder="招标编号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">招标名字</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="biddingName" name="biddingName"
                                   placeholder="招标名字">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">开始时间</label>
                        <div class="col-sm-7">
                            <input readonly type="text" class="form-control"
                                   id="biddingStartTime" <%--name="biddingStartTime"--%>
                                   placeholder="开始时间">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">结束时间</label>
                        <div class="col-sm-7">
                            <input readonly type="text" class="form-control"
                                   id="biddingEndTime" <%--name="biddingEndTime"--%>
                                   placeholder="结束时间">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">招标内容</label>
                        <div class="col-sm-7">
                            <textarea cols="30" rows="10" class="form-control custom-textarea"
                                      id="biddingContent" name="biddingContent"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">剩余最大招标金额</label>
                        <div class="col-sm-7">
                            <input readonly value="${project.leaveBiddingPrices}" type="text" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">招标价格</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="prices" name="prices"
                                   placeholder="招标价格">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">招标文件上传</label>
                        <div class="col-sm-7">
                            <input id="file" name="file" data-show-upload="false" type="file" class="file">
                        </div>
                    </div>


                </form>

                <div class="text-center">
                    <button id="save" type="button " class="btn btn-info btn-lg"> 保 存</button>
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
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script>


    $(document).ready(function () {

        <%--$('#projectNumber').initProjects('${pageContext.request.contextPath}/project/get-projects?projectStatus=' + ProjectStatusEnum.bidding);--%>

        $('#save').click(function () {

            if ($('#projectNumber').val() == '-1') {
                alert('项目编号不能为空');
                return;
            }

            if ($('#biddingNumber').val() == '') {
                alert('招标编号不能为空');
                return;
            }

            if ($('#biddingName').val() == '') {
                alert('招标名字不能为空');
                return;
            }

            if ($('#biddingStartTime').val() == '') {
                alert('开始时间不能为空');
                return;
            }

            if ($('#biddingEndTime').val() == '') {
                alert('结束时间不能为空');
                return;
            }

            if ($('#biddingContent').val() == '') {
                alert('招标内容不能为空');
                return;
            }

            if ($('#prices').val() == '') {
                alert('招标价格不能为空');
                return;
            }

            if (isNaN($('#prices').val())) {
                alert('招标价格是数字');
                return;
            }

            if ($('#prices').val() <= 0) {
                alert('招标价格必须大于0');
                return;
            }

            if ($('#file').val() == '') {
                alert('招标文件不能为空');
                return;
            }

            var param = new FormData($('#form')[0]);
            param.append('biddingStartTime', new Date($('#biddingStartTime').val()).getTime());
            param.append('biddingEndTime', new Date($('#biddingEndTime').val()).getTime());

            $.ajax({
                url: '${pageContext.request.contextPath}/bidding/sure-bidding-create',
                type: 'POST',
                data: param,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    if (data.errorCode == 0) {
                        alert('添加成功');
                        window.location.href = '${pageContext.request.contextPath}/bidding/bidding-list';
                    } else if (data.errorCode == 5) {
                        alert('添加失败,招标总额超过预算总额');
                    } else {
                        alert(data.errorMessage);
                    }
                },
                error: function (data) {
                    alert(data.errorMessage);
                }
            });
        });

        $('#biddingStartTime').datetimepicker({
            //language:  'fr',
            language: 'zh-CN',//显示中文
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1
        });

        $('#biddingEndTime').datetimepicker({
            //language:  'fr',
            language: 'zh-CN',//显示中文
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1
        });
    })
</script>


</body>

</html>
