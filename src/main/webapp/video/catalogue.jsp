<%--
  Created by IntelliJ IDEA.
  User: zhangjunjie
  Date: 2019/12/18
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@  page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    tr:hover{
        cursor: hand;
    }
</style>
<head>
    <title>Title</title>
</head>
<body>
<table border="100">
    <tr >
        <th>视频</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="ce" varStatus="st" >
        <tr align="center">
            <td onclick="catalogue(this)">${ce.fileName}</td>
            <td hidden="hidden">${ce.fileUrl}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<script>
    function catalogue(o) {
        window.location.href="/cartoonCata.do?url="+o.nextElementSibling.innerText
    }
</script>