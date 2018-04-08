<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

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
                <h1 class="page-header">新建标书</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row custom-content">



            <%--</div>--%>
            <%--&lt;%&ndash;pannel-body&ndash;%&gt;--%>
            <%--</div>--%>
            <%--&lt;%&ndash;pannel&ndash;%&gt;--%>
            <%--</div>--%>
            <%--&lt;%&ndash; col-lg-6 &ndash;%&gt;--%>

            <div class="col-lg-offset-1 col-lg-10">
                <%--<div class="panel panel-info">--%>
                <%--<div class="panel-heading">--%>
                <%--<h3 class="panel-title">预算详情</h3>--%>
                <%--</div>--%>
                <%--<div class="panel-body">--%>
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div<%--action=""${pageContext.request.contextPath}/page/wechat/articlecity-list""--%>>
                                <div class="form-group">
                                    <label class="control-label col-lg-1 text-right"
                                           style="margin-top: 8px;">项目名称:</label>
                                    <div class="col-lg-2">
                                        <select id="type" name="type" class="form-control">
                                            <option value="all">所有</option>
                                            <option value="internal_group">内部群</option>
                                            <option value="outside_group">外部群</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-2">
                                        <button type="button" id="show-project-detail" class="btn btn-primary">点击查看项目详情
                                        </button>
                                    </div>
                                </div>
                                <%--<div class="form-group ">--%>
                                    <%--<label class="control-label col-lg-2 text-right"--%>
                                           <%--style="margin-top: 8px;">是否为教育群:</label>--%>
                                    <%--<div class="col-lg-2">--%>

                                        <%--<select id="roomCatalog" name="roomCatalog" class="form-control">--%>
                                            <%--<option value="all">所有</option>--%>
                                            <%--<option value="edu_room">是</option>--%>
                                            <%--<option value="not_a_edu_room">否</option>--%>
                                        <%--</select>--%>
                                    <%--</div>--%>
                                <%--</div>--%>


                            </div>
                        </div>
                    </div>
                </div>

                <table id="table"></table>
                <%--<div style="text-align: center">--%>
                    <%--<button id="add-budget" type="button" class="btn btn-info btn-lg"> 新增标书</button>--%>
                <%--</div>--%>




                <%--</div>--%>
                <%--&lt;%&ndash;pannel-body&ndash;%&gt;--%>
                <%--</div>--%>
                <%--pannel--%>
            </div>
            <%-- col-lg-6 --%>


        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


    <div class="modal fade bs-example-modal-lg" id="add-budget-modal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="modalLabel">添加预算</h4>
                </div>
                <div class="modal-body">

                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">预算模块</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="budget-aspect" placeholder="预算模块">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label">预算描述</label>
                            <div class="col-sm-7">
                            <textarea cols="30" rows="10" class="form-control custom-textarea"
                                      id="budget-content"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">金额</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="budget-money"
                                       placeholder="金额">
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>

                        <button type="button" id="sure-assign" class="btn btn-primary">确定</button>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="../common/common_bottom_resource.jsp" %>
        <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-table/js/bootstrap-table.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-table/js/bootstrap-table-locale-all.min.js"></script>
        <script>
            $(document).ready(function () {


                $('#add-budget').click(function () {
                    $('#add-budget-modal').modal('show');
                });

                var oTable = new TableInit();
                oTable.Init();

            });


            var TableInit = function () {
                var oTableInit = new Object();
                //初始化Table
                oTableInit.Init = function () {
                    $('#table').bootstrapTable({
                        url: '${pageContext.request.contextPath}/budget/budget-detail',         //请求后台的URL（*）
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
                            field: 'name',
                            title: 'name'
                        }/*, {
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
        </script>

    </div>
    <!-- /#wrapper -->


</body>

</html>
