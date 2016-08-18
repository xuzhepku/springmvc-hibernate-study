<%--
  Created by IntelliJ IDEA.
  User: XuZhe
  Date: 2016/8/13
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sf:form method="post" modelAttribute="user" enctype="multipart/form-data">
    username:<sf:input path="username"/><sf:errors path="username"/><br/>
    password:<sf:password path="password"/><sf:errors path="password"/><br/>
    nickname:<sf:input path="nickname"/><br/>
    email:<sf:input path="email"/><sf:errors path="email"/><br/>
    Attach:<input type="file" name="attachs"/><br/>
    Attach:<input type="file" name="attachs"/><br/>
    Attach:<input type="file" name="attachs"/><br/>
    <input type="submit" value="Add User">
</sf:form>
</body>
</html>
