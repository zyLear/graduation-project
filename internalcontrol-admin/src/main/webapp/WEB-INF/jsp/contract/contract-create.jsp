<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>创建合同</title>

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
                <h1 class="page-header">创建合同</h1>
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
                        <label class="col-sm-2 control-label">合同编号</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="contractNumber" name="contractNumber"
                                   placeholder="合同编号">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">je</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="projectName" name="projectName"
                                   placeholder="合同名字">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">申请人</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="applicant" name="applicant" placeholder="申请人">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">申请部门</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="applicationDepartment"
                                   name="applicationDepartment" placeholder="申请部门">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">合同内容</label>
                        <div class="col-sm-7">
                            <textarea cols="60" rows="20" class="form-control custom-textarea"
                                      id="projectContent" name="projectContent"></textarea>
                        </div>
                    </div>


                    <div id="itemsPanel">
                        <div name="item" class="form-group">
                            <label class="col-sm-2 control-label">合同项</label>
                            <div class="col-sm-7">
                                <textarea cols="60" rows="3" class="form-control custom-textarea"></textarea>
                            </div>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" placeholder="金额">
                            </div>
                            <div class="col-sm-1">
                                <button type="button" class="btn btn-info " onclick="addItem()"> 增加新项</button>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">总金额</label>
                        <div class="col-sm-7">
                            <input disabled type="text" class="form-control" id="contractMoney" name="contractMoney"
                                   placeholder="总金额">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">申请表上传</label>
                        <div class="col-sm-7">
                            <input id="file" name="file" data-show-upload="false" type="file" class="file">
                        </div>
                    </div>

                </form>
                <div style="text-align: center">
                    <button id="save" type="button" class="btn btn-info btn-lg"> 保 存</button>
                </div>
            </div>
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

                $('#item').each(function () {
                    var object = new Object();
                    object.itemMoney = $(this).find('.input').val();
                    object.itemContent = $(this).find('.textarea').val();
                });
                var object = new Object();
                object.itemMoney = 100;
                object.itemContent = 'content';

                items.push(object);
                items.push(object);
                items.push(object);

                var param = new FormData($('#form')[0]);
                param.append('items', JSON.stringify(items));


                $.ajax({
                        url: '${pageContext.request.contextPath}/project/sure-contract-create',
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


        addItem = function () {
            var html = '  <div name="item" class="form-group">' +
                '<label class="col-sm-2 control-label">合同项</label>' +
                '<div class="col-sm-7">' +
                '<textarea cols="60" rows="3" class="form-control custom-textarea"></textarea>' +
                '</div>' +
                '<div class="col-sm-2">' +
                '<input type="text" class="form-control" placeholder="金额">' +
                '</div>' +
                '<div class="col-sm-1">' +
                '<button type="button" class="btn btn-info " onclick="deleteItem(this)"> 删除项</button>' +
                '</div>' +
                '</div>';
            $('#itemsPanel').append(html);
        };

        deleteItem = function ($this) {
            $($this).parent().parent().remove();
        };


    </script>

</div>
<!-- /#wrapper -->


</body>

</html>
