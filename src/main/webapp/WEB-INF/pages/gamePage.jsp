<%--
  Created by IntelliJ IDEA.
  User: Teomant
  Date: 30.05.2018
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="user" type="org.teomant.entity.UserEntity"--%>
<%--@elvariable id="game" type="org.teomant.entity.GameEntity"--%>
<%--@elvariable id="attempt" type="org.teomant.entity.AttemptEntity"--%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  Let`s play!
  <br/>
    <c:choose>
      <c:when test="${user.games.get(user.games.size()-1).finished}">
        <form action="/newGame" method="post">
          <c:forEach items="${user.games.get(user.games.size()-1).attempts}" var="attempt">
            ${attempt.value} bulls: ${attempt.bull} cows: ${attempt.cow}
            </br>
          </c:forEach>
          Hey! You`ve got it! Correct anser:${user.games.get(user.games.size()-1).task}<br/>
          <button type="submit">NewGame</button>
        </form>
        <br />
      </c:when>
      <c:otherwise>
        <c:forEach items="${user.games.get(user.games.size()-1).attempts}" var="attempt">
          ${attempt.value} bulls: ${attempt.bull} cows: ${attempt.cow}
          </br>
        </c:forEach>
        <form:form method="post" action="/game" modelAttribute="attemptForm">
          <spring:bind path="attempt">
            <label>So?</label>
            <form:input path="attempt" placeholder="You answer"/>
            <form:errors path="attempt"/>
          </spring:bind>
          <br/>
          <button type="submit" > Submit</button>
        </form:form>
        <br />
      </c:otherwise>
    </c:choose>
    <br/>
    <br/>
    <a href="/logout">logout</a>
  </body>
</html>
