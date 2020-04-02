<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    ul{
        margin: auto auto;
    }
    ul li{
        width: 200px;
        height: 100px;
        text-align: center;
        list-style: none;
        line-height: 100px;
        display: inline-block;
    }
    ul li:hover{
        cursor:hand;
    }
</style>
<body onkeydown="show()">
<ul onclick="In()">
    <%--已  实现--%>
    <li tabindex="1" onclick="ins('1')">漫画</li>
    <li tabindex="2" onclick="ins('2')">视频</li>
    <li tabindex="3" onclick="ins('3')">歌曲</li>
    <li tabindex="4" onclick="ins('4')">图片</li>
    <li tabindex="5" onclick="ins('0')">文本</li>
</ul>
</body>

</html>
<script>
    function show() {
        var s=document.getElementsByTagName("img")[0];
        console.log(window.event.keyCode);
    }
    function ins(str) {
        window.location.href="/open/"+str+".do"
    }
</script>

