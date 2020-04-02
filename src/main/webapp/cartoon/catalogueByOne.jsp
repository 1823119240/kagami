<%--
  Created by IntelliJ IDEA.
  User: zhangjunjie
  Date: 2019/12/19
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:forEach items="${requestScope.list}" var="l" varStatus="st">
        <c:choose>
            <c:when test="${l.fileUrl==requestScope.urlIndex}">
                <div  onclick="ins(this)" class="d0" style="font-size:12px; overflow: hidden; line-height:60px;text-align:center;display: inline-block; width: 100px;height: 60px; margin:10px;border: 1px solid black">${l.fileName}</div>
                <div hidden="hidden">${l.fileUrl}</div>
            </c:when>
            <c:otherwise>
                <div  onclick="ins(this)" class="d0" style="font-size:12px; overflow: hidden; line-height:60px;text-align:center;display: inline-block; width: 100px;height: 60px; margin:10px;border: 1px solid red">${l.fileName}</div>
                <div hidden="hidden">${l.fileUrl}</div>
            </c:otherwise>
        </c:choose>

    </c:forEach>
</div>
<p>
    <a href="/open/1.do">返回</a>
</p>
</body>
</html>
<script>
    function ins(o){
        o=o.nextElementSibling
        window.location.href="/loadCartoonRead.do?url="+o.innerText;
    }
</script>
