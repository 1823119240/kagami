<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhangjunjie
  Date: 2019/12/19
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="text-align: center" onkeyup="jump()">

<p>
    <h1><a href="/cartoonCata.do?url=${requestScope.paramsUrl}">返回</a></h1>
</p>
<a  href="/cartoonRead.do?url=${requestScope.paramUrl}&page=${requestScope.page+1}"><img  src="/temp/${requestScope.url}" /></a>
<p>
    <a href="/cartoonRead.do?url=${requestScope.paramUrl}&page=${requestScope.page-1}" style="margin-right:60px; ">上一页</a>
        <select onclick="jumpBySelect()">
            <c:forEach begin="0" end="${requestScope.MaxCount-1}" var="page">
            <option
                    <c:if test='${requestScope.page==page}'>selected='selected'</c:if>
                    value="/cartoonRead.do?url=${requestScope.paramUrl}&page=${page}" >
                第${page+1}页
                </option>
            </c:forEach>
        </select>
    <a href="/cartoonRead.do?url=${requestScope.paramUrl}&page=${requestScope.page+1}">下一页</a>
    <%--存储下一页和上一页的路径--%>
    <h1 hidden="hidden" id="up">/cartoonRead.do?url=${requestScope.paramUrl}&page=${requestScope.page-1}</h1>
    <h1 hidden="hidden" id="down">/cartoonRead.do?url=${requestScope.paramUrl}&page=${requestScope.page+1}</h1>
</p>
</body>
</html>
<script>
    //跳转页面
    function jump() {
        //如果按下的是a或者左方向键就跳转上一页
        if (window.event.key=='a' || window.event.key=='ArrowLeft'){
            window.location.href=document.getElementById("up").innerText
        }
        //如果按下的是d或者右方向键就跳转上一页
        if(window.event.key=='d' || window.event.key=='ArrowRight'){
            window.location.href=document.getElementById("down").innerText
        }
    }
    function yes() {
        console.log(",,,")
    }
    function jumpBySelect(){
        console.log(document.getElementsByTagName("select")[0].value)
    }

</script>
