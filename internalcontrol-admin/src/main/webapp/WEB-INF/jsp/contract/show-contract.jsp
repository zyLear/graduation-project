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

    <title>合同详情</title>

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
                <h1 class="page-header">合同详情</h1>
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
                                    <select readonly id="bidNumber" name="bidNumber" class="form-control">
                                        <option value="none">${contract.bidNumber}</option>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">合同编号</label>
                                <div class="col-sm-9">
                                    <input readonly type="text" class="form-control" id="contractNumber"
                                           name="contractNumber"
                                           value="${contract.contractNumber}" placeholder="合同编号">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">合同名称</label>
                                <div class="col-sm-9">
                                    <input readonly type="text" class="form-control" id="contractName"
                                           name="contractName"
                                           value="${contract.contractName}" placeholder="合同名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">合同内容</label>
                                <div class="col-sm-9">
                            <textarea readonly cols="60" rows="14" class="form-control custom-textarea"
                                      id="contractContent"
                                      name="contractContent">${contract.contractContent}</textarea>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">总金额</label>
                                <div class="col-sm-9">
                                    <input readonly disabled type="text" class="form-control" id="contractMoney"
                                           value="${contract.contractMoney}" name="contractMoney"
                                           placeholder="总金额">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">合同文件</label>
                                <div class="col-sm-7 custom-link">
                                    <a href="${pageContext.request.contextPath}/downloader/download?filePath=${contract.filePath}">${contract.fileName}</a>
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
                            <c:forEach items="${contract.items}" var="item">
                                <div name="item" class="form-group">
                                    <label class="col-sm-2 control-label">合同项</label>
                                    <div class="col-sm-7">
                                    <textarea readonly cols="60" rows="3" class="form-control custom-textarea"
                                              placeholder="合同项描述"> ${item.itemContent}</textarea>
                                    </div>
                                    <div class="col-sm-2">
                                        <input readonly value="${item.itemMoney}" type="text" class="form-control"
                                               placeholder="金额">
                                        <c:choose>
                                            <c:when test="${empty item.finishDay}">
                                                <c:if test="${pageContext.session.getAttribute('authority')==1}">
                                                    <button type="button" class="btn btn-info custom-button"
                                                            onclick="sureFinish(${item.itemId})">
                                                        确定完成
                                                    </button>
                                                </c:if>
                                                <c:if test="${pageContext.session.getAttribute('authority')==0}">
                                                    <button disabled type="button" class="btn btn-info custom-button">
                                                        未完成
                                                    </button>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                完成时间：${item.finishDay}
                                            </c:otherwise>
                                        </c:choose>


                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <%--panel body --%>
                    </div>
                    <%--panel --%>

                    <div class="text-center">
                        <button onclick="back()" type="button" class="btn btn-info btn-lg"> 返回</button>
                    </div>

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
