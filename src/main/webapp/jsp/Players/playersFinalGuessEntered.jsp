<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../taglib.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 11/3/25
  Time: 6:20 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Final Guess Entered</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" type="text/css" />
</head>
<body class="player-pages">
<%@include file="/jsp/header.jsp" %>

<main class="main-other">
    <h3>Your Final Guess</h3>

    <p>You guessed: <strong><c:out value="${guessedName}"/></strong></p>

    <c:choose>
        <c:when test="${isCorrect}">
            <p>Correct! The murderer was <strong><c:out value="${actualMurdererName}"/></strong>.</p>
        </c:when>
        <c:otherwise>
            <p>Not quite. The murderer was <strong><c:out value="${actualMurdererName}"/></strong>.</p>
        </c:otherwise>
    </c:choose>

</main>

<%@include file="/jsp/playerFooter.jsp" %>
</body>
</html>
