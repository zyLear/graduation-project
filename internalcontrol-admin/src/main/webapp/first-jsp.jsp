<%--
  Created by IntelliJ IDEA.
  User: 表哥小珠
  Date: 2018/3/24
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
this is first jsp file 2

<a onclick="click1()"> view pdf </a>

<%@include file="WEB-INF/jsp/common/common_bottom_resource.jsp" %>

<script>


  //  $(document).read(click());
    function click1() {
        var a="resources/plugins/pdfjs/generic/web/viewer.html?file=" + encodeURIComponent("/downloadPdf?fileName=C:/Develop/111.pdf");
        window.location.href=a;
    }
</script>
</body>
</html>
