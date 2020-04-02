<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: zhangjunjie
  Date: 2019/12/20
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .d0:hover{
        cursor: hand;
        background: red;
    }
</style>
<head>
    <title>Title</title>
</head>
<body>
<div id="zong">
    <c:forEach items="${requestScope.list}" var="l">
        <div  onclick="ins(this)" class="d0" style="font-size:12px; overflow: hidden; line-height:60px;text-align:center;display: inline-block; width: 100px;height: 60px; margin:10px;border: 1px solid red">${l.fileName}</div>
        <div hidden="hidden">${l.fileUrl}</div>
    </c:forEach>
</div>
<p>
    <a href="/open/2.do">返回</a>
</p>
</body>
</html>
<script>
    function ins(o){
        window.location.href="/cartoonRead.do?url="+o.nextElementSibling.innerText+"&page=1"
    }
</script>