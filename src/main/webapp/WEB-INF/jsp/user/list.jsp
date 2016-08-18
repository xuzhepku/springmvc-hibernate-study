<%--
  Created by IntelliJ IDEA.
  User: XuZhe
  Date: 2016/8/12
  Time: 16:14
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" type="text/css">
</head>
<body>
当前登录用户：${loginUser.username}
<h1>
    <c:forEach items="${users}" var="user">
        <a href="${user.value.username}">${user.value.username}</a>
        ----${user.value.nickname}
        ----${user.value.password}
        ----${user.value.email}
        <a href="${user.value.username}/update">Update user</a>
        <a href="${user.value.username}/delete">Delete user</a>
        <br/>
    </c:forEach>
</h1>
</body>
</html>
