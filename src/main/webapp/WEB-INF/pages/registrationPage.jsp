<%--
  Created by IntelliJ IDEA.
  User: Teomant
  Date: 06.06.2018
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
Registration page:
<form:form method="post" action="/registration" modelAttribute="registrationForm">
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
</body>
</html>
