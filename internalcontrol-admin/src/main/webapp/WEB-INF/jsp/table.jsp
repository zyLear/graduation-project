<%--
  Created by IntelliJ IDEA.
  User: xiezongyu
  Date: 2018/3/24
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>table</title>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <%--<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <%--<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">--%>

    <%--<link href="https://cdn.bootcss.com/bootstrap/3.0.0/css/bootstrap-theme.min.css" rel="stylesheet">--%>

    <%--<link href="https://cdn.bootcss.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">--%>

    <%@include file="common/common_head_resource.jsp" %>

    <%--<link href="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.min.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap-table/css/bootstrap-table.min.css"
          rel="stylesheet">

</head>


<body>


<div style="padding: 100px;">

    <div id="toolbar" class="btn-group">
        <button type="button" class="btn btn-default">
            <i class="glyphicon glyphicon-plus"></i>
        </button>
        <button type="button" class="btn btn-default">
            <i class="glyphicon glyphicon-heart"></i>
        </button>
        <button type="button" class="btn btn-default">
            <i class="glyphicon glyphicon-trash"></i>
        </button>
    </div>
    <table id="table"></table>
</div>

<%--<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>--%>
<%--<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->--%>
<%--&lt;%&ndash;<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>&ndash;%&gt;--%>


<%--&lt;%&ndash;<script src="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table-locale-all.min.js"></script>&ndash;%&gt;--%>
<%--<script src="https://cdn.bootcss.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>--%>

<%@include file="common/common_bottom_resource.jsp" %>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-table/js/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-table/js/bootstrap-table-locale-all.min.js"></script>

<script>

    $(document).ready(function () {

        //1.初始化Table
        var oTable = new TableInit();
        oTable.Init();

        //2.初始化Button的点击事件
//        var oButtonInit = new ButtonInit();
//        oButtonInit.Init();

    });


    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#table').bootstrapTable({
                url: '${pageContext.request.contextPath}/data',         //请求后台的URL（*）
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
                search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
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
                    field: 'name',
                    title: '部门名称'
                }, {
                    field: 'parentName',
                    title: '上级部门',
                    formatter: function (value, row, index) {
                        value
                    }
                }, {
                    field: 'level',
                    title: '部门级别'
                }, {
                    field: 'description',
                    title: '描述'
                }]
            });
        };

        //得到查询的参数
        oTableInit.queryParams = function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset  //页码
//                departmentname: $("#txt_search_departmentname").val(),
//                statu: $("#txt_search_statu").val()
            };
            return temp;
        };
        return oTableInit;
    };


    //    var ButtonInit = function () {
    //        var oInit = new Object();
    //        var postdata = {};
    //
    //        oInit.Init = function () {
    //            //初始化页面上面的按钮事件
    //        };
    //
    //        return oInit;
    //    };
</script>
</body>
</html>
