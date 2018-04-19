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

    <title>标书列表</title>

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
                <h1 class="page-header">招标列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row custom-content">


            <div class="panel panel-default">
                <%--<div class="panel-heading">Filter</div>--%>
                <div class="panel-body">
                    <div class="form-group form-horizontal">
                        <label class="control-label col-xs-2">标书编号</label>
                        <div class="col-xs-2">
                            <select id="biddingNumber" class="form-control selectpicker" data-header="Select type">
                                <option value="none">未选择</option>
                            </select>
                        </div>
                        <%--<label class="control-label col-xs-2" for="filter_resource_type">Excluded Thumb Status:</label>--%>
                        <%--<div class="col-xs-2">--%>
                        <%--<select id="filter_thumb_status" name="thumbStatus" class="form-control selectpicker" data-header="Select Status">--%>
                        <%--<option  data-subtext="" selected="selected" value="none">None</option>--%>
                        <%--<option  data-subtext="" value="2">Thumbs Up</option>--%>
                        <%--<option  data-subtext="" value="3">Thumbs Down</option>--%>
                        <%--</select>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-4" style="text-align:left;">--%>
                        <%--<button type="button" style="margin-left:50px" id="btn_query" class="btn btn-default">Query</button>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>


            <table id="table"></table>


        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


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
                    url: '${pageContext.request.contextPath}/bid/get-bid-list',         //请求后台的URL（*）
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
                    columns: [/*{
                     checkbox: true
                     },*/ {
                        field: 'projectNumber',
                        title: '项目编号'
                    }, {
                        field: 'projectName',
                        title: '项目名称'/*,
                         formatter: function (value, row, index) {
                         value
                         }*/
                    }, {
                        field: 'biddingNumber',
                        title: '招标编号'
                    }, {
                        field: 'biddingName',
                        title: '招标名称'
                    }, {
                        field: 'bidNumber',
                        title: '标书编号'
                    }, {
                        field: 'bidCompany',
                        title: '投标公司'
                    },{
                        field: 'id',
                        title: '标书内容',
                        formatter: function (value, row, index) {
                            return '点击查看';
                        }
                    }, {
                        field: 'bidStatus',
                        title: '标书状态',
                        formatter: function (value, row, index) {
                            return formatBiddingStatus(value);
                        }
                    }, {
                        field: 'bidPrices',
                        title: '投标价格'
                    }, {
                        field: 'filePath',
                        title: '投标文件',
                        formatter: function (value, row, index) {
                            return value;
                        }
                    }, {
                        field: 'biddingNumber',
                        title: '操作',
                        formatter: function (value, row, index) {
                            var html = "";
                            if (row.biddingStatus == BiddingStatusEnum.close) {
                                html += '<button onclick="changeBiddingStatus(\'' + value + '\',\'' + BiddingStatusEnum.open + '\')" ' +
                                    'type="button" class="btn btn-info custom-button-inline">启动招标</button>';
                            } else if (row.biddingStatus == BiddingStatusEnum.open) {
                                html += '<button onclick="changeBiddingStatus(\'' + value + '\',\'' + BiddingStatusEnum.close + '\')" ' +
                                    'type="button" class="btn btn-info custom-button-inline">停止招标</button>';
                            } else {
                                html += '<button disabled=disabled type="button" class="btn btn-info custom-button-inline">已完成</button>';
                            }
                            html += '<button onclick="showBid(\'' + value + '\')" type="button" class="btn btn-info">查看招标情况</button>';
                            return html;
                        }
                    }]
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


        showBid = function (value) {
            alert(value);
        };


        changeBiddingStatus = function (number, status) {
            var text;
            if (BiddingStatusEnum.open == status) {
                text = '确定启动投标吗？';
            } else {
                text = '确定停止投标吗？';
            }
            if (confirm(text)) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/bidding/change-bidding-status',
                    type: 'POST',
                    data: {
                        "biddingNumber": number,
                        "biddingStatus": status
                    },
                    success: function (data) {
                        alert(data.errorMessage);
                        window.location.reload();
                    },
                    error: function (data) {
                        alert('错误');
                    }

                });
            }
        }


    </script>

</div>
<!-- /#wrapper -->


</body>

</html>
