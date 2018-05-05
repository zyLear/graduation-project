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

            <form id="form">
                <%-- class="form-horizontal"--%>
                <div class="col-lg-6 form-horizontal">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">基本内容</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标书编号</label>
                                <div class="col-sm-9">
                                    <select id="bidNumber" name="bidNumber" class="form-control">
                                        <option value="-1">未选择</option>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">合同编号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="contractNumber" name="contractNumber"
                                           placeholder="合同编号">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">合同名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="contractName" name="contractName"
                                           placeholder="合同名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">合同内容</label>
                                <div class="col-sm-9">
                            <textarea cols="60" rows="14" class="form-control custom-textarea"
                                      id="contractContent" name="contractContent"></textarea>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">总金额</label>
                                <div class="col-sm-9">
                                    <input disabled type="text" class="form-control" id="contractMoney"
                                           name="contractMoney"
                                           placeholder="总金额">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">申请表上传</label>
                                <div class="col-sm-9">
                                    <input id="file" name="file" data-show-upload="false" type="file" class="file">
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
                            <h3 class="panel-title">合同项面板</h3>
                        </div>
                        <div class="panel-body" id="itemsPanel">
                            <div name="item" class="form-group">
                                <label class="col-sm-1 control-label custom-label">合同项</label>
                                <div class="col-sm-8">
                                    <textarea cols="60" rows="3" class="form-control custom-textarea"
                                              placeholder="合同项描述"></textarea>
                                </div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="金额">
                                    <button type="button" class="btn btn-info custom-button" onclick="addItem()"> 增加新项
                                    </button>
                                </div>
                                <%--<div class="col-sm-1">--%>

                                <%--</div>--%>
                            </div>

                        </div>
                        <%--panel body --%>
                    </div>
                    <%--panel --%>
                </div>
                <%--<div class="col-lg-6 form-horizontal">--%>

            </form>
            <div style="text-align: center">
                <button id="save" type="button" class="btn btn-info btn-lg"> 保 存</button>
            </div>

        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


    <%@include file="../common/common_bottom_resource.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-fileinput/js/fileinput.min.js"></script>
    <script>

        $(document).ready(function () {

            $('#bidNumber').initBids('${pageContext.request.contextPath}/bid/get-bids?bidStatus=' + BidStatusEnum.winning);

            $('#save').click(function () {


                if ($('#bidNumber').val() == '') {
                    alert('标书编号不能为空');
                    return;
                }

                if ($('#contractNumber').val() == '') {
                    alert('合同编号不能为空');
                    return;
                }

                if ($('#contractName').val() == '') {
                    alert('合同名字不能为空');
                    return;
                }

                if ($('#contractContent').val() == '') {
                    alert('合同内容不能为空');
                    return;
                }

//
//                if ($('#projectBudget').val() == '') {
//                    alert('项目预算不能为空');
//                    return;
//                }
//
//                if (isNaN($('#projectBudget').val())) {
//                    alert('金额必须是数字');
//                    return;
//                }

                if ($('#file').val() == '') {
                    alert('申请表不能为空');
                    return;
                }


                var items = new Array();
                var needBounce = false;
                $('[name="item"]').each(function () {
                    var object = new Object();
                    object.itemMoney = $(this).find('input').val();
                    object.itemContent = $(this).find('textarea').val();

                    if (object.itemContent == '') {
                        needBounce = true;
                        alert('合同项描述不能为空');
                        return false;
                    }

                    if (object.itemMoney == '') {
                        needBounce = true;
                        alert('金额不能为空');
                        return false;
                    }

                    if (isNaN(object.itemMoney)) {
                        needBounce = true;
                        alert('金额必须是数字');
                        return false;
                    }

                    items.push(object);
                });
                if (needBounce) {
                    return;
                }
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
                            if (data.errorCode == 0) {
                                alert('添加成功');
                                window.location.href = '${pageContext.request.contextPath}/contract/contract-list';
                            } else {
                                alert(data.errorMessage);
                            }
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
                '<label class="col-sm-1 control-label custom-label">合同项</label>' +
                '<div class="col-sm-8">' +
                '<textarea cols="60" rows="3" class="form-control custom-textarea"></textarea>' +
                '</div>' +
                '<div class="col-sm-2">' +
                '<input type="text" class="form-control" placeholder="金额">' +
                '<button type="button" class="btn btn-info custom-button" onclick="deleteItem(this)"> 删除项</button>' +
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
