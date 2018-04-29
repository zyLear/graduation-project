<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>预算列表</title>

    <%@include file="../common/common_head_resource.jsp" %>
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap-table/css/bootstrap-table.min.css"
          rel="stylesheet">

</head>

<body>

<div id="wrapper">

    <%@include file="../common/common_navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">预算列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row custom-content">

            <table id="table"></table>


        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


    <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="modalLabel">预算内容</h4>
                </div>
                <div class="modal-body">

                    <textarea readonly cols="60" rows="20" class="form-control custom-textarea"
                              id="content" name="projectContent"></textarea>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <%--<button type="button" id="sure-assign" class="btn btn-primary">确定</button>--%>
                </div>
            </div>
        </div>
    </div>

    <%@include file="../common/common_bottom_resource.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-table/js/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-table/js/bootstrap-table-locale-all.min.js"></script>
    <script>
        $(document).ready(function () {


//            $('#add-budget').click(function () {
//                $('#add-budget-modal').modal('show');
//            });

            var oTable = new TableInit();
            oTable.Init();

        });


        var TableInit = function () {
            var oTableInit = new Object();
            //初始化Table
            oTableInit.Init = function () {
                $('#table').bootstrapTable({
                    url: '${pageContext.request.contextPath}/budget/get-budget-list',         //请求后台的URL（*）
                    method: 'get',                      //请求方式（*）
                    toolbar: '#toolbar',                //工具按钮用哪个容器
                    striped: true,                      //是否显示行间隔色
                    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                    pagination: true,                   //是否显示分页（*）
                    sortable: true,                     //是否启用排序
                    sortOrder: "asc",                   //排序方式
                    queryParams: oTableInit.queryParams,//传递参数（*）
                    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                    pageNumber: 1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,                       //每页的记录行数（*）
                    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                    search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                    strictSearch: false,
                    locale: 'zh-CN',
                    showColumns: true,                  //是否显示所有的列
                    showRefresh: true,                  //是否显示刷新按钮
                    minimumCountColumns: 2,             //最少允许的列数
                    clickToSelect: true,                //是否启用点击选中行
                    //   height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                    uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                    showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
                    cardView: false,                    //是否显示详细视图
                    detailView: false,                   //是否显示父子表
                    columns: [{
                        field: 'projectNumber',
                        title: '项目编号'
                    }, {
                        field: 'projectName',
                        title: '项目名称'
                    }, {
                        field: 'budgetAspect',
                        title: '预算模块'
                    }, {
                        field: 'id',
                        title: '预算内容',
                        formatter: function (value, row, index) {
                            return '<button onclick="showContent(' + value + ')" type="button" class="btn btn-default">点击查看</button>';
                        }
                    }, {
                        field: 'budgetMoney',
                        title: '金额'
                    }, {
                        field: 'filePath',
                        title: '申请表',
                        formatter: function (value, row, index) {
                            return '点击查看';
                        }
                    }, {
                        field: 'createTime',
                        title: '创建时间',
                        formatter: function (value, row, index) {
                            return new Date(value).format('yyyy年MM月dd日 hh:mm:ss');
                        }
                    }/*, {
                     field: 'projectNumber',
                     title: '操作'/!*,
                     formatter: function (value, row, index) {
                     var html = "";
                     if(row.projectStatus==ProjectStatusEnum.budgeting){
                     html += '<button onclick="addBudget(\'' + value + '\')" type="button" class="btn btn-info">添加预算</button>';
                     }else {
                     html += '<button onclick="showBudget(\'' + value + '\')" type="button" class="btn btn-success">查看预算</button>';
                     }

                     if (row.projectStatus == ProjectStatusEnum.in_approval) {
                     html += '<button onclick="approval(\'' + value + '\')" ' +
                     'type="button" class="btn btn-info custom-button-inline">审批项目</button>';
                     }/!* else if (row.biddingStatus == BiddingStatusEnum.open) {
                     html += '<button onclick="changeBiddingStatus(\'' + value + '\',\'' + BiddingStatusEnum.close + '\')" ' +
                     'type="button" class="btn btn-info custom-button-inline">停止招标</button>';
                     }*!/ else {
                     html += '<button disabled=disabled type="button" class="btn btn-info custom-button-inline">已经审批</button>';
                     }
                     return html;
                     }*!/
                     }*/]
                });
            };

            //得到查询的参数
            oTableInit.queryParams = function (params) {
                var temp = {
                    limit: params.limit,   //页面大小
                    offset: params.offset  //页码
//                departmentname: $("#txt_search_departmentname").val(),
//                statu: $("#txt_search_statu").val()
                };
                return temp;
            };
            return oTableInit;
        };

        showContent = function (value) {
            $.ajax({
                url: '${pageContext.request.contextPath}/budget/get-budget-content',
                type: 'POST',
                data: {
                    "id": value
                },
                success: function (data) {
                    if (data.errorCode == 0) {
                        $('#content').text(data.data[0].budgetContent);
                        $('#myModal').modal('show');
                    } else {
                        alert(data.errorMessage);
                    }
                },
                error: function (data) {
                    alert('网络错误');
                }
            });
        };


        addBudget = function (value) {
            <%--window.location.href = '${pageContext.request.contextPath}/budget/budget-application?projectNumber=' + value;--%>
        };

        showBudget = function (value) {
            <%--window.location.href = '${pageContext.request.contextPath}/budget/show-budget?projectNumber=' + value;--%>
        };


        approval = function (value) {
            <%--window.location.href = '${pageContext.request.contextPath}/project/project-approval?projectNumber=' + value;--%>
        }

    </script>

</div>
<!-- /#wrapper -->


</body>

</html>
