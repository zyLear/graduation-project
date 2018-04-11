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
                            <select id="projectNumber" name="projectNumber" class="form-control">
                                <option value="none">未选择</option>
                                <option value="internal_group">1111</option>
                                <option value="outside_group">2222</option>
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
                        <label class="col-sm-2 control-label">招标内容</label>
                        <div class="col-sm-7">
                            <textarea cols="30" rows="10" class="form-control custom-textarea"
                                      id="biddingContent" name="biddingContent"></textarea>
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
<script src="${pageContext.request.contextPath}/resources/dist/js/common-custom.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-fileinput/js/fileinput.min.js"></script>
<script>
    $(document).ready(function () {
        $('#save').click(function () {
            $.ajax({
                    url: '${pageContext.request.contextPath}/bidding/sure-bidding-create',
                    type: 'POST',
                    data: new FormData($('#form')[0]),
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
    })
</script>


</body>

</html>
