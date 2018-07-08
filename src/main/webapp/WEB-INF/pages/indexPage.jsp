<%--
  Created by IntelliJ IDEA.
  User: Teomant
  Date: 06.06.2018
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
    function MyFunction() {
        alert('test');
        var errorHandler = function () {
            alert('Can`t receive messages')
        };
        $.ajax({
            url: "${pageContext.request.contextPath}/test",
            type: 'GET',
            timeout: 1000*20,
            contentType: "application/json",
            data : {"number" : "10"},
            success: function(response) {
                alert('success');
            },
             error: errorHandler
        });
    }
</script>
<body>
<form:form method="post" action="/login" modelAttribute="loginForm">
    <spring:bind path="username">
        <label>Login</label>
            <form:input path="username" placeholder="User name"/>
            <form:errors path="username"/>
    </spring:bind>
    <br/>
    <spring:bind path="pass">
        <label>Password</label>
            <form:password path="pass" placeholder="Password"/>
            <form:errors path="pass"/>
    </spring:bind>
    <br/>
    <button type="submit" > Login</button>
</form:form>
<br/>
<br/>
<br/>
<a href="/registration">Registration</a>
<br/>
<button type="button"  onclick="javascript:MyFunction()">test</button>
</body>
</html>
